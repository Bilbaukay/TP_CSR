package main;
import java.util.ArrayList;

import main.client.Gare;
import main.client.Train;
import main.client.Voyageur;
import main.serveur.AppBilleterie;

import org.restlet.Component;
import org.restlet.data.Protocol;


public class Main {

	private static Gare[] gare;
	
	private static ArrayList<Train> trains;
	
	private static ArrayList<Voyageur> voyageurs;
	
	private static int SERVER_PORT = 8888;
	
	private static int NB_TRAINS = 3;
	
	private static int NB_VOYAGEURS = 10;
	
	public static void main(String[] args) throws Exception 
	{
		//On lance le serveur de billeterie
		Component comp = new Component();
		comp.getServers().add(Protocol.HTTP,  SERVER_PORT);
		comp.getDefaultHost().attach(new AppBilleterie());
		comp.start();
		
		//On initialise le système de réservation
		ReservationSystem rs = new ReservationSystem(new Connector("http://127.0.0.1:"+SERVER_PORT));
		
		
		//On initialise les gares
		gare = new Gare[3];
		gare[0] = new Gare('A', rs);
		gare[1] = new Gare('B', rs);
		gare[2] = new Gare('C', rs);
		
		voyageurs = new ArrayList<Voyageur>();
		for(int j = 0; j < NB_VOYAGEURS; j++)
		{
			//On génère aléatoirement la gare dans laquelle le voyageur achète son billet
			int dep = (int)(Math.random()*3);
			int dest;
			
			do{
				dest = (int)(Math.random()*3);
			}while(dep == dest);
			
			Voyageur v = new Voyageur(gare[dep], j, gare[dest].getIdGare());
			voyageurs.add(v);
			v.start();
		}
		
		//On initialise les trains
		trains = new ArrayList<Train>();
		for(int i = 0; i < NB_TRAINS; i++)
		{
			//On génère aléatoirement la gare de départ et d'arrivée
			int dep;
			int arr = (int)(Math.random()*3);
			do{
				dep = (int)(Math.random()*3);
			}while(dep == arr);
			
			//On créé le train et on le démarre
			Train t = new Train(gare[dep], gare[arr], i);
			trains.add(t);
			t.start();
		}
	}
	
	public static Gare getGareById(char id)
	{
		for(int i = 0; i < gare.length; i++)
			if(gare[i].getIdGare() == id)
				return gare[i];
		
		return null;
	}

}
