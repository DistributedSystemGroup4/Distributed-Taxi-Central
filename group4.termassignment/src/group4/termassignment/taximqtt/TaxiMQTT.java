package group4.termassignment.taximqtt;

import no.ntnu.item.arctis.runtime.Block;
import group4.termassignment.taxisystem.component.TaxiInfo;
import group4.termassignment.taxisystem.component.TourOrder;

public class TaxiMQTT extends Block {

	public final static java.lang.String TAXIUPDATE = "ntnu/item/ttm4115/group4/taxiUpdate";
	public final static java.lang.String ORDERALLOCATE = "ntnu/item/ttm4115/group4/orderAllocate";
	public final int group;
	
	public TaxiMQTT(int group) {
	    this.group = group;
	}
	
	public Object TaxiInfoTransform(TaxiInfo taxiInfo) {
	return (Object) taxiInfo;
	}
	public Object TourOrderTransform(TourOrder tourOrder) {
		if(tourOrder != null)
			return (Object) tourOrder;
		else 
			return null;
	}
	public TourOrder ObjectTransform(Object obj)
	{
		if(obj != null)
			return (TourOrder) obj;
		else
			return null;
	}

}
