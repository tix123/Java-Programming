package server;

import java.io.*;
import java.net.Socket;

import problemdomain.*;

/**
 * I/O handler.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class InputOutputHandler implements Runnable
{
	private ClientConnection input;
	private ClientConnection output;

	/**
	 * Connect ObjectInputStream to ObjectOutputStream
	 * 
	 * @param input The connection which send object
	 * @param output The connection which receive object
	 */
	public InputOutputHandler(ClientConnection input, ClientConnection output)
	{
		this.input = input;
		this.output = output;
	}

	@Override
	public void run()
	{
		while (!this.input.getSocket().isClosed() && !this.output.getSocket().isClosed())
		{
			try
			{
				Object receive =  this.input.getOis().readObject();
				if(receive instanceof Message)
				{
					Message message = (Message)receive;
					ServerGUI.addMessage(message.toString());
					this.output.getOos().writeObject(message);
					this.output.getOos().reset();
				}
				
				if(receive instanceof Missile)
				{
					Missile missile = (Missile)receive;
					ServerGUI.addMessage(missile.toString());
					this.output.getOos().writeObject(missile);
					this.output.getOos().reset();
				}
				
				if(receive instanceof Square)
				{
					Square square = (Square)receive;
					ServerGUI.addMessage(square.toString());
					this.output.getOos().writeObject(square);
					this.output.getOos().reset();
				}
				
				if(receive instanceof Game)
				{
					Game game = (Game)receive;
					this.output.getOos().writeObject(game);
					this.output.getOos().reset();
				}
			}
			catch (EOFException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			
		}
	}

}
