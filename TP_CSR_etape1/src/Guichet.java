
public class Guichet {

	private static final int IMPRESSION_TICKET=500;
	
	public synchronized void vendreTicket()
	{
		try {
			Thread.sleep(IMPRESSION_TICKET);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
			
	}
	
	
}
