import java.util.Scanner;
import java.sql.*;

/**
 * The movie management system
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 4/13/2020
 */
public class AppDriver
{
	/**
	 * The server of database
	 */
	private static final String SERVER = "localhost";
	
	/**
	 * The port of database
	 */
	private static final int PORT = 3306;
	
	/**
	 * The name of database
	 */
	private static final String DATABASE = "cprg251";
	
	/**
	 * The user's name of database
	 */
	private static final String USERNAME = "cprg251";
	
	/**
	 * The password of database
	 */
	private static final String PASSWORD = "password";

	public static void main(String[] args)
	{
		final String DB_URL = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE,
				USERNAME, PASSWORD);

		Scanner keyboard = new Scanner(System.in);
		int option = 0;

		while (option != 5)
		{
			System.out.print("Movie Management system\n"
								+ "1 Add New Movie and Save\n"
								+ "2 Generate List of Movies Released in a Year\n"
								+ "3 Generate List of Random Movies\n"
								+ "4 Delete a Movie by ID\n"
								+ "5 Exit\n"
								+ "\n"
								+ "Enter an option: ");

			option = keyboard.nextInt();
			System.out.printf("%n");

			switch (option)
			{
				case 1:
					addMovie(DB_URL, keyboard);
					break;
				case 2:
					findMovieByYear(DB_URL, keyboard);
					break;
				case 3:
					generateRandomMovies(DB_URL, keyboard);
					break;
				case 4:
					deleteMovie(DB_URL, keyboard);
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid option!");
					System.out.printf("%n");
					break;
			}
		}
		keyboard.close();
	}

	/**
	 * Ask the user to enter a number and generate a random movie list
	 * @param DB_URL The URL of Maria database 
	 * @param keyboard The scanner for the system input
	 */
	public static void generateRandomMovies(String DB_URL, Scanner keyboard)
	{
		int num;
		try
		{
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();

			System.out.print("Enter number of movies: ");
			num = keyboard.nextInt();

			String sqlStatement = "SELECT SUM(duration),title,year FROM movies NATURAL JOIN (SELECT id FROM movies ORDER BY RAND() LIMIT "
									+ num
									+ ") AS t GROUP BY title WITH ROLLUP;";

			ResultSet result = stmt.executeQuery(sqlStatement);

			System.out.printf("%n");
			System.out.println("Movie List");
			System.out.printf("%-12s%-6s%s%n", "Duration", "Year", "Title");

			while (result.next())
			{
				int duration = result.getInt("SUM(duration)");
				String title = result.getString("title");
				String year = result.getString("Year").substring(0, 4);

				if (title == null)
				{
					System.out.printf("%n");
					System.out.printf("Total duration: %d minutes%n", duration);
				}
				else
				{
					System.out.printf("%-12d%-6s%s%n", duration, year, title);
				}
			}

			System.out.printf("%n");

			conn.close();
			stmt.close();
			result.close();
		}
		catch (SQLException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}

	}

	/**
	 * Ask the user to enter a year and generate a movie list in that year
	 * @param DB_URL The URL of Maria database 
	 * @param keyboard The scanner for the system input
	 */
	public static void findMovieByYear(String DB_URL, Scanner keyboard)
	{
		String yearInput;
		try
		{
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();

			System.out.print("Enter the year to find movies: ");
			yearInput = Integer.toString(keyboard.nextInt());
			// String sqlStatement = "SELECT * FROM movies WHERE year = '" + year + "'";
			String sqlStatement = "SELECT sum(duration),year,title FROM movies WHERE year = '"
									+ yearInput
									+ "' GROUP BY title WITH ROLLUP";

			ResultSet result = stmt.executeQuery(sqlStatement);

			System.out.printf("%n");
			System.out.println("Movie List");
			System.out.printf("%-12s%-6s%s%n", "Duration", "Year", "Title");

			while (result.next())
			{
				int duration = result.getInt("SUM(duration)");
				String title = result.getString("title");
				String year = result.getString("Year").substring(0, 4);

				if (title == null)
				{
					System.out.printf("%n");
					System.out.printf("Total duration: %d minutes%n", duration);
				}
				else
				{
					System.out.printf("%-12d%-6s%s%n", duration, year, title);
				}
			}

			System.out.printf("%n");

			conn.close();
			stmt.close();
			result.close();
		}
		catch (SQLException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}

	}

	/**
	 * Delete a movie into the movie list
	 * @param DB_URL The URL of Maria database 
	 * @param keyboard The scanner for the system input
	 */
	public static void deleteMovie(String DB_URL, Scanner keyboard)
	{
		int id;
		String option;
		
		try
		{
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();

			System.out.print("Enter the movie ID to delete: ");
			id = keyboard.nextInt();

			String sqlStatement = "SELECT * from movies WHERE id = " + id;
			ResultSet result = stmt.executeQuery(sqlStatement);
			System.out.printf("%n");
			
			if(result.next())
			{
				System.out.println("ID: " + result.getInt("id"));
				System.out.println("Druation: " + result.getInt("duration"));
				System.out.println("Title: " + result.getString("title"));
				System.out.println("Year: " + result.getString("year").substring(0,4));
				System.out.printf("%n");
				System.out.print("Are you sure to delete this movie. (Y/N): ");
				keyboard.nextLine();
				option = keyboard.nextLine();
				
				switch(option)
				{
					case "y":
					case "Y":
						sqlStatement = "DELETE FROM movies WHERE id = " + id;
						stmt.executeUpdate(sqlStatement);

						System.out.println("Movie ID " + id + " is deleted.");
						break;
						
					case "n":
					case "N":
						System.out.println("Movie ID " + id + " does not delete. Return to main menu.");
						break;
						
					default:
						System.out.println("Invalid option!");
						break;	
				}
				
			}
			else
			{
				System.out.println("Movie ID " + id + " does not exist.");
			}
			
			
			
			System.out.printf("%n");

			conn.close();
			stmt.close();
		}
		catch (SQLException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	/**
	 * Add a movie into the movie list
	 * @param DB_URL The URL of Maria database 
	 * @param keyboard The scanner for the system input
	 */
	public static void addMovie(String DB_URL, Scanner keyboard)
	{
		int lastId = 0;
		int duration;
		String title;
		String year;

		try
		{
			Connection conn = DriverManager.getConnection(DB_URL);
			Statement stmt = conn.createStatement();

			String sqlStatement = "SELECT * FROM movies ORDER BY id";
			ResultSet result = stmt.executeQuery(sqlStatement);

			while (result.next())
			{
				lastId = result.getInt("id");
			}

			System.out.print("Enter duration: ");
			duration = keyboard.nextInt();
			while (duration <= 0)
			{
				System.out.println("The duration cannot be zero or a negative number!");
				System.out.print("Enter duration: ");
				duration = keyboard.nextInt();
			}

			keyboard.nextLine();

			System.out.print("Enter movie title: ");
			title = keyboard.nextLine();
			while (title.equals(""))
			{
				System.out.println("The title cannot be empty!");
				System.out.print("Enter movie title: ");
				title = keyboard.nextLine();
			}

			System.out.print("Enter year: ");
			year = keyboard.nextLine();
			while (Integer.parseInt(year) <= 0)
			{
				System.out.println("The year cannot be zero or a negative number!");
				System.out.print("Enter year: ");
				year = keyboard.nextLine();
			}

			System.out.println("Saving movies... ");

			sqlStatement = "INSERT INTO movies "
							+ "(id, duration, title, year) "
							+ "VALUES ("
							+ (lastId + 1)
							+ ", "
							+ duration
							+ ", '"
							+ title
							+ "', '"
							+ year
							+ "')";

			stmt.executeUpdate(sqlStatement);

			System.out.println("Added movie to the data file.");
			System.out.printf("%n");

			conn.close();
			stmt.close();
			result.close();
		}
		catch (SQLException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
	}

}
