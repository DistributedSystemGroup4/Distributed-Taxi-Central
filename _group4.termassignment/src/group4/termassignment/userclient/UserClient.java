package group4.termassignment.userclient;

import group4.termassignment.taxisystem.component.TourOrder;
import no.ntnu.item.arctis.runtime.Block;

public class UserClient extends Block {

	public java.lang.String alias;
	public TourOrder userTourOrder = new TourOrder(); 
	//update status = false i.e. cancel a taxi
	public TourOrder cancelRequest() {
		userTourOrder.userAlias = alias;
		userTourOrder.taxiAlias = "";
		userTourOrder.status = false;
		return userTourOrder;
	}
	public static String getAlias(String initAlias){return initAlias;}
	public static String getAlias(Object initAliasObject){return ((group4.termassignment.userclient.UserClient) initAliasObject).alias;}
	
	//create a request with pick up address
	public TourOrder sendRequest(String request) {
		userTourOrder.userAlias = alias;
		userTourOrder.taxiAlias = "";
		userTourOrder.address = request;
		userTourOrder.status = true;
		return userTourOrder;
	}
}
