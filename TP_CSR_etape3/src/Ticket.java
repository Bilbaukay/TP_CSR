
public class Ticket {

	private Train train;
	
	private Voyageur voyageur;
	
	
	public Ticket(Train train, Voyageur voyageur)
	{
		this.train = train;
		this.voyageur = voyageur;
	}
	
	public Train getTrain()
	{
		return this.train;
	}
	
	public Voyageur getVoyageur()
	{
		return this.voyageur;
	}
}
