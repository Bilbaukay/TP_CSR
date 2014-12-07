import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONObject;
import org.restlet.Component;
import org.restlet.resource.ResourceException;
import org.restlet.data.Protocol;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import serveur.AppBilleterie;
import client.Gare;


public class Main {

	private static Gare[] gare;
	
	public static void main(String[] args) throws Exception 
	{
		
		//On initialise les gares
		/*gare = new Gare[3];
		gare[0] = new Gare('A');
		gare[1] = new Gare('B');
		gare[2] = new Gare('C');
		*/
		//On lance le serveur de billeterie
		Component comp = new Component();
		comp.getServers().add(Protocol.HTTP,  8888);
		
		comp.getDefaultHost().attach(new AppBilleterie());
		
		comp.start();
		
		ClientResource client = new ClientResource("http://127.0.0.1:8888/trajets");

		ClientResource client2 = new ClientResource("http://127.0.0.1:8888/trajet/B");
		
		HashMap<String, String> m = new HashMap<String, String>();
		
		m.put("idGareDepart", "A");
		m.put("idGareArrivee", "B");
		m.put("nbPlaces", Integer.toString(5));
		/*
		JsonRepresentation j = new JsonRepresentation(form.getWebRepresentation());
		
		System.out.println(j);*/
		client.post(new JsonRepresentation(new JSONObject(m)));
		
		JsonRepresentation jp = new JsonRepresentation(client.get());
		JSONObject obj = jp.getJsonObject();
		Iterator it = obj.keys();
		
		while(it.hasNext())
			System.out.println(new JSONObject(obj.getString((String)it.next())));
		
		
		try{
		System.out.println(new JsonRepresentation(client2.get()));
		System.out.println(client2.getResponse().getStatus());
		System.out.println(new JsonRepresentation(client2.get()));
		System.out.println(client2.getResponse().getStatus());
		System.out.println(new JsonRepresentation(client2.get()));
		System.out.println(client2.getResponse().getStatus());
		System.out.println(new JsonRepresentation(client2.get()));
		System.out.println(client2.getResponse().getStatus());
		System.out.println(new JsonRepresentation(client2.get()));
		System.out.println(client2.getResponse().getStatus());
		System.out.println(new JsonRepresentation(client2.get()));
		System.out.println(client2.getResponse().getStatus());
		System.out.println(new JsonRepresentation(client2.get()));
		System.out.println(client2.getResponse().getStatus());
		}catch(ResourceException e){
			e.printStackTrace();
			e.getStatus();
			System.out.println("coucou"+e.getClass()+e.getStatus()+Status.CLIENT_ERROR_NOT_FOUND);
		}
		
		/*
		//On renseigne le serveur de billeterie à utiliser dans les gares
		gare[0].setServeurBilleterie(sb);
		gare[1].setServeurBilleterie(sb);
		gare[2].setServeurBilleterie(sb);
		*/
	}
	
	public static Gare getGareById(char id)
	{
		for(int i = 0; i < gare.length; i++)
			if(gare[i].getIdGare() == id)
				return gare[i];
		
		return null;
	}

}
