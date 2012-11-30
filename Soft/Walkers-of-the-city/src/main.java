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
		auto[] autos = new auto[5];
		
		autos[0] = new auto(map, 6, 2, 70, false, posiciones.length, 0);
		autos[1] = new auto(map, 2, 6, 70, true, posiciones.length, 1);
		autos[2] = new auto(map, 5, 11, 40, false, posiciones.length, 2);
		autos[3] = new auto(map, 2, 5, 40, true, posiciones.length, 3);
		autos[4] = new auto(map, 6, 2, 30, false, posiciones.length, 4);
		
		map.place(autos);
		monitor mon=new monitor();
		System.out.print ("10"+mon.getMatriz_incidencia()[1][0]+" 01"+mon.getMatriz_incidencia()[0][1]);
		
	}

}
