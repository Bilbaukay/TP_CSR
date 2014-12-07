package client;

public class Guichet {

	private static final int IMPRESSION_TICKET=3000;
	
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
		Ticket ticket = this.gare.getEv().getReservationSystem()
		
		System.out.println("Impression du ticket du voyageur "+voyageur.getVoyageurId()+" pour le train "+train.getIdTrain()+" en cours");
		
		this.imprimerTicket();
		
		this.gare.getEv().freeGuichet(this);
		
		return ;
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
