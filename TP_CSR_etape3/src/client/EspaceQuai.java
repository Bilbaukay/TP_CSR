package client;
import java.util.ArrayList;
import java.util.Hashtable;


public class EspaceQuai {
	
	private ArrayList<Train> trains_en_gare;
	
	private ArrayList<Voyageur> voyageurs_a_quai;

	static final int NB_VOIES = 2;
	
	private int voies_occupees;
	
	private Gare gare;
	
	public EspaceQuai(Gare gare)
	{
		this.trains_en_gare = new ArrayList<Train>();
		this.voyageurs_a_quai = new ArrayList<Voyageur>();
		this.gare = gare;
	}
	
	public void addVoyageurAQuai(Voyageur v)
	{
		this.voyageurs_a_quai.add(v);
	}
	
	public synchronized ArrayList<Train> getTrainsGare()
	{
		return this.trains_en_gare;
	}
	
	public synchronized void arriverEnGare(Train t)
	{
		while(voies_occupees==NB_VOIES)
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		this.voies_occupees++;
		this.trains_en_gare.add(t);
		this.notifyAll();
	}
	
	public synchronized boolean quitterGare(Train t)
	{
		//Si on a oublié un voyageur sur l'espace quai, impossible de quitter la gare
		if(!t.verifierVoyageur()){
			System.out.println("Oups ! Il y a encore un voyageur du train "+t.getIdTrain()+" sur le quai ! En attente.");
			return false;
		}
		
		//Sinon, tout va bien
		this.voies_occupees--;
		this.trains_en_gare.remove(t);
		this.notifyAll();
		return true;
	}
	
	public synchronized Train reserverTrain()
	{	
		Train t = this.getTrainFree();
		
		while(t == null)
		{
			try {
				this.wait();
			} catch (InterruptedException e) {
			
			}
			
			t = this.getTrainFree();
		}
		
		t.increasePlacesVendues();
		t.decreasePlacesLibres();
		
		return t;
	}
	
	public synchronized Train getTrainFree()
	{
		//Pour chaque train en gare
		for(int i=0; i< this.trains_en_gare.size(); i++) //On ne rentre même pas dans la boucle s'il n'y en a pas
			//On regarde s'il reste des places libres
			if(this.trains_en_gare.get(i).getPlacesLibres()>0)
				return this.trains_en_gare.get(i);
		
		return null;
	}
	
}
