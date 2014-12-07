package serveur;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import serveur.objects.Trajet;
import serveur.objects.TrajetList;
import serveur.resources.TrajetListResource;
import serveur.resources.TrajetResource;



public class AppBilleterie extends Application {
	
	public AppBilleterie()
	{
		super();
		TrajetList.addTrajet(new Trajet('C', 'B', 0));
		TrajetList.addTrajet(new Trajet('C', 'D', 8));
	}
	
	@Override
    public Restlet createInboundRoot() {
		
        Router router = new Router(getContext());
        router.attach("/trajets", TrajetListResource.class);
        router.attach("/trajet/{idGareArrivee}", TrajetResource.class);
        return router;
    }
}
