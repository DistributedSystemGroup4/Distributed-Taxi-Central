package group4.termassignment.taxicentral;

import no.ntnu.item.arctis.runtime.Block;

public class TaxiCentral extends Block {

	public String subscribeTopics() {
		String topics = "ntnu/item/ttm4115/group4/userRequest,ntnu/item/ttm4115/group4/notifyUser,ntnu/item/ttm4115/group4/orderAllocate,ntnu/item/ttm4115/group4/taxiUpdate";
		return topics;
	}
}
