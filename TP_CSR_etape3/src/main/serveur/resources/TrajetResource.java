package main.serveur.resources;

import main.serveur.objects.Trajet;
import main.serveur.objects.TrajetList;

import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class TrajetResource extends ServerResource {
	
	@Get
	public String reserverTrajet()
	{
		Trajet t = TrajetList.reserverTrajetByIdGareArrivee(getAttribute("idGareArrivee").charAt(0));
		if(t==null){
			this.getResponse().setStatus(Status.CLIENT_ERROR_NOT_FOUND);
			return "";
		}
		
		t.decreasePlaces();
		
		return t.getJson().toString();
		
	}
	
}
