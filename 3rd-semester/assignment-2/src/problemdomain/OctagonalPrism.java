package problemdomain;

/**
 * Octagonal Prism with area and volume information.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/26/2020
 */
public class OctagonalPrism extends Shape
{
	private double height;
	private double length;
	
	/**
	 * @param height The height of the octagonal prism
	 * @param length The length of the octagonal prism
	 */
	public OctagonalPrism(double height, double length)
	{
		super();
		this.height = height;
		this.length = length;
	}

	/**
	 * @return the area
	 */
	@Override
	public double getArea()
	{
		double area = this.length * this.length * 2 * (1 + Math.sqrt(2));
		return area;
	}
	
	/**
	 * @return the volume
	 */
	@Override
	public double getVolume()
	{
		double volume = this.length * this.length * this.height * 2 * (1 + Math.sqrt(2));
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
