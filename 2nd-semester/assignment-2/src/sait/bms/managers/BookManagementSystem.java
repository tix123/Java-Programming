package sait.bms.managers;

import java.io.*;
import java.util.*;
import sait.bms.problemdomain.*;

/**
 * This program is the operation methods of the book management
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 2/13/2020
 */
public class BookManagementSystem
{
	/**
	 * Finds the path of books.txt
	 * 
	 * @return file path
	 */
	public static String findFilePath()
	{
		int slashPos = 0, lastSlashPos = 0;
		String filePath;

		filePath = System.getProperty("java.class.path");
		while (slashPos != -1)
		{
			lastSlashPos = slashPos;
			slashPos = filePath.indexOf(File.separator, slashPos + 1);
		}
		if (lastSlashPos == 0)
		{
			filePath = "res" + File.separator + "books.txt";
		}
		else
		{
			filePath = filePath.substring(0, lastSlashPos + 1);
			filePath += "res" + File.separator + "books.txt";
		}
		return filePath;
	}

	/**
	 * Load all books from books.txt to an array list
	 * 
	 * @param bookList An array list which saves all the books
	 * @param filePath the path of books.txt
	 * @throws IOException When the program fails to access the file
	 */
	public static void loadBook(ArrayList<Book> bookList, String filePath) throws IOException
	{
		String callNumber, title, authors, publisher, format, diet, genre, frequency;

		int available, total, year, bookType;

		long isbn;
		String[] bookInfoArray;

		File f = new File(filePath);
		Scanner s = new Scanner(f);
		while (s.hasNextLine())
		{
			bookInfoArray = s.nextLine().split(";");
			isbn = Long.parseLong(bookInfoArray[0]);
			callNumber = bookInfoArray[1];
			available = Integer.parseInt(bookInfoArray[2]);
			total = Integer.parseInt(bookInfoArray[3]);
			title = bookInfoArray[4];

			bookType = (int) (isbn % 10);
			switch (bookType)
			{
				case 0:
				case 1:
					authors = bookInfoArray[5];
					format = bookInfoArray[6];
					bookList.add(new ChildrensBook(isbn, callNumber, available, total, title, authors, format));
					break;

				case 2:
				case 3:
					publisher = bookInfoArray[5];
					diet = bookInfoArray[6];
					bookList.add(new Cookbook(isbn, callNumber, available, total, title, publisher, diet));
					break;

				case 4:
				case 5:
				case 6:
				case 7:
					authors = bookInfoArray[5];
					year = Integer.parseInt(bookInfoArray[6]);
					genre = bookInfoArray[7];
					bookList.add(new Paperback(isbn, callNumber, available, total, title, authors, year, genre));
					break;

				case 8:
				case 9:
					frequency = bookInfoArray[5];
					bookList.add(new Periodical(isbn, callNumber, available, total, title, frequency));
					break;
			}
		}
		s.close();
	}

	/**
	 * Display system menu and ask the user to choose one option
	 * 
	 * @param keyboard The scanner for the system input
	 * @return The option that the user chose
	 */
	public static String displayMenu(Scanner keyboard)
	{
		String option;
		System.out.print("Welcome in ABC Book Company: How May We Assist You?\n"
							+ "1     Checkout Book\n"
							+ "2     Find Books by Title\n"
							+ "3     Display Books by Type\n"
							+ "4     Produce Random Book List\n"
							+ "5     Save & Exit"
							+ "\n"
							+ "Enter option: ");
		option = keyboard.nextLine();
		return option;
	}

	/**
	 * Ask the user to input ISBN and check out the book
	 * 
	 * @param bookList An array list which saves all the books
	 * @param keyboard The scanner for the system input
	 */
	public static void checkoutBook(ArrayList<Book> bookList, Scanner keyboard)
	{
		long isbn;
		String isbnString;
		boolean notFound = true;
		System.out.print("Enter ISBN of book: ");
		isbnString = keyboard.nextLine();
		if (isbnString.matches("[0-9]+"))
		{
			isbn = Long.parseLong(isbnString);
			for (int i = 0; i < bookList.size(); i++)
			{
				if (bookList.get(i).getIsbn() == isbn)
				{
					notFound = false;
					if (bookList.get(i).getAvailable() != 0)
					{
						System.out.printf("%n");
						System.out.println("The book \"" + bookList.get(i).getTitle() + "\" has been checked out.");
						System.out.println("It can be located using a call number: " + bookList.get(i).getCallNumber());
						bookList.get(i).setAvailable(bookList.get(i).getAvailable() - 1);
					}
					else
					{
						System.out.println("This book is not available.");
					}
				}
			}
			if (notFound)
			{
				System.out.println("This ISBN does not exist.");
			}
		}
		else
		{
			System.out.println("Invalid Input! Digital number only.");
		}
		System.out.printf("%n");
	}

