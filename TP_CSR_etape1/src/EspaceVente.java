public class EspaceVente {
	
	private Guichet guichet;
	
	private Gare gare;
	
	public Guichet getGuichet()
	{
		return this.guichet;
	}
	
	public EspaceVente(Gare gare)
	{
		//Coucou
		this.gare = gare;
		this.guichet = new Guichet();
	}

}
