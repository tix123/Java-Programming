package sait.bms.problemdomain;

/**
 * This program defines the ChildrenBook object and its methods
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 2/13/2020
 */
public class ChildrensBook extends Book
{
	private String authors;
	private String format;

	/**
	 * Generate a ChildrensBook object
	 * 
	 * @param isbn       The ISBN of the ChildrensBook
	 * @param callNumber The call number of the ChildrensBook
	 * @param available  The available of the ChildrensBook
	 * @param total      The total of the ChildrensBook
	 * @param title      The title of the ChildrensBook
	 * @param authors    The authors of the ChildrensBook
	 * @param format     The format of the ChildrensBook
	 */
	public ChildrensBook(long isbn, String callNumber, int available, int total, String title, String authors, String format)
	{
		super(isbn, callNumber, available, total, title);
		this.authors = authors;
		this.format = format;
	}

	/**
	 * Get the author of the ChildrensBook object
	 * 
	 * @return the authors
	 */
	public String getAuthors()
	{
		return authors;
	}

	/**
	 * Get the format of the ChildrensBook object
	 * 
	 * @return the format
	 */
	public String getFormat()
	{
		return format;
	}

	/**
	 * The display format of the ChildrensBook object
	 * 
	 * @return the display format of the object
	 */
	@Override
	public String toString()
	{
		String formatString = "";

		switch (format)
		{
			case "P":
				formatString = "Picture book";
				break;

			case "E":
				formatString = "Early Readers";
				break;

			case "C":
				formatString = "Chapter book";
				break;
		}

		return super.toString()	+ String.format("%-20s%-20s%n%-20s%-20s%n%n", "Authors:", authors, "Format:", formatString);
	}
}
