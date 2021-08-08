package problemdomain;

import java.net.*;
import java.io.*;

/**
 * Client I/O connection.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class ClientConnection
{
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	/**
	 * @param socket The socket
	 * @param ois    The ObjectInputStream
	 * @param oos    The ObjectOutputStream
	 */
	public ClientConnection(Socket socket, ObjectInputStream ois, ObjectOutputStream oos)
	{
		this.socket = socket;
		this.ois = ois;
		this.oos = oos;
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket()
	{
		return socket;
	}

	/**
	 * @return the ois
	 */
	public ObjectInputStream getOis()
	{
		return ois;
	}

	/**
	 * @return the oos
	 */
	public ObjectOutputStream getOos()
	{
		return oos;
	}

}
