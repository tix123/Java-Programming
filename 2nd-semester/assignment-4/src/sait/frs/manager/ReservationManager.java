package sait.frs.manager;

import java.io.*;
import java.util.*;

import sait.frs.exception.*;
import sait.frs.problemdomain.*;

public class ReservationManager
{
	private ArrayList<Reservation> reservations;

	/**
	 * Constructor of reservation manager.
	 */
	public ReservationManager()
	{
		reservations = new ArrayList<Reservation>();
		populateFromBinary();
	}

	/**
	 * Makes a reservation.
	 * @param flight Flight to book reservation for.
	 * @param name Name of person (cannot be null or empty).
	 * @param citizenship Citizenship of person (cannot be null or empty).
	 * @return Created reservation instance.
	 * @throws NoMoreSeatsException Thrown if flight is booked up.
	 * @throws InvalidNameException Thrown if name is null or empty.
	 * @throws InvalidCitizenshipException Thrown if citizenship is null or empty.
	 * @throws NullFlightException Thrown if flight is null.
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship) throws NoMoreSeatsException, InvalidNameException, InvalidCitizenshipException, NullFlightException
	{
		if (flight == null)
		{
			throw new NullFlightException();
		}
		else if (name.equalsIgnoreCase(""))
		{
			throw new InvalidNameException();
		}
		else if (citizenship.equalsIgnoreCase(""))
		{
			throw new InvalidCitizenshipException();
		}
		else if (getAvailableSeats(flight) <= 0)
		{
			throw new NoMoreSeatsException();
		}
		else
		{
			String code = generateReservationCode(flight);
			String flightCode = flight.getCode();
			String airline = flight.getAirlineName();
			double cost = flight.getCostPerSeat();
			boolean active = true;

			Reservation reservation = new Reservation(code, flightCode, airline, name, citizenship, cost, active);
			reservations.add(reservation);
			persist();
			return reservation;
		}

	}

	/**
	 * Finds reservations containing either reservation code or airline.
	 * @param code Reservation code to search for.
	 * @param airline Airline to search for.
	 * @param name Travelers name to search for.
	 * @return Any matching Reservation objects.
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name)
	{
		ArrayList<Reservation> findReservations = new ArrayList<Reservation>();
		if (code.equals("") && airline.equals("") && name.equals(""))
		{
			return findReservations;
		}
		else
		{
			for (Reservation i : reservations)
			{
				if (i.getCode().toUpperCase().contains(code.toUpperCase())
					&& i.getAirline().toUpperCase().contains(airline.toUpperCase())
					&& i.getName().toUpperCase().contains(name.toUpperCase()))
				{
					findReservations.add(i);
				}
			}
			return findReservations;
		}
	}

	/**
	 * Finds reservation with the exact reservation code.
	 * @param code  Reservation code.
	 * @return Reservation object or null if none found.
	 */
	public Reservation findReservationByCode(String code)
	{
		Reservation reservation = null;
		for (Reservation i : reservations)
		{
			if (i.getCode().equalsIgnoreCase(code))
			{
				reservation = i;
			}
		}
		return reservation;
	}

	/**
	 * Saves records in memory to hard drive.
	 */
	public void persist()
	{
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
			filePath = "res" + File.separator + "reservations.bin";
		}
		else
		{
			filePath = filePath.substring(0, lastSlashPos + 1);
			filePath += "res" + File.separator + "reservations.bin";
		}

		try
		{
			FileOutputStream f = new FileOutputStream(filePath);
			DataOutputStream s = new DataOutputStream(f);
			for (Reservation i : reservations)
			{
				s.writeUTF(i.getCode());
				s.writeUTF(i.getFlightCode());
				s.writeUTF(i.getAirline());
				s.writeUTF(i.getName());
				s.writeUTF(i.getCitizenship());
				s.writeDouble(i.getCost());
				s.writeBoolean(i.isActive());
			}
			s.close();
		}
		catch (IOException e)
		{
			e.getMessage();
		}

	}

	/**
	 * Check available seats of the flight
	 * @param flight The flight to check the available seats.
	 * @return Number of available seats
	 */
	private int getAvailableSeats(Flight flight)
	{
		int availableSeats;
		int bookedSeats = 0;
		for (Reservation i : reservations)
		{
			if (flight.getCode().equalsIgnoreCase(i.getFlightCode()) && i.isActive())
			{
				bookedSeats++;
			}
		}

		availableSeats = flight.getSeat() - bookedSeats;
		return availableSeats;
	}

	/**
	 * Generate reservation code
	 * @param flight The booked flight
	 * @return Reservation code
	 */
	private String generateReservationCode(Flight flight)
	{
		String reservationCode = "";
		int randomNumber;

		if (flight.isDomestic())
		{
			reservationCode += "D";
		}
		else
		{
			reservationCode += "I";
		}

		boolean repeat = true;
		while (repeat)
		{
			repeat = false;
			randomNumber = (int) (Math.random() * 9000 + 1000);
			reservationCode += Integer.toString(randomNumber);
			for (Reservation i : reservations)
			{
				if (i.getCode().equalsIgnoreCase(reservationCode))
				{
					repeat = true;
				}
			}
		}
		return reservationCode;
	}

	/**
	 * Populates reservations with Reservation objects from Random Access File.
	 */
	private void populateFromBinary()
	{
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
			filePath = "res" + File.separator + "reservations.bin";
		}
		else
		{
			filePath = filePath.substring(0, lastSlashPos + 1);
			filePath += "res" + File.separator + "reservations.bin";
		}
		
		String code;
		String flightCode;
		String airline;
		String name;
		String citizenship;
		double cost;
		boolean active;
		boolean endOfFile = false;
		
		try
		{
			FileInputStream f = new FileInputStream(filePath);
			DataInputStream s = new DataInputStream(f);
			while(!endOfFile)
			{
				code = s.readUTF();
				flightCode = s.readUTF();
				airline = s.readUTF();
				name = s.readUTF();
				citizenship = s.readUTF();
				cost = s.readDouble();
				active = s.readBoolean();
				reservations.add(new Reservation(code, flightCode, airline, name,  citizenship, cost, active));
			}
			s.close();
		}
		catch (EOFException e)
		{
			endOfFile = true;
		}
		catch (IOException e)
		{
			e.getMessage();
		}
		
	}

}
