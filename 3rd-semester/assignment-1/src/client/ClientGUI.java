package client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;

import problemdomain.*;
import server.ServerGUI;

/**
 * Create Client GUI.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class ClientGUI
{
	private JFrame frame;

	private DefaultListModel chatListModel;
	private JList chatList;

	private String username;
	private Socket socket;

	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	private ArrayList<JButton> yourBoard;
	private int[][] yourSquareArray = new int[10][10];
	private ArrayList<JButton> opponentBoard;

	private JButton connectButton;
	private JButton disconnectButton;

	private Ship destroyer;
	private Ship submarine;
	private Ship cruiser;
	private Ship battleship;
	private Ship carrier;

	private final int SQUARE_SIZE = 100;
	private final int HORIZONTAL = 1;
	private final int DESTROYER_SIZE = 2;
	private final int SUBMARINE_SIZE = 3;
	private final int CRUISER_SIZE = 3;
	private final int BATTLESHIP_SIZE = 4;
	private final int CARRIER_SIZE = 5;
	private final int SEA = 0;
	private final int SHIP = 1;
	private final int MISS = 2;
	private final int HIT = 3;
	private final int YOU = 1;

	private boolean yourTurn = false;
	private boolean playAgain = false;
	private boolean youWin = false;
	private boolean firstGame = true;

	/**
	 * Creates the client GUI.
	 * 
	 */
	public ClientGUI()
	{
		this.frame = new JFrame("Battleship Client");

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new BorderLayout());

		JPanel gamePanel = this.gamePanel();
		this.frame.add(gamePanel, BorderLayout.CENTER);

		JPanel chatPanel = this.createChatPanel();
		this.frame.add(chatPanel, BorderLayout.EAST);

		JPanel connectionPanel = this.createConnectionPanel();
		this.frame.add(connectionPanel, BorderLayout.SOUTH);

		this.frame.pack();
	}

	/**
	 * Creates the game panel.
	 * 
	 * @return JPanel for game board.
	 */
	private JPanel gamePanel()
	{
		JPanel panel = new JPanel(new BorderLayout());

		JPanel opponentPanel = this.createOpponentPanel();
		panel.add(opponentPanel, BorderLayout.NORTH);

		JLabel centerLine = new JLabel();
		panel.add(centerLine, BorderLayout.CENTER);

		JPanel yourPanel = this.createYourPanel();
		panel.add(yourPanel, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the chat panel.
	 * 
	 * @return JPanel that will create a chat list.
	 */
	private JPanel createChatPanel()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(400, 400));
		this.chatListModel = new DefaultListModel();
		this.chatList = new JList(this.chatListModel);

		JLabel chatTitle = new JLabel("Chat", SwingConstants.CENTER);

		panel.add(chatTitle, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(this.chatList);
		panel.add(scrollPane, BorderLayout.CENTER);

		JPanel inputButtonPanel = new JPanel(new BorderLayout());

		JTextField textField = new JTextField();

		inputButtonPanel.add(textField, BorderLayout.CENTER);

		JButton sendButton = new JButton("Send");

		sendButton.addActionListener((ActionEvent evt) ->
		{
			// Get the text to send from the text field.
			String text = textField.getText();

			// Create a Message object.
			Message send = new Message(this.username, text);

			try
			{
				// Send Message to the server.
				this.objectOutputStream.writeObject(send);
				this.objectOutputStream.reset();

				// If it's sent successfully, clear the text field.
				textField.setText("");

				// If it's sent successfully, add message to chat list.
				this.addMessage(send.toString());
			}
			catch (IOException e)
			{
				e.printStackTrace();
				this.addMessage("Unable to send message.");
			}

		});

		inputButtonPanel.add(sendButton, BorderLayout.EAST);

		panel.add(inputButtonPanel, BorderLayout.SOUTH);

		return panel;
	}

	/**
	 * Creates the connection/disconnection button panel.
	 * 
	 * @return JPanel will create connection/disconnection button.
	 */
	private JPanel createConnectionPanel()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));

		connectButton = new JButton("Connect");

		connectButton.addActionListener((ActionEvent evt) ->
		{
			String ip = JOptionPane.showInputDialog(this.frame, "Enter ip address or hostname:");
			int port = Integer.parseInt(JOptionPane.showInputDialog(this.frame, "Enter port:"));

			try
			{
				socket = new Socket(ip, port);

				OutputStream outputStream = socket.getOutputStream();
				objectOutputStream = new ObjectOutputStream(outputStream);

				InputStream inputStream = socket.getInputStream();
				objectInputStream = new ObjectInputStream(inputStream);

				ServerHandler serverHandler = new ServerHandler(this, socket, objectInputStream);
				Thread thread = new Thread(serverHandler);
				thread.start();

				this.addMessage("Connected!");
				disconnectButton.setEnabled(true);
				connectButton.setEnabled(false);
			}
			catch (IOException e)
			{
				// e.printStackTrace();
				this.addMessage("Unable to connect");
			}
		});

		panel.add(connectButton);

		disconnectButton = new JButton("Disconnect");
		disconnectButton.setEnabled(false);

		disconnectButton.addActionListener((ActionEvent evt) ->
		{
			try
			{
				this.objectInputStream.close();
				this.objectOutputStream.close();
				this.socket.close();

				this.addMessage("Disconnected!");
				disconnectButton.setEnabled(false);
				connectButton.setEnabled(true);
			}
			catch (IOException e)
			{
				// e.printStackTrace();
				this.addMessage("Unable to disconnect");
			}
		});

		panel.add(disconnectButton);

		return panel;
	}

	/**
	 * Add a message to the JList.
	 * 
	 * @param message The message
	 */
	public void addMessage(String message)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				chatListModel.addElement(message);
			}
		});
	}

	/**
	 * Ask user input their name and display ClientGUI.
	 * 
	 */
	public void display()
	{
		this.frame.setVisible(true);
		this.username = JOptionPane.showInputDialog(this.frame, "Enter username: ");
		this.frame.setTitle("Battleship client | Username: " + this.username);
	}

	/**
	 * Creates the opponent board panel.
	 * 
	 * @return JPanel will create board for opponent.
	 */
	private JPanel createOpponentPanel()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(180, 200));

		// create title
		JLabel boardTitle = new JLabel("Opponent Squares", SwingConstants.CENTER);
		boardTitle.setOpaque(true);
		boardTitle.setBackground(Color.black);
		boardTitle.setForeground(Color.white);
		panel.add(boardTitle, BorderLayout.NORTH);

		// create square
		JPanel opponentSquaresPanel = new JPanel(new GridLayout(0, 10));

		opponentBoard = new ArrayList<JButton>();

		for (int i = 0; i < SQUARE_SIZE; i++)
		{
			String buttonNum = "" + i;
			JButton square = new JButton(buttonNum);
			square.setFont(new Font("serif", Font.PLAIN, 0));
			square.setBackground(Color.blue);
			opponentSquaresPanel.add(square);
			opponentBoard.add(square);

			square.addActionListener((ActionEvent evt) ->
			{
				if (yourTurn == true)
				{
					Missile missile = new Missile(this.username, evt.getActionCommand());

					try
					{
						this.objectOutputStream.writeObject(missile);
						this.objectOutputStream.reset();
						yourTurn = false;
						this.addMessage("Now it's your opponent turn");
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			});
		}

		panel.add(opponentSquaresPanel, BorderLayout.CENTER);

		return panel;
	}

	/**
	 * Creates your game board panel.
	 * 
	 * @return JPanel will create game board for you.
	 */
	private JPanel createYourPanel()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(180, 200));
		JLabel boardTitle = new JLabel("Your Squares", SwingConstants.CENTER);
		boardTitle.setOpaque(true);
		boardTitle.setBackground(Color.black);
		boardTitle.setForeground(Color.white);
		panel.add(boardTitle, BorderLayout.NORTH);

		JPanel yourSquaresPanel = new JPanel(new GridLayout(0, 10));

		yourBoard = new ArrayList<JButton>();

		for (int i = 0; i < SQUARE_SIZE; i++)
		{
			JButton square = new JButton();
			square.setBackground(Color.blue);
			square.setEnabled(false);
			yourSquaresPanel.add(square);
			yourBoard.add(square);
		}

		panel.add(yourSquaresPanel, BorderLayout.CENTER);

		return panel;
	}

	
	/**
	 * Handle game behavior
	 * 
	 * @param start Control game to start
	 * @param yourTurn set your turn or not
	 * @param youWin You win previous game or not
	 * @param playAgain You want to play again or not
	 */
	public void gameHandler(boolean start, boolean yourTurn, boolean youWin, boolean playAgain)
	{
		if (start == true)
		{
			gameStart();
			this.addMessage("Game Start!");
		}

		if (yourTurn == true)
		{
			this.yourTurn = true;
			this.addMessage("Now it's your turn");
		}
		else
		{
			this.yourTurn = false;
			this.addMessage("Now it's your opponent turn");
		}

		if (youWin == true)
		{
			youWin();
		}

		if (playAgain == true && this.playAgain == true)
		{
			firstGame = false;
			gameStart();
		}
	}

	/**
	 * handle to missile object
	 * 
	 * @param squareNumString Row and column of the missile
	 */
	public void missileHandler(String squareNumString)
	{
		int squareNum = Integer.parseInt(squareNumString);
		int row = squareNum / 10;
		int column = squareNum % 10;
		Square square;
		switch (yourSquareArray[row][column])
		{
		case SEA:
			yourSquareArray[row][column] = MISS;
			updateYourBoard(row, column, MISS);
			square = new Square(this.username, row, column, MISS);
			try
			{
				this.objectOutputStream.writeObject(square);
				this.objectOutputStream.reset();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}

			this.yourTurn = true;
			this.addMessage("Now it's your turn");
			break;

		case SHIP:
			yourSquareArray[row][column] = HIT;
			updateYourBoard(row, column, HIT);
			square = new Square(this.username, row, column, HIT);
			try
			{
				this.objectOutputStream.writeObject(square);
				this.objectOutputStream.reset();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}

			yourTurn = checkAllShip();
			if (yourTurn == true)
			{
				this.addMessage("Now it's your turn");
			}
			break;
		}
	}

	/**
	 * Check all ship sink or not
	 * 
	 * @return all ship sink or not
	 */
	public boolean checkAllShip()
	{
		boolean yourTurn;
		checkShip(destroyer);
		checkShip(submarine);
		checkShip(cruiser);
		checkShip(battleship);
		checkShip(carrier);

		if (destroyer.isSink() && submarine.isSink() && cruiser.isSink() && battleship.isSink() && carrier.isSink())
		{
			yourTurn = false;
			youLose();
		}
		else
		{
			yourTurn = true;
		}
		return yourTurn;
	}

	/**
	 * Execute when you lose
	 */
	public void youLose()
	{
		Game youWinTheGame = new Game(false, false, true, false);
		try
		{
			this.objectOutputStream.writeObject(youWinTheGame);
			this.objectOutputStream.reset();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		youWin = false;

		int result = JOptionPane.showConfirmDialog(this.frame, "You Lose! Play again?", "Game Over",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION)
		{
			this.playAgain = true;
			Game continuePlay = new Game(false, false, false, true);
			try
			{
				this.objectOutputStream.writeObject(continuePlay);
				this.objectOutputStream.reset();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		if (result == JOptionPane.NO_OPTION)
		{
			this.playAgain = false;
		}

	}

	/**
	 * Execute when you win
	 */
	public void youWin()
	{
		youWin = true;

		int result = JOptionPane.showConfirmDialog(this.frame, "You Win! Play again?", "Game Over",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION)
		{
			this.playAgain = true;
			Game continuePlay = new Game(false, false, false, true);
			try
			{
				this.objectOutputStream.writeObject(continuePlay);
				this.objectOutputStream.reset();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		if (result == JOptionPane.NO_OPTION)
		{
			this.playAgain = false;
		}
	}

	
	/**
	 * Check ship sink or not
	 * 
	 * @param ship The ship which will be checked
	 */
	public void checkShip(Ship ship)
	{
		int hitCount = 0;
		if (!ship.isSink())
		{
			if (ship.getDirection() == HORIZONTAL)
			{
				for (int i = 0; i < ship.getShipSize(); i++)
				{
					if (yourSquareArray[ship.getRow()][ship.getColumn() + i] == HIT)
					{
						hitCount++;
					}
				}

				if (hitCount == ship.getShipSize())
				{
					ship.setSink(true);
					this.addMessage("Your " + ship.getShipName() + " is sunk!");
					Message message = new Message(this.username, "You sunk my " + ship.getShipName() + "!");
					try
					{
						this.objectOutputStream.writeObject(message);
						this.objectOutputStream.reset();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			else
			{
				for (int i = 0; i < ship.getShipSize(); i++)
				{
					if (yourSquareArray[ship.getRow() + i][ship.getColumn()] == HIT)
					{
						hitCount++;
					}
				}

				if (hitCount == ship.getShipSize())
				{
					ship.setSink(true);
					this.addMessage("Your " + ship.getShipName() + " is sunk!");
					Message message = new Message(this.username, "You sunk my " + ship.getShipName() + "!");
					try
					{
						this.objectOutputStream.writeObject(message);
						this.objectOutputStream.reset();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * Execute when game start
	 */
	private void gameStart()
	{
		resetYourSquareArray();
		resetYourBoard();
		resetOpponentBoard();
		carrier = setRandomShipPos(CARRIER_SIZE, "carrier");
		battleship = setRandomShipPos(BATTLESHIP_SIZE, "battleship");
		cruiser = setRandomShipPos(CRUISER_SIZE, "cruiser");
		submarine = setRandomShipPos(SUBMARINE_SIZE, "submarine");
		destroyer = setRandomShipPos(DESTROYER_SIZE, "destroyer");
		
		if (!firstGame == true && youWin == true)
		{
			Message restart = new Message(this.username, "Game Restart!");
			this.addMessage("Game Restart!");
			
			yourTurn = true;
			this.addMessage("Now it's your turn");
			Game youGoSecond = new Game(false, false, false, false);
			try
			{
				this.objectOutputStream.writeObject(restart);
				this.objectOutputStream.reset();
				this.objectOutputStream.writeObject(youGoSecond);
				this.objectOutputStream.reset();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
		
		youWin = false;
		playAgain = false;

	}

	/**
	 * Reset your board to initial status
	 */
	private void resetYourBoard()
	{
		for (int i = 0; i < yourBoard.size(); i++)
		{
			yourBoard.get(i).setBackground(Color.blue);
		}
	}

	/**
	 * Reset your opponent's board to initial status
	 */
	private void resetOpponentBoard()
	{
		for (int i = 0; i < opponentBoard.size(); i++)
		{
			opponentBoard.get(i).setBackground(Color.blue);
			opponentBoard.get(i).setEnabled(true);
		}
	}

	/**
	 * Reset your square array to initial status
	 */
	private void resetYourSquareArray()
	{
		for (int i = 0; i < yourSquareArray.length; i++)
		{
			for (int j = 0; j < yourSquareArray[i].length; j++)
			{
				yourSquareArray[i][j] = SEA;
			}
		}
	}

	/**
	 * Update your game board when you get square information
	 * 
	 * @param row The row of the square
	 * @param column The column of the square
	 * @param update The status of the square which have to update
	 */
	private void updateYourBoard(int row, int column, int update)
	{
		switch (update)
		{
		case MISS:
			yourBoard.get(row * 10 + column).setBackground(Color.gray);
			break;
		case HIT:
			yourBoard.get(row * 10 + column).setBackground(Color.red);
			break;
		}

	}

	/**
	 * Update square status of your opponent's board
	 * 
	 * @param row The row of the square
	 * @param column The column of the square
	 * @param status The status of the square which have to update
	 */
	public void squareHandler(int row, int column, int status)
	{
		switch (status)
		{
		case MISS:
			opponentBoard.get(row * 10 + column).setBackground(Color.gray);
			opponentBoard.get(row * 10 + column).setEnabled(false);
			break;
		case HIT:
			opponentBoard.get(row * 10 + column).setBackground(Color.red);
			opponentBoard.get(row * 10 + column).setEnabled(false);
			break;
		}
	}

	/**
	 * Setup ship position according to yourSquareArray
	 * 
	 * @param shipDir The direction of the ship
	 * @param row The row of the ship
	 * @param column The column of the ship
	 * @param shipSize The size of the ship
	 */
	private void setShipPosOnGUI(int shipDir, int row, int column, int shipSize)
	{
		int firstPos = (row * 10) + column;
		if (shipDir == HORIZONTAL)
		{
			for (int i = 0; i < shipSize; i++)
			{
				yourBoard.get(firstPos + i).setBackground(Color.black);
			}
		}
		else
		{
			int nextPos = 0;
			for (int i = 0; i < shipSize; i++)
			{
				yourBoard.get(firstPos + nextPos).setBackground(Color.black);
				nextPos += 10;
			}
		}
	}

	/**
	 * Generate random position for the ship
	 * 
	 * @param shipSize The size of the ship
	 * @param shipName The name of the ship
	 * @return The ship object
	 */
	private Ship setRandomShipPos(int shipSize, String shipName)
	{
		boolean check = false;
		int shipDirection = 0;
		int row = 0;
		int column = 0;
		while (check == false)
		{
			check = true;
			shipDirection = (int) (Math.random() * 2);

			// 1 = horizontal
			if (shipDirection == HORIZONTAL)
			{
				row = (int) (Math.random() * 10);
				column = (int) (Math.random() * (10 - shipSize));

				// check position is okay
				for (int i = 0; i < shipSize; i++)
				{
					if (yourSquareArray[row][column + i] == SHIP)
					{
						check = false;
					}
				}

				// set ship to array
				if (check == true)
				{
					for (int i = 0; i < shipSize; i++)
					{
						yourSquareArray[row][column + i] = SHIP;
					}
					setShipPosOnGUI(shipDirection, row, column, shipSize);
					this.addMessage("Placing " + shipName + " horizontally starting at " + row + "," + column);
				}
			}
			else
			{
				row = (int) (Math.random() * (10 - shipSize));
				column = (int) (Math.random() * 10);

				// check position is okay
				for (int i = 0; i < shipSize; i++)
				{
					if (yourSquareArray[row + i][column] == SHIP)
					{
						check = false;
					}
				}

				// set ship to array
				if (check == true)
				{
					for (int i = 0; i < shipSize; i++)
					{
						yourSquareArray[row + i][column] = SHIP;
					}
					setShipPosOnGUI(shipDirection, row, column, shipSize);
					this.addMessage("Placing " + shipName + " vertically starting at " + row + "," + column);
				}
			}
		}
		return new Ship(shipDirection, row, column, shipSize, false, shipName);
	}
}
