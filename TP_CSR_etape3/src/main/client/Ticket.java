package main.client;

public class Ticket {

	private Train train;
	
	private Gare gareDepart;
	
	private Gare gareArrivee;
	
	private Voyageur voyageur;
	
	
	public Ticket(Voyageur voyageur, Gare gareDepart, Gare gareArrivee)
	{
		this.voyageur = voyageur;
		this.gareDepart = gareDepart;
		this.gareArrivee = gareArrivee;
	}
	
	public void setTrain(Train train)
	{
		this.train = train;
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
