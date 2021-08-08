package server;

import problemdomain.*;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import server.*;

/**
 * Pair up connections.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class ClientHandler implements Runnable
{
	private ClientConnection connection1;
	private ClientConnection connection2;
	private final int PLAYER_1 = 1;

	/**
	 * Connect 2 connection together and start the game
	 * 
	 * @param connection1 The first connection
	 * @param connection2 The second connection
	 */
	public ClientHandler(ClientConnection connection1, ClientConnection connection2)
	{
		this.connection1 = connection1;
		this.connection2 = connection2;
	}

	@Override
	public void run()
	{
		InputOutputHandler ioHandler1 = new InputOutputHandler(this.connection1, this.connection2);
		Thread thread1 = new Thread(ioHandler1);
		thread1.start();

		InputOutputHandler ioHandler2 = new InputOutputHandler(this.connection2, this.connection1);
		Thread thread2 = new Thread(ioHandler2);
		thread2.start();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ServerGUI.addMessage(String.format("[%s] Game Start!", format.format(new Date())));

		Game goFirst = new Game(true, true, false, false);
		Game goSecond = new Game(true, false, false, false);

		int whoGoFirst = (int) (Math.random() * 2);
		try
		{
			if (whoGoFirst == PLAYER_1)
			{
				connection1.getOos().writeObject(goFirst);
				connection1.getOos().reset();
				connection2.getOos().writeObject(goSecond);
				connection2.getOos().reset();
			}
			else
			{
				connection1.getOos().writeObject(goFirst);
				connection1.getOos().reset();
				connection2.getOos().writeObject(goSecond);
				connection2.getOos().reset();
			}
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

		try
		{
			thread1.join();
			thread2.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

}
