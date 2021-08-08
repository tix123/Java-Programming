package problemdomain;

/**
 * Cylinder with area and volume information.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/26/2020
 */
public class Cylinder extends Shape
{
	private double height;
	private double radius;

	/**
	 * @param height The height of the cylinder
	 * @param radius The radius of the cylinder
	 */
	public Cylinder(double height, double radius)
	{
		this.height = height;
		this.radius = radius;
	}

	/**
	 * @return the area
	 */
	@Override
	public double getArea()
	{
		double area = Math.PI * this.radius * this.radius;
		return area;
	}

	/**
	 * @return the volume
	 */
	@Override
	public double getVolume()
	{
		double volume = Math.PI * this.radius * this.radius * this.height;
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
