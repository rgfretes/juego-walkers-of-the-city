public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] posiciones = {{-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1},
				              {-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1},
				              {-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1},
				              {-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1},
				              {-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1},
				              { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				              { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				              {-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1},
				              {-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1},
				              {-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1},
				              {-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1},
				              {-1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1}};
		gui interfaz = new gui(posiciones);

		auto a = new auto(interfaz, 11, 6, 100, true, posiciones.length);
		a.start();
		
		auto b = new auto(interfaz, 0, 5, 1200, true, posiciones.length);
		b.start();

		auto c = new auto(interfaz, 11, 6, 2000, true, posiciones.length);
		c.start();

		auto d = new auto(interfaz, 5, 0, 1000, false, posiciones.length);
		d.start();

		auto e = new auto(interfaz, 6, 11, 1150, false, posiciones.length);
		e.start();
	}

}
