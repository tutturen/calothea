package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Aktivitet;
import models.Gruppe;
import models.Kalender;
import models.User;

import org.junit.Test;

public class GruppeTest {
	
	String gruppeNavn = "Gruppe 47";
	String gruppeNavn2 = "Subgruppe Gruppe 47 KTN";
	String gruppeNavn3 = "Subgruppe Gruppe 47 DB";
	String kalenderNavn = "Gruppe 47 sin kalender";
	String kalenderNavn2 = "Gruppe 47 sin KTN-kalender";
	String kalenderNavn3 = "Gruppe 47 sin DB-kalender";
	int kalenderID = 5;
	int kalenderID2 = 6;
	int kalenderID3 = 6;
	ArrayList<Aktivitet> aktiviteter = new ArrayList<Aktivitet>();
	ArrayList<Aktivitet> aktiviteter2 = new ArrayList<Aktivitet>();
	ArrayList<Aktivitet> aktiviteter3 = new ArrayList<Aktivitet>();
	
	User user1 = new User("thea@gmail.com", "thea", "123", "thea sofie");
	User user2 = new User("kari@gmail.com", "Kari", "jomsemari", "Kari Krymnagaard");
	
	@Test
	public void testConstructor(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		assertEquals(gruppe.getGruppeNavn(), gruppeNavn);
		assertEquals(gruppe.getGruppeMedlemmer().size(), 0);
		assertEquals(gruppe.getSubgrupper().size(), 0);
		assertEquals(gruppe.getKalender(), kalender);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBlankName(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		new Gruppe(kalender, "");
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullName(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		new Gruppe(kalender, null);
		fail();
	}
	
	@Test
	public void testAddMedlem(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		gruppe.addMedlem(user1);
		assertEquals(gruppe.getGruppeMedlemmer().size(), 1);
		gruppe.addMedlem(user2);
		assertEquals(gruppe.getGruppeMedlemmer().size(), 2);
		assertEquals(gruppe.getGruppeMedlemmer().contains(user1), true);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAddMedlemAlreadyInGroup(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		gruppe.addMedlem(user1);
		gruppe.addMedlem(user1);
		fail();
	}

	@Test
	public void testRemoveMedlem(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		gruppe.addMedlem(user1);
		gruppe.addMedlem(user2);
		assertEquals(gruppe.getGruppeMedlemmer().size(), 2);
		assertEquals(gruppe.getGruppeMedlemmer().contains(user1), true);
		assertEquals(gruppe.getGruppeMedlemmer().contains(user2), true);
		gruppe.removeMedlem(user2);
		assertEquals(gruppe.getGruppeMedlemmer().size(), 1);
		assertEquals(gruppe.getGruppeMedlemmer().contains(user2), false);
		gruppe.removeMedlem(user1);
		assertEquals(gruppe.getGruppeMedlemmer().size(), 0);
		assertEquals(gruppe.getGruppeMedlemmer().contains(user1), false);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testRemoveMedlemNotInGroup(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		gruppe.removeMedlem(user1);
		fail();
	}
	
	@Test
	public void testSetKalender(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		gruppe.setKalender(kalender);
		assertEquals(gruppe.getKalender(), kalender);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetGruppeNavn(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		gruppe.setGruppeNavn("Gruppe 47");
		assertEquals(gruppe.getGruppeNavn(), "Gruppe 47");
		gruppe.setGruppeNavn("");
		fail();	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetGruppeNavn2(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		gruppe.setGruppeNavn(null);
		fail();	
	}

	@Test
	public void testAddSubGruppe(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Kalender kalender2 = new Kalender(aktiviteter2, kalenderNavn2, kalenderID2);
		Kalender kalender3 = new Kalender(aktiviteter3, kalenderNavn3, kalenderID3);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		Gruppe subgruppe = new Gruppe(kalender2, gruppeNavn2);
		Gruppe subgruppe2 = new Gruppe(kalender3, gruppeNavn3);
		gruppe.addSubGruppe(subgruppe);
		assertEquals(gruppe.getSubgrupper().size(), 1);
		gruppe.addSubGruppe(subgruppe2);
		assertEquals(gruppe.getSubgrupper().size(), 2);
		assertEquals(gruppe.getSubgrupper().contains(subgruppe), true);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAddSubGruppeAlreadyInGroup(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Kalender kalender2 = new Kalender(aktiviteter2, kalenderNavn2, kalenderID2);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		Gruppe subgruppe = new Gruppe(kalender2, gruppeNavn2);
		gruppe.addSubGruppe(subgruppe);;
		gruppe.addSubGruppe(subgruppe);;
		fail();
	}

	@Test
	public void testRemoveSubGroup(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Kalender kalender2 = new Kalender(aktiviteter2, kalenderNavn2, kalenderID2);
		Kalender kalender3 = new Kalender(aktiviteter3, kalenderNavn3, kalenderID3);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		Gruppe subgruppe = new Gruppe(kalender2, gruppeNavn2);
		Gruppe subgruppe2 = new Gruppe(kalender3, gruppeNavn3);
		gruppe.addSubGruppe(subgruppe);;
		gruppe.addSubGruppe(subgruppe2);;
		assertEquals(gruppe.getSubgrupper().size(), 2);
		assertEquals(gruppe.getSubgrupper().contains(subgruppe), true);
		assertEquals(gruppe.getSubgrupper().contains(subgruppe2), true);
		gruppe.removeSubGruppe(subgruppe2);
		assertEquals(gruppe.getSubgrupper().size(), 1);
		assertEquals(gruppe.getSubgrupper().contains(subgruppe2), false);
		gruppe.removeSubGruppe(subgruppe);
		assertEquals(gruppe.getSubgrupper().size(), 0);
		assertEquals(gruppe.getSubgrupper().contains(subgruppe), false);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testRemoveSubGroupNotInGroup(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Gruppe gruppe = new Gruppe(kalender, gruppeNavn);
		gruppe.removeSubGruppe(gruppe);
		fail();
	}
		
}
