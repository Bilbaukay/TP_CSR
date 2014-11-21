
public class Guichet {

	private static final int IMPRESSION_TICKET=100;
	
	private EspaceQuai eq;
	
	public synchronized void vendreTicket()
	{
		try {
			Thread.sleep(IMPRESSION_TICKET);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
			
	}
	
	
}
