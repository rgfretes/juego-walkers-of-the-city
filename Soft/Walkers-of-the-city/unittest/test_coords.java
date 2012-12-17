import junit.framework.TestCase;


public class test_coords extends TestCase {
	coords c;

	public void setUp()
	{
		c = new coords(1,1);
	}
	
	public void testSet_posx() {
		//fail("Not yet implemented");
		c.set_posx(2);
		assertEquals("La coordenada x deberia ser igual a 2", 2, c.get_posx());
	}

	public void testSet_posy() {
		//fail("Not yet implemented");
		c.set_posy(2);
		assertEquals("La coordenada x deberia ser igual a 2", 2, c.get_posy());
	}
	
}
