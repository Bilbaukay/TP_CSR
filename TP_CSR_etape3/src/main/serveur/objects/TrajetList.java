package main.serveur.objects;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class TrajetList {

	private static ArrayList<Trajet> trajetList = new ArrayList<Trajet>();
	
	public synchronized static void addTrajet(Trajet t)
	{
		TrajetList.trajetList.add(t);
	}
	
	public synchronized static Trajet reserverTrajetByIdGareArrivee(char idGareArrivee)
	{	
		for(Trajet t : trajetList)
			if(t.getIdGareArrivee() == idGareArrivee && t.getNbPlaces() > 0){
				System.out.println("success");
				return t;
			}
		
		return null;
	}
	
	public static JSONObject toJSON()
	{
		JSONObject obj = new JSONObject();
		
		for(Trajet t : trajetList)
			try {
				obj.put(String.valueOf(t.getIdTrajet()), t.getJson().toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return obj;
	}
	
	public synchronized static boolean trajetExists(char idGareDep, char idGareArr)
	{
		for(Trajet t : trajetList)
			if(t.getIdGareDepart() == idGareDep && t.getIdGareArrivee() == idGareArr)
				return true;
		
		return false;
	}
	
	public synchronized static Trajet getTrajet(char idDep, char idArr)
	{
		for(Trajet t : trajetList)
			if(t.getIdGareDepart() == idDep && t.getIdGareArrivee() == idArr)
				return t;
		
		return null;
	}
	
}
