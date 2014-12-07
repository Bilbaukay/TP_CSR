package main;
import java.io.IOException;
import java.util.HashMap;

import main.client.Ticket;
import main.client.Train;
import main.client.Voyageur;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;


public class ReservationSystem {

	private Connector connector;
	
	public static String URL_TRAJET = "trajet/";
	
	public static String URL_TRAJETS = "trajets";
	
	public ReservationSystem(Connector c)
	{
		this.connector = c;
	}
	
	public synchronized void ajouterTrajet(Train train)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("idGareDepart", Character.toString(train.getArrivee().getIdGare()));
		map.put("idGareArrivee", Character.toString(train.getDestination().getIdGare()));
		map.put("nbPlaces", Integer.toString(train.getPlacesLibres()));
		
		Representation r = this.connector.getResource(URL_TRAJETS).post(new JsonRepresentation(new JSONObject(map)));
		
		this.notifyAll();
	}
	
	public synchronized Ticket reserverTrajet(Voyageur v, char gare)
	{
		Ticket t = null;
		
		try {
			
			Representation r = this.connector.getResource(URL_TRAJET+gare).get();
			JSONObject obj = new JsonRepresentation(r).getJsonObject();
						
			t = new Ticket(v, Main.getGareById((char)obj.getInt("idGareDepart")), Main.getGareById((char)obj.getInt("idGareArrivee")));
			
		} catch (ResourceException r){
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return this.reserverTrajet(v, gare);
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
}
