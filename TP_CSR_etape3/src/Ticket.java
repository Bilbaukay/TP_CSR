
public class Ticket {

	private Train train;
	
	private Gare gareDepart;
	
	private Gare gareArrivee;
	
	private Voyageur voyageur;
	
	
	public Ticket(Train train, Voyageur voyageur, Gare gareDepart, Gare gareArrivee)
	{
		this.train = train;
		this.voyageur = voyageur;
		this.gareDepart = gareDepart;
		this.gareArrivee = gareArrivee;
	}
	
	public Train getTrain()
	{
		return this.train;
	}
	
	public Voyageur getVoyageur()
	{
		return this.voyageur;
	}
	
	public Gare getGareDepart()
	{
		return this.gareDepart;
	}
	
	public Gare getGareArrivee()
	{
		return this.gareArrivee;
	}
}
