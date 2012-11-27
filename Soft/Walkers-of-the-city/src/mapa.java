import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;

public class mapa {
	
	private ConcurrentHashMap<String, ArrayList<auto>> mapa;
	private gui interfaz;
	private int lims;
	
	mapa(int[][] estructura)
	{
		mapa = new ConcurrentHashMap<String, ArrayList<auto>>();
		interfaz = new gui(estructura);
		lims = estructura.length;
	}
	
	public void place(auto[] autos)
	{
		coords pos;
		ArrayList<auto> autos_en_pos;
		
		for (auto a : autos)
		{
			pos = a.get_coords();
			interfaz.place(pos.get_posy(),pos.get_posx()); //coloco el auto en la interfaz
			autos_en_pos = mapa.get(pos.toString());
			if(autos_en_pos != null) //coloco el auto en el mapa
				autos_en_pos.add(a);
			else
			{
				autos_en_pos = new ArrayList<auto>();
				autos_en_pos.add(a);
				mapa.put(pos.toString(), autos_en_pos);
			}
			a.start();
		}
		//show_map();
	}
	
	public void refresh(int anty, int antx, int newy, int newx, auto a)
	{
		ArrayList<auto> autos_en_pos;
		
		autos_en_pos = mapa.get( (new coords(antx, anty)).toString() );
		if(autos_en_pos != null) //coloco el auto en el mapa
			autos_en_pos.remove(a);
		
		autos_en_pos = mapa.get( (new coords(newx, newy)).toString() );
		if(autos_en_pos != null) //coloco el auto en el mapa
			autos_en_pos.add(a);
		else
		{
			autos_en_pos = new ArrayList<auto>();
			autos_en_pos.add(a);
			mapa.put( (new coords(newx, newy).toString() ), autos_en_pos);
		}
		
		interfaz.refresh(anty, antx, newy, newx);
		//show_map();
	}
	
	private void show_map()
	{
		ArrayList<auto> autos_en_pos;
		
		for(int i=0; i<lims; i++)
		{
			for(int j=0; j<lims; j++)
			{
				autos_en_pos = mapa.get( (new coords(j, i)).toString() );
				if(autos_en_pos == null)
					System.out.print("n ");
				else
					System.out.print("a ");
			}
			System.out.println("");
		}
	}
	
	public void show_pos(coords pos)
	{
		ArrayList<auto> autos_en_pos;
		
		autos_en_pos = mapa.get(pos.toString());
		if(autos_en_pos == null)
			System.out.println("No hay nadie aqui");
		else
		{
			for(auto a : autos_en_pos)
			{
				System.out.println(a.toString());
			}
		}
	}
}
