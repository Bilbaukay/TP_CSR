import java.util.ArrayList;
import java.util.Hashtable;


public class EspaceQuai {
	
	private ArrayList<Train> trains_en_gare;
	
	// Hashtable pour stocker le nombre de places libres respectif de chaque train en fonction de leur id
	private Hashtable allPlacesLibres = new Hashtable();
	// LOL

	static final int NB_VOIES = 10;
	
	private int voies_occupees;
	
	private Gare gare;
	
	// Parcours de la table allPlacesLibres pour obtenir la somme des places libres dans tous les trains, somme qui correspond au nombre de tickets mis en vente
	public int getNombreTicketsVente()
	{
		int c=0;
		for(int i=0; i<this.allPlacesLibres.size(); i++)
			c+=(int)this.allPlacesLibres.get(i);
		return c;
	}
	
	public EspaceQuai(Gare gare)
	{
		this.gare = gare;
	}
	
	public ArrayList<Train> getTrainsGare()
	{
		return this.trains_en_gare;
	}
	
	public Hashtable getPlacesLibres()
	{
		return this.allPlacesLibres;
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
		this.notify();
	}
	
	public synchronized void quitterGare(Train t)
	{
		this.voies_occupees--;
		this.trains_en_gare.remove(t);
		this.notify();
	}
	
	public synchronized void monterDansTrain(Voyageur v)
	{	
		//Boucle infinie pour recommencer au début
		while(true)
		{
			//Pour chaque train en gare
			for(int i=0; i< this.trains_en_gare.size(); i++) //On ne rentre même pas dans la boucle s'il n'y en a pas
			{
				//On regarde s'il reste des places libres
				if(this.trains_en_gare.get(i).getPlacesLibres()>0){
					this.trains_en_gare.get(i).voyageurMonte(v);
					return;
				}
			}
			
			//Sinon, on attend qu'un autre train arrive
			while(this.voies_occupees==EspaceQuai.NB_VOIES)
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
		}
	}
	
}
