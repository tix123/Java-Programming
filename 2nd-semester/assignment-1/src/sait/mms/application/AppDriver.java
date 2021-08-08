package sait.mms.application;

import java.io.*;
import java.util.*;
import sait.mms.problemdomain.Movie;

/**
 * The main program to operate the movie management system
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 1/19/2020
 */

public class AppDriver
{
	public static void main(String[] args) throws IOException
	{
		int option = 0;

		ArrayList<Movie> movieList = new ArrayList<>();
		sait.mms.managers.MovieManagementSystem.loadMovie(movieList);			//Load movies into the movie list
		Scanner keyboard = new Scanner(System.in);
		while (option != 4)
		{
			option = sait.mms.managers.MovieManagementSystem.displayMenu(keyboard);		//Display the system menu and select an option

			switch (option)
			{
				case 1:			// Add a movie into movie list and save it into movie.txt
					sait.mms.managers.MovieManagementSystem.addMovie(movieList,keyboard);
					System.out.println("Added movie to the data file.");
					System.out.printf("%n");
					break;
				
				case 2:			// Ask the user to enter a year and generate a movie list in that year
					sait.mms.managers.MovieManagementSystem.generateMovieInYear(movieList,keyboard);
					break;
				
				case 3:			// Ask the user to enter a number and generate a random movie list
					sait.mms.managers.MovieManagementSystem.generateRandomMovie(movieList,keyboard);
					break;
				
				case 4:			//Save movie list to the movie.txt file and leave the system
					FileWriter file = new FileWriter("res/movies.txt");
					PrintWriter pw = new PrintWriter(file);
					pw.printf("%d,%s,%d", movieList.get(0).getDuration(),movieList.get(0).getTitle(),movieList.get(0).getYear());
					for(int i=1; i<movieList.size(); i++)
					{
						pw.printf("%n%d,%s,%d", movieList.get(i).getDuration(),movieList.get(i).getTitle(),movieList.get(i).getYear());
					}
					file.close();	
					pw.close();	
					break;
					
				default:
					System.out.println("Invalid option!");
					System.out.printf("%n");
					break;	
			}
		}
		keyboard.close();
	}
}
