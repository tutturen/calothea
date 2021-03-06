package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Aktivitet;
import models.Group;
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
	
	User user1 = new User(2, "thea@gmail.com", "Thea Sofie", "Prosjektleder");
	User user2 = new User(3, "kari@gmail.com", "Kari Krymnagaard", "Assistent");
	
	@Test
	public void testConstructor(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Group gruppe = new Group(kalender, gruppeNavn);
		assertEquals(gruppe.getName(), gruppeNavn);
		assertEquals(gruppe.getMembers().size(), 0);
		assertEquals(gruppe.getSubgrupper().size(), 0);
		assertEquals(gruppe.getKalender(), kalender);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBlankName(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		new Group(kalender, "");
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullName(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		new Group(kalender, null);
		fail();
	}
	
	@Test
	public void testAddMedlem(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Group gruppe = new Group(kalender, gruppeNavn);
		gruppe.addMedlem(user1);
		assertEquals(gruppe.getMembers().size(), 1);
		gruppe.addMedlem(user2);
		assertEquals(gruppe.getMembers().size(), 2);
		assertEquals(gruppe.getMembers().contains(user1), true);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testAddMedlemAlreadyInGroup(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Group gruppe = new Group(kalender, gruppeNavn);
		gruppe.addMedlem(user1);
		gruppe.addMedlem(user1);
		fail();
	}

	@Test
	public void testRemoveMedlem(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Group gruppe = new Group(kalender, gruppeNavn);
		gruppe.addMedlem(user1);
		gruppe.addMedlem(user2);
		assertEquals(gruppe.getMembers().size(), 2);
		assertEquals(gruppe.getMembers().contains(user1), true);
		assertEquals(gruppe.getMembers().contains(user2), true);
		gruppe.removeMedlem(user2);
		assertEquals(gruppe.getMembers().size(), 1);
		assertEquals(gruppe.getMembers().contains(user2), false);
		gruppe.removeMedlem(user1);
		assertEquals(gruppe.getMembers().size(), 0);
		assertEquals(gruppe.getMembers().contains(user1), false);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testRemoveMedlemNotInGroup(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Group gruppe = new Group(kalender, gruppeNavn);
		gruppe.removeMedlem(user1);
		fail();
	}
	
	@Test
	public void testSetKalender(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Group gruppe = new Group(kalender, gruppeNavn);
		gruppe.setKalender(kalender);
		assertEquals(gruppe.getKalender(), kalender);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetGruppeNavn(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Group gruppe = new Group(kalender, gruppeNavn);
		gruppe.setGruppeNavn("Gruppe 47");
		assertEquals(gruppe.getName(), "Gruppe 47");
		gruppe.setGruppeNavn("");
		fail();	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetGruppeNavn2(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Group gruppe = new Group(kalender, gruppeNavn);
		gruppe.setGruppeNavn(null);
		fail();	
	}

	@Test
	public void testAddSubGruppe(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Kalender kalender2 = new Kalender(aktiviteter2, kalenderNavn2, kalenderID2);
		Kalender kalender3 = new Kalender(aktiviteter3, kalenderNavn3, kalenderID3);
		Group gruppe = new Group(kalender, gruppeNavn);
		Group subgruppe = new Group(kalender2, gruppeNavn2);
		Group subgruppe2 = new Group(kalender3, gruppeNavn3);
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
		Group gruppe = new Group(kalender, gruppeNavn);
		Group subgruppe = new Group(kalender2, gruppeNavn2);
		gruppe.addSubGruppe(subgruppe);;
		gruppe.addSubGruppe(subgruppe);;
		fail();
	}

	@Test
	public void testRemoveSubGroup(){
		Kalender kalender = new Kalender(aktiviteter, kalenderNavn, kalenderID);
		Kalender kalender2 = new Kalender(aktiviteter2, kalenderNavn2, kalenderID2);
		Kalender kalender3 = new Kalender(aktiviteter3, kalenderNavn3, kalenderID3);
		Group gruppe = new Group(kalender, gruppeNavn);
		Group subgruppe = new Group(kalender2, gruppeNavn2);
		Group subgruppe2 = new Group(kalender3, gruppeNavn3);
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
		Group gruppe = new Group(kalender, gruppeNavn);
		gruppe.removeSubGruppe(gruppe);
		fail();
	}
		
}
