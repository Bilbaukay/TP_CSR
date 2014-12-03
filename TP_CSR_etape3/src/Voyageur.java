import java.util.ArrayList;


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
		// Le voyageur réserve un guichet et y achète son ticket
		Ticket ticket = this.gare.getEv().reserverGuichet().getTrainTicket(this);
		System.out.println("Le voyageur "+this.voyageurId+" a acheté son ticket pour le train "+ticket.getTrain().getIdTrain());
		
		// Enfin il monte dans le train
		ticket.getTrain().voyageurMonte(this);
	}
	
	public int getVoyageurId()
	{
		return this.voyageurId;
	}
	
}
