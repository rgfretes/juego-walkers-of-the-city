
public class coords {
	private int posx;
	private int posy;
	
	coords(int x,int y)
	{
		set_posx(x);
		set_posy(y);
	}

	public void set_posx(int x)
	{
		posx = x;
	}
	
	public void set_posy(int y)
	{
		posy = y;
	}
	
	public int get_posx()
	{
		return posx;
	}
	
	public int get_posy()
	{
		return posy;
	}
	
	public boolean equals(Object o) 
	{
		System.out.println("HOLA");
		if (o instanceof coords) 
		{
			if(posx == ((coords)o).get_posx() && posy == ((coords)o).get_posy())
				return true;
			else
				return false;
		} 
		else 
		{
			return false;
		}
	}
	
	public String toString()
	{
		String res;
		res = Integer.toString(posy) + Integer.toString(posx);
		return res;
	}
}
