import java.util.concurrent.ConcurrentHashMap;

public class mapa {
	
	private ConcurrentHashMap<coords, auto> mapa;
	/*
	 * -1 ---> inaccesible
	 *  0 ---> accesible
	 *  n --->
	 */
	private gui interfaz;
	
	mapa(int[][] estructura, auto[] autos)
	{
		interfaz = new gui(estructura);
		for (auto a : autos)
		{
			a.start();
		}
	}
}
