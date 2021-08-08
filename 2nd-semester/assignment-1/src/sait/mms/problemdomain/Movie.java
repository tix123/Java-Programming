package sait.mms.problemdomain;

/**
 * This program defines the movie object and its methods
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 1/19/2020
 */

public class Movie
{
	private int duration;
	private String title;
	private int year;
	
	/**
	 * Generate a movie object
	 * @param duration The duration of the movie
	 * @param title The Title of the movie
	 * @param year The publishing year of the movie
	 */
	public Movie(int duration, String title, int year)
	{
		this.duration = duration;
		this.title = title;
		this.year = year;
	}
	
	/**
	 * Get the duration of the movie object
	 * @return The duration of the movie
	 */
	public int getDuration()
	{
		return duration;
	}
	
	/**
	 * Get the title of the movie object
	 * @return The title of the movie
	 */
	public String getTitle()
	{
		return title;
	}
	
	/**
	 * Get the publishing year of the movie
	 * @return The publishing year of the movie
	 */
	public int getYear()
	{
		return year;
	}
	
	/**
	 * The display format of the movie object
	 */
	public String toString()
	{
		return String.format("%-12d%-6d%s%n",duration,year,title);
	}
}
