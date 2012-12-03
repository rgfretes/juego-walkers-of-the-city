import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.concurrent.locks.ReentrantLock;

public class DibujaPanel extends JPanel {

	private int[][] posiciones;
	/*
	 * -1 --> inaacesible
	 * 0  --> accesible
	 * n  --> n autos
	 */
	private ReentrantLock lock_sistema_so;
	private boolean sistema;
	
	DibujaPanel(int [][] pos)
	{
		posiciones = pos;
		sistema = true;
		lock_sistema_so = new ReentrantLock();
	}
	
	public void paintComponent( Graphics g ) {
		lock_sistema_so.lock();
		if(sistema)
		{
			super.paintComponent( g );
			pintar(g);
		}
		sistema = true;
		lock_sistema_so.unlock();
	}
	
	private Graphics pintar( Graphics g )
	{
		int ancho = getWidth();
		int alto = getHeight();
		for(int i=0; i<posiciones.length; i++)
		{
			for(int j=0; j<posiciones.length; j++)
			{
				if(posiciones[i][j] == -1)
					g.setColor( Color.blue);
				else
					g.setColor( Color.lightGray);
				g.fillRect( j*ancho/posiciones.length, i*alto/posiciones.length, ancho/posiciones.length, alto/posiciones.length );
				g.setColor( Color.red );
				g.drawRect( j*ancho/posiciones.length, i*alto/posiciones.length, ancho/posiciones.length, alto/posiciones.length );
				if(posiciones[i][j] == 1)
				{
					g.setColor( Color.green );
					g.fillRect( j*ancho/posiciones.length + ancho/posiciones.length/2 - ancho/15/2, i*alto/posiciones.length + alto/posiciones.length/2 - alto/15/2, ancho/15, alto/15 );
				}
				else if(posiciones[i][j] > 1)
				{
					g.setColor( Color.red );
					g.fillRect( j*ancho/posiciones.length + ancho/posiciones.length/2 - ancho/15/2, i*alto/posiciones.length + alto/posiciones.length/2 - alto/15/2, ancho/15, alto/15 );
				}
				
			}
		}
		return g;
	}
	
	public void refresh(int anty, int antx, int newy, int newx)
	{
		lock_sistema_so.lock();
		posiciones[anty][antx]--;
		posiciones[newy][newx]++;
		Graphics g = pintar( getGraphics() );
		sistema = false;
		paintComponent(g);
		lock_sistema_so.unlock();
	}
	
	public void place(int y, int x)
	{
		posiciones[y][x]++;
		paintComponent(getGraphics());
	}
	
	public void unplace(int y, int x)
	{
		posiciones[y][x]--;
		paintComponent(getGraphics());
	}
}
