package group4.termassignment.user;

import group4.termassignment.usermqtt.UserMQTT;
import no.ntnu.item.arctis.runtime.Block;

public class User extends Block {

	public UserMQTT userMQTT = new UserMQTT(4);
	public String subscribeTopics(String alias) {
		String topics = "ntnu/item/ttm4115/group4/notifyUser/u"+ alias;
		return topics;
	}

}
