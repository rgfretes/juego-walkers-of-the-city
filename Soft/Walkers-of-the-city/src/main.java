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
		auto[] autos = new auto[20];
		monitor monit= new monitor();
		
		autos[0] = new auto(map, 5, 8, 310, false, posiciones.length, 0,monit);
		autos[1] = new auto(map, 2, 6, 367, true, posiciones.length, 1,monit);
		autos[2] = new auto(map, 5, 11, 344, false, posiciones.length, 2,monit);
		autos[3] = new auto(map, 2, 5, 344, true, posiciones.length, 3,monit);
		autos[4] = new auto(map, 6, 2, 321, false, posiciones.length, 4,monit);
		autos[5] = new auto(map, 5, 8, 630, false, posiciones.length, 5,monit);
		autos[6] = new auto(map, 2, 6, 637, true, posiciones.length, 6,monit);
		autos[7] = new auto(map, 5, 11, 344, false, posiciones.length, 7,monit);
		autos[8] = new auto(map, 2, 5, 434, true, posiciones.length, 8,monit);
		autos[9] = new auto(map, 6, 2, 313, false, posiciones.length, 9,monit);
		autos[10] = new auto(map, 5, 8, 60, false, posiciones.length, 10,monit);
		autos[11] = new auto(map, 2, 6, 602, true, posiciones.length, 11,monit);
		autos[12] = new auto(map, 5, 11, 30, false, posiciones.length, 12,monit);
		autos[13] = new auto(map, 2, 5, 44, true, posiciones.length, 13,monit);
		autos[14] = new auto(map, 6, 2, 30, false, posiciones.length, 14,monit);
		autos[15] = new auto(map, 5, 8, 60, false, posiciones.length, 15,monit);
		autos[16] = new auto(map, 2, 6, 60, true, posiciones.length, 16,monit);
		autos[17] = new auto(map, 5, 11, 30, false, posiciones.length, 17,monit);
		autos[18] = new auto(map, 2, 5, 40, true, posiciones.length, 18,monit);
		autos[19] = new auto(map, 6, 2, 30, false, posiciones.length, 19,monit);
		
		map.place(autos);
		//monitor mon=new monitor();
		
	}

}
