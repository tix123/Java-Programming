package problemdomain;

/**
 * Triangular Prism with area and volume information.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/26/2020
 */
public class TriangularPrism extends Shape
{
	private double height;
	private double length;
	
	/**
	 * @param height The height of the triangular prism
	 * @param length The length of the triangular prism
	 */
	public TriangularPrism(double height, double length)
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
		double area = this.length * this.length * Math.sqrt(3) / 4 ;
		return area;
	}

	/**
	 * @return the volume
	 */
	@Override
	public double getVolume()
	{
		double volume = this.length * this.length * this.height * Math.sqrt(3) / 4 ;
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
