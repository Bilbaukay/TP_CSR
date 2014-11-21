
public class Voyageur extends Thread {

	private EspaceVente ev;
	private EspaceQuai eq;
	
	private int voyageurId;
	
	public Voyageur(EspaceVente ev, EspaceQuai eq, int voyageurId)
	{
		this.eq = eq;
		this.ev = ev;
		this.voyageurId = voyageurId;
	}
	
	public void run()
	{
		// Le voyageur achète un billet
		this.ev.getGuichet().vendreTicket();
		
		// Le voyageur monte dans le train
		this.eq.monterDansTrain(this);
	}
	
}
