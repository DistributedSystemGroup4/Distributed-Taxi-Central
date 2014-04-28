package group4.termassignment.taxidispatcher;

import group4.termassignment.taxisystem.component.TaxiInfo;
import group4.termassignment.taxisystem.component.TourOrder;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Distance;
import no.ntnu.item.ttm4115.simulation.routeplanner.Journey;

public class TaxiDispatcher extends Block {

	public TaxiInfo[] taxiId = new TaxiInfo[100];
	public TourOrder[] orderId = new TourOrder[100];
	public String USERREQUEST = "ntnu/item/ttm4115/group4/userRequest";
	public String NOTIFYUSER = "ntnu/item/ttm4115/group4/notifyUser";
	public String ORDERALLOCATE = "ntnu/item/ttm4115/group4/orderAllocate";
	public String TAXIUPDATE = "ntnu/item/ttm4115/group4/taxiUpdate";
	public static int taxiQ = 0;
	public static int userQ = 0;
	public String notification = null;
	public java.lang.Object messageOutData;
	public java.lang.String messageOutTopic;
	public String startAddress = null;
	public String endAddress = null;
	public no.ntnu.item.ttm4115.simulation.routeplanner.Route aRoute;
	public no.ntnu.item.ttm4115.simulation.routeplanner.Journey aJourney;
	
	public void proceedMessage(Object data, String topic) 
	{
		switch(topic)
		{
		case "USERREQUEST": if(data != null)
			HandleUserRequest((TourOrder)data);break;
		case "ORDERALLOCATE": if(data != null)
			HandleOrderAllocate((TourOrder)data);break;
		case "TAXIUPDATE": if(data != null)
			HandleTaxiUpdate((TaxiInfo)data);break;
		}
	}

	private void HandleTaxiUpdate(TaxiInfo data) {
		// TODO Auto-generated method stub
		if(data != null)
		{//if taxi not exist yet in array, add it
			if(TaxiExisted(data) == false)
				taxiId[taxiQ++] = data;
			else
				UpdateTaxiId(data);			
		}
	}

	private void UpdateTaxiId(TaxiInfo data) {
		// TODO Auto-generated method stub
		for(int i = 0; i < taxiId.length; i++)
			if(taxiId[i].taxiAlias == data.taxiAlias)
			{
				taxiId[i].available = data.available;
				taxiId[i].destination = data.destination;
				taxiId[i].location = data.location;
				taxiId[i].onDuty = data.onDuty;
				break;
			}
	}
	
	private boolean TaxiExisted(TaxiInfo data) {
		// TODO Auto-generated method stub
		for (int i = 0; i < taxiId.length; i++)
		{
			if(taxiId[i].taxiAlias.equals(data.taxiAlias))
				return true;
		}
		return false;
	}

	private boolean OrderExisted(TourOrder order)
	{
		for (int i = 0; i < orderId.length; i++)
		{
			if(orderId[i].userAlias.equals(order.userAlias))
				return true;
		}
		return false;
	}
	
	private void HandleOrderAllocate(TourOrder data) {
		// TODO Auto-generated method stub
		if(data != null)
		{
			//handle confirmation
			if(data.taxiAcceptOrder == true)
			{				
				UpdateTaxiIdOnConfirm(data);
				RemoveOrder(data);
			}
			//handle rejection i.e. when taxi is unavailable
			else
			{
				UpdateTaxiIdOnReject(data);
				FindATaxi(data);
			}
		}
	}

	private boolean FindATaxi(TourOrder data) {
		// TODO Auto-generated method stub
		//Criteria for selection:
		//1. shortest travel distance
		//2. longest time
		
		Distance minDistance;
		int candid = -1;
		int i=0;
		for(i = 0; i < taxiId.length; i++)
		{
			if(taxiId[i].available == true)
			{
				//check distance. if distance < minDistance then update minDistance and candid = i
				//since we go from element 0 (begin of array) so we start with the longest time element. 
				//Thats why if distance = minDistance, we don't need to update anything with it
				aJourney = new Journey(taxiId[i].location, data.address);
				if(aRoute != null)
				{
					for(int j=0;j<aRoute.legs.size();j++){
						minDistance = aRoute.legs.get(j).distance;
						int val = minDistance.value;
					}
					 
				}				
			}
		}
		if(candid == -1) //no taxi is available, dritt dårlig!!
		{			
			System.out.print("None is available");
			return false;
		}
		//call send order to taxi
		SendOrder(taxiId[candid], data);
		System.out.print("taxiId " + candid + " got a go!!");
		return true;
	}

	private void SendOrder(TaxiInfo taxiInfo, TourOrder data) {
		// TODO Auto-generated method stub
		messageOutTopic = "ntnu/item/ttm4115/group4/orderAllocate/t" + taxiInfo.taxiAlias;
		messageOutData = (Object) data;
	}

	private void UpdateTaxiIdOnReject(TourOrder data) {
		// TODO Auto-generated method stub
		for(int i = 0; i < taxiId.length; i++)
			if(taxiId[i].taxiAlias == data.taxiAlias)
			{
				taxiId[i].available = false;
			}
	}

	private void RemoveOrder(TourOrder data) {
		// TODO Auto-generated method stub
		if(data != null)
		{
			int i,j;
			//if an order has been cancelled or has been confirmed by a taxi, remove it from array
			if(data.status == false || data.taxiAcceptOrder == true)
			{
				for (i = 0; i < orderId.length; i++)
					if(orderId[i].userAlias == data.userAlias)
					{
						for (j = i; j < orderId.length - 1; j++)
						{
							orderId[j] = orderId[j+1];
							SendNotification(orderId[j].userAlias, j+1); //update queue
						}
						orderId[j] = null;
						userQ = j;
					}
			}
		}
	}

	private void UpdateTaxiIdOnConfirm(TourOrder data) {
		// TODO Auto-generated method stub
		for(int i = 0; i < taxiId.length; i++)
			if(taxiId[i].taxiAlias == data.taxiAlias)
			{
				taxiId[i].available = false;
				taxiId[i].destination = data.address;				
				break;
			}
	}

	private void HandleUserRequest(TourOrder data) {
		// TODO Auto-generated method stub
		if(data != null)
		{//if order is not cancelled by user then find a taxi for this order
			if(data.status == true)
			{//if cannot find a taxi for user, add user order to queue
				if(FindATaxi(data) == false)
				{
					orderId[userQ++] = data;
					//call notification handling
					SendNotification(data.userAlias, userQ);					
				}
			}
			else //if the order is cancelled by user and has been in queue then remove it from queue
			{
				if(OrderExisted(data) == true)//in queue
					RemoveOrder(data);
			}		
		}
	}

	private void SendNotification(String userAlias, int i) {
		// TODO Auto-generated method stub
		messageOutTopic = "ntnu/item/ttm4115/group4/notifyUser/u" + userAlias;
		notification = "To user " + userAlias + ": Your order is placed in queue at number " + i;
		messageOutData = (Object) notification;
	}

	
}
