import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * @author Marcos
 * @version 1.0
 * @created 18-dic-2012 12:55:28 a.m.
 */
public class Conexion {

	private Socket socket;
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	private int puerto;
	private String ip;
	private DataOutputStream mensaje;
	private DataInputStream salida;


	public Conexion(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param ip
	 * @param puerto
	 */
	public void conectar(String ip, int puerto){
		this.ip=ip;
		this.puerto=puerto;
		try {
			socket= new Socket(ip , puerto);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * 
	 * @param salida
	 */
	public void enviar(String salida){

	}

	public String recibir(){
		return "";
	}

}