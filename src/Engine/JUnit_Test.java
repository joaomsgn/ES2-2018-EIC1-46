package Engine;

import org.junit.Test;



public class JUnit_Test {

	@Test
	public void testLogIn() {
		new LogIn(new Worker());
	}

	@Test
	public void testToString() {
		new LogIn(new Worker()).toString();
	}
	
	@Test
	public void testMail() {
		Mail mail = new Mail();
	}

	@Test
	public void testSetValues() {
		new Mail().setValues("isctee@gmail.com", "isctee@gmail.com", "isctee@gmail.com", "isctee@gmail.com");
	}
	
	@Test
	public void testPerformTask() {
		Mail m = new Mail();
		m.setValues("isctee@gmail.com", "isctee@gmail.com", "isctee@gmail.com", "isctee@gmail.com");
		m.performTask();
	}

	@Test
	public void testUser() {
		new User("teste","teste");
	}

	@Test
	public void testGetMail() {
		User u = new User("teste","teste");
		u.setMail("teste");
		u.getMail();
	}

	@Test
	public void testSetMail() {
		User u = new User("teste","teste");
		u.setMail("teste");
	}

	@Test
	public void testGetPassword() {
		User u = new User("teste","teste");
		u.setPassword("teste");
		u.getPassword();
	}

	@Test
	public void testSetPassword() {
		User u = new User("teste","teste");
		u.setPassword("teste");
	}

	@Test
	public void testIsOnline() {
		User u = new User("teste","teste");
		u.isOnline();
	}

	@Test
	public void testSetOnline() {
		User u = new User("teste","teste");
		u.setOnline(true);
	}
	
}
