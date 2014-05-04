package group4.termassignment.taxisystem.taxisystem.component;

public class TourOrder {
	public String userAlias;
	public String taxiAlias;
	public String address;
	public boolean status; //"true": request a taxi and "false": cancel a taxi
	public boolean taxiAcceptOrder; //"true": taxi accept order and "false": taxi reject order

	public TourOrder()
	{}
}
