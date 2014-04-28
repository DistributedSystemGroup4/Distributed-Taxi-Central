package group4.termassignment.taxisystem.component;

public class TaxiInfo {
public String taxiAlias;
public boolean onDuty; //true = OnDuty, false = OffDuty
public boolean available; //true = available, false = unavailable
public String location; //current address of the taxi
public String destination; //destination if taxi is in a tour order

public TaxiInfo()
{}
}
