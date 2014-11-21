
public class Guichet {

	private static final int IMPRESSION_TICKET=100;
	
	private boolean busy = false;
	
	private EspaceQuai eq;
	
	private int nbrTickets = this.eq.getNombreTicketsVente();
		
	public boolean getBusy()
	{
		return this.busy;
	}
	
	public synchronized void vendreTicket()
	{
		if(this.nbrTickets > 0)
		{
			this.busy = true;
				try {
					Thread.sleep(IMPRESSION_TICKET);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			this.busy = false;
			this.nbrTickets--;
			for(int i=0; i<this.eq.getTrainsGare().size(); i++)
			{
				if(this.eq.getTrainsGare().get(i).getPlacesVendues() < this.eq.getTrainsGare().get(i).getPlacesLibres())
				{
					this.eq.getTrainsGare().get(i).increasePlacesVendues();
					this.eq.getTrainsGare().get(i).decreasePlacesLibres();
					return;
				}
			}
		
		}
	}
	
	
}
