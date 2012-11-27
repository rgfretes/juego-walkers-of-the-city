
public class auto extends Thread {
	
	private gui interfaz;
	private int posx;
	private int posy;
	private int retardo;
	private boolean sobrey;
	private enum movetype {COMON, DERECHO, DERECHA, IZQUIERDA};
	private movetype move;
	private int lims;
	
	auto(gui interfaz, int posy, int posx, int retardo, boolean sobrey, int lims)
	{
		this.interfaz = interfaz;
		this.posx = posx;
		this.posy = posy;
		interfaz.place(posy,posx);
		this.retardo = retardo;
		this.sobrey = sobrey;
		this.move = movetype.COMON;
		this.lims = lims;
	}
	
	public void move()
	{
		if(this.move == movetype.COMON)
			moveforward();
		else
			movesomehow();
	}
	
	public void moveforward()
	{
		int ant;
		if(sobrey)
		{
			if(posx % 2 != 0) //va para abajo
			{
				ant = posy;
				posy = (posy+1)%lims;
			}
			else
			{
				ant = posy;
				posy = (posy-1);
				if(posy<0)
					posy = lims-1;
			}
			interfaz.refresh(ant, posx, posy, posx);
		}
		else
		{
			if(posy % 2 != 0) //va para la izquierda
			{
				ant = posx;
				posx = (posx+1)%lims;
			}
			else
			{
				ant = posx;
				posx = (posx-1);
				if(posx<0)
					posx = lims-1;
			}
			interfaz.refresh(posy, ant, posy, posx);
		}
	}
	
	public void movesomehow()
	{
		
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
