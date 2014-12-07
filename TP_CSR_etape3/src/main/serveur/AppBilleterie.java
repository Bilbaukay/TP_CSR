package main.serveur;
import main.serveur.objects.Trajet;
import main.serveur.objects.TrajetList;
import main.serveur.resources.TrajetListResource;
import main.serveur.resources.TrajetResource;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;



public class AppBilleterie extends Application {
	
	public AppBilleterie()
	{
		super();
	}
	
	@Override
    public Restlet createInboundRoot() {
		
        Router router = new Router(getContext());
        router.attach("/trajets", TrajetListResource.class);
        router.attach("/trajet/{idGareArrivee}", TrajetResource.class);
        return router;
    }
}
