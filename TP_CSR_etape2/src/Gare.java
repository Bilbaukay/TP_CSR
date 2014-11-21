
public class Gare extends Thread {

	static final int NB_TRAINS=10;
	
	static final int NB_VOYAGEURS=100;
	
	private EspaceQuai eq;

	public Gare()
	{
		this.eq = new EspaceQuai(this);
	}
	
	public void run()
	{
		
	}
	
	public EspaceQuai getEq() {
		return eq;
	}

	public void setEq(EspaceQuai eq) {
		this.eq = eq;
	}

}
