
public class Gare extends Thread {

	static final int NB_TRAINS=3;
	
	static final int NB_VOYAGEURS=6;
	
	private EspaceQuai eq;
	
	private EspaceVente ev;

	public Gare()
	{
		this.eq = new EspaceQuai(this);
		this.ev = new EspaceVente(this);
	}
	
	public void run()
	{
		for(int i=0; i<Gare.NB_VOYAGEURS;i++)
			new Voyageur(this, i).start();
		
		for(int j=0; j<Gare.NB_TRAINS;j++)
			new Train(this, j).start();
	}
	
	public EspaceQuai getEq() {
		return eq;
	}

	public EspaceVente getEv() {
		return ev;
	}

}