	/**
	 * Ask the user to input and search book by title
	 * 
	 * @param bookList An array list which saves all the books
	 * @param keyboard The scanner for the system input
	 */
	public static void findBookByTitle(ArrayList<Book> bookList, Scanner keyboard)
	{
		String input;
		boolean notFound = true;
		System.out.print("Enter title to search for: ");
		input = keyboard.nextLine();
		System.out.printf("%n");
		System.out.println("Matching books: ");
		for (int i = 0; i < bookList.size(); i++)
		{
			if (bookList.get(i).getTitle().toUpperCase().contains(input.toUpperCase()))
			{
				notFound = false;
				System.out.print(bookList.get(i));
			}
		}
		if (notFound)
		{
			System.out.println("No matching result.");
		}
		System.out.printf("%n");
	}

	/**
	 * Ask the user to input a book type and display books by the type
	 * 
	 * @param bookList An array list which saves all the books
	 * @param keyboard The scanner for the system input
	 */
	public static void displayBooksByType(ArrayList<Book> bookList, Scanner keyboard)
	{
		String bookType;
		String format, diet, genre, frequency;
		boolean notFound = true;

		System.out.print("#     Type\n"
							+ "1     Children's Books\n"
							+ "2     Cookbooks\n"
							+ "3     Paperbacks\n"
							+ "4     Periodicals"
							+ "\n"
							+ "Enter type of book: ");
		bookType = keyboard.nextLine();

		switch (bookType)
		{
			case "1":
				System.out.print("Enter a format (P for Picture book, E for Early Readers, C for Chapter book): ");
				format = keyboard.nextLine();
				System.out.printf("%n");
				System.out.println("Matching books: ");

				switch (format)
				{
					case "P":
					case "p":
					case "E":
					case "e":
					case "C":
					case "c":
						for (int i = 0; i < bookList.size(); i++)
						{
							if (bookList.get(i) instanceof ChildrensBook)
							{
								if (((ChildrensBook) bookList.get(i)).getFormat().equalsIgnoreCase(format))
								{
									notFound = false;
									System.out.print(bookList.get(i));
								}
							}
						}
						if (notFound)
						{
							System.out.println("No matching result.");
						}
						break;

					default:
						System.out.println("Invalid option!");
						break;
				}
				break;

			case "2":

				System.out.print("Enter a diet (D for Diabetic, V for Vegetarian, G for Gluten-free, I for International, N for None): ");
				diet = keyboard.nextLine();
				System.out.printf("%n");
				System.out.println("Matching books: ");

				switch (diet)
				{
					case "D":
					case "d":
					case "V":
					case "v":
					case "G":
					case "g":
					case "i":
					case "I":
					case "N":
					case "n":
						for (int i = 0; i < bookList.size(); i++)
						{
							if (bookList.get(i) instanceof Cookbook)
							{
								if (((Cookbook) bookList.get(i)).getDiet().equalsIgnoreCase(diet))
								{
									notFound = false;
									System.out.print(bookList.get(i));
								}
							}
						}
						if (notFound)
						{
							System.out.println("No matching result.");
						}
						break;

					default:
						System.out.println("Invalid option!");
						break;
				}
				break;

			case "3":

				System.out.print("Enter a genre (A for Adventure, D for Drama, E for Education, C for Classic, F for Fantasy, S for Science Fiction): ");
				genre = keyboard.nextLine();
				System.out.printf("%n");
				System.out.println("Matching books: ");

				switch (genre)
				{
					case "A":
					case "a":
					case "D":
					case "d":
					case "E":
					case "e":
					case "C":
					case "c":
					case "F":
					case "f":
					case "S":
					case "s":
						for (int i = 0; i < bookList.size(); i++)
						{
							if (bookList.get(i) instanceof Paperback)
							{
								if (((Paperback) bookList.get(i)).getGenre().equalsIgnoreCase(genre))
								{
									notFound = false;
									System.out.print(bookList.get(i));
								}
							}
						}
						if (notFound)
						{
							System.out.println("No matching result.");
						}
						break;

					default:
						System.out.println("Invalid option!");
						break;
				}
				break;

			case "4":

				System.out.print("Enter a frequency (D for Daily, W for Weekly, M for Monthly, B for Bimonthly, or Q for Quarterly): ");
				frequency = keyboard.nextLine();
				System.out.printf("%n");
				System.out.println("Matching books: ");

				switch (frequency)
				{
					case "D":
					case "d":
					case "W":
					case "w":
					case "M":
					case "m":
					case "B":
					case "b":
					case "Q":
					case "q":
						for (int i = 0; i < bookList.size(); i++)
						{
							if (bookList.get(i) instanceof Periodical)
							{
								if (((Periodical) bookList.get(i)).getFrequency().equalsIgnoreCase(frequency))
								{
									notFound = false;
									System.out.print(bookList.get(i));
								}
							}
						}
						if (notFound)
						{
							System.out.println("No matching result.");
						}
						break;

					default:
						System.out.println("Invalid option!");
						break;
				}
				break;

			default:
				System.out.println("Invalid option!");
				break;
		}
		System.out.printf("%n");
	}

