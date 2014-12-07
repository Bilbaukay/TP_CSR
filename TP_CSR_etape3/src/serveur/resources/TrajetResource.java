package serveur.resources;

import org.json.JSONObject;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import serveur.objects.Trajet;
import serveur.objects.TrajetList;

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
		
		System.out.println(t.getJson());
		
		return t.getJson().toString();
		
	}
	
}
