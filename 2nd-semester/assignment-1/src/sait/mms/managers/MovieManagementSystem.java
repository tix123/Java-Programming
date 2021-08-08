package sait.mms.managers;

import java.io.*;
import java.util.*;
import sait.mms.problemdomain.Movie;

/**
 * This program is the operation methods of the movie management
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 1/19/2020
 */

public class MovieManagementSystem
{
	/**
	 * Display system menu and ask the user to choose one option
	 * @param keyboard The scanner for the system input
	 * @return The option that the user choose
	 */
	public static int displayMenu(Scanner keyboard)
	{
		int option;
		
		System.out.print("Movie Management system\n"
				       + "1 Add New Movie and Save\n"
		               + "2 Generate List of Movies Released in a Year\n"
		               + "3 Generate List of Random Movies\n"
		               + "4 Exit\n"
		               + "\n"
		               + "Enter an option: ");
		
		option = keyboard.nextInt();
		System.out.printf("%n");
		
		return option;
	}
	
	/**
	 * Add a movie into the movie list and save it into movie.txt
	 * @param movieList An array list which saves all the movies 
	 * @param keyboard The scanner for the system input
	 * @throws IOException When the program fails to access the file
	 */
	public static void addMovie(ArrayList<Movie> movieList,Scanner keyboard) throws IOException
	{
		String title;
		int duration;
		int year;
		
		System.out.print("Enter duration: ");
		duration = keyboard.nextInt();
		while(duration<=0)
		{
			System.out.println("The duration cannot be zero or a negative number!");
			System.out.print("Enter duration: ");
			duration = keyboard.nextInt();
		}
		
		keyboard.nextLine(); 				// absorb a next line
		
		System.out.print("Enter movie title: ");
		title = keyboard.nextLine();
		while(title.equals(""))
		{
			System.out.println("The title cannot be empty!");
			System.out.print("Enter movie title: ");
			title = keyboard.nextLine();
		}
		
		System.out.print("Enter year: ");
		year = keyboard.nextInt();
		while(year<=0)
		{
			System.out.println("The year cannot be zero or a negative number!");
			System.out.print("Enter year: ");
			year = keyboard.nextInt();
		}
		
		System.out.println("Saving movies... ");
		
		FileWriter file = new FileWriter("res/movies.txt",true);
		PrintWriter pw = new PrintWriter(file);
		pw.printf("%n%d,%s,%d",duration,title,year);
		movieList.add(new Movie(duration,title,year));
		
		file.close();
		pw.close();
		
	}
	
	/**
	 * Ask the user to enter a year and generate a movie list in that year
	 * @param movieList An array list which saves all the movies 
	 * @param keyboard The scanner for the system input
	 */
	public static void generateMovieInYear(ArrayList<Movie> movieList,Scanner keyboard)
	{
		int totalDuration = 0;
		int year;
		
		System.out.print("Enter in year: ");
		year = keyboard.nextInt();
		System.out.printf("%n");
		System.out.println("Movie List");
		System.out.printf("%-12s%-6s%s%n","Duration","Year","Title");
		for(int i = 0 ; i < movieList.size(); i++)
		{
			if(year==movieList.get(i).getYear())
			{
				System.out.print(movieList.get(i));
				totalDuration += movieList.get(i).getDuration();
			}
		}
		System.out.printf("%nTotal duration: %d minutes%n",totalDuration);
		System.out.printf("%n");
	}
	
	/**
	 * Ask the user to enter a number and generate a random movie list
	 * @param movieList An array list which saves all the movies
	 * @param keyboard The scanner for the system input
	 */
	public static void generateRandomMovie(ArrayList<Movie> movieList,Scanner keyboard)
	{
		int totalDuration = 0;
		int randomNumber;
		
		System.out.print("Enter number of movies: ");
		randomNumber = keyboard.nextInt();
		System.out.printf("%n");
		Collections.shuffle(movieList);

		System.out.println("Movie List");
		System.out.printf("%-12s%-6s%s%n","Duration","Year","Title");
		for(int i = 0 ; i < randomNumber; i++)
		{
				System.out.print(movieList.get(i));
				totalDuration += movieList.get(i).getDuration();
		}
		System.out.printf("%nTotal duration: %d minutes%n",totalDuration);
		System.out.printf("%n");
	}
	
	/**
	 * Load all movies from movie.txt to an array list
	 * @param movieList An array list which saves all the movies
	 * @throws IOException When the program fails to access the file
	 */
	public static void loadMovie(ArrayList<Movie> movieList) throws IOException
	{
		int commaPos1,
		    commaPos2,
		    duration,
		    year;
		String temp,
		       title;
		
		File sourceFile = new File("res/movies.txt");
		Scanner fileInput = new Scanner(sourceFile);
		while(fileInput.hasNextLine())
		{
			temp = fileInput.nextLine();
			commaPos1 = temp.indexOf(",");
			commaPos2 = temp.indexOf(",",commaPos1+1);
			duration = Integer.parseInt(temp.substring(0,commaPos1));
			title = temp.substring(commaPos1+1,commaPos2);
			year = Integer.parseInt(temp.substring(commaPos2+1));
			movieList.add(new Movie(duration,title,year));
		}
		fileInput.close();
	}
}
