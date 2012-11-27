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
		
		mapa map = new mapa(posiciones);
		auto[] autos = new auto[1];
		
		autos[0] = new auto(map, 11, 6, 500, true, posiciones.length, 0);
		/*autos[1] = new auto(map, 0, 5, 1200, true, posiciones.length, 1);
		autos[2] = new auto(map, 11, 6, 2000, true, posiciones.length, 2);
		autos[3] = new auto(map, 5, 0, 1000, false, posiciones.length, 3);
		autos[4] = new auto(map, 6, 11, 1150, false, posiciones.length, 4);*/
		
		map.place(autos);
	}

}
