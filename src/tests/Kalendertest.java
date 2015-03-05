package tests;

import static org.junit.Assert.*;
import models.Aktivitet;
import models.Kalender;
import models.User;

import java.util.ArrayList;
import java.util.Date;

import models.Rom;

import org.junit.Test;

@SuppressWarnings(value={"deprecation"})
public class Kalendertest {
	Rom rom;
	Date startDate1 = new Date(2015, 3, 12, 12, 0);
	Date endDate1 = new Date(2015, 3, 12, 13, 0);
	Date startDate2 = new Date(2015, 3, 12, 14, 0);
	Date endDate2 = new Date(2015, 3, 12, 15, 0);
	Date startDate3 = new Date(2015, 3, 12, 16, 0);
	Date endDate3 = new Date(2015, 3, 12, 17, 0);
	Date testDate1 = new Date(2015, 3, 12, 11, 0);
	Date testDate2 = new Date(2015, 3, 12, 15, 30);
	User eier = new User(24, "simhellem@gmail.com", "Simen Hellem", "Utvikler");
	String navn = "kal";
	int ID = 3;
	
	
	Aktivitet aktivitet1 = new Aktivitet(eier, navn, startDate1, endDate1);
	Aktivitet aktivitet2 = new Aktivitet(eier, navn, startDate2, endDate2);
	Aktivitet aktivitet3 = new Aktivitet(eier, navn, startDate3, endDate3);
	
	ArrayList<Aktivitet> aktiviteter = new ArrayList<Aktivitet>();
	
	public void fillList(){
		aktiviteter.add(aktivitet1);
		aktiviteter.add(aktivitet2);
		aktiviteter.add(aktivitet3);
	}
	
	

	@Test
	public void testConstructor(){
		fillList();
		Kalender kalender = new Kalender(aktiviteter, navn, ID);
		assertEquals(kalender.getAktivitetListe(), aktiviteter);
		assertEquals(kalender.getKalenderID(), ID);
		assertEquals(kalender.getNavn(), navn);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSlettAkt(){
		fillList();
		Kalender kalender = new Kalender(aktiviteter, navn, ID);
		kalender.fjernAktivitet(aktivitet1);
		kalender.fjernAktivitet(aktivitet1);
		fail();
	}
	
	@Test
	public void getAktOnTime(){
		fillList();
		Kalender kalender = new Kalender(aktiviteter, navn, ID);
		ArrayList<Aktivitet> test = new ArrayList<Aktivitet>();
		test.add(aktivitet1);
		test.add(aktivitet2);
		
		assertEquals(test, kalender.getAktiviteter(testDate1, testDate2) ); 
		
	}
	
	
	
	
	

}
