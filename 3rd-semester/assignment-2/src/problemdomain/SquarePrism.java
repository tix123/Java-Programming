package problemdomain;

/**
 * Square Prism with area and volume information.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/26/2020
 */
public class SquarePrism extends Shape
{
	private double height;
	private double length;
	
	/**
	 * @param height The height of the square prism
	 * @param length The length of the square prism
	 */
	public SquarePrism(double height, double length)
	{
		this.height = height;
		this.length = length;
	}

	/**
	 * @return the area
	 */
	@Override
	public double getArea()
	{
		double area = this.length * this.length;
		return area;
	}
	
	/**
	 * @return the volume
	 */
	@Override
	public double getVolume()
	{
		double volume = this.length * this.length * this.height;
		return volume;
	}
	
	/**
	 * @return the height
	 */
	@Override
	public double getHeight()
	{
		return height;
	}
	
}
