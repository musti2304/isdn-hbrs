import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import junit.framework.TestCase;


/*
 * Demo Klasse zur Aufgabe 3.1 von lect08
 */
public class DemoTest extends TestCase {
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Before Class");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("After Class");
	}

	@Before
	public void setUp() {
		System.out.println("setUp");
	}

	@After
	public void tearDown() {
		System.out.println("tearDown");
	}

	@Test
	public void ersterTest() {
		System.out.println("Erster Test");
	}

	@Test
	public void zweiterTest() {
		System.out.println("Zweiter Test");
	}
}