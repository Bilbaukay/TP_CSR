import java.util.ArrayList;


public class Train extends Thread {
	
	private ArrayList<Voyageur> voyageurs;
	
	private static final int VITESSE_TRAIN=299;
	
	private static final int ARRET_TRAIN=5000;
	
	private static final int CAPACITE_TRAIN=300;
	
	private Gare destination;
	
	private int places_libres;
	
	private int idTrain;
		
	public Train(int id, Gare destination)
	{
		this.idTrain = id;
		this.destination = destination;
	}
	
	public void run()
	{
		
		// Temps qu'il faut au train pour arriver en gare
		try {
			Thread.sleep(10000/VITESSE_TRAIN);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		// Le train cherche à se garer sur une voie libre
		this.arriverEnGare();
		
		// Le train attend les voyageurs
		try {
			Thread.sleep(ARRET_TRAIN);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Le train quitte la gare
		this.quitterGare();
		
		this.destination = null;
		
	}
	
	
	private void quitterGare()
	{
		this.destination.getEq().quitterGare(this);
	}
	
	
	private void arriverEnGare()
	{
		this.destination.getEq().arriverEnGare(this);
	}
	
	public void voyageurMonte(Voyageur v)
	{
		this.voyageurs.add(v);
		this.places_libres--;
	}
	
	public int getPlacesLibres()
	{
		return this.places_libres;
	}
	
	public void initPlacesLibres()
	{
		this.places_libres = (int)(Math.random()*CAPACITE_TRAIN);
		System.out.println(this.places_libres);
	}
	
	
	public void SetDestination(Gare g)
	{
		this.destination = g;
	}

	public int getIdTrain() {
		return this.idTrain;
	}
	
}
