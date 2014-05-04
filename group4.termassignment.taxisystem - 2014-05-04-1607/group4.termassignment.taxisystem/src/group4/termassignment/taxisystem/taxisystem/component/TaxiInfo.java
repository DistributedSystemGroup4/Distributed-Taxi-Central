package group4.termassignment.taxisystem.taxisystem.component;

public class TaxiInfo {
	public String taxiAlias;
	public boolean onDuty; //true = OnDuty, false = OffDuty
	public boolean available; //true = available, false = unavailable
	public String location; //current address of the taxi
	public String destination; //destination of current tour order
	public String userAlias;//the current user it serves
	public TaxiInfo()
	{}
}
