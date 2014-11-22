
public class Voyageur extends Thread {

	private Gare gare;
	
	private int voyageurId;
	
	public Voyageur(Gare gare, int voyageurId)
	{
		this.gare = gare;
		this.voyageurId = voyageurId;
	}
	
	public void run()
	{
		// Le voyageur achÃ¨te un billet
		System.out.println("Voyageur "+this.voyageurId+" achète son ticket");
		this.gare.getEv().getGuichet().vendreTicket();
		
		System.out.println("Voyageur "+this.voyageurId+" attend un train");
		// Le voyageur monte dans le train
		this.gare.getEq().monterDansTrain(this);
	}
	
	public int getVoyageurId()
	{
		return this.voyageurId;
	}
	
}
