package group4.termassignment.taxiclient;

import group4.termassignment.taxisystem.component.TaxiInfo;
import group4.termassignment.taxisystem.component.TourOrder;
import no.ntnu.item.arctis.runtime.Block;

public class TaxiClient extends Block {

	public java.lang.String alias;
	public TaxiInfo taxi = new TaxiInfo();
	public TourOrder tourOrder = null;
	public static String getAlias(String initAlias){return initAlias;}
	public static String getAlias(Object initAliasObject){return ((group4.termassignment.taxiclient.TaxiClient) initAliasObject).alias;}
	public static String getAlias(TourOrder initAliasTourOrder){return ((group4.termassignment.taxisystem.component.TourOrder) initAliasTourOrder).taxiAlias;}
	
	public TaxiClient()
	{
		taxi.taxiAlias = alias;
		taxi.location = "Østre Gløshaugen, Trondheim";//default address
	}
	
	public void acceptRequest() {
		if(tourOrder != null)
		{
			taxi.confirm = true;
			taxi.location = tourOrder.address;
		}
	}

	public void registerOnDuty() {
		taxi.onDuty = true;
	}

	public void registerOffDuty() {
		taxi.onDuty = false;
	}

	public void setAvailableStatus() {
		taxi.available = true;
	}

	public void setUnavailableStatus() {
		if(tourOrder == null)
			taxi.available = false;
		else
		{
			taxi.confirm = false;
			tourOrder.taxiAcceptOrder = false; //change from "true" to "false"
		}
	}
		
	public String displayOrder(TourOrder aTourOrder) {
		String message = null;
		if(aTourOrder != null)
		{
			tourOrder = aTourOrder;
			message = "User" + aTourOrder.userAlias + " wants to be picked up at " + aTourOrder.address;
		}
		return message;
	}
	public TaxiInfo updateTaxiInfo() {
		return taxi;
	}
	public TourOrder updateOrder() {
		return tourOrder;
	}

}
