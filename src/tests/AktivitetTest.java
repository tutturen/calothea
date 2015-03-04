package tests;

import static org.junit.Assert.*;

import java.util.Date;

import models.Aktivitet;
import models.Rom;
import models.User;

import org.junit.Test;

public class AktivitetTest {

	User owner = new User(12, "jon@gmail.com", "Jon", "Systemutvikler");
	
	User otherUser1 = new User(13, "gryndir@gmail.com", "Gryndir Bekkeløg", "Produktbehandler");
	User otherUser2 = new User(4, "kari@gmail.com", "Kari Krymnagaard", "Sjef");


	Date startDate = new Date(System.currentTimeMillis() + 5000L);
	Date endDate = new Date(System.currentTimeMillis() + 10000L);
	Rom rom = new Rom("Rom 401", 401, 13);
	Rom smallRom = new Rom("Lite rom", 25, 2);
	
	@Test
	public void testConstructor() {
		Aktivitet aktivitet = new Aktivitet(owner, startDate, endDate, rom);
		assertEquals(aktivitet.getBrukereInvitert().size(), 0);
		assertEquals(aktivitet.getDeltagere().size(), 1);
		assertEquals(aktivitet.getEier(), owner);
		assertEquals(aktivitet.getEndDate(), endDate);
		assertEquals(aktivitet.getStartDate(), startDate);
		assertEquals(aktivitet.getRom(), rom);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testCantInviteOwner() {
		Aktivitet aktivitet = new Aktivitet(owner, startDate, endDate, rom);
		aktivitet.addToInvitedList(owner);
	}
	
	@Test
	public void testSendInvite() {
		Aktivitet aktivitet = new Aktivitet(owner, startDate, endDate, rom);
		aktivitet.addToInvitedList(otherUser1);
		assertEquals(aktivitet.getBrukereInvitert().size(), 1);
		aktivitet.addToInvitedList(otherUser2);
		assertEquals(aktivitet.getBrukereInvitert().size(), 2);
		assertEquals(aktivitet.getBrukereInvitert().contains(otherUser1), true);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEndDateBeforeStartDate() {
		new Aktivitet(owner, endDate, startDate, rom);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testInviteTooMany() {
		Aktivitet aktivitet = new Aktivitet(owner, startDate, endDate, smallRom);
		aktivitet.addToInvitedList(otherUser1);
		assertTrue(aktivitet.getBrukereInvitert().contains(otherUser1));
		aktivitet.addToInvitedList(otherUser1);
	}
	
	@Test
	public void testAcceptInvite() {
		Aktivitet aktivitet = new Aktivitet(owner, startDate, endDate, rom);
		aktivitet.addToInvitedList(otherUser1);
		assertEquals(aktivitet.getBrukereInvitert().size(), 1);
		assertEquals(aktivitet.getDeltagere().size(), 1);
		aktivitet.acceptInvitation(otherUser1);
		assertEquals(aktivitet.getBrukereInvitert().size(), 0);
		assertEquals(aktivitet.getDeltagere().size(), 2);
	}
	
	@Test
	public void testRemoveInvite() {
		Aktivitet aktivitet = new Aktivitet(owner, startDate, endDate, rom);
		aktivitet.addToInvitedList(otherUser1);
		assertEquals(aktivitet.getBrukereInvitert().contains(otherUser1), true);
		
		aktivitet.removeFromInvitedList(otherUser1);
		assertEquals(aktivitet.getBrukereInvitert().contains(otherUser1), false);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testRemoveOwnerInvite() {
		Aktivitet aktivitet = new Aktivitet(owner, startDate, endDate, rom);
		aktivitet.removeFromInvitedList(owner);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testRemoveNonExistentInvite() {
		Aktivitet aktivitet = new Aktivitet(owner, startDate, endDate, rom);
		aktivitet.removeFromInvitedList(otherUser1);
	}
	
	
	
	
}
