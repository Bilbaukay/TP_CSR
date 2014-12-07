package client;
import java.util.ArrayList;


public class Voyageur extends Thread {

	private Gare gare;
	
	private int voyageurId;
	
	private char gareDestination;
	
	public Voyageur(Gare gare, int voyageurId, char gareDestination)
	{
		this.gare = gare;
		this.voyageurId = voyageurId;
		this.gareDestination = gareDestination;
	}
	
	public void run()
	{	
		// Le voyageur r�serve un guichet et y ach�te son ticket
		Ticket ticket = this.gare.getEv().reserverGuichet().getTrainTicket(this);
		System.out.println("Le voyageur "+this.voyageurId+" a achet� son ticket pour le train "+ticket.getTrain().getIdTrain());
		
		// Enfin il monte dans le train
		ticket.getTrain().voyageurMonte(this);
	}
	
	public int getVoyageurId()
	{
		return this.voyageurId;
	}
	
}
