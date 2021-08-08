package problemdomain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Message with username and time.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class Message implements Serializable
{
	private Date date;
	private String username;
	private String message;

	/**
	 * @param username The name of user
	 * @param message  The message
	 */
	public Message(String username, String message)
	{
		this.date = new Date();
		this.username = username;
		this.message = message;
	}

	/**
	 * @return the date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Gets the human readable representation of a message.
	 */
	@Override
	public String toString()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.format("[%s] %s sent: %s", format.format(this.date), this.username, this.message);
	}
}
