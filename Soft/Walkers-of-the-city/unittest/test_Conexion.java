import junit.framework.TestCase;


public class test_Conexion extends TestCase {
	Conexion c;

	public void setUp()
	{
		c = new Conexion();
		
	}
	
	public void testconectar() {
		//fail("Not yet implemented");
		c.conectar("localhost", 5000);
		assertEquals("no hay conexion", 0, c.getSocket().getPort());
	}
	/*De no producirse la conexion el puerto en el socket queda como 0
	 getPort() Returns:
		the remote port number to which this socket is connected,
	    or 0 if the socket is not connected yet.*/

	
}