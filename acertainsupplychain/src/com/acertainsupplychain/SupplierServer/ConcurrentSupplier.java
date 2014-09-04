package com.acertainsupplychain.SupplierServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.acertainsupplychain.ItemQuantity;
import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.exceptions.InvalidItemException;
import com.acertainsupplychain.exceptions.LockException;
import com.acertainsupplychain.exceptions.OrderProcessingException;
import com.acertainsupplychain.interfaces.ItemSupplier;
import com.acertainsupplychain.interfaces.Supplier;

public class ConcurrentSupplier implements ItemSupplier,Supplier {
	private int supplierID;
	private String serverOfSupplier;
	private List<SupplierItem> items;
	
	public ConcurrentSupplier(int supplierID,String serverOfSupplier,List<SupplierItem> items){
		
		this.supplierID = supplierID;
		this.serverOfSupplier = serverOfSupplier;
		this.items=items;
//		System.out.println("+++++++++++++++++++ SUPPLIER++++++++++++++++++++++++++");
//		System.out.println("The supplier : "+ this.supplierID);
//		System.out.println("Adress : "+ this.serverOfSupplier);
//		for (SupplierItem item:items){
//			System.out.println(item.getItemId());
//		}
		
	}
	
	private void writeToLog(String from,int stepID, String status) {
		
		PrintWriter out=null;
		try {
			out = new PrintWriter(new FileOutputStream(new File("Supplier_"+serverOfSupplier+".txt"),true));
			out.println(" Order ID : "+stepID+" Ordered From : "+ from +" is in Status : "+status);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			out.close();
		}		
	}
	
	private void writeToLog(String from,int stepID,int itemID, String status, int quantity) {
		
		PrintWriter out=null;
		try {
			out = new PrintWriter(new FileOutputStream(new File("Supplier_"+serverOfSupplier+".txt"),true));
			out.println("The Item with ID : "+itemID+" Of Order : "+stepID+" Ordered From : "+ from +" has been : "+status +" Quantity ordered : "+quantity);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			out.close();
		}		
	}
	
private void writeToLog(String from,int stepID,int itemID, String status) {
		
		PrintWriter out=null;
		try {
			out = new PrintWriter(new FileOutputStream(new File("Supplier_"+serverOfSupplier+".txt"),true));
			out.println("The Item with ID : "+itemID+" Of Order : "+stepID+" Ordered From : "+ from +" has been : "+status);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			out.close();
		}		
	}

	@Override
	public void executeStep(OrderStep step) throws OrderProcessingException, LockException {
		
		
		//validate
		List<SupplierItem> toLock= new ArrayList<SupplierItem>();
		for (ItemQuantity item:step.getItems()){
			boolean found = false;
			for (SupplierItem supplyItem:items){
				if (supplyItem.getItemId()==item.getItemId()){
						toLock.add(supplyItem);
						found=true;
						break;
				}
			}
			if (!found)
				throw new InvalidItemException();
		}
		
		int locked = 0;
		for (SupplierItem supplyItem:toLock){
			if (!supplyItem.lockForWriting()){
				if (!(locked ==0)){
					for (int i=0;i<locked;i++)
						toLock.get(i).unlockWriter();
				}					
				throw new LockException("Item in use",supplyItem.getItemId());
			}
			else locked++;
		}
			
		writeToLog(step.getFrom(), step.getStepID(), "STARTED");
		for (ItemQuantity item:step.getItems()){
			for (SupplierItem supplyItem:toLock){
				if (item.getItemId()==supplyItem.getItemId()){
					writeToLog(step.getFrom(), step.getStepID(),item.getItemId() ,"ONGOING");
					supplyItem.setQuantity(supplyItem.getQuantity()+item.getQuantity());
					supplyItem.unlockWriter();
					writeToLog(step.getFrom(), step.getStepID(),item.getItemId() ,"ORDERED",item.getQuantity());
					break;
				}
			}
		}
		writeToLog(step.getFrom(), step.getStepID(), "END");
	}

	@Override
	public List<ItemQuantity> getOrdersPerItem(Set<Integer> itemIds)
			throws InvalidItemException, LockException {
		
		List<ItemQuantity> result= new ArrayList<ItemQuantity>();
		List<SupplierItem> toLock= new ArrayList<SupplierItem>();
		
		for (Integer itemID:itemIds){
			boolean found = false;
			
			for (SupplierItem supplyItem:items){
				if (supplyItem.getItemId()==itemID){
						toLock.add(supplyItem);
						found=true;
						break;
				}
			}
			if (!found)
				throw new InvalidItemException();
		}
		
		int locked = 0;
		for (SupplierItem supplyItem:toLock){
			if (!supplyItem.lockForReading()){
				if (!(locked ==0)){
					for (int i=0;i<locked;i++)
						toLock.get(i).unlockReader();
				}
				throw new LockException("Item in use",supplyItem.getItemId());
			}
			else locked++;
		}
		
		for (SupplierItem supplyItem:toLock){
			result.add(new ItemQuantity(supplyItem.getItemId(), supplyItem.getQuantity()));
			supplyItem.unlockReader();			
		}
		
		return result;
	}

	/**
	 * @return the serverOfSupplier
	 */
	public String getServerOfSupplier() {
		return serverOfSupplier;
	}

	/**
	 * @param serverOfSupplier the serverOfSupplier to set
	 */
	public void setServerOfSupplier(String serverOfSupplier) {
		this.serverOfSupplier = serverOfSupplier;
	}

	/**
	 * @return the supplierID
	 */
	public int getSupplierID() {
		return supplierID;
	}
	
	public List<SupplierItem> getItems() {
		return items;
	}

	public void setItems(List<SupplierItem> items) {
		this.items = items;
	}
}
