import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author usuario
 * @version 1.0
 * @created 28-nov-2012 03:53:09 p.m.
 */
public class monitor {

	private Cola[] acciones;
	private ReentrantLock lock;
	private static int[][] matriz_incidencia=
		  {{0,0,1,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,0,0,0},
		   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,-1,0,0,0,0,0},
		   {0,1,0,0,0,0,0,0,0,0,0,0,0,1,-1,0,0,0,0,0,0,0,0,0},
		   {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,-1,0,0,0,0},
		   {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0},
		   {0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,-1,0,0,0,0,0,0,0,0},
		   {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,-1},
		   {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,-1,0,0},
		   {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0},
		   {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,-1,0},
		   {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,-1,0,0,0,0,0,0},
		   {0,0,0,0,0,0,0,0,0,0,0,1,0,-1,0,0,0,0,0,0,0,0,0,0},
		   {-1,-1,-1,0,0,0,0,0,-1,0,-1,-1,1,0,1,0,0,0,1,0,0,0,0,0},
		   {0,-1,-1,-1,-1,-1,0,0,0,0,0,-1,0,0,0,1,0,0,0,1,1,0,0,0},
		   {0,0,-1,0,-1,-1,-1,-1,-1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1},
		   {0,0,0,0,0,-1,0,-1,-1,-1,-1,-1,0,1,0,0,0,1,0,0,0,0,1,0}};
	public int[][] getMatriz_incidencia() {
		return matriz_incidencia;
	}





	private int numero_antiguedad;
	private int[] estado;
	public int[] getEstado() {
		return estado;
	}





	private HashMap<String,Integer> relacion_transicion_recurso_retornado;
	private HashMap<String,Integer> relacion_transicion_secuencia;
	private int[][] relacion_recurso_secuencia=
		   {{1,0,0,0},
			{1,1,0,0},
			{1,1,1,0},
			{0,1,0,0},
			{0,1,1,0},
			{0,1,1,1},
			{0,0,1,0},
			{0,0,1,1},
			{1,0,1,1},
			{0,0,0,1},
			{1,0,0,1},
			{1,1,0,1}};
	public monitor(){
		lock=new ReentrantLock();
		acciones=new Cola[12];
		for(int i=0;i<12;i++)
			acciones[i]=new Cola(i%3);
		numero_antiguedad=0;
		int []estado={0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1};
		this.estado=estado;
		relacion_transicion_secuencia= new HashMap<String, Integer>();
		relacion_transicion_recurso_retornado= new HashMap<String, Integer>();
		relacion_transicion_secuencia.put("ab",  1);
		relacion_transicion_secuencia.put("abc", 2);
		relacion_transicion_secuencia.put("b",   3);
		relacion_transicion_secuencia.put("bc",  4);
		relacion_transicion_secuencia.put("bcd", 5);
		relacion_transicion_secuencia.put("c",   6);
		relacion_transicion_secuencia.put("cd",  7);
		relacion_transicion_secuencia.put("cda", 8);
		relacion_transicion_secuencia.put("d",   9);
		relacion_transicion_secuencia.put("da", 10);
		relacion_transicion_secuencia.put("dab",11); // no falta a ?? 
		relacion_transicion_recurso_retornado.put("abc", 12);
		relacion_transicion_recurso_retornado.put("dab", 13);
		relacion_transicion_recurso_retornado.put("ab",  14);
		relacion_transicion_recurso_retornado.put("b",   15);
		relacion_transicion_recurso_retornado.put("cda", 16);
		relacion_transicion_recurso_retornado.put("da",  17);
		relacion_transicion_recurso_retornado.put("a",   18);
		relacion_transicion_recurso_retornado.put("bc",  19);
		relacion_transicion_recurso_retornado.put("bcd", 20);
		relacion_transicion_recurso_retornado.put("cd",  21);
		relacion_transicion_recurso_retornado.put("d",   22);
		relacion_transicion_recurso_retornado.put("c",   23);

		}
		
		
		
							
		

	

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param auto_llamador
	 * @param secuencia_recurso
	 */
	public void return_recurso(auto auto_llamador, String secuencia_recurso){
		lock.lock();
		int tran=relacion_transicion_recurso_retornado.get(secuencia_recurso);
		for(int i=0;i<16;i++){
			estado[i]=estado[i]+matriz_incidencia[i][tran];
		}
		int[] disps=get_recursos_disponibles();
		int inanib=buscar_inanibido();
		
		if(inanib!=15){
			
			if(check_liberacion_inanibido(inanib))
			{
				acciones[inanib].eliminar().notify();
				return;
			}
			disps=recalcular_disponibles_con_inanibido(disps,inanib);
		}
		
		int[] aux =obtener_acciones_posibles(disps);
		if(aux==null)
		{
			lock.unlock();
			return;
		}
		Cola xua=get_cola_max_prioridad(aux);
		xua.eliminar().notify();
		return;
		}

	/**
	 * 
	 * @param auto_llamador
	 * @param secuencia
	 
	 */
	public void reservar_recursos(auto auto_llamador, String secuencia) throws InterruptedException{
		lock.lock();
		int tran=relacion_transicion_secuencia.get(secuencia);
		sacar_numero(auto_llamador);
		int[] disps=get_recursos_disponibles();
		int inanib=buscar_inanibido();
		
		if(inanib!=15){
			
			disps=recalcular_disponibles_con_inanibido(disps,inanib);
		}
			
		
		if(check_recursos_disponibles(disps, tran))
			tomar_recursos(tran);
		else{
			acciones[tran].adicionar(auto_llamador);
			//lock.unlock();
			wait();
			tomar_recursos(tran);
			}
		}



	private boolean check_recursos_disponibles(int[] disps, int tran) {
		boolean aux=true;
		for(int i=0;i<4;i++){
			if(relacion_recurso_secuencia[tran][i]==1)
				if(disps[i]==0)
					aux=false;
		}
		return aux;
	}

	/**
	 * 
	 * @param inanibidos
	 */


	private int buscar_inanibido(){
		int a=15;
		for(int i = 0;i<12;i++)
		{
			if(!acciones[i].esVacia()){
				if(acciones[i].get_primer().get_numero_antiguedad() < (numero_antiguedad-10)){
					if(a==15)a=i;
					else
						if(acciones[i].get_primer().get_numero_antiguedad() < acciones[a].get_primer().get_numero_antiguedad())
							a=i;
				
			
				}	
			}
			
		}	
		return a;
	}

	/**
	 * 
	 * @param recursos
	 */

	/**
	 * 
	 * @param inanibido
	 */
	private Boolean check_liberacion_inanibido(int inanibido){
		return null;
	}

	private int[] get_recursos_disponibles(){
		int[] aux={estado[12],estado[13],estado[14],estado[15]};
		return aux;
	}

	/**
	 * 
	 * @param disps
	 */
	private int[] obtener_acciones_posibles(int[] disps){
		int[]posibles = null;
		int xua=0,aux;
		for(int i=0;i<12;i++){
			 aux=1;
			for (int j=0;j<4;j++){
				if(relacion_recurso_secuencia[i][j]==1)
					if(disps[j]==0)
						aux=0;
			}
			if(aux==1){
				if(!acciones[i].esVacia()){
				if(posibles==null)posibles=new int[12];
				posibles[xua]=i;
				xua++;
				}
			}
			
			
		}
		return posibles;
	}

	/**
	 * 
	 * @param disp
	 * @param inanib
	 */
	private int[] recalcular_disponibles_con_inanibido(int[] disp, int inanib){
		int[] aux = {0,0,0,0};
		for(int i=0;i<4;i++){
			aux[i]=disp[i]-relacion_recurso_secuencia[inanib][i];
		}
		
		
		return aux;
	}

	/**
	 * 
	 * @param auto_llamador
	 */
	private void sacar_numero(auto auto_llamador){
		auto_llamador.set_numero_antiguedad(this.numero_antiguedad);
		this.numero_antiguedad++;
		}



	/**
	 * 
	 * @param colas
	 */
	private Cola get_cola_max_prioridad(int[] colas){
		Cola aux=acciones[colas[0]];
		for(int i=1;i<12;i++){
			if(aux.getPrioridad()<acciones[colas[i]].getPrioridad())
				aux=acciones[colas[i]];
		}
			
		return aux;
	}





	/**
	 * 
	 * @param inanibs
	 * @param disps
	 */


	/**
	 * 
	 * @param recursos
	 */
	private void tomar_recursos(int tran){
		for(int i=0;i<16;i++){
			estado[i]=estado[i]+matriz_incidencia[i][tran];
		}
		lock.unlock();

	}

}