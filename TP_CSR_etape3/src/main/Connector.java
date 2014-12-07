package main;
import java.util.HashMap;

import org.restlet.resource.ClientResource;


public class Connector {
	
	private String baseURL;
	
	private HashMap<String, ClientResource> mapClients;
	
	public Connector(String baseURL)
	{
		this.baseURL = baseURL;
		this.mapClients = new HashMap<String, ClientResource>();
	}
	
	public ClientResource getResource(String url)
	{
		if(!this.mapClients.containsKey(url)){
			ClientResource c = new ClientResource(this.baseURL);
			c.addSegment(url);
			
			this.mapClients.put(url, c);
		}
		
		return this.mapClients.get(url);
	}
	
	
}
