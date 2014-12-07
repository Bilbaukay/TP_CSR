package main.serveur.resources;

import main.serveur.objects.Trajet;
import main.serveur.objects.TrajetList;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;


public class TrajetListResource extends ServerResource {

	@Get
	public String getList()
	{
		return TrajetList.toJSON().toString();
	}
	
	@Post("json")
	public Representation addPlacesToTrajet(Representation rq)
	{
		
		try {
			//On récupère un objet json à partir de la requête
			JsonRepresentation repr = new JsonRepresentation(rq);
			JSONObject jsonObj = repr.getJsonObject();
			
			//On récupère les informations
			char idDep = ((String)jsonObj.get("idGareDepart")).charAt(0);
			char idArr = ((String)jsonObj.get("idGareArrivee")).charAt(0);
			int nbPlaces = jsonObj.getInt("nbPlaces");
			
			//Si le trajet n'existe pas encore, on le créé, sinon on update juste le nombre de places libres
			if(!TrajetList.trajetExists(idDep, idArr))
				TrajetList.addTrajet(new Trajet(idDep, idArr, nbPlaces));
			else
				TrajetList.getTrajet(idDep, idArr).addPlaces(nbPlaces);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
