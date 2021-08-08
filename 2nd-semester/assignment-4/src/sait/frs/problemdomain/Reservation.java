package sait.frs.problemdomain;

import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException;

public class Reservation
{
	private String code;
	private String flightCode;
	private String airline;
	private String name;
	private String citizenship;
	private double cost;
	private boolean active;

	/**
	 * @param code The reservation code
	 * @param flightCode The flight code
	 * @param airline The airline  of the 
	 * @param name The name of the customer
	 * @param citizenship The citizenship of customer
	 * @param cost The cost per seat of the flight
	 * @param active reservation is active or inactive
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship, double cost, boolean active)
	{
		super();
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.name = name;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @return the flightCode
	 */
	public String getFlightCode()
	{
		return flightCode;
	}

	/**
	 * @return the airline
	 */
	public String getAirline()
	{
		return airline;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the citizenship
	 */
	public String getCitizenship()
	{
		return citizenship;
	}

	/**
	 * @return the cost
	 */
	public double getCost()
	{
		return cost;
	}

	/**
	 * @return the active
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * @param name the name to set
	 * @throws InvalidNameException When the name is empty
	 */
	public void setName(String name) throws InvalidNameException
	{
		if (name.equalsIgnoreCase(""))
		{
			throw new InvalidNameException();
		}
		else
		{
			this.name = name;
		}
	}

	/**
	 * @param citizenship the citizenship to set
	 * @throws InvalidCitizenshipException When the citizenship is empty
	 */
	public void setCitizenship(String citizenship) throws InvalidCitizenshipException
	{
		if (citizenship.equalsIgnoreCase(""))
		{
			throw new InvalidCitizenshipException();
		}
		else
		{
			this.citizenship = citizenship;
		}
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	@Override
	public String toString()
	{
		return  code;
	}

}
