package problemdomain;

/**
 * Pyramid with area and volume information.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/26/2020
 */
public class Pyramid extends Shape
{
	private double height;
	private double length;
	
	/**
	 * @param height The height of the pyramid
	 * @param length The length of the pyramid
	 */
	public Pyramid(double height, double length)
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
		double volume = this.length * this.length * this.height / 3;
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
