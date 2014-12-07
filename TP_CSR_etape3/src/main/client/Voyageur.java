package main.client;
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
		System.out.println("Le voyageur "+this.voyageurId+" est dans la gare "+this.gare.getIdGare()+" pour acheter un ticket pour la destination "+this.gareDestination);
		
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
	
	public char getGareDestination()
	{
		return this.gareDestination;
	}
	
}
