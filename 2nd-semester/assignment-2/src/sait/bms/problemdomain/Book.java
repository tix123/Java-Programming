package sait.bms.problemdomain;

/**
 * This program defines the book object and its methods
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 2/13/2020
 */
public class Book
{
	private long isbn;
	private String callNumber;
	private int available;
	private int total;
	private String title;

	/**
	 * Generate a book object
	 * 
	 * @param isbn       The ISBN of the book
	 * @param callNumber The call number of the book
	 * @param available  The available of the book
	 * @param total      The total of the book
	 * @param title      The title of the book
	 */
	public Book(long isbn, String callNumber, int available, int total, String title)
	{
		this.isbn = isbn;
		this.callNumber = callNumber;
		this.available = available;
		this.total = total;
		this.title = title;
	}

	/**
	 * Get the ISBN of the book object
	 * 
	 * @return the isbn
	 */
	public long getIsbn()
	{
		return isbn;
	}

	/**
	 * Get the call number of the book object
	 * 
	 * @return the callNumber
	 */
	public String getCallNumber()
	{
		return callNumber;
	}

	/**
	 * Get the available of the book object
	 * 
	 * @return the available
	 */
	public int getAvailable()
	{
		return available;
	}

	/**
	 * Set the available of the book object
	 * 
	 * @param available the available to set
	 */
	public void setAvailable(int available)
	{
		this.available = available;
	}

	/**
	 * Get the total of the book object
	 * 
	 * @return the total
	 */
	public int getTotal()
	{
		return total;
	}

	/**
	 * Get the title of the book object
	 * 
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * The display format of the book object
	 * 
	 * @return the display format of the object
	 */
	@Override
	public String toString()
	{
		return String.format("%-20s%-20d%n%-20s%-20s%n%-20s%-20d%n%-20s%-20d%n%-20s%-20s%n",
				"ISBN:", isbn,"Call Number:", callNumber, "Available:", available, "Total:", total, "Title:", title);

	}

}
