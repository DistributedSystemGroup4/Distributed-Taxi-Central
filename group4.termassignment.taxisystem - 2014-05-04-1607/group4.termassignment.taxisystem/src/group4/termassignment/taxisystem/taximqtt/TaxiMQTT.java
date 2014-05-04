package group4.termassignment.taxisystem.taximqtt;

import group4.termassignment.taxisystem.taxisystem.component.TaxiInfo;
import group4.termassignment.taxisystem.taxisystem.component.TourOrder;
import no.ntnu.item.arctis.runtime.Block;

public class TaxiMQTT extends Block {


	//public final int group;
	public TourOrder test;
	public java.lang.String messageDataIn;
	public java.lang.String messageTopicIn;
	public final static java.lang.String TAXIUPDATE  = "ntnu/item/ttm4115/group4/taxiUpdate";
	public final static java.lang.String ORDERALLOCATE = "ntnu/item/ttm4115/group4/orderAllocate";
	public java.lang.String MessageDataIn;
	public java.lang.String MessageTopicIn;
	//private TaxiInfo testTaxiInfo;
	

	
	public TourOrder ObjectTransform(Object obj)
	{		
		if(obj != null)
			return (TourOrder) obj;
		else
			return null;
	}


	public void ProceedObject(String topic, Object data) 
	{
		switch(topic)
		{
		case TAXIUPDATE:
			messageDataIn = ((TaxiInfo)data).toString();
			messageTopicIn = TAXIUPDATE;
			break;
		case ORDERALLOCATE:
			messageDataIn = ((TourOrder)data).toString();
			messageTopicIn = ORDERALLOCATE;
			break;
		}
	}


	public void messageDataIn(String str) {
		messageDataIn = str;
	}


	public void messageTopicIn(String str) {
		messageTopicIn = str;
		
	}


	public String TaxiMQTTConnectionFailed(String fail) {
		System.out.println("************************************");
		System.out.println("Connection Failed with the Taxi MQTT");
		System.out.println(fail);
		
		return fail;
	}
	
}
