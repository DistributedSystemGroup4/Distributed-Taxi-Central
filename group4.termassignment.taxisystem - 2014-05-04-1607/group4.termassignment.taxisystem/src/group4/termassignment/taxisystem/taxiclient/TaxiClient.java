package group4.termassignment.taxisystem.taxiclient;

import com.bitreactive.library.android.maps.model.MapUpdate;
import com.bitreactive.library.android.maps.model.Marker;
import com.bitreactive.library.android.maps.model.Position;

import group4.termassignment.taxisystem.taxisystem.component.TaxiInfo;
import group4.termassignment.taxisystem.taxisystem.component.TourOrder;
import no.ntnu.item.arctis.runtime.Block;

public class TaxiClient extends Block {

	public java.lang.String alias_;

	public TaxiInfo taxi = new TaxiInfo();
	public TourOrder tourOrder = null;
	public static String getAlias(String initAlias){return initAlias;}
	public static String getAlias(Object initAliasObject){return ((group4.termassignment.taxisystem.taxiclient.TaxiClient) initAliasObject).alias_;}
	public static String getAlias(TourOrder initAliasTourOrder){return ((group4.termassignment.taxisystem.taxisystem.component.TourOrder) initAliasTourOrder).taxiAlias;}
	
	public TaxiClient()
	{
		taxi.taxiAlias = alias_;
		taxi.location = "Østre Gløshaugen, Trondheim";//default address
		taxi.destination = null;
		
		
		
		
		
		
	}
	
	public void acceptRequest() {
		if(tourOrder != null)
		{			
			taxi.destination = tourOrder.address;
			tourOrder.taxiAlias = taxi.taxiAlias;
			tourOrder.taxiAcceptOrder = true;
		}
	}

	//Do sth with update location of a taxi on map either here or in Taxi block
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
			tourOrder.taxiAlias = "";
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
	
	
	public Position createRandomPosition(String alias) {
		this.alias_ = alias;
		
		//==|| Creating a random position somewhere in Trondheim ||==
		double trondheimLatHelp =  Math.random();
		
		while(trondheimLatHelp > 0.4) {
			trondheimLatHelp =  Math.random();
		}
		trondheimLatHelp /= 10;
		System.out.println("lat = " + trondheimLatHelp);
		
		double fromlat = 63.4 +  trondheimLatHelp; // so that the marker do not get too far away, especially in the fjord/ocean
		double fromlng = 10.4 +  Math.random() / 10;
		
		//MapUpdate mapUpdate = new MapUpdate();

		String location = fromlat + "," + fromlng; //##Block variable##
		System.out.println("## Test Location : " + location + " ##");
		
		Position position = new Position(fromlat, fromlng);	
				
		return position;
	}
	
	
	public String setAddress(String address) {
		taxi.location = address;
		
		System.out.println("The address set for taxi:" + alias_ + " is " + address);
		
		return this.alias_;
	}
	public MapUpdate showTaxiInMap(Position pos) {
		 MapUpdate mu = new MapUpdate();
		 
		// mu.
		 
		// Position position = new Position(step.endLocation.latitude*1E6, step.endLocation.longitude*1E6);
		 //mu.addMarker(Marker.createMarker(route.taxiAlias).title(route.taxiAlias).description("Taxi-" + alias_).position(position));
			
		 System.out.println("taxi:" + alias_ +  " lat = " + pos.getLatitude() * 1E6+ ", long = " + pos.getLongitude() * 1E6);
		 
		Position pos2 = new Position(pos.getLatitude() * 1E6,pos.getLongitude() * 1E6);		
		//pos.
			
		 mu.addMarker(Marker.createMarker(alias_).title(alias_).description("Free…").position(pos2));

		 
		// return mapUpdate;
		 
		 System.out.println("MapUpdate sent to MapMQTT");
		return mu;
	}
	
	
	
}
