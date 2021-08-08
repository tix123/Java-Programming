package sait.frs.exception;

/**
 * This exception is thrown when citizenship is missing.
 * 
 */
public class InvalidCitizenshipException extends Exception
{
	public InvalidCitizenshipException()
	{
		super("citizenship is missing.");
	}
}
