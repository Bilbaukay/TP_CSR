import serveur.ServeurBilleterie;


public class Main {

	public static void main(String[] args) 
	{
		Gare[] gare = new Gare[3];
		gare[0] = new Gare('A');
		gare[1] = new Gare('B');
		gare[2] = new Gare('C');
		
		ServeurBilleterie sb = new ServeurBilleterie();
		
		gare[0].setServeurBilleterie(sb);
	}

}
