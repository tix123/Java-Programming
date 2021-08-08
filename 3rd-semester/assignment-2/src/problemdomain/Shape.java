package problemdomain;

/**
 * abstract class Shape.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/26/2020
 */
public abstract class Shape implements Comparable<Shape>
{
	private double height;

	public Shape()
	{

	}

	public abstract double getArea();

	public abstract double getVolume();

	public abstract double getHeight();
	
	/**
	 * @param other Other shape for compare
	 * @return The compare result
	 */
	@Override
	public int compareTo(Shape other)
	{
		double difference = this.getHeight() - other.getHeight();
		if (difference > 0)
		{
			return 1;
		}
		else if (difference < 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

	
}
