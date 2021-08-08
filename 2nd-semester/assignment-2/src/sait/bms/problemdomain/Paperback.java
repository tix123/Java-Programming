package sait.bms.problemdomain;

/**
 * This program defines the Paperback object and its methods
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 2/13/2020
 */
public class Paperback extends Book
{
	private String authors;
	private int year;
	private String genre;

	/**
	 * Generate a Paperback object
	 * 
	 * @param isbn       The ISBN of the Paperback
	 * @param callNumber The call number of the Paperback
	 * @param available  The available of the Paperback
	 * @param total      The total of the Paperback
	 * @param title      The title of the Paperback
	 * @param authors    The authors of the Paperback
	 * @param year       The year of the Paperback
	 * @param genre      The genre of the Paperback
	 */
	public Paperback(long isbn, String callNumber, int available, int total, String title, String authors, int year, String genre)
	{
		super(isbn, callNumber, available, total, title);
		this.authors = authors;
		this.year = year;
		this.genre = genre;
	}

	/**
	 * Get the author of the Paperback object
	 * 
	 * @return the authors
	 */
	public String getAuthors()
	{
		return authors;
	}

	/**
	 * Get the year of the Paperback object
	 * 
	 * @return the year
	 */
	public int getYear()
	{
		return year;
	}

	/**
	 * Get the genre of the Paperback object
	 * 
	 * @return the genre
	 */
	public String getGenre()
	{
		return genre;
	}

	/**
	 * The display format of the Paperback object
	 * 
	 * @return the display format of the object
	 */
	@Override
	public String toString()
	{
		String genreString = "";
		switch (genre)
		{
			case "A":
				genreString = "Adventure";
				break;

			case "D":
				genreString = "Drama";
				break;

			case "E":
				genreString = "Education";
				break;

			case "C":
				genreString = "Classic";
				break;

			case "F":
				genreString = "Fantasy";
				break;

			case "S":
				genreString = "Fiction";
				break;
		}

		return super.toString() + String.format("%-20s%-20s%n%-20s%-20d%n%-20s%-20s%n%n", "Authors:", authors, "Year:", year, "Genre:", genreString);
	}

}
