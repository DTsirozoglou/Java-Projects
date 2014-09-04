package acertainsupplychain.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.acertainsupplychain.ItemQuantity;
import com.acertainsupplychain.OrderStep;
import com.acertainsupplychain.Globalclient.GlobalClientHTTPProxy;
import com.acertainsupplychain.exceptions.InvalidItemException;
import com.acertainsupplychain.exceptions.InvalidWorkflowException;
import com.acertainsupplychain.exceptions.OrderProcessingException;
import com.acertainsupplychain.interfaces.ItemSupplier;
import com.acertainsupplychain.interfaces.OrderManager;
import com.acertainsupplychain.interfaces.OrderManager.StepStatus;
import com.acertainsupplychain.interfaces.Supplier;

import acertainsupplychain.BrokerServer.IntegrationBrokerHTTPServer;
import acertainsupplychain.Configuration.Initializer;
import acertainsupplychain.Configuration.ServerInitializer;
import acertainsupplychain.LocalClient.LocalClientHTTPProxy;

public class Testing {

	private static Initializer initializationManager;
	private static OrderManager globalClient;
	private static ItemSupplier localClient;
	private static Supplier localSupplier;
	private static ServerInitializer serversUP;
	private static List<IntegrationBrokerHTTPServer> brokerServers;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		initializationManager = new Initializer(true, false, false);
		localSupplier = initializationManager.getSuppliers().get(0);
		localClient = new LocalClientHTTPProxy(localSupplier);
		serversUP = new ServerInitializer(initializationManager);
		brokerServers = serversUP.getbrokerServers();
		serversUP.joinThreads();
		globalClient = new GlobalClientHTTPProxy();
	}

	@Test
	public void asynchronicityTest1() {

		List<OrderStep> order = initializationManager.getSteps();
		List<OrderStep> order1 = new ArrayList<OrderStep>();
		int count = 0;
		for (OrderStep ord : order) {
			if (count < 14) {
				order1.add(ord);
				count++;
			}
		}
		try {
			
			int flowID = globalClient.registerOrderWorkflow(order1);
			
			List<StepStatus> status1 = globalClient.getOrderWorkflowStatus(flowID);
			int onGoing = 0;
			int completed = 0;
			for (StepStatus st : status1) {
				if (st == StepStatus.REGISTERED)
					onGoing++;
				else
					completed++;
			}

			List<StepStatus> status2 = globalClient.getOrderWorkflowStatus(flowID);
			int onGoing2 = 0;
			int completed2 = 0;
			for (StepStatus st : status2) {
				if (st == StepStatus.REGISTERED)
					onGoing2++;
				else
					completed2++;
			}
			

			assertTrue(onGoing > onGoing2);
			assertTrue(completed < completed2);

		} catch (OrderProcessingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void asynchronicityTest2() {

		List<OrderStep> order = initializationManager.getSteps();
		OrderStep localOrder = order.get(15);

		Set<Integer> getOrders = new HashSet<Integer>();
		for (ItemQuantity it : localOrder.getItems())
			getOrders.add(it.getItemId());

		try {

			List<ItemQuantity> result = localClient.getOrdersPerItem(getOrders);

			localClient.executeStep(localOrder);
			
			Thread.sleep(1000);

			List<ItemQuantity> result1 = localClient.getOrdersPerItem(getOrders);

			for (ItemQuantity item : result) {
				for (ItemQuantity item1 : result1) {
					if (item.getItemId() == item1.getItemId()) {
						for (ItemQuantity item2 : localOrder.getItems()) {
							if (item.getItemId() == item2.getItemId()) {
								assertTrue((item.getQuantity() + item2.getQuantity())==item1.getQuantity());
							}
						}
					}
				}
			}
		}

		catch (OrderProcessingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void atomicTest() {

		List<OrderStep> order = initializationManager.getSteps();
		OrderStep localOrder = order.get(16);

		Set<Integer> getOrders = new HashSet<Integer>();
		for (ItemQuantity it : localOrder.getItems())
			getOrders.add(it.getItemId());

		try {

			List<ItemQuantity> result1 = localClient.getOrdersPerItem(getOrders);

			ItemQuantity wrongItem = new ItemQuantity(1000, 60);
			localOrder.getItems().add(wrongItem);
			
			localClient.executeStep(localOrder);

			List<ItemQuantity> result2 = localClient
					.getOrdersPerItem(getOrders);
		
			for (ItemQuantity item : result2) {
				for (ItemQuantity item1 : result1) {
					if (item.getItemId() == item1.getItemId()) {
						assertTrue(item.getQuantity() == item1.getQuantity());
					}
				}
			}

			boolean invalid = false;
			getOrders.add(555);
			
			try {
				localClient.getOrdersPerItem(getOrders);
			} catch (InvalidItemException e) {
				invalid = true;
			}
			assertTrue(invalid);

		} catch (OrderProcessingException e) {
		}

	}

	@Test
	public void atomicTest2() {

		List<OrderStep> order = initializationManager.getSteps();
		List<OrderStep> order1 = new ArrayList<OrderStep>();
		int count = 0;
		for (OrderStep ord : order) {
			if ((count > 17) & (count < 30)) {
				order1.add(ord);
				count++;
			}
			count++;
		}

		ItemQuantity wrongItem = new ItemQuantity(1000, 60);
		order1.get(0).getItems().add(wrongItem);

		boolean invalidflow = false;
		try {

			int flowID = globalClient.registerOrderWorkflow(order1);
			
			Thread.sleep(120);
			
			List<StepStatus> status = globalClient
					.getOrderWorkflowStatus(flowID);
			
			for (int i = 0; i < status.size(); i++) {
				if (i == 0)
					assertTrue(status.get(i) == StepStatus.FAILED);
				else
					assertTrue(status.get(i) == StepStatus.SUCCESSFUL);
			}

			try {
				int wrongflowID = 333;
				@SuppressWarnings("unused")
				List<StepStatus> status2 = globalClient
						.getOrderWorkflowStatus(wrongflowID);
			} catch (InvalidWorkflowException e) {
				invalidflow = true;
				
			}
			assertTrue(invalidflow);

		} catch (OrderProcessingException e) {
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void failureTest() {

		List<OrderStep> order = initializationManager.getSteps();
		List<OrderStep> order1 = new ArrayList<OrderStep>();
		int count = 0;
		
		for (OrderStep ord : order) {
			if ((count > 33) & (count < 48)) {
				order1.add(ord);
				count++;
			}
			count++;
		}

		try {
			int flowID = globalClient.registerOrderWorkflow(order1);
			Thread.sleep(90);
			List<StepStatus> status = globalClient
					.getOrderWorkflowStatus(flowID);
			for (int i = 0; i < status.size(); i++) {
				if ((i == 2) || (i == 13))
					assertTrue(status.get(i) == StepStatus.SERVER_DOWN);
				else
					assertTrue(status.get(i) == StepStatus.SUCCESSFUL);
			}

		} catch (OrderProcessingException e) {
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void brokersFailure() {
		
		List<OrderStep> order = initializationManager.getSteps();
		List<OrderStep> order1 = new ArrayList<OrderStep>();
		int count = 0;
		for (OrderStep ord : order) {
			if (count > 47) {
				order1.add(ord);
				count++;
			}
			count++;
		}

		try {
			int flowID = globalClient.registerOrderWorkflow(order1);
			Thread.sleep(50);
			boolean noBrokers = false;

			for (IntegrationBrokerHTTPServer server : brokerServers) 
				server.stop();
			
			try {
				globalClient.getOrderWorkflowStatus(flowID);
			} catch (Exception e) {
				noBrokers = true;
			}
			
			assertTrue(noBrokers);
		} catch (OrderProcessingException e) {
		} catch (InterruptedException e) {
		}
	}
}
