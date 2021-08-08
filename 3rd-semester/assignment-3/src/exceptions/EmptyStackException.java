
package exceptions;

/**
 * This exception is thrown when stack is empty.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public class EmptyStackException extends Exception
{
	public EmptyStackException()
	{
		super("The stack is empty.");
	}
}
