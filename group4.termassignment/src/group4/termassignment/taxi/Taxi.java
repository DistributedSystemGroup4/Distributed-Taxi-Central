package group4.termassignment.taxi;

import group4.termassignment.taximqtt.TaxiMQTT;
import group4.termassignment.taxisystem.component.TaxiInfo;
import no.ntnu.item.arctis.runtime.Block;
import no.ntnu.item.ttm4115.simulation.routeplanner.Journey;

public class Taxi extends Block {

	public TaxiMQTT taxiMQTT = new TaxiMQTT(4);

	public String subscribeTopics() {
		String topics = "orderAllocate";
		return topics;
	}

	public Journey createJourney(TaxiInfo taxi) {
	Journey journey = null;
	if(taxi != null)
		journey = new Journey(taxi.location, taxi.location);
	return journey;
	}


}
