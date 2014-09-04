package acertainsupplychain.WorkLoad;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import acertainsupplychain.Configuration.Initializer;

import com.acertainsupplychain.ItemQuantity;
import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.SupplierServer.SupplierItem;

public class Generator {
	
	Random r =new Random(System.currentTimeMillis());
	private final int maxNumOfSteps =3;
	private final int maxNumOfOrderedItems = 5;
	private final int maxNumOfEnquiryItems = 20;
	
	public Generator() {

	}
	
	public List <OrderStep> generateSteps(Initializer initial){
		List <OrderStep> flow = new ArrayList<OrderStep>();
		int numOfSteps = r.nextInt(maxNumOfSteps);
		if (numOfSteps==0)
			numOfSteps++;
		for (int i=0;i<numOfSteps;i++)
			flow.add(generateStep(initial));
		return flow;
	}
	
	public int  generateflowID(List<Integer> createdflows){
		
//		if (r.nextFloat()*100 <= 100f) {
		int fl = r.nextInt(createdflows.size());
		return createdflows.get(fl);
//		}
//		else return -1;		
	}
	
	public OrderStep generateStep(Initializer initial){
		
		OrderStep step=null;
		List <ItemQuantity> items = initial.getItems();
		int itemsOfStep = r.nextInt(maxNumOfOrderedItems);
		if (itemsOfStep==0)
			itemsOfStep++;
		List <ItemQuantity> finalorder=new ArrayList<ItemQuantity>();
//		if (r.nextFloat()*100 <= 100f) {
			int j=0;
			while(j<itemsOfStep)
			{
				int itemToAdd =r.nextInt(200);
				ItemQuantity item=items.get(itemToAdd); //new ItemQuantity(items.get(itemToAdd).getItemId(),items.get(itemToAdd).getQuantity() );
				if(finalorder.contains(item)){
					continue;
				}
				else
					finalorder.add(item);
				j ++;
			}			
			step = new OrderStep(initial.getSuppliers().get(0).getSupplierID(), finalorder);
//		}
//		else{
//			for(int j=0; j <itemsOfStep;j++){
//				int itemToAdd =r.nextInt(items.size());
//				finalorder.add(new ItemQuantity(items.get(itemToAdd).getItemId(),items.get(itemToAdd).getQuantity() ));
//			}			
//			step = new OrderStep(initial.getSuppliers().get(0).getSupplierID(), finalorder);
//		}
		return step;
	}
	
	public Set<Integer> generateOrderToGet(Initializer initial){
		Set<Integer> getOrders = new HashSet<Integer>();
		List<SupplierItem> supplierItems =initial.getSuppliers().get(0).getItems();
		int numberOfItemToGet = maxNumOfEnquiryItems;// r.nextInt(maxNumOfEnquiryItems);
		if (numberOfItemToGet==0)
			numberOfItemToGet++;
		for (int i=0; i<numberOfItemToGet;i++){
			int itemToAdd =r.nextInt(supplierItems.size());
			if(getOrders.contains(itemToAdd)){
				continue;
			}
			else
				getOrders.add(supplierItems.get(itemToAdd).getItemId());
		}
		if (r.nextFloat()*100 < 90f) 
		return getOrders;
		else {
			getOrders.add(-1);
			return getOrders;
		}
	}
}
