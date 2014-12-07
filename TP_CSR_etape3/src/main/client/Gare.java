package main.client;

import main.ReservationSystem;


public class Gare  {
	
	private EspaceQuai eq;
	
	private EspaceVente ev;
	
	private char idGare;
	
	private ReservationSystem rs;

	public Gare(char id, ReservationSystem rs)
	{
		this.idGare = id;
		this.rs = rs;
		this.eq = new EspaceQuai(this);
		this.ev = new EspaceVente(this);
	}
	
	public EspaceQuai getEq() {
		return this.eq;
	}

	public EspaceVente getEv() {
		return this.ev;
	}
	
	public char getIdGare()
	{
		return this.idGare;
	}
	
	public ReservationSystem getReservationSystem()
	{
		return this.rs;
	}

}