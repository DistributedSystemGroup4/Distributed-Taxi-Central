package group4.termassignment.usermqtt;

//import group4.termassignment.taxisystem.component.TourOrder;
import no.ntnu.item.arctis.runtime.Block;
//import no.ntnu.item.ttm4115.mqtt.basicmqtt.BasicMQTT.AdvancedConfiguration;

public class UserMQTT extends Block {
public final int group;
public final static java.lang.String NOTIFYUSER = "ntnu/item/ttm4115/group4/notifyUser";
public final static java.lang.String USERREQUEST = "ntnu/item/ttm4115/group4/userRequest";
 
	/*public AdvancedConfiguration createConfig() {
		return new AdvancedConfiguration(NOTIFYUSER, group);
	}*/

	// Do not edit this constructor.
	public UserMQTT(int group) {
	    this.group = group;
	}

	/*public String getString(TourOrder tourOrder) {
		String message = null;
		if(tourOrder != null)
		{
			message = tourOrder.userAlias + " " + tourOrder.taxiAlias + " " + tourOrder.address + " " + tourOrder.status + " " + tourOrder.taxiAcceptOrder;
		}
		return message;
	}*/

}
