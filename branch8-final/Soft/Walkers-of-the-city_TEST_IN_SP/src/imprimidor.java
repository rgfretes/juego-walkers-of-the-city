
public class imprimidor extends Thread {
	//private DibujaPanel a;
	private int retardo;
	
	imprimidor(int a)
	{
		retardo = a;
	}

	public void show_crash()
	{
		System.out.println("Cantidad de choques que hubo en cruce:" + auto.cont_choques);
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
			show_crash();
		}
	}
}
