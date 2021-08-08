package sait.bms.application;

import java.io.*;
import java.util.*;
import sait.bms.problemdomain.*;

/**
 * The main program to operate the book management system
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 2/13/2020
 */
public class AppDriver
{

	public static void main(String[] args) throws IOException
	{
		String option = "";
		String filePath = sait.bms.managers.BookManagementSystem.findFilePath(); // finds the path of books.txt
		ArrayList<Book> bookList = new ArrayList<>();
		Scanner keyboard = new Scanner(System.in);

		sait.bms.managers.BookManagementSystem.loadBook(bookList, filePath); // loads books.txt to array

		while (!option.equals("5"))
		{
			option = sait.bms.managers.BookManagementSystem.displayMenu(keyboard); // displays main menu
			switch (option)
			{
				case "1": // checks out a book by ISBN
					sait.bms.managers.BookManagementSystem.checkoutBook(bookList, keyboard);
					break;

				case "2": // finds books by title
					sait.bms.managers.BookManagementSystem.findBookByTitle(bookList, keyboard);
					break;

				case "3": // displays books by type
					sait.bms.managers.BookManagementSystem.displayBooksByType(bookList, keyboard);
					break;

				case "4": // produces random book list
					sait.bms.managers.BookManagementSystem.produceRandomBookList(bookList, keyboard);
					break;

				case "5": // save the array to books.txt and exit
					sait.bms.managers.BookManagementSystem.saveAndExit(bookList, filePath);
					break;

				default:
					System.out.println("Invalid option!");
					System.out.printf("%n");
					break;
			}
		}
	}

}
