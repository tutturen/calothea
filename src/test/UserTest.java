package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Aktivitet;
import models.Gruppe;
import models.Kalender;
import models.User;

import org.junit.Test;

public class UserTest {
	
	String email = "thea@gmail.com";
	String username = "thea";
	String passord = "123";
	String navn = "thea sofie";
	

	
	@Test
	public void testConstructor(){
		User test1 = new User(email, username, passord, navn);
		
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
