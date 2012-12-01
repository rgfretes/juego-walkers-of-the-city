public class main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
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
		System.out.println("tome"+mon.getEstado()[12]);
		mon.reservar_recursos(autos[0], "abc");
		 System.out.println("tome"+mon.getEstado()[12]);
		mon.return_recurso(autos[0], "abc");
		System.out.println("devolvi"+mon.getEstado()[12]);
		
		
	}

}
