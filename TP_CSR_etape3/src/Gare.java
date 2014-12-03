import serveur.ServeurBilleterie;


public class Gare extends Thread {

	static final int NB_TRAINS=3;
	
	static final int NB_VOYAGEURS=6;
	
	private EspaceQuai eq;
	
	private EspaceVente ev;
	
	private ServeurBilleterie servBill = null;
	
	private char idGare;

	public Gare(char id)
	{
		this.idGare = id;
		this.eq = new EspaceQuai(this);
		this.ev = new EspaceVente(this);
	}
	
	public void run()
	{

	}
	
	public EspaceQuai getEq() {
		return this.eq;
	}

	public EspaceVente getEv() {
		return this.ev;
	}
	
	public char getIdGare()
	{
		return this.idGare;
	}
	
	public ServeurBilleterie getServeurBilleterie()
	{
		return this.servBill;
	}
	
	public void setServeurBilleterie(ServeurBilleterie sb)
	{
		this.servBill = sb;
	}

}