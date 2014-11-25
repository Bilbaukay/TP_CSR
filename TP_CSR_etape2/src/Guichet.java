
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
		Train train = this.gare.getEq().reserverTrain();
		
		System.out.println("Impression du ticket du voyageur "+voyageur.getVoyageurId()+" pour le train "+train.getIdTrain()+" en cours");
		
		this.imprimerTicket();
		
		this.gare.getEv().freeGuichet(this);
		
		return new Ticket(train, voyageur);
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
