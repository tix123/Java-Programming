package application;

import java.io.*;
import java.util.*;

import problemdomain.*;
import utilities.*;

/**
 * Application driver.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/26/2020
 */
public class AppDriver
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String sortType = "";
		String compareType = "";
		String filePath = "";
		Comparable[] array;

		for (String arg : args)
		{
			if (arg.startsWith("-") && arg.length() > 2)
			{
				char key = arg.charAt(1);
				String value = arg.substring(2);

				switch (key)
				{
				case 's':
				case 'S':
					sortType = value;
					break;

				case 't':
				case 'T':
					compareType = value;
					break;

				case 'f':
				case 'F':
					filePath = "res/" + value;
					break;
				}
			}
		}

		if (sortType.isEmpty() || compareType.isEmpty() || filePath.isEmpty())
		{
			System.out.println("You miss some parameters");
		}
		else
		{
			System.out.println("Sort type: " + sortType + " | Compare type: " + compareType + " | File path: " + filePath);

			File f = new File(filePath);
			Scanner s = new Scanner(f);
			int arraySize = s.nextInt();
			array = new Comparable[arraySize];
			int count = 0;

			while (s.hasNext())
			{
				String shapeName = s.next();
				double height = s.nextDouble();
				double length = s.nextDouble();

				switch (shapeName)
				{
				case "Cylinder":
					Cylinder cylinder = new Cylinder(height, length);
					array[count] = cylinder;
					break;

				case "Cone":
					Cone cone = new Cone(height, length);
					array[count] = cone;
					break;
					
				case "Pyramid":
					Pyramid pyramid = new Pyramid(height,length);
					array[count] = pyramid;
					break;
					
				case "SquarePrism":
					SquarePrism squarePrism = new SquarePrism(height,length);
					array[count] = squarePrism;
					break;
					
				case "PentagonalPrism":
					PentagonalPrism pentagonalPrism = new PentagonalPrism(height,length);
					array[count] = pentagonalPrism;
					break;
					
				case "OctagonalPrism":
					OctagonalPrism octagonalPrism = new OctagonalPrism(height,length);
					array[count] = octagonalPrism;
					break;
					
				case "TriangularPrism":
					TriangularPrism triangularPrism = new TriangularPrism(height,length);
					array[count] = triangularPrism;
					break;
				}
				count++;
			}

			MyArray.sort(array, sortType, compareType);

		}
	}
}
