package sait.bms.problemdomain;

/**
 * This program defines the Cookbook object and its methods
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 2/13/2020
 */
public class Cookbook extends Book
{
	private String publisher;
	private String diet;

	/**
	 * Generate a Cookbook object
	 * 
	 * @param isbn       The ISBN of the Cookbook
	 * @param callNumber The call number of the Cookbook
	 * @param available  The available of the Cookbook
	 * @param total      The total of the Cookbook
	 * @param title      The title of the Cookbook
	 * @param publisher  The publisher of the Cookbook
	 * @param diet       The diet of the Cookbook
	 */
	public Cookbook(long isbn, String callNumber, int available, int total, String title, String publisher, String diet)
	{
		super(isbn, callNumber, available, total, title);
		this.publisher = publisher;
		this.diet = diet;
	}

	/**
	 * Get the publisher of the Cookbook object
	 * 
	 * @return the publisher
	 */
	public String getPublisher()
	{
		return publisher;
	}

	/**
	 * Get the diet of the Cookbook object
	 * 
	 * @return the diet
	 */
	public String getDiet()
	{
		return diet;
	}

	/**
	 * The display format of the Cookbook object
	 * 
	 * @return the display format of the object
	 */
	@Override
	public String toString()
	{
		String dietString = "";
		switch (diet)
		{
			case "D":
				dietString = "Diabetic";
				break;

			case "V":
				dietString = "Vegetarian";
				break;

			case "G":
				dietString = "Gluten-free";
				break;

			case "I":
				dietString = "International";
				break;

			case "N":
				dietString = "None";
				break;
		}

		return super.toString() + String.format("%-20s%-20s%n%-20s%-20s%n%n", "Publisher:", publisher, "Diet:", dietString);
	}
}
