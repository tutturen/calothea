package models;

public class Invitation {
	
	Aktivitet aktivitet;
	String melding;
	boolean accepted;
	//Do we need to have a user here?

	//Opprettes when you receive the user is added to an activity
	public Invitation(Aktivitet aktivitet) {
		this.aktivitet = aktivitet;
		this.melding = "Du er invitert til avtale klokken:" + aktivitet.getStartDate().toString() + "Slutt:" + aktivitet.getEndDate().toString() + "i rom:" + aktivitet.getRom().getRomnavn();
		this.accepted = false;
		
		
	}
	
	public Aktivitet getAktivitet(){
		return aktivitet;
		
	}
	public String getMelding(){
		return melding;
		
	}
	
	//Hvis denne kan brukes til aa akkseptere invitasjon
	public void acceptInvitation(){
		this.accepted = true;
	}
	
	
}
