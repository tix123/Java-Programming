package sait.bms.problemdomain;

/**
 * This program defines the Periodical object and its methods
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 2/13/2020
 */
public class Periodical extends Book
{
	private String frequency;

	/**
	 * Generate a Periodical object
	 * 
	 * @param isbn       The ISBN of the Periodical
	 * @param callNumber The call number of the Periodical
	 * @param available  The available of the Periodical
	 * @param total      The total of the Periodical
	 * @param title      The title of the Periodical
	 * @param frequency  The frequency of the Periodical
	 */
	public Periodical(long isbn, String callNumber, int available, int total, String title, String frequency)
	{
		super(isbn, callNumber, available, total, title);
		this.frequency = frequency;
	}

	/**
	 * Get the author of the Periodical object
	 * 
	 * @return the frequency
	 */
	public String getFrequency()
	{
		return frequency;
	}

	/**
	 * The display format of the Periodical object
	 * 
	 * @return the display format of the object
	 */
	@Override
	public String toString()
	{
		String frequencyString = "";
		switch (frequency)
		{
			case "D":
				frequencyString = "Daily";
				break;

			case "W":
				frequencyString = "Weekly";
				break;

			case "M":
				frequencyString = "Monthly";
				break;

			case "B":
				frequencyString = "Bimonthly";
				break;

			case "Q":
				frequencyString = "Quarterly";
				break;
		}

		return super.toString() + String.format("%-20s%-20s%n%n", "Frequency:", frequencyString);
	}

}
