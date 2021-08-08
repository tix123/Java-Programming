package sait.frs.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException;
import sait.frs.exception.NullFlightException;
import sait.frs.manager.Manager;
import sait.frs.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 */
public class ReservationsTab extends TabBase
{
	/**
	 * Instance of travel manager.
	 */
	private Manager manager;
	
	/**
	 * List of reservations.
	 */
	private JList<Reservation> reservationsList;
	
	/**
	 * Creates a reservation model.
	 */
	private DefaultListModel<Reservation> reservationsModel;
	
	/**
	 * Text field for the reservation code.
	 */
	private JTextField codeText;
	
	/**
	 * Text field for the reservation fight.
	 */
	private JTextField flightText;
	
	/**
	 * Text field for the reservation airline.
	 */
	private JTextField airlineText;
	
	/**
	 * Text field for the reservation cost.
	 */
	private JTextField costText;
	
	/**
	 * Text field for the reservation name.
	 */
	private JTextField nameText;
	
	/**
	 * Text field for the reservation citizenship.
	 */
	private JTextField citizenshipText;
	
	/**
	 * Combo box for the reservation status.
	 */
	private JComboBox statusBox;
	
	/**
	 * Listener for the list.
	 */
	private MyListSelectionListener listener;
	
	/**
	 * Text field for searching by code.
	 */
	private JTextField searchCodeText;
	
	/**
	 * Text field for searching by airline.
	 */
	private JTextField searchAirlineText;
	
	/**
	 * Text field for searching by name.
	 */
	private JTextField searchNameText;

	/**
	 * Creates the components for reservations tab.
	 * 
	 * @param manager Instance of travel manager.
	 */
	public ReservationsTab(Manager manager)
	{
		this.manager = manager;
		panel.setLayout(new BorderLayout());

		panel.add(createNorthPanel(), BorderLayout.NORTH);
		panel.add(createCenterPanel(), BorderLayout.CENTER);
		panel.add(createEastPanel(), BorderLayout.EAST);
		panel.add(createSouthPanel(), BorderLayout.SOUTH);
	}

	/**
	 * Creates the north(title) panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel()
	{
		JPanel panel = new JPanel();

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
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
		reservationsModel = new DefaultListModel<>();
		reservationsList = new JList<>(reservationsModel);

		reservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(reservationsList);
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
	private JPanel createEastPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 20));

		JLabel reserveTitle = new JLabel("Reserve", SwingConstants.CENTER);
		reserveTitle.setFont(new Font("serif", Font.PLAIN, 27));

		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new UpdateButtonListener());

		panel.add(reserveTitle, BorderLayout.NORTH);
		panel.add(reserveCenterPanel(), BorderLayout.CENTER);
		panel.add(reserveWestPanel(), BorderLayout.WEST);
		panel.add(updateButton, BorderLayout.SOUTH);

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

		JLabel codeLabel = new JLabel("Code: ", SwingConstants.RIGHT);
		panel.add(codeLabel);

		JLabel flightLabel = new JLabel("Flight: ", SwingConstants.RIGHT);
		panel.add(flightLabel);

		JLabel airlineLabel = new JLabel("Airline: ", SwingConstants.RIGHT);
		panel.add(airlineLabel);

		JLabel costLabel = new JLabel("Cost: ", SwingConstants.RIGHT);
		panel.add(costLabel);

		JLabel nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
		panel.add(nameLabel);

		JLabel citizenshipLabel = new JLabel("Citizenship: ", SwingConstants.RIGHT);
		panel.add(citizenshipLabel);

		JLabel statusLabel = new JLabel("Status: ", SwingConstants.RIGHT);
		panel.add(statusLabel);

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
		String[] status = { "Active", "Inactive" };
		panel.setLayout(new GridLayout(0, 1));

		codeText = new JTextField(10);
		codeText.setEditable(false);
		panel.add(codeText);

		flightText = new JTextField(10);
		flightText.setEditable(false);
		panel.add(flightText);

		airlineText = new JTextField(10);
		airlineText.setEditable(false);
		panel.add(airlineText);

		costText = new JTextField(10);
		costText.setEditable(false);
		panel.add(costText);

		nameText = new JTextField(10);
		panel.add(nameText);

		citizenshipText = new JTextField(10);
		panel.add(citizenshipText);

		statusBox = new JComboBox(status);
		panel.add(statusBox);

		return panel;
	}

	/**
	 * Creates the south(find reservation) panel.
	 * 
	 * @return JPanel that goes in south.
	 */
	private JPanel createSouthPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel searchTitle = new JLabel("Search", SwingConstants.CENTER);
		searchTitle.setFont(new Font("serif", Font.PLAIN, 27));
		JButton findReservationsButton = new JButton("Find Reservations");
		findReservationsButton.addActionListener(new FindReservationsButtonListener());

