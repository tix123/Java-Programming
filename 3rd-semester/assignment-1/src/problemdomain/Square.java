package problemdomain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Square information.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class Square implements Serializable
{
	private String username;
	private int row;
	private int column;
	private int status;

	/**
	 * @param username The name of the user
	 * @param row      The row of this square
	 * @param column   The column of this square
	 * @param status   The status of this square
	 */
	public Square(String username, int row, int column, int status)
	{
		this.username = username;
		this.row = row;
		this.column = column;
		this.status = status;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @return the row
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * @return the column
	 */
	public int getColumn()
	{
		return column;
	}

	/**
	 * @return the status
	 */
	public int getStatus()
	{
		return status;
	}

	/**
	 * Gets the human readable representation of a square.
	 */
	@Override
	public String toString()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String statusString = "";
		;
		switch (status)
		{
		case 2:
			statusString = "missed";
			break;
		case 3:
			statusString = "hit a target";
			break;
		}
		return String.format("[%s] %s report a missile is %s at %d,%d", format.format(new Date()), this.username,
				statusString, row, column);
	}
}
