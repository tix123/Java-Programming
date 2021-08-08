package utilities;

import java.util.Comparator;

import problemdomain.Shape;

/**
 * To compare volume
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/26/2020
 */
public class VolumeComparator implements Comparator<Shape>
{
	/**
	 * Compare volume of Shape
	 * 
	 * @param s1 The first shape
	 * @param s2 The second shape
	 * @return The compare result
	 */
	@Override
	public int compare(Shape s1, Shape s2)
	{
		double difference = s1.getVolume() - s2.getVolume();
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
