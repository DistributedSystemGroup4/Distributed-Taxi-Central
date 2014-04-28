package group4.termassignment.user;

import group4.termassignment.taxisystem.component.TourOrder;
import group4.termassignment.usermqtt.UserMQTT;
import no.ntnu.item.arctis.runtime.Block;

public class User extends Block {

	public UserMQTT userMQTT = new UserMQTT(4);
	public String subscribeTopics(TourOrder data) {
		String topics = "generic-map-ui-group04/notifyUser/u"+ data.userAlias;
		return topics;
	}

}
