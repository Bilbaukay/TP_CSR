package main.serveur.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Trajet {

	private static int idCounter = 0;
	
	private int idTrajet;
	
	private char idGareDepart;
	
	private char idGareArrivee;
	
	private int nbPlaces;
	
	public Trajet(char idGareDepart, char idGareArrivee, int nbPlaces)
	{

		this.idGareArrivee = idGareArrivee;
		this.idGareDepart = idGareDepart;
		this.nbPlaces = nbPlaces;
		this.idTrajet = idCounter;
		
		idCounter++;
	}
	
	public int getIdTrajet()
	{
		return this.idTrajet;
	}
	
	public char getIdGareDepart()
	{
		return this.idGareDepart;
	}
	
	public char getIdGareArrivee()
	{
		return this.idGareArrivee;
	}
	
	public synchronized int getNbPlaces()
	{
		return this.nbPlaces;
	}
	
	public synchronized void decreasePlaces()
	{
		this.nbPlaces--;
	}
	
	public JSONObject getJson()
	{
		JSONObject obj = new JSONObject();
		try {
			obj.put("idGareDepart", this.idGareDepart);
			obj.put("idGareArrivee", this.idGareArrivee);
			obj.put("nbPlaces", this.nbPlaces);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public synchronized void addPlaces(int nbPlaces)
	{
		this.nbPlaces += nbPlaces;
	}
	
}
