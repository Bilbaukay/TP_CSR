package main.client;

import main.Main;

public class Guichet {

	private static final int IMPRESSION_TICKET=300;
	
	private int id;	
	
	private Gare gare;
	
	public Guichet(int id, Gare gare)
	{
		this.id = id;
		this.gare = gare;
	}
	
	public int getId()
	{
		return this.id;
	}

	public synchronized Ticket getTrainTicket(Voyageur voyageur)
	{
		System.out.println("Le voyageur "+voyageur.getVoyageurId()+" est au guichet "+this.id);
		
		//On récupère un ticket depuis les système de réservation pour une certaine destination
		Ticket ticket = this.gare.getReservationSystem().reserverTrajet(voyageur, voyageur.getGareDestination());
		
		//On récupère un train de libre dans la gare de départ et on y réserve une place
		Train train = Main.getGareById(ticket.getGareDepart().getIdGare()).getEq().reserverTrain(ticket.getGareArrivee());
		
		//On set le train dans le ticket
		ticket.setTrain(train);
		
		//On imprime le ticket
		System.out.println("Impression du ticket du voyageur "+voyageur.getVoyageurId()+" pour le train "+train.getIdTrain()+" en cours");
		
		this.imprimerTicket();
		
		//On libère le guichet
		this.gare.getEv().freeGuichet(this);
		
		return ticket;
	}
	
	public void imprimerTicket()
	{
		try {
			Thread.sleep(IMPRESSION_TICKET);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
			
	}	
}
