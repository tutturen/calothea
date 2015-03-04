package tests;

import static org.junit.Assert.*;

import models.User;

import org.junit.Test;

public class UserTest {
	
	int id = 5;
	String email = "thea@gmail.com";
	String rolle = "Utvikler";
	String passord = "123";
	String navn = "Thea Sofie";
	

	
	@Test
	public void testConstructor(){
		User user = new User(id, email, navn, rolle);
		assertEquals(user.getId(), id);
		assertEquals(user.getEmail(), email);
		assertEquals(user.getRole(), rolle);
		assertEquals(user.getName(), navn);
	
	}
	@Test(expected = IllegalArgumentException.class)
	public void testmailA(){
		new User(id, "theagmail.com", navn, rolle);
		fail();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testMailDomene(){
		new User(id, "thea@Ggmail", navn, rolle);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testName(){
		new User(id, email, "123dj)", rolle);
		fail();
	}
		

}