	/**
	 * Ask the user to enter a number and generate a random book list
	 * 
	 * @param bookList An array list which saves all the books
	 * @param keyboard The scanner for the system input
	 */
	public static void produceRandomBookList(ArrayList<Book> bookList, Scanner keyboard)
	{
		int number;
		String numberString;
		System.out.print("Enter number of books: ");
		numberString = keyboard.nextLine();
		System.out.printf("%n");
		System.out.println("Random books: ");
		if (numberString.matches("[0-9]+"))
		{
			number = Integer.parseInt(numberString);
			Collections.shuffle(bookList);
			for (int i = 0; i < number; i++)
			{
				System.out.print(bookList.get(i));
			}
		}
		else
		{
			System.out.println("Invalid input! Digital number only.");
		}
		System.out.printf("%n");
	}

	/**
	 * Save book data from the array to the book.txt file and exit the system
	 * 
	 * @param bookList An array list which saves all the books
	 * @param filePath The path of books.txt
	 * @throws IOException When the program fails to access the file
	 */
	public static void saveAndExit(ArrayList<Book> bookList, String filePath) throws IOException
	{
		FileWriter file = new FileWriter(filePath);
		PrintWriter pw = new PrintWriter(file);
		for (int i = 0; i < bookList.size(); i++)
		{
			if (i == 0)
			{
				pw.printf("%d;%s;%d;%d;%s;", bookList.get(i).getIsbn(), bookList.get(i).getCallNumber(),
						bookList.get(i).getAvailable(), bookList.get(i).getTotal(), bookList.get(i).getTitle());
			}
			else
			{
				pw.printf("%n%d;%s;%d;%d;%s;", bookList.get(i).getIsbn(), bookList.get(i).getCallNumber(), 
						bookList.get(i).getAvailable(), bookList.get(i).getTotal(), bookList.get(i).getTitle());
			}
			if (bookList.get(i) instanceof ChildrensBook)
			{
				pw.printf("%s;%s", ((ChildrensBook) bookList.get(i)).getAuthors(),
								   ((ChildrensBook) bookList.get(i)).getFormat());
			}
			if (bookList.get(i) instanceof Cookbook)
			{
				pw.printf("%s;%s", ((Cookbook) bookList.get(i)).getPublisher(),
								   ((Cookbook) bookList.get(i)).getDiet());
			}
			if (bookList.get(i) instanceof Paperback)
			{
				pw.printf("%s;%d;%s", ((Paperback) bookList.get(i)).getAuthors(),
									  ((Paperback) bookList.get(i)).getYear(),
									  ((Paperback) bookList.get(i)).getGenre());
			}
			if (bookList.get(i) instanceof Periodical)
			{
				pw.printf("%s", ((Periodical) bookList.get(i)).getFrequency());
			}
		}
		pw.close();
		file.close();
	}
}
