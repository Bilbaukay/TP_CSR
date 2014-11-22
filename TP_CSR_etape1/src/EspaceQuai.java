import java.util.ArrayList;
import java.util.Hashtable;


public class EspaceQuai {
	
	private ArrayList<Train> trains_en_gare;
	
	static final int NB_VOIES = 2;
	
	private int voies_occupees;
	
	private Gare gare;
	
	public EspaceQuai(Gare gare)
	{
		this.gare = gare;
		this.trains_en_gare = new ArrayList<Train>();
	}
	
	public ArrayList<Train> getTrainsGare()
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
	
	public synchronized void quitterGare(Train t)
	{
		System.out.println("coucoubis");
		this.voies_occupees--;
		this.trains_en_gare.remove(t);
		System.out.println("coucouter");
		this.notify();
		System.out.println("coucouquad");
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
