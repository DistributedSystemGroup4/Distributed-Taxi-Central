package group4.termassignment.userclient;

import no.ntnu.item.arctis.runtime.Block;

public class UserClient extends Block {

	public java.lang.String alias;

	public String cancelRequest() {
		return "User " + alias + " cancel request!!";
	}
	public static String getAlias(String initAlias){return initAlias;}
	public static String getAlias(Object initAliasObject){return ((group4.termassignment.userclient.UserClient) initAliasObject).alias;}
	public String setSubTopic() { return "taxi";
	}
}
