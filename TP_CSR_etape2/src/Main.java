
public class Main {

	public static void main(String[] args) 
	{
		Gare gare = new Gare();
		EspaceQuai q = gare.getEq();
		
		// Instanciation de la hashtable du nombre de places libres dans chaque train en fonction de l'idTrain
		for(int i=0; i<q.getTrainsGare().size(); i++)
			q.getPlacesLibres().put(i, q.getTrainsGare().get(i).getPlacesLibres()); 
		
		gare.start();
	}

}
