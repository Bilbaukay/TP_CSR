package client;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class EspaceVente {
	
	private HashMap<Guichet, Boolean> guichets;
	
	private Gare gare;
	
	private ReservationSystem rs;
	
	public static final int NOMBRE_GUICHETS = 3;
	
	public EspaceVente(Gare gare)
	{
		this.gare = gare;
		this.guichets = new HashMap<Guichet, Boolean>();
		
		for(int i=0; i < EspaceVente.NOMBRE_GUICHETS; i++)
			this.guichets.put(new Guichet(i, this.gare), false);
	}
	
	public synchronized Guichet reserverGuichet()
	{
		Guichet guichet = this.getGuichetFree();
		
		while(guichet == null)
		{
			
			try {
				
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			guichet = this.getGuichetFree();
		}
		
		this.busyGuichet(guichet);
		
		return guichet;
	}
	
	public synchronized Guichet getGuichetFree()
	{
		Iterator<Entry<Guichet, Boolean>> it = this.guichets.entrySet().iterator();
		
		while(it.hasNext())
		{
			Entry<Guichet, Boolean> temp = it.next();
			if(temp.getValue()==false)
				return temp.getKey();
		}
		
		return null;
	}
	
	public synchronized void freeGuichet(Guichet guichet)
	{
		System.out.println("Le guichet "+guichet.getId()+" est libéré.");
		this.guichets.put(guichet, false);
		this.notify();
	}
	
	public synchronized void busyGuichet(Guichet guichet)
	{
		System.out.println("Le guichet "+guichet.getId()+" est occupé.");
		this.guichets.put(guichet, true);
	}

}
