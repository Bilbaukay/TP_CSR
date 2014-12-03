package serveur;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;



public class ServeurBilleterie extends Application {

	@Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/trajets", TrajetList);
        router.attach("/trajets")
        return router;
      
    }
}
