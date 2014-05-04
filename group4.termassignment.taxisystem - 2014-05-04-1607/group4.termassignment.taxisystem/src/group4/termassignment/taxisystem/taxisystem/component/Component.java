package group4.termassignment.taxisystem.taxisystem.component;

import group4.termassignment.taxisystem.taximqtt.TaxiMQTT;
import group4.termassignment.taxisystem.taxisystem.component.TaxiInfo;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Journey;

public class Component extends Block {

	public TaxiMQTT taxiMQTT = new TaxiMQTT();
	public boolean mapConnection;
	public boolean taxiMQTTConnection;
	
	public String subscribeTopics(String alias) {
		String topics = "ntnu/item/ttm4115/group4/orderAllocate/t" + alias;
		
		System.out.println("Subscribing: " + topics);
		return topics;
	}

	public Journey createJourney(TaxiInfo taxi) {
		Journey journey = null;
		if(taxi != null)
		{
			if(taxi.destination == null)
				journey = new Journey(taxi.location, taxi.location);
			else
				journey = new Journey(taxi.location, taxi.destination);
		}
			return journey;
		}

	public void mapConnectionEstablished() {		
		mapConnection = true;
		
		System.out.println("###################################");
		System.out.println("Connection with the Map established");
		System.out.println("###################################");
		
	}

	public void mapConnectionFailureMsg() {
		mapConnection = false;
		
		System.out.println("###################################");
		System.out.println("+++++ Map Connection Failed ++++");
		System.out.println("###################################");
	}

	public void TaxiMQTTConnected() {
		taxiMQTTConnection = true;
		
		System.out.println("*******************************************");
		System.out.println("Connection with the MQTT Broker established");
		System.out.println("*******************************************");
				
	}

	public void proceedMapPublish() {
		System.out.println("Now Proceed with the MapUpdating");
		
	}

	public void proceedTaxiPublish() {
		System.out.println("Now proceed with the TaxiMQTT updating");
	}

	public void doNothing() {
		System.out.println("Doing nothing");
	}

	public String startTaxiMQTT() {
		return "Starting TaxiMQTT";
	}

}
