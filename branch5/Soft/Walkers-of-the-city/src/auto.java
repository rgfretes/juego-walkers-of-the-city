import java.util.Random;
public class auto extends Thread {

	private mapa map;
	private coords pos;
	private int retardo;
	private boolean pory;
	private enum movetype {COMON, DERECHO, DERECHA, IZQUIERDA};
	private movetype move;
	private int lims;
	private int step;
	private int esquina;
	private int id;
	private int numero_antiguedad;
	private monitor corner;

	public int get_numero_antiguedad() {
		return numero_antiguedad;
	}


	public void set_numero_antiguedad(int numero_antiguedad) {
		this.numero_antiguedad = numero_antiguedad;
	}


	auto(mapa map, int posy, int posx, int retardo, boolean sobrey, int lims,int id, monitor corner)
	{
		this.map = map;
		this.pos = new coords(posx, posy);
		//interfaz.place(posy,posx);
		this.retardo = retardo;
		this.move = movetype.COMON;
		this.lims = lims;
		this.step=0;
		this.pory = sobrey;
		this.id = id;
		this.corner = corner;
	}


	public String toString()
	{
		return "Auto id = " + Integer.toString(id);
	}

	public int get_id()
	{
		return id;
	}

	public coords get_coords()
	{
		return pos;
	}

	private int esEsquina()
	{
		if( (pos.get_posx()==5 && pos.get_posy()==4) ){
			esquina = 2;
			return 2;
		} 
		else if( (pos.get_posx()==7 && pos.get_posy()==5)){
			esquina = 1;
			return 1;
		}
		else if((pos.get_posx()==6 && pos.get_posy()==7) ){
			esquina = 4 ;
			return 4; 
		}
		else if( (pos.get_posx()==4 && pos.get_posy()==6) ){ 
			esquina = 3 ;
			return 3;
		}
		else{ 
			esquina = 0;
			return 0;
		}

	}

