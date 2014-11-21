import java.util.ArrayList;


public class EspaceVente {
	
	private ArrayList<Guichet> guichets;
	
	public ArrayList<Guichet> getGuichets()
	{
		return this.guichets;
	}
	
	public EspaceVente()
	{
		this.guichets = new ArrayList<Guichet>();
	}

}
