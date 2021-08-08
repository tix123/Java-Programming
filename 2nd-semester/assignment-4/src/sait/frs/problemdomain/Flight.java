package sait.frs.problemdomain;

public class Flight
{
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seat;
	private double costPerSeat;
	
	/**
	 * @param code The flight code
	 * @param from The from airport of the flight
	 * @param to The to airport of the flight
	 * @param weekday The day of the flight
	 * @param time The time of the flight
	 * @param seat The seat of flight
	 * @param costPerSeat The cost of flight
	 */
	public Flight(String code, String from, String to, String weekday, String time, int seat, double costPerSeat)
	{
		this.code = code;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seat = seat;
		this.costPerSeat = costPerSeat;
		parseCode(code);
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @return the airlineName
	 */
	public String getAirlineName()
	{
		return airlineName;
	}

	/**
	 * @return the from
	 */
	public String getFrom()
	{
		return from;
	}

	/**
	 * @return the to
	 */
	public String getTo()
	{
		return to;
	}

	/**
	 * @return the weekday
	 */
	public String getWeekday()
	{
		return weekday;
	}

	/**
	 * @return the time
	 */
	public String getTime()
	{
		return time;
	}

	/**
	 * @return the seat
	 */
	public int getSeat()
	{
		return seat;
	}

	/**
	 * @return the costPerSeat
	 */
	public double getCostPerSeat()
	{
		return costPerSeat;
	}
	
	/**
	 * 
	 * @return the isDomestic
	 */
	public boolean isDomestic()
	{
		boolean isDomestic = false;
		
		if(from.substring(0,1).equalsIgnoreCase("Y") && to.substring(0,1).equalsIgnoreCase("Y"))
		{
			isDomestic = true;
		}
		return isDomestic;
	}
	
	/**
	 * Parse the flight code to airline name
	 * @param code flight code
	 */
	private void parseCode(String code)
	{
		String airlineName;
		switch (code.substring(0, 2))
		{
			case "OA":
				airlineName = "Otto Airlines";
				break;
			case "CA":
				airlineName = "Conned Air";
				break;
			case "TB":
				airlineName = "Try a Bus Airways";
				break;
			case "VA":
				airlineName = "Vertical Airways";
				break;
			default:
				airlineName = "";
				break;
		}
		this.airlineName = airlineName;
	}

	/**
	 * Gets the human readable representation of a flight.
	 */
	@Override
	public String toString()
	{
		return code + ", From: " + from + ", To: "+ to + ", Day: "+ weekday + ", Cost: " + costPerSeat;
	}
}