	public void moveforward()
	{
		int ant;

		// verifico si estoy por entrar a una esquina:
		if( esEsquina() != 0 ){
			try {
				movesomehow();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if(pory)
		{
			if(pos.get_posx() % 2 != 0) //va para abajo
			{
				ant = pos.get_posy();
				pos.set_posy((pos.get_posy()+1)%lims);
			}
			else
			{
				ant = pos.get_posy();
				pos.set_posy((pos.get_posy()-1));
				if(pos.get_posy()<0)
					pos.set_posy(lims-1);
			}
			map.refresh(ant, pos.get_posx(), pos.get_posy(), pos.get_posx(), this);
		}
		else // es por x
		{
			if(pos.get_posy() % 2 == 0) //va para la derecha
			{
				ant = pos.get_posx();
				pos.set_posx((pos.get_posx()+1)%lims);
			}
			else
			{
				ant = pos.get_posx();
				pos.set_posx((pos.get_posx()-1));
				if(pos.get_posx()<0){
					pos.set_posx(lims-1);}
			}
			map.refresh(pos.get_posy(), ant, pos.get_posy(), pos.get_posx(), this);
		}

	}

	public void move()
	{
		// LLAMADA METODO QUE MANEJA EL MOVIMIENTO
		if(this.move == movetype.COMON){
			moveforward();}
		else{
			try {
				movesomehow();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	}


	public void movesomehow() throws InterruptedException
	{   
		int antx, anty;
		antx = pos.get_posx();
		anty = pos.get_posy();

		// si recien entro a esquina defino tipo de movimiento
		if(this.move == movetype.COMON)
		{
			step = 0;

			// DEFINICION TIPO DE MOVIMIENTO
			Random r1 = new Random();
			int movet;
			movet = r1.nextInt(3);


			//forzar movimiento
			movet = 0;

			//ESTABLECIMIENTO DEL MOVETYPE
			if(movet == 0){
				this.move = movetype.DERECHA;}
			else if(movet == 1){
				this.move = movetype.DERECHO;}
			else{
				this.move = movetype.IZQUIERDA;}

			// obtencion de monitor indicado
			//mont = map.get_cruce(pos);

			solicitar_recursos();
			
		}
		// realizo el movimiento
		switch(this.move){
		case DERECHA:
			mov_derecha();
			devolver_recurso();
			break;
		case DERECHO:
			mov_derecho();
			devolver_recurso();
			break;
		case IZQUIERDA:
			mov_izquierda();
			devolver_recurso(); 
			break;    
		}

		map.refresh(anty,antx,pos.get_posy(),pos.get_posx(),this);
	}

	private void solicitar_recursos() throws InterruptedException
	{
		//segun el movimiento es el pedido de recursos
		switch(this.move)
		{
		case DERECHA:
			switch(esquina){
			case 1:
				corner.reservar_recursos(this, "a");
				break;
			case 2:
				corner.reservar_recursos(this, "b");
				break;
			case 3:
				corner.reservar_recursos(this, "c");
				break;
			case 4:
				corner.reservar_recursos(this, "d");
				break;
			}
			break;
		case  DERECHO:
			switch(esquina){
			case 1:
				corner.reservar_recursos(this, "ab");
				break;
			case 2:
				corner.reservar_recursos(this, "bc");
				break;
			case 3:
				corner.reservar_recursos(this, "cd");
				break;
			case 4:
				corner.reservar_recursos(this, "da");
				break;
			}
			break;
		case IZQUIERDA:
			switch(esquina){
			case 1:
				corner.reservar_recursos(this, "abc");
				break;
			case 2:
				corner.reservar_recursos(this, "bcd");
				break;
			case 3:
				corner.reservar_recursos(this, "cda");
				break;
			case 4:
				corner.reservar_recursos(this, "dab");
				break;
			}
			break;
		}
	}
	
	private void devolver_recurso()
	{
		switch(this.move){
		case DERECHA:
			if(step == 0){ 

				switch(esquina){
				case 1:
					corner.return_recurso(this, "a");
					break;
				case 2:
					corner.return_recurso(this, "b");
					break;
				case 3:
					corner.return_recurso(this, "c");
					break;
				case 4:
					corner.return_recurso(this, "d");
					break;
				}
			}
			break;
		case DERECHO:

			switch(step)
			{
			case 2: // step 1

				switch(esquina){
				case 1:
					corner.return_recurso(this, "ab");
					break;
				case 2:
					System.out.println("devuelvo b");
					corner.return_recurso(this, "bc");
					break;
				case 3:
					corner.return_recurso(this, "cd");
					break;
				case 4:
					corner.return_recurso(this, "da");
					break;
				}

				break;
			case 0: // step 2

				switch(esquina){
				case 1:
					corner.return_recurso(this, "b");
					break;
				case 2:
					System.out.println("devuelvo c");
					corner.return_recurso(this, "c");
					break;
				case 3:
					corner.return_recurso(this, "d");
					break;
				case 4:
					corner.return_recurso(this, "a");
					break;
				}
				break; 
			}

			break;
		case IZQUIERDA:

			switch(step)
			{
			case 2: // step 1
				switch(esquina){
				case 1:
					corner.return_recurso(this, "abc");
					break;
				case 2:
					corner.return_recurso(this, "bcd");
					break;
				case 3:
					corner.return_recurso(this, "cda");
					break;
				case 4:
					corner.return_recurso(this, "dab");
					break;
				}
				break;
			case 3: // step 2

				switch(esquina){
				case 1:
					corner.return_recurso(this, "bc");
					break;
				case 2:
					corner.return_recurso(this, "cd");
					break;
				case 3:
					corner.return_recurso(this, "da");
					break;
				case 4:
					corner.return_recurso(this, "ab");
					break;
				}
			case 0: // step 3

				switch(esquina){
				case 1:
					corner.return_recurso(this, "c");
					break;
				case 2:
					corner.return_recurso(this, "d");
					break;
				case 3:
					corner.return_recurso(this, "a");
					break;
				case 4:
					corner.return_recurso(this, "b");
					break;
				}


				break; 
			}


			break;    
		}


	}

	private void mov_derecha()
	{
		//por y
		if(esquina == 2){ // para abajo
			switch(step){
			case 0:
				pos.set_posy((pos.get_posy()+1) %lims);
				step++;
				break;
			case 1:
				pos.set_posx((pos.get_posx() - 1) % lims); 
				pory = false;
				this.move = movetype.COMON; 
				step=0;
				break;
			}
		}
		else if(esquina == 4){   // para arriba
			switch(step){
			case 0:
				pos.set_posy((pos.get_posy() -1 )%lims);
				step++;
				break;
			case 1:
				pos.set_posx((pos.get_posx() + 1) % lims); 
				pory = false;
				this.move = movetype.COMON;
				step=0;
				break;
			}
		}

		//por x
		else if( esquina == 3)
		{    // para la derecha
			switch(step){
			case 0:
				pos.set_posx( (pos.get_posx() +1 ) % lims);
				step++;
				break;
			case 1:
				pos.set_posy( (pos.get_posy() + 1) % lims );
				pory = true;
				this.move = movetype.COMON;
				step=0;
				break;
			}
		}
		else if(esquina == 1)
		{ // para la izquierda
			switch(step){
			case 0:
				pos.set_posx( (pos.get_posx() - 1 ) % lims);
				step++;
				break;
			case 1:
				pos.set_posy( (pos.get_posy() - 1) % lims );
				pory = true;
				this.move = movetype.COMON;
				step=0;
				break;
			}
		}
		else 
			moveforward();

	}

	private void mov_derecho()
	{

		if(esquina == 2){ // por "y" para abajo
			switch(step){
			case 0:
				pos.set_posy((pos.get_posy()+1) %lims);
				step++;
				break;
			case 1:
				pos.set_posy((pos.get_posy()+1) %lims); 
				step++;
				break;
			case 2:
				pos.set_posy((pos.get_posy()+1) %lims);
				this.move = movetype.COMON;
				step=0;
				break;
			}
		}
		else if(esquina == 4){  // para arriba  
			switch(step){
			case 0:
				pos.set_posy((pos.get_posy() -1 )%lims);
				step++;
				break;
			case 1:
				pos.set_posy((pos.get_posy() - 1) % lims); 
				step++;
				break;
			case 2:
				pos.set_posy((pos.get_posy() - 1) % lims); 
				this.move = movetype.COMON;
				step=0;
				break;
			}
		}

		//por x
		else if( esquina == 3)
		{    // para la derecha
			switch(step){
			case 0:
				pos.set_posx( (pos.get_posx() +1 ) % lims);
				step++;
				break;
			case 1:
				pos.set_posx( (pos.get_posx() + 1) % lims );
				this.move = movetype.COMON;
				step++;
				break;
			case 2:
				pos.set_posx( (pos.get_posx() + 1) % lims );
				this.move = movetype.COMON;
				step=0;
				break;
			}
		}
		else if(esquina == 1)
		{ // para la izquierda
			switch(step){
			case 0:
				pos.set_posx( (pos.get_posx() - 1 ) % lims);
				step++;
				break;
			case 1:
				pos.set_posx( (pos.get_posx() - 1 ) % lims);
				step++;
				break;
			case 2:
				pos.set_posx( (pos.get_posx () - 1) % lims );
				this.move = movetype.COMON;
				step=0;
				break;
			}
		}      

	}

	private void mov_izquierda()
	{


		if(esquina == 2){ // por "y" para abajo
			switch(step){
			case 0:
				pos.set_posy((pos.get_posy()+1) %lims);
				step++;
				break;
			case 1:
				pos.set_posy((pos.get_posy()+1) %lims); 
				step++;
				break;
			case 2:
				pos.set_posx( (pos.get_posx() + 1 ) % lims);
				step++;
				break;
			case 3:
				pos.set_posx( (pos.get_posx() + 1 % lims));
				move= movetype.COMON;
				pory= false;
				step=0;
				break;    
			}
		}
		else if(esquina == 4){  // para arriba  
			switch(step){
			case 0:
				pos.set_posy((pos.get_posy() - 1 )%lims);
				step++;
				break;
			case 1:
				pos.set_posy((pos.get_posy() - 1 )%lims);
				step++;
				break;
			case 2:
				pos.set_posx((pos.get_posx() - 1 )%lims );
				step++;
				break;
			case 3:
				pos.set_posx((pos.get_posx() - 1 )%lims);
				move = movetype.COMON;
				pory= false;
				step=0;
				break;      
			}
		}
		else if( esquina == 3) //x para la derecha
		{    
			switch(step){
			case 0:
				pos.set_posx( (pos.get_posx()+1 ) % lims);
				step++;
				break;
			case 1:
				pos.set_posx( (pos.get_posx()+ 1) % lims );
				step++;
				break;
			case 2:
				pos.set_posy( (pos.get_posy()-1) % lims );
				step++;
				break;
			case 3:
				pos.set_posy( (pos.get_posy()-1) %lims);
				move = movetype.COMON;
				step=0;
				pory = true;  
				break;
			}
		}
		else if(esquina == 1)
		{ // para la izquierda
			switch(step){
			case 0:
				pos.set_posx( (pos.get_posx()-1) );
				step++;
				break;
			case 1:
				pos.set_posx( (pos.get_posx()- 1)  );

				step++;
				break;
			case 2:
				pos.set_posy( (pos.get_posy()+1) );

				step++;
				break;
			case 3:
				pos.set_posy( (pos.get_posy()+1));

				move = movetype.COMON;
				pory = true;
				step =0;
				break;
			}
		}

	}

	public void run()
	{
		while(true)
		{
			try 
			{
				sleep(retardo);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			move();
		}
	}
}
