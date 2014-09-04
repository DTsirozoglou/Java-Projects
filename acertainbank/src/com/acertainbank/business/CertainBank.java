package com.acertainbank.business;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import com.acertainbank.server.BankHTTPServer;
import com.acertainbank.utils.BankConstants;
import com.acertainbank.utils.InitialConfiguration;

public class CertainBank {
	private final String filePath = "A:\\eclipse-standard-kepler-R-win32-x86_64\\workspace\\acertainbank\\src\\server.properties";
	private List<String> serverAddresses;
	private Set<Account> accounts;
	private Set<Branch> branches;
	private HashMap<Branch, Set<Account>> accountManager = new HashMap<Branch, Set<Account>>();
	private HashMap<String, Set<Integer>> accountManagerPartitions = new HashMap<String, Set<Integer>>();
	private InitialConfiguration ic = new InitialConfiguration();
	private HashMap<Branch, Set<Account>> tmpAccountManager = new HashMap<Branch, Set<Account>>();
	public HashMap<Branch, Set<Account>> getTmpAccountManager() {
		return tmpAccountManager;
	}

	public CertainBank(boolean synched) throws IOException {
		initializeAccounts();
		initializeBranches();
		initializeServers();
		for (Branch br : branches)
		{
			accountManager.put(br, br.getAccount());
			tmpAccountManager.put(br, br.getAccount());
		}
		
		Partition[] partitions;
		if(!synched)
			partitions= new Partition[serverAddresses.size()];
		else
			partitions= new SynchronizedPartition[serverAddresses.size()];
		int i=0;
		for(String address : serverAddresses)
		{
			if(!synched){
			Partition p = new Partition(address,this);
			partitions[i]=p;
			}
			else{
			SynchronizedPartition ps = new SynchronizedPartition(address,this);
			partitions[i]=ps;
			}
			i++;
		}
		if (accountManager.size() > 0) {
			Iterator<Branch> it = accountManager.keySet().iterator();
			while (it.hasNext() && (accountManager.size() > 0)) {
				for (i=0;i<partitions.length;i++) {
					if (accountManager.size() == 0)
						break;
					Branch br = (Branch) it.next();
					Set<Account> acc = (Set<Account>) accountManager.get(br);
					partitions[i].addRecord(br, acc);
					it.remove();
				}
			}
		}
		
		Thread[] threads = new Thread[ic.getNumOfServers()];
		for (i = 0; i < partitions.length; i++) {
			Set<Integer> brachesId =new HashSet<Integer>();
			Set<Branch> braches =partitions[i].getPartition().keySet();
			for(Branch br:braches)
				brachesId.add(br.getId());
			accountManagerPartitions.put(serverAddresses.get(i),brachesId);
			
			BankHTTPServer server; 
				server= new BankHTTPServer(serverAddresses.get(i)
					.substring(serverAddresses.get(i).lastIndexOf(":") + 1),
					partitions[i]);
			Thread t = new Thread(server);
			threads[i] = t;
			t.start();
		}
		for (i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public HashMap<String, Set<Integer>> getAccountManagerPartitions() {
		return accountManagerPartitions;
	}
	
	private void initializeAccounts() {
		accounts = new HashSet<Account>();
		Random r = new Random(System.currentTimeMillis());
		for (int i = 0; i < ic.getNumOfAccounts(); i++)
			accounts.add(new Account(r.nextDouble() * 100, i));
	}

	private void initializeBranches() {
		branches = new HashSet<Branch>();
		Random r = new Random(System.currentTimeMillis());
		List<Account> lAccounts = new ArrayList<Account>(accounts);
		int div = ic.getNumOfAccounts() / ic.getNumOfBranches();
		for (int branch = 0; branch < ic.getNumOfBranches(); branch++) {
			Set<Account> tmpAccounts = new HashSet<Account>();
			for (int acc = 0; acc < div; acc++) {
				int index = r.nextInt(lAccounts.size());
				tmpAccounts.add(lAccounts.get(index));
				lAccounts.remove(index);
			}
			branches.add(new Branch(tmpAccounts, branch));
		}
		if (lAccounts.size() > 0) {
			while (lAccounts.size() > 0) {
				for (Branch br : branches) {
					if (lAccounts.size() == 0)
						break;
					br.getAccount().add(lAccounts.get(lAccounts.size() - 1));
					lAccounts.remove(lAccounts.size() - 1);
				}
			}
		}
	}
	
	private void initializeServers() throws IOException {
		Properties props = new Properties();
		serverAddresses = new ArrayList<String>();
		props.load(new FileInputStream(filePath));
		String serverAddresses = props.getProperty(BankConstants.KEY_SERVER);
		for (String server : serverAddresses.split(BankConstants.SPLIT_REGEX)) {
			if (!server.toLowerCase().startsWith("http://")) {
				server = new String("http://" + server);
			}
			this.serverAddresses.add(server);
		}
	}

	public Set<Branch> getBranches() {
		return branches;
	}
}
