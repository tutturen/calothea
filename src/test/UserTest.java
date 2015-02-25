package test;

import static org.junit.Assert.*;

import models.User;

import org.junit.Test;

public class UserTest {
	
	String email = "thea@gmail.com";
	String username = "thea";
	String passord = "123";
	String navn = "thea sofie";
	

	
	@Test
	public void testConstructor(){
		User user = new User(email, username, passord, navn);
		assertEquals(user.getEmail(), email);
		assertEquals(user.getBrukerNavn(), username);
		assertEquals(user.getNavn(), navn);
	
	}
	@Test(expected = IllegalArgumentException.class)
	public void testmailA(){
		new User("theagmail.com", username, passord, navn);
		fail();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testMailDomene(){
		new User("thea@Ggmail", username, passord, navn);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testName(){
		new User(email, username, passord, "123dj)");
		fail();
	}
		

}
