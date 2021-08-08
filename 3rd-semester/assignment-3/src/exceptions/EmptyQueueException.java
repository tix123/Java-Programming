
package exceptions;

/**
 * This exception is thrown when queue is empty.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public class EmptyQueueException extends Exception
{
	public EmptyQueueException()
	{
		super("The queue is empty.");
	}
}
