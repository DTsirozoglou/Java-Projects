package com.acertainbank.client.workloads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.acertainbank.business.Account;
import com.acertainbank.business.Branch;

public class Generator {
	Random r =new Random(System.currentTimeMillis());
	public Generator() {

	}
	
	public int getRandomBranch (int numOfBranches){
		if (r.nextFloat()*100 < 95f) {
			return r.nextInt(numOfBranches);
		} else
        	return -1;
	}

	public int getRandomAccount (Branch br){
		if (r.nextFloat()*100 < 95f) {
			List<Account> validAccount = new ArrayList<Account>(br.getAccount());
			return validAccount.get(r.nextInt(validAccount.size())).getId();
        } else
        	return -1;
		
	}
	
	public double getRandomAmount (){
		if (r.nextFloat()*100 < 95f) {
			return r.nextDouble() * 100;
        } else
        	return -1;
	}

}
