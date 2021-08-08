package sait.frs.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import sait.frs.problemdomain.*;

public class FlightManager
{
	/**
	 * Used to search for reservations on any day of the week.
	 */
	public final String WEEKDAY_ANY = "Any";
	
	/**
	 * Used to search for reservations on Sunday.
	 */
	public final String WEEKDAY_SUNDAY = "Sunday";
	
	/**
	 * Used to search for reservations on Monday.
	 */
	public final String WEEKDAY_MONDAY = "Monday";
	
	/**
	 * Used to search for reservations on Tuesday.
	 */
	public final String WEEKDAY_TUESDAY = "Tuesday";
	
	/**
	 * Used to search for reservations on Wednesday.
	 */
	public final String WEEKDAY_WEDNESDAY = "Wednesday";
	
	/**
	 * Used to search for reservations on Thursday.
	 */
	public final String WEEKDAY_THURSDAY = "Thursday";
	
	/**
	 * Used to search for reservations on Friday.
	 */
	public final String WEEKDAY_FRIDAY = "Friday";
	
	/**
	 * Used to search for reservations on Saturday.
	 */
	public final String WEEKDAY_SATURDAY = "Saturday";
	private ArrayList<Flight> flights;
	private ArrayList<String> airports;
	
	/**
	 * Constructor of flight manager.
	 */
	public FlightManager() 
	{
		try
		{
			populateAirports();
			populateFlights();
		}
		catch (IOException e)
		{
			e.getMessage();
		}
	}

	/**
	 * @return the flights
	 */
	public ArrayList<Flight> getFlights() 
	{
		return flights;
	}

	/**
	 * @return the airports
	 */
	public ArrayList<String> getAirports() 
	{
		return airports;
	}

	/**
	 * Finds a airport using code.
	 * @param code airport code
	 * @return airport name
	 * @throws FileNotFoundException When the file does not exist.
	 */
	public String findAirportByCode(String code) throws FileNotFoundException 
	{
		String[] airportArray;
		String airportName = "";

		int slashPos = 0;
		int lastSlashPos = 0;
		String filePath;

		filePath = System.getProperty("java.class.path");
		while (slashPos != -1)
		{
			lastSlashPos = slashPos;
			slashPos = filePath.indexOf(File.separator, slashPos + 1);
		}
		if (lastSlashPos == 0)
		{
			filePath = "res" + File.separator + "airports.csv";
		}
		else
		{
			filePath = filePath.substring(0, lastSlashPos + 1);
			filePath += "res" + File.separator + "airports.csv";
		}

		File f = new File(filePath);
		Scanner s = new Scanner(f);

		while (s.hasNextLine())
		{
			airportArray = s.nextLine().split(",");
			if (code.equalsIgnoreCase(airportArray[0]))
			{
				airportName = airportArray[1];
			}
		}
		s.close();
		return airportName;
	}

	/**
	 * Finds a flight using code.
	 * @param code flight code
	 * @return found flight
	 */
	public Flight findFlightByCode(String code) 
	{
		Flight findFlightByCode = null;
		
		for (Flight i : flights)
		{
			if (i.getCode().equalsIgnoreCase(code))
			{
				findFlightByCode = i;
			}
		}
		return findFlightByCode;
	}

	public ArrayList<Flight> findFlights(String from, String to, String weekday) 
	{
		ArrayList<Flight> findFlight = new ArrayList<Flight>();

		if (weekday.equalsIgnoreCase(WEEKDAY_ANY))
		{
			for (Flight i : flights)
			{
				if (i.getFrom().equalsIgnoreCase(from) && i.getTo().equalsIgnoreCase(to))
				{
					findFlight.add(i);
				}
			}
		}
		else
		{
			for (Flight i : flights)
			{
				if (i.getFrom().equalsIgnoreCase(from)
					&& i.getTo().equalsIgnoreCase(to)
					&& i.getWeekday().equalsIgnoreCase(weekday))
				{
					findFlight.add(i);
				}
			}
		}

		return findFlight;
	}

	/**
	 * Populates flights from CSV file
	 * @throws IOException When file does not exist.
	 */
	private void populateFlights() throws IOException
	{
		String[] flightArray;
		flights = new ArrayList<Flight>();

		int slashPos = 0;
		int lastSlashPos = 0;
		String filePath;

		filePath = System.getProperty("java.class.path");
		while (slashPos != -1)
		{
			lastSlashPos = slashPos;
			slashPos = filePath.indexOf(File.separator, slashPos + 1);
		}
		if (lastSlashPos == 0)
		{
			filePath = "res" + File.separator + "flights.csv";
		}
		else
		{
			filePath = filePath.substring(0, lastSlashPos + 1);
			filePath += "res" + File.separator + "flights.csv";
		}

		File f = new File(filePath);
		Scanner s = new Scanner(f);

		while (s.hasNextLine())
		{
			flightArray = s.nextLine().split(",");
			String code = flightArray[0];
			String from = flightArray[1];
			String to = flightArray[2];
			String weekday = flightArray[3];
			String time = flightArray[4];
			int seat = Integer.parseInt(flightArray[5]);
			double costPerSeat = Double.parseDouble(flightArray[6]);

			if (code.matches("[a-zA-z]{2}-[0-9]{4}"))
			{
				flights.add(new Flight(code, from, to, weekday, time, seat, costPerSeat));
			}
		}
		s.close();
	}

	/**
	 * Populates airports from CSV file
	 * @throws IOException When file does not exist.
	 */
	private void populateAirports() throws IOException
	{
		airports = new ArrayList<String>();
		String[] airportArray;

		int slashPos = 0;
		int lastSlashPos = 0;
		String filePath;

		filePath = System.getProperty("java.class.path");
		while (slashPos != -1)
		{
			lastSlashPos = slashPos;
			slashPos = filePath.indexOf(File.separator, slashPos + 1);
		}
		if (lastSlashPos == 0)
		{
			filePath = "res" + File.separator + "airports.csv";
		}
		else
		{
			filePath = filePath.substring(0, lastSlashPos + 1);
			filePath += "res" + File.separator + "airports.csv";
		}

		File f = new File(filePath);
		Scanner s = new Scanner(f);

		while (s.hasNextLine())
		{
			airportArray = s.nextLine().split(",");
			airports.add(airportArray[0]);
		}
		s.close();
	}

}
