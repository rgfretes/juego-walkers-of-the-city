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
	private int route;
	private int id;
	private int numero_antiguedad;
	
	public int get_numero_antiguedad() {
		return numero_antiguedad;
	}


	public void set_numero_antiguedad(int numero_antiguedad) {
		this.numero_antiguedad = numero_antiguedad;
	}


	auto(mapa map, int posy, int posx, int retardo, boolean sobrey, int lims,int id)
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
		this.route = 0;
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

	private boolean esEsquina()
	{
		if( (pos.get_posx()==5 && pos.get_posy()==4) || (pos.get_posx()==7 && pos.get_posy()==5)
				|| (pos.get_posx()==6 && pos.get_posy()==7) || (pos.get_posx()==4 && pos.get_posy()==6) ) {
			return true;
		}
		else {
			return false;}

	}
	public void moveforward()
	{
		int ant;
		
		// verifico si estoy por entrar a una esquina:
			if( esEsquina() ){
				
				movesomehow();}
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
			movesomehow();}
	}


	public void movesomehow()
	{   
		//monitor mont;
		int antx, anty;
		antx = pos.get_posx();
		anty = pos.get_posy();

		// si recien entro a esquina defino tipo de movimiento
		if(this.move == movetype.COMON)
		{
			step = 0;
			route  = 0;
			
			// DEFINICION TIPO DE MOVIMIENTO
			Random r1 = new Random();
			int movet;
			movet = r1.nextInt(3);
			
			
			 //forzar movimiento
			//movet = 2;
			
			//ESTABLECIMIENTO DEL MOVETYPE
			if(movet == 0){
				this.move = movetype.DERECHA;}
			else if(movet == 1){
				this.move = movetype.DERECHO;}
			else{
				this.move = movetype.IZQUIERDA;
				//  indicacion de tipo de ruta:
				if(pos.get_posx() % 2 != 0 && pory == true)
					route = 1;
				else if(pos.get_posy() % 2 == 0 && pory == false)
					route= 2;
				}
			
			
			
			// obtencion de monitor indicado
			//mont = map.get_cruce(pos);

			//segun el movimiento es el pedido de recursos
			switch(this.move)
			{
			case DERECHA:
				// pedir recursos

				break;
			case  DERECHO:
				//pedir
				break;
			case IZQUIERDA:
				//pedir
				break;
			}
		}
		// realizo el movimiento
		switch(this.move){
		case DERECHA:
			mov_derecha();
			//devolver recurso desocupado (anterior)
			break;
		case DERECHO:
			mov_derecho();
			//devolver recurso desocupado
			break;
		case IZQUIERDA:
			mov_izquierda();
			//devolver recurso desocupado 
			break;    
		}
		
		map.refresh(anty,antx,pos.get_posy(),pos.get_posx(),this);
	}

	private void mov_derecha()
	{
		
		if(pory ) { // por y
			if(pos.get_posx() % 2 != 0){ // para abajo
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
			else{ // para arriba  
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
		}
		else // por x
		{    
			if(pos.get_posy() % 2 == 0){ // para la derecha
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
			else{ // para la izquierda
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
		}
	}
	private void mov_derecho()
	{
		if(pory ) { // por y
			if(pos.get_posx() % 2 != 0){ // para abajo
				switch(step){
				case 0:
					pos.set_posy((pos.get_posy()+1) %lims);
					step++;
					break;
				case 1:
					pos.set_posy((pos.get_posy()+1) %lims); 
					this.move = movetype.COMON;
					step=0;
					break;
				}
			}
			else{ // para arriba  
				switch(step){
				case 0:
					pos.set_posy((pos.get_posy() -1 )%lims);
					step++;
					break;
				case 1:
					pos.set_posy((pos.get_posy() - 1) % lims); 
					this.move = movetype.COMON;
					step=0;
					break;
				}
			}
		}
		else // por x
		{    
			if(pos.get_posy() % 2 == 0){ // para la derecha
				switch(step){
				case 0:
					pos.set_posx( (pos.get_posx() +1 ) % lims);
					step++;
					break;
				case 1:
					pos.set_posx( (pos.get_posx() + 1) % lims );
					this.move = movetype.COMON;
					step=0;
					break;
				}
			}
			else{ // para la izquierda
				switch(step){
				case 0:
					pos.set_posx( (pos.get_posx() - 1 ) % lims);
					step++;
					break;
				case 1:
					pos.set_posx( (pos.get_posx () - 1) % lims );
					this.move = movetype.COMON;
					step=0;
					break;
				}
			}      
		}
	}

	private void mov_izquierda()
	{
		
		
		if(pory && route == 1){ // para abajo
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
						route = 0;
						step=0;
						break;    
					}
			}
			else if (pory  && route != 1){ // para arriba  
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
					route = 0;
					break;      
				}
			}
		else if ( !pory && route == 2) // por x para la derecha
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
						route = 0;
						pory = true;  
						route = 0;
						break;
					}
			}
		else{ // POR X para la izquierda
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
