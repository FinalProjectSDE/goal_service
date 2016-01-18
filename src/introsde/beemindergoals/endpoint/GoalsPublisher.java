package introsde.beemindergoals.endpoint;


import introsde.beemindergoals.soap.GoalsImpl;

import javax.xml.ws.Endpoint;

public class GoalsPublisher {
	public static String SERVER_URL = "http://localhost";
	public static String PORT = "6915";
	public static String BASE_URL = "/soap/goals";
	
	public static String getEndpointURL() {
		return SERVER_URL+":"+PORT+BASE_URL;
	}
 
	public static void main(String[] args) {
		String endpointUrl = getEndpointURL();
		System.out.println("Starting Goals Service...");
		System.out.println("--> Published at = "+endpointUrl);
		Endpoint.publish(endpointUrl, new GoalsImpl());
    }
}

