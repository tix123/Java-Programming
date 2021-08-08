package sait.frs.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException;
import sait.frs.exception.NoMoreSeatsException;
import sait.frs.exception.NullFlightException;
import sait.frs.manager.*;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase
{
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;
	
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	
	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	/**
	 * Creates a reservation model.
	 */
	private DefaultListModel<Flight> flightsModel;

	/**
	 * Combo box of searching by from.
	 */
	private JComboBox fromBox;
	
	/**
	 * Combo box of searching by to.
	 */
	private JComboBox toBox;
	
	/**
	 * Combo box of searching by weekday.
	 */
	private JComboBox weekdayBox;
	
	/**
	 * Text field for the flight.
	 */
	private JTextField flightText;
	
	/**
	 * Text field for the airline.
	 */
	private JTextField airlineText;
	
	/**
	 * Text field for the day.
	 */
	private JTextField dayText;
	
	/**
	 * Text field for the time.
	 */
	private JTextField timeText;
	
	/**
	 * Text field for the cost.
	 */
	private JTextField costText;
	
	/**
	 * Text field for the name.
	 */
	private JTextField nameText;
	
	/**
	 * Text field for the citizenship.
	 */
	private JTextField citizenshipText;
	
	/**
	 * Listener for the list.
	 */
	private MyListSelectionListener listener;
	
	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager Instance of flight manager.
	 * @param reservationManager Instance of reservation manager.
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager)
	{
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;
		panel.setLayout(new BorderLayout(0, 5));
		panel.add(createNorthPanel(), BorderLayout.NORTH);
		panel.add(createCenterPanel(), BorderLayout.CENTER);
		panel.add(creatEastPanel(), BorderLayout.EAST);
		panel.add(creatSouthPanel(), BorderLayout.SOUTH);
	}

	/**
	 * Creates the north(title) panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel()
	{
		JPanel panel = new JPanel();
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		return panel;
	}

	/**
	 * Creates the center(list) panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel()
	{
		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout(0, 20, 0));

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		scrollPane.setPreferredSize(new Dimension(360, 240));
		listener = new MyListSelectionListener();
	
		panel.add(scrollPane);

		return panel;
	}

	/**
	 * Creates the east(reserve) panel.
	 * 
	 * @return JPanel that goes in east.
	 */
	private JPanel creatEastPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 20));

		JLabel reserveTitle = new JLabel("Reserve", SwingConstants.CENTER);
		reserveTitle.setFont(new Font("serif", Font.PLAIN, 27));

		JButton reserveButton = new JButton("Reserve");
		reserveButton.addActionListener(new ReserveButtonListener());
		
		panel.add(reserveTitle, BorderLayout.NORTH);
		panel.add(reserveCenterPanel(), BorderLayout.CENTER);
		panel.add(reserveWestPanel(), BorderLayout.WEST);
		panel.add(reserveButton, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the west panel of reserve panel.
	 * 
	 * @return JPanel that goes in west of reserve panel.
	 */
	private JPanel reserveWestPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));

		JLabel flightLabel = new JLabel("Flight: ", SwingConstants.RIGHT);
		panel.add(flightLabel);

		JLabel airlineLabel = new JLabel("Airline: ", SwingConstants.RIGHT);
		panel.add(airlineLabel);

		JLabel dayLabel = new JLabel("Day: ", SwingConstants.RIGHT);
		panel.add(dayLabel);

		JLabel timeLabel = new JLabel("Time: ", SwingConstants.RIGHT);
		panel.add(timeLabel);

		JLabel costLabel = new JLabel("Cost: ", SwingConstants.RIGHT);
		panel.add(costLabel);

		JLabel nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
		panel.add(nameLabel);

		JLabel citizenshipLabel = new JLabel("Citizenship: ", SwingConstants.RIGHT);
		panel.add(citizenshipLabel);

		return panel;
	}

	/**
	 * Creates the center panel of reserve panel.
	 * 
	 * @return JPanel that goes in center of reserve panel.
	 */
	private JPanel reserveCenterPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));

		flightText = new JTextField(10);
		flightText.setEditable(false);
		panel.add(flightText);

		airlineText = new JTextField(10);
		airlineText.setEditable(false);
		panel.add(airlineText);

		dayText = new JTextField(10);
		dayText.setEditable(false);
		panel.add(dayText);

		timeText = new JTextField(10);
		timeText.setEditable(false);
		panel.add(timeText);

		costText = new JTextField(10);
		costText.setEditable(false);
		panel.add(costText);

		nameText = new JTextField(10);
		panel.add(nameText);

		citizenshipText = new JTextField(10);
		panel.add(citizenshipText);

		return panel;
	}

	/**
	 * Creates the south(flight finder) panel.
	 * 
	 * @return JPanel that goes in south.
	 */
	private JPanel creatSouthPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel flightFinderTitle = new JLabel("Flight Finder", SwingConstants.CENTER);
		flightFinderTitle.setFont(new Font("serif", Font.PLAIN, 27));
		JButton findFlightButton = new JButton("Find Flights");
		findFlightButton.addActionListener(new FlightFinderButtonListener());

		panel.add(flightFinderTitle, BorderLayout.NORTH);
		panel.add(finderCenterPanel(), BorderLayout.CENTER);
		panel.add(finderWestPanel(), BorderLayout.WEST);
		panel.add(findFlightButton, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the west panel of flight finder panel.
	 * 
	 * @return JPanel that goes in west of flight finder panel.
	 */
	private JPanel finderWestPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		JLabel fromLabel = new JLabel("From: ", SwingConstants.RIGHT);
		panel.add(fromLabel);

		JLabel toLabel = new JLabel("To: ", SwingConstants.RIGHT);
		panel.add(toLabel);

		JLabel dayLabel = new JLabel("Day: ", SwingConstants.RIGHT);
		panel.add(dayLabel);

		return panel;
	}

	/**
	 * Creates the center panel of flight finder panel.
	 * 
	 * @return JPanel that goes in center of flight finder panel.
	 */
	private JPanel finderCenterPanel()
	{
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(0, 1));
		String[] weekdayList =
		{ flightManager.WEEKDAY_ANY, flightManager.WEEKDAY_MONDAY, flightManager.WEEKDAY_TUESDAY, flightManager.WEEKDAY_WEDNESDAY, flightManager.WEEKDAY_THURSDAY, flightManager.WEEKDAY_FRIDAY, flightManager.WEEKDAY_SATURDAY, flightManager.WEEKDAY_SUNDAY };

		fromBox = new JComboBox(flightManager.getAirports().toArray());
		panel.add(fromBox);

		toBox = new JComboBox(flightManager.getAirports().toArray());
		panel.add(toBox);

		weekdayBox = new JComboBox(weekdayList);
		panel.add(weekdayBox);
		
		return panel;
	}

	/**
	 * Action listener for reserve button.
	 * 
	 */
	private class ReserveButtonListener implements ActionListener
	{
		/**
		 * Called when the user clicking the reserve button.
		 */
		public void actionPerformed(ActionEvent e)
		{
			try 
			{
				Reservation reservation = reservationManager.makeReservation(flightsList.getSelectedValue(), nameText.getText(), citizenshipText.getText());
				JOptionPane.showMessageDialog(null, "Reservation created. Youre code is " + reservation.getCode());
			}
			
			catch (NullFlightException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			catch (NoMoreSeatsException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			catch (InvalidNameException e1)
			{
				
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			catch (InvalidCitizenshipException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	/**
	 * Action listener for flight finder button.
	 * 
	 */
	private class FlightFinderButtonListener implements ActionListener
	{
		/**
		 * Called when the user clicking the flight finder button.
		 */
		public void actionPerformed(ActionEvent e)
		{
			
			flightText.setText("");
			airlineText.setText("");
			dayText.setText("");
			timeText.setText("");
			costText.setText("");
			flightsList.removeListSelectionListener(listener);
			flightsList.clearSelection();
			flightsModel.removeAllElements();
			
			ArrayList<Flight> flights = flightManager.findFlights((String) fromBox.getSelectedItem(), 
					(String) toBox.getSelectedItem(), (String) weekdayBox.getSelectedItem());
			for (int i = 0; i < flights.size(); i++) 
			{
				flightsModel.addElement(flights.get(i));
			}
			
			flightsList.addListSelectionListener(listener);
		}
	}

	/**
	 * Listener for the list.
	 * 
	 */
	private class MyListSelectionListener implements ListSelectionListener
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			flightText.setText(flightsList.getSelectedValue().getCode());
			airlineText.setText(flightsList.getSelectedValue().getAirlineName());
			dayText.setText(flightsList.getSelectedValue().getWeekday());
			timeText.setText(flightsList.getSelectedValue().getTime());
			costText.setText(Double.toString(flightsList.getSelectedValue().getCostPerSeat()));
		}

	}
}