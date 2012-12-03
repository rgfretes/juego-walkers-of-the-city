
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.*;

public class gui {
	
	private JFrame aplicacion;
	private DibujaPanel panel;
	private ReentrantLock lock_auto_auto;
	
	public gui(int [][] pos)
	{
		construirVentana(pos);
		lock_auto_auto = new ReentrantLock();
	}
	
	private void construirVentana(int [][] pos)
	{
		panel = new DibujaPanel(pos);
		aplicacion = new JFrame();
		aplicacion.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		aplicacion.add( panel );
		aplicacion.setSize( 250, 250 );
		aplicacion.setVisible( true );
	}
	
	public void refresh(int anty, int antx, int newy, int newx)
	{
		//lock_auto_auto.lock();
		panel.refresh(anty, antx, newy, newx);
		//lock_auto_auto.unlock();
	}
	
	public void place(int y, int x)
	{
		panel.place(y, x);
	}
}
