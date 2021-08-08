package server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import problemdomain.ClientConnection;
import problemdomain.Message;

/**
 * Create server GUI.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/7/2020
 */
public class ServerGUI {
	
	private JFrame frame;

	private static DefaultListModel chatListModel;
	private JList chatList;

	
	/**
	 * Create server GUI
	 * 
	 * @throws IOException when server socket has an error
	 */
	public ServerGUI() throws IOException {
		this.frame = new JFrame("Battleship Server");

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new BorderLayout());

		JPanel messagePanel = this.createMessagePanel();
		this.frame.add(messagePanel, BorderLayout.CENTER);

		this.frame.setSize(400, 400);
		this.frame.setVisible(true);


		ServerSocket listener = new ServerSocket(1234);

		this.addMessage(currentTime() + "Server start (port 1234)");
		

		ArrayList<ClientConnection> connections = new ArrayList<>();

		while (listener.isBound()) {
			try {
				Socket client = listener.accept();

				this.addMessage(currentTime() + "Client connected.");

				InputStream inputStream = client.getInputStream();
				ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

				OutputStream outputStream = client.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

				ClientConnection connection = new ClientConnection(client, objectInputStream, objectOutputStream);

				connections.add(connection);

				if (connections.size() % 2 == 0) {
					ClientConnection connection1 = connections.get(0);
					ClientConnection connection2 = connections.get(1);

					// Spin up a thread to handle connections
					ClientHandler clientHandler = new ClientHandler(connection1, connection2);
					Thread chatThread = new Thread(clientHandler);
					chatThread.start();
					
					// Remove connections array list
					connections.remove(connection1);
					connections.remove(connection2);
				}

			} catch (IOException ex) {

			}
		}

		listener.close();
	}

	/**
	 * Add a message to the JList.
	 * 
	 * @param message The message
	 */
	public static void addMessage(String message) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run()
			{
				chatListModel.addElement(message);
			}
		});
	}
	
	
	/**
	 * Transfer current time to string format
	 * 
	 * @return Current time in a human readable format
	 */
	private String currentTime()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.format("[%s] ", format.format(new Date()));
	}
	
	/**
	 * Create message panel
	 * 
	 * @return JPanel that will create a chat list
	 */
	private JPanel createMessagePanel() {
		JPanel panel = new JPanel(new BorderLayout());

		this.chatListModel = new DefaultListModel();
		this.chatList = new JList(this.chatListModel);

		JScrollPane scrollPane = new JScrollPane(this.chatList);
		panel.add(scrollPane, BorderLayout.CENTER);

		JButton exitButton = new JButton("EXIT");
		panel.add(exitButton, BorderLayout.SOUTH);

		exitButton.addActionListener((ActionEvent evt) -> {
		System.exit(0);
			
		});
		
		return panel;
	}


}
