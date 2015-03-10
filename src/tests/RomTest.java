package tests;

import static org.junit.Assert.*;
import models.Rom;

import org.junit.Test;

public class RomTest {
	
	private int legalMaxAntall = 25;
	private int legalRomNr = 401;
	private String legalRomName = "Rom 401";

	@Test
	public void testConstructor() {
		Rom rom = new Rom(legalRomName, legalRomNr, legalMaxAntall);
		assertEquals(rom.getAntall(), legalMaxAntall);
		assertEquals(rom.getName(), legalRomName);
		assertEquals(rom.getRomNr(), legalRomNr);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testZeroNr() {
	    new Rom(legalRomName, 0, legalMaxAntall);
	    fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeNr() {
	    new Rom(legalRomName, -1, legalMaxAntall);
	    fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testZeroMaxAntall() {
		new Rom(legalRomName, legalRomNr, 0);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNegativeMaxAntall() {
		new Rom(legalRomName, legalRomNr, -1);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNullName() {
		new Rom(null, legalRomNr, legalMaxAntall);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShortName() {
		new Rom("", legalRomNr, legalMaxAntall);
		fail();
	}
	
	

}
