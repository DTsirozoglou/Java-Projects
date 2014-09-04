package com.acertainsupplychain.interfaces;

import java.util.List;
import java.util.Set;

import com.acertainsupplychain.ItemQuantity;
import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.SupplierServer.SupplierItem;
import com.acertainsupplychain.exceptions.InvalidItemException;
import com.acertainsupplychain.exceptions.LockException;
import com.acertainsupplychain.exceptions.OrderProcessingException;

/**
 */
public interface Supplier {

	public String getServerOfSupplier();

	public void setServerOfSupplier(String serverOfSupplier);

	public int getSupplierID() ;
	
	public List<SupplierItem> getItems() ;

	public void setItems(List<SupplierItem> items) ;
	public void executeStep(OrderStep step) throws OrderProcessingException;
	public List<ItemQuantity> getOrdersPerItem(Set<Integer> itemIds)
			throws InvalidItemException,LockException;

}
