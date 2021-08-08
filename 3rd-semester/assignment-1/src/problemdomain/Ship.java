package problemdomain;

import java.io.Serializable;

/**
 * Ship information.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class Ship implements Serializable
{
	private int direction;
	private int row;
	private int column;
	private int shipSize;
	private boolean sink;
	private String shipName;

	/**
	 * @param direction The direction of the ship
	 * @param row       The start row of the ship
	 * @param column    The start column of the ship
	 * @param shipSize  The size of the ship
	 * @param sink      Is is sink or not
	 * @param shipName  The name of the ship
	 */
	public Ship(int direction, int row, int column, int shipSize, boolean sink, String shipName)
	{
		this.direction = direction;
		this.row = row;
		this.column = column;
		this.shipSize = shipSize;
		this.sink = sink;
		this.shipName = shipName;
	}

	/**
	 * @return the sink
	 */
	public boolean isSink()
	{
		return sink;
	}

	/**
	 * @param sink the sink to set
	 */
	public void setSink(boolean sink)
	{
		this.sink = sink;
	}

	/**
	 * @return the direction
	 */
	public int getDirection()
	{
		return direction;
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
	 * @return the shipSize
	 */
	public int getShipSize()
	{
		return shipSize;
	}

	/**
	 * @return the shipName
	 */
	public String getShipName()
	{
		return shipName;
	}

}
