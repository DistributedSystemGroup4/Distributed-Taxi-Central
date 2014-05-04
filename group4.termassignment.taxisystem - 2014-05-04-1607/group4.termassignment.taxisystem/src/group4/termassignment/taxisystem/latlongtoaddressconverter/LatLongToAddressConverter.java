package group4.termassignment.taxisystem.latlongtoaddressconverter;

import com.bitreactive.library.android.maps.model.Position;

import no.ntnu.item.arctis.runtime.Block;

public class LatLongToAddressConverter extends Block {
	

	private static final String ENC = "UTF-8";
	
	private String taxiAlias;

	@SuppressWarnings("deprecation")
	public String createURI(Position pos) {
				
		double lat = pos.getLatitude();
		double lon = pos.getLongitude();
		
		String latStr = "" + lat;
		String lonStr = "" + lon;
		
		System.out.println(latStr + "," + lonStr);
		System.out.println(latStr.substring(0, latStr.indexOf('.')+ 7) + "," + lonStr.substring(0, lonStr.indexOf('.')+ 7));
		latStr = latStr.substring(0, latStr.indexOf('.')+ 7);
		lonStr = lonStr.substring(0, lonStr.indexOf('.')+ 7);
		
		System.out.println("URI created : LatLongToAddressConverter");
		
		//https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&sensor=true_or_false&key=API_KEY
		String uri = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latStr + "," + lonStr + "&sensor=true&key=AIzaSyCcNHOyea9-2KWbf1Ja803q2L_JWA-esU0";
		System.out.println(uri);
		
		return uri;
		//return "http://maps.googleapis.com/maps/api/directions/json?origin="+fromAddress+"&destination="+toAddress+"&sensor=true";
	}

	
	public String checkJSONstring(String jsonStr) {
		//System.out.println("JSON : " + jsonStr);
		String address = "";
		int formattedAddressIndex = jsonStr.indexOf("formatted_address");
		
		if(formattedAddressIndex > -1) {
			int len1 = (Integer)jsonStr.length();
			String firstSubString = jsonStr.substring(formattedAddressIndex + 22, len1 - 1);			
			
			address = firstSubString.substring(0, firstSubString.indexOf('"'));
			
			System.out.println("address = " + address);
		
			//String address = jsonStr.substring(formattedAddressIndex, formattedAddressIndex + )
		}
		
		return address;
	}

	

}
