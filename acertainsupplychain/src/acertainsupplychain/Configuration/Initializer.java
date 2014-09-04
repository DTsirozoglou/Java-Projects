package acertainsupplychain.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import com.acertainsupplychain.ItemQuantity;
import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.SupplierServer.ConcurrentSupplier;
import com.acertainsupplychain.SupplierServer.SupplierItem;
import com.acertainsupplychain.SupplierServer.SynchronizedSupplier;
import com.acertainsupplychain.interfaces.Supplier;
import com.acertainsupplychain.utils.ServerConstants;

public class Initializer {
	
	private InitialConfiguration ic = new InitialConfiguration();
	private List <ItemQuantity> items= new ArrayList<ItemQuantity>();
	private List <OrderStep> steps= new ArrayList<OrderStep>();

	private List <Integer> brokers = new ArrayList<Integer>();
	private List <Supplier> suppliers = new ArrayList<Supplier>();
	private final String filePath = "A:\\eclipse\\workspace\\acertainsupplychain\\src\\SupplierServer.properties";
	private final String filePath2 = "A:\\eclipse\\workspace\\acertainsupplychain\\src\\BrokerServer.properties";
	
	public Initializer(boolean one,boolean workload,boolean sychSupplier) throws IOException {

		initializeItems();
		initializeInteBrokers();
		
		if (sychSupplier){
			if (!one)
				initializeSuppliers(true);
			else initializeSupplier(true);
		}
		else{
			if (!one)
				initializeSuppliers(false);
			else initializeSupplier(false);
		}

		if (!workload)
			initializeOrderSteps();
		
	}
	
	private void initializeSupplier(boolean sych) throws IOException{
		Properties props = new Properties();
		props.load(new FileInputStream(filePath));
		String serverAddresses = props.getProperty(ServerConstants.KEY_SERVER);
		int supplierID =0;
		Set<SupplierItem> supplierItems = new HashSet<SupplierItem>();
		for (int i =0; i<300;i++){
				supplierItems.add(new SupplierItem(i));
		}
			
		List<SupplierItem> finalItems = new ArrayList<SupplierItem>(supplierItems);
		String server = new String("http://" + serverAddresses);
		if (!sych)
			suppliers.add(new ConcurrentSupplier
					(supplierID, server.substring(server.lastIndexOf(":")+1), finalItems));
		else suppliers.add(new SynchronizedSupplier
				(supplierID, server.substring(server.lastIndexOf(":")+1), finalItems));
	}
	
	private void initializeSuppliers(boolean sych) throws IOException{
		Properties props = new Properties();
		props.load(new FileInputStream(filePath));
		String serverAddresses = props.getProperty(ServerConstants.KEY_SERVER);
		int supplierID =0;
		for (String server : serverAddresses.split(ServerConstants.SPLIT_REGEX)) {
			
			Set<SupplierItem> supplierItems = new HashSet<SupplierItem>();
			for (int i =0; i<items.size()-300;i++){
				supplierItems.add(new SupplierItem(i));
			}
			
			List<SupplierItem> finalItems = new ArrayList<SupplierItem>(supplierItems);
			server = new String("http://" + server);
			if (!sych)
				suppliers.add(new ConcurrentSupplier
						(supplierID, server.substring(server.lastIndexOf(":")+1), finalItems));
			else 
				suppliers.add(new SynchronizedSupplier
						(supplierID, server.substring(server.lastIndexOf(":")+1), finalItems));
			supplierID++;
		}
	}
	
	private void initializeInteBrokers() throws IOException{
		Properties props = new Properties();
		props.load(new FileInputStream(filePath2));
		String serverAddresses = props.getProperty(ServerConstants.KEY_BROKER_SERVER);
		for (String server : serverAddresses.split(ServerConstants.SPLIT_REGEX)) {
			
			server = new String("http://" + server);
			brokers.add(Integer.valueOf(server.substring(server.lastIndexOf(":")+1)));
		}
	}
	
	private void initializeItems(){
		for (int i =0; i<ic.getNumOfItems();i++){			
			items.add(new ItemQuantity(i, i));			
		}
	}
	
	private void initializeOrderSteps(){
		
		Random r = new Random(System.currentTimeMillis());
		int itemsOfStep = ic.getNumOfItems() /ic.getNumOfSteps();
		for (int i=0;i<ic.getNumOfSteps();i++){
			int supplierId = r.nextInt(ic.getNumOfServers());
			List <ItemQuantity> finalorder=new ArrayList<ItemQuantity>();
			Random r1 = new Random(System.currentTimeMillis());
			for(int j=0; j <itemsOfStep;j++){
				int itemToAdd =r1.nextInt(items.size()-300);
				ItemQuantity item = items.get(itemToAdd);
				if(finalorder.contains(item)){
					continue;
				}
				else
					finalorder.add(item);
			}
			OrderStep step;
			if ((i==36)||(i==47))
				step = new OrderStep(supplierId+20, finalorder);
			else step = new OrderStep(supplierId, finalorder); 
//			step.display();
//			System.out.println("++++++++++++++++ New Step ++++++++++++++++++++++++++++++");
			steps.add(step);
			}			
	}
		
	/**
	 * @return the suppliers
	 */
	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	/**
	 * @return the steps
	 */
	public List<OrderStep> getSteps() {
		return steps;
	}
	
	/**
	 * @return the brokers
	 */
	public List<Integer> getBrokers() {
		return brokers;
	}

	public List<ItemQuantity> getItems() {
		return items;
	}

	public void setItems(List<ItemQuantity> items) {
		this.items = items;
	}

	public InitialConfiguration getIc() {
		return ic;
	}
}
