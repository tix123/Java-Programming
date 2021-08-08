package problemdomain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Missile with username and coordinate.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class Missile implements Serializable
{
	private String username;
	private String squareNum;

	/**
	 * @param username  The name of user
	 * @param squareNum The number of square
	 */
	public Missile(String username, String squareNum)
	{
		this.username = username;
		this.squareNum = squareNum;
	}

	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @return the squareNum
	 */
	public String getSquareNum()
	{
		return squareNum;
	}

	/**
	 * Gets the human readable representation of a missile.
	 */
	@Override
	public String toString()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int row = Integer.parseInt(squareNum) / 10;
		int column = Integer.parseInt(squareNum) % 10;
		return String.format("[%s] %s fire a missile to %d,%d", format.format(new Date()), this.username, row, column);
	}

}
