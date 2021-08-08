package client;

import problemdomain.*;

import java.io.*;
import java.net.*;

/**
 * Server input handler.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class ServerHandler implements Runnable
{
	private ClientGUI gui;
	private Socket server;
	private ObjectInputStream ois;

	/**
	 * Receive ObjectInputStream from server
	 * 
	 * @param gui The ClientGUI
	 * @param server The server socket
	 * @param ois The ObjectInputStream of client
	 */
	public ServerHandler(ClientGUI gui, Socket server, ObjectInputStream ois)
	{
		this.gui = gui;
		this.server = server;
		this.ois = ois;
	}

	@Override
	public void run()
	{
		while (!this.server.isClosed())
		{
			try
			{
				Object receive =  this.ois.readObject();
						
				if(receive instanceof Missile)
				{
					Missile missile = (Missile)receive;
					this.gui.missileHandler(missile.getSquareNum());
				}
				
				if(receive instanceof Message)
				{
					Message message = (Message)receive;
					this.gui.addMessage(message.toString());
				}
				
				if(receive instanceof Game)
				{
					Game game = (Game)receive;
					this.gui.gameHandler(game.isStart(), game.isYourTurn(), game.isYouWin(), game.isPlayAgain());
				}
				
				if(receive instanceof Square)
				{
					Square square = (Square)receive;
					this.gui.squareHandler(square.getRow(), square.getColumn(), square.getStatus());
				}

			}
			catch (SocketException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

	}

}