		panel.add(searchTitle, BorderLayout.NORTH);
		panel.add(searchCenterPanel(), BorderLayout.CENTER);
		panel.add(searchWestPanel(), BorderLayout.WEST);
		panel.add(findReservationsButton, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the west panel of find reservation panel.
	 * 
	 * @return JPanel that goes in west of find reservation panel.
	 */
	private JPanel searchWestPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));
		JLabel codeLabel = new JLabel("Code: ", SwingConstants.RIGHT);
		panel.add(codeLabel);

		JLabel airlineLabel = new JLabel("Airline: ", SwingConstants.RIGHT);
		panel.add(airlineLabel);

		JLabel nameLabel = new JLabel("Name: ", SwingConstants.RIGHT);
		panel.add(nameLabel);

		return panel;
	}

	/**
	 * Creates the center panel of find reservation panel.
	 * 
	 * @return JPanel that goes in center of find reservation panel.
	 */
	private JPanel searchCenterPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));

		searchCodeText = new JTextField();
		panel.add(searchCodeText);

		searchAirlineText = new JTextField();
		panel.add(searchAirlineText);

		searchNameText = new JTextField();
		panel.add(searchNameText);

		return panel;
	}

	/**
	 * Action listener for find reservations button.
	 * 
	 */
	private class FindReservationsButtonListener implements ActionListener
	{
		/**
		 * Called when the user clicking the find reservations button.
		 */
		public void actionPerformed(ActionEvent e)
		{
			codeText.setText("");
			flightText.setText("");
			airlineText.setText("");
			costText.setText("");
			nameText.setText("");
			citizenshipText.setText("");
			statusBox.setSelectedIndex(0);
			reservationsList.removeListSelectionListener(listener);
			reservationsList.clearSelection();
			reservationsModel.removeAllElements();

			ArrayList<Reservation> reservations = manager.findReservations(searchCodeText.getText(), searchAirlineText
					.getText(), searchNameText.getText());
			for (int i = 0; i < reservations.size(); i++)
			{
				reservationsModel.addElement(reservations.get(i));
			}

			reservationsList.addListSelectionListener(listener);
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
			codeText.setText(reservationsList.getSelectedValue().getCode());
			flightText.setText(reservationsList.getSelectedValue().getFlight().getCode());
			airlineText.setText(reservationsList.getSelectedValue().getFlight().getAirline());
			costText.setText(Double.toString(reservationsList.getSelectedValue().getFlight().getCostPerSeat()));
			nameText.setText(reservationsList.getSelectedValue().getName());
			citizenshipText.setText(reservationsList.getSelectedValue().getCitizenship());
			if (reservationsList.getSelectedValue().isActive())
			{
				statusBox.setSelectedIndex(0);
			}
			else 
			{
				statusBox.setSelectedIndex(1);
			}
		}
	}

	/**
	 * Action listener for update button.
	 * 
	 */
	private class UpdateButtonListener implements ActionListener
	{
		/**
		 * Called when the user clicking the update button.
		 */
		public void actionPerformed(ActionEvent e)
		{
			Boolean active = true;

			try
			{
				manager.findReservationByCode(codeText.getText()).setName(nameText.getText());
				manager.findReservationByCode(codeText.getText()).setCitizenship(citizenshipText.getText());

				if (statusBox.getSelectedIndex() == 0)
				{
					active = true;
				}
				else if (statusBox.getSelectedIndex() == 1)
				{
					active = false;
				}
				manager.findReservationByCode(codeText.getText()).setActive(active);
				manager.persist();
				JOptionPane.showMessageDialog(null, "The reservation code " + codeText.getText() + " is update.");
			}
			catch (InvalidNameException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			catch (InvalidCitizenshipException e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			catch (NullPointerException e1)
			{
				JOptionPane.showMessageDialog(null, "No reservation selected.");
			}
		}
	}
}
