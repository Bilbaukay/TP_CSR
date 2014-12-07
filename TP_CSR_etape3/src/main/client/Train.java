package main.client;
import java.util.ArrayList;


public class Train extends Thread {
	
	private ArrayList<Voyageur> voyageurs;
	
	private static final int VITESSE_TRAIN=299;
	
	private static final int ARRET_TRAIN=5000;
	
	private static final int CAPACITE_TRAIN=8;
	
	private Gare arrivee;
	
	private Gare destination;
	
	private int places_libres;
	
	private int places_vendues;
	
	private int idTrain;
		
	public Train(Gare arrivee, Gare destination, int id)
	{
		this.idTrain = id;
		this.arrivee = arrivee;
		this.destination = destination;
		this.initPlacesLibres();
		this.voyageurs = new ArrayList<Voyageur>();
		this.places_vendues = 0;
	}
	
	public void run()
	{
		System.out.println("Train "+this.idTrain+" en route vers la gare "+this.arrivee.getIdGare()+" avec "+this.places_libres+" places libres à destination de la gare "+this.destination.getIdGare());
		// Temps qu'il faut au train pour arriver en gare
		try {
			Thread.sleep(10000/VITESSE_TRAIN);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		
		System.out.println("Train "+this.idTrain+" arrivé en gare "+this.arrivee.getIdGare()+", en attente pour se garer");
		// Le train cherche Ã  se garer sur une voie libre
		this.arriverEnGare();
		
		System.out.println(("Train "+this.idTrain+" garé en gare "+this.arrivee.getIdGare()+" ! Arrêt momentané."));
		
		// Le train attend les voyageurs
		try {
			Thread.sleep(ARRET_TRAIN);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("La vente de ticket du train "+this.idTrain+" est stoppée, tous les voyageurs doivent monter à bord !");
		// La vente de ticket est stoppée
		this.places_libres = 0;
		
		// Le train quitte la gare
		this.quitterGare();
		System.out.println("Train "+this.idTrain+" en partance de la gare "+this.arrivee.getIdGare()+" avec "+this.voyageurs.size()+" voyageurs à bord, destination : "+this.destination.getIdGare());
		
		this.destination = null;
		
	}
	
	
	private synchronized void quitterGare()
	{
		while(!this.destination.getEq().quitterGare(this))
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		this.notifyAll();
	}
	
	
	private synchronized void arriverEnGare()
	{
		while(!this.arrivee.getEq().arriverEnGare(this))
		{
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void voyageurMonte(Voyageur v)
	{
		this.voyageurs.add(v);
		this.places_libres--;
		System.out.println("Voyageur "+v.getVoyageurId()+" monté dans le train "+this.idTrain);
		this.notify();
	}
	
	public synchronized boolean verifierVoyageur()
	{
		return this.voyageurs.size() == this.places_vendues;
	}
	
	public synchronized void decreasePlacesLibres()
	{
		this.places_libres--;
	}
	
	public synchronized void increasePlacesVendues()
	{
		this.places_vendues++;
	}
	
	public synchronized int getPlacesLibres()
	{
		return this.places_libres;
	}
	
	public int getPlacesVendues()
	{
		return this.places_vendues;
	}
	
	public void initPlacesLibres()
	{
		this.places_libres = (int)(Math.random()*CAPACITE_TRAIN);
		//System.out.println(this.places_libres);
	}
	
	
	public void SetDestination(Gare g)
	{
		this.destination = g;
	}
	
	public Gare getDestination()
	{
		return this.destination;
	}
	
	public Gare getArrivee()
	{
		return this.arrivee;
	}

	public int getIdTrain() {
		return this.idTrain;
	}
	
}
