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
		auto[] autos = new auto[2];
		monitor monit= new monitor();
		
		autos[0] = new auto(map, 5, 4, 400, false, posiciones.length, 0,monit);
		autos[1] = new auto(map, 2, 6, 670, true, posiciones.length, 1,monit);
		/*autos[2] = new auto(map, 5, 11, 340, false, posiciones.length, 2,monit);
		autos[3] = new auto(map, 2, 5, 440, true, posiciones.length, 3,monit);
		autos[4] = new auto(map, 6, 2, 310, false, posiciones.length, 4,monit);*/
		
		map.place(autos);
		monitor mon=new monitor();
		System.out.print ("10"+mon.getMatriz_incidencia()[1][0]+" 01"+mon.getMatriz_incidencia()[0][1]);
		
	}

}
