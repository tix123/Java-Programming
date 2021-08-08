package utilities;

import java.util.*;

import problemdomain.*;

/**
 * All the sorting algorithm.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 10/26/2020
 */
public class MyArray
{
	/**
	 * Sort manager for all sorting methods
	 * 
	 * @param items The array of all Shapes
	 * @param sortAlgorithm The type of sort algorithm
	 * @param compareType The type of compare
	 */
	public static void sort(Comparable[] items, String sortAlgorithm, String compareType)
	{
		// assign comparator
		Comparator comparator = null;
		switch (compareType)
		{
		case "a":
		case "A":
			comparator = new AreaComparator();
			break;

		case "v":
		case "V":
			comparator = new VolumeComparator();
			break;
		}

		// sort
		switch (sortAlgorithm)
		{
		case "b":
		case "B":
			bubbleSort(items, comparator);
			break;

		case "s":
		case "S":
			selectionSort(items, comparator);
			break;

		case "i":
		case "I":
			insertionSort(items, comparator);
			break;

		case "m":
		case "M":
			mergeSort(items, comparator);
			break;

		case "q":
		case "Q":
			quickSort(items, comparator);
			break;

		case "z":
		case "Z":
			cocktailSort(items, comparator);
			break;
		}

		// display result
		switch (compareType)
		{
		case "a":
		case "A":
			System.out.println("The first value is: " + ((Shape) items[0]).getArea());
			for (int i = 999; i < items.length; i = i + 1000)
			{
				System.out.println("The " + (i + 1) + "th value is: " + ((Shape) items[i]).getArea());
			}
			System.out.println("The last value is: " + ((Shape) items[items.length - 1]).getArea());
			break;

		case "v":
		case "V":
			System.out.println("The first value is: " + ((Shape) items[0]).getVolume());
			for (int i = 999; i < items.length; i = i + 1000)
			{
				System.out.println("The " + (i + 1) + "th value is: " + ((Shape) items[i]).getVolume());
			}
			System.out.println("The last value is: " + ((Shape) items[items.length - 1]).getVolume());
			break;

		case "h":
		case "H":
			System.out.println("The first value is: " + ((Shape) items[0]).getHeight());
			for (int i = 999; i < items.length; i = i + 1000)
			{
				System.out.println("The " + (i + 1) + "th value is: " + ((Shape) items[i]).getHeight());
			}
			System.out.println("The last value is: " + ((Shape) items[items.length - 1]).getHeight());
			break;
		}
	}

	/**
	 * Bubble sort
	 * 
	 * @param items The array of all Shapes
	 * @param comparator The comparator
	 */
	private static void bubbleSort(Comparable[] items, Comparator comparator)
	{
		long start = 0;
		long stop = 0;
		if (comparator == null)
		{
			start = System.currentTimeMillis();
			int limit = items.length;
			boolean swapDone = true;
			while (limit > 1 && swapDone)
			{
				swapDone = false;
				for (int i = 0; i < limit - 1; i++)
				{
					swapDone = true;
					if (((Shape) items[i]).compareTo(((Shape) items[i + 1])) < 0)
					{
						Comparable temp = items[i];
						items[i] = items[i + 1];
						items[i + 1] = temp;
					}
				}
				limit--;
			}
			stop = System.currentTimeMillis();
		}
		else
		{
			start = System.currentTimeMillis();
			int limit = items.length;
			boolean swapDone = true;
			while (limit > 1 && swapDone)
			{
				swapDone = false;
				for (int i = 0; i < limit - 1; i++)
				{
					swapDone = true;
					if (comparator.compare((Shape) items[i], (Shape) items[i + 1]) < 0)
					{
						Comparable temp = items[i];
						items[i] = items[i + 1];
						items[i + 1] = temp;
					}
				}
				limit--;
			}
			stop = System.currentTimeMillis();
		}

		long timeSpent = stop - start;

		System.out.println("Sorting time: " + timeSpent + "ms");

	}

	/**
	 * Selection sort
	 * 
	 * @param items The array of all Shapes
	 * @param comparator The comparator
	 */
	private static void selectionSort(Comparable[] items, Comparator comparator)
	{
		long start = 0;
		long stop = 0;
		if (comparator == null)
		{
			start = System.currentTimeMillis();
			int limit = items.length;
			int maxIndex = 0;
			for (int i = 0; i < limit - 1; i++)
			{
				maxIndex = i;
				for (int j = i + 1; j < limit; j++)
				{
					if (((Shape) items[j]).compareTo(((Shape) items[maxIndex])) > 0)
					{
						maxIndex = j;
					}
				}
				if (maxIndex != i)
				{
					Comparable temp = items[i];
					items[i] = items[maxIndex];
					items[maxIndex] = temp;
				}
			}
			stop = System.currentTimeMillis();
		}
		else
		{
			start = System.currentTimeMillis();
			int limit = items.length;
			int maxIndex = 0;
			for (int i = 0; i < limit - 1; i++)
			{
				maxIndex = i;
				for (int j = i + 1; j < limit; j++)
				{
					if (comparator.compare((Shape) items[j], (Shape) items[maxIndex]) > 0)
					{
						maxIndex = j;
					}
				}
				if (maxIndex != i)
				{
					Comparable temp = items[i];
					items[i] = items[maxIndex];
					items[maxIndex] = temp;
				}
			}
			stop = System.currentTimeMillis();
		}

		long timeSpent = stop - start;

		System.out.println("Sorting time: " + timeSpent + "ms");

	}

	/**
	 * Insertion sort
	 * 
	 * @param items The array of all Shapes
	 * @param comparator The comparator
	 */
	private static void insertionSort(Comparable[] items, Comparator comparator)
	{
		long start = 0;
		long stop = 0;
		if (comparator == null)
		{
			start = System.currentTimeMillis();
			int limit = items.length;
			int j;
			Comparable temp;
			for (int i = 1; i < limit; i++)
			{
				temp = items[i];
				for (j = i - 1; j >= 0 && ((Shape) items[j]).compareTo(((Shape) temp)) < 0; j--)
				{
					items[j + 1] = items[j];
				}
				items[j + 1] = temp;
			}
			stop = System.currentTimeMillis();
		}
		else
		{
			start = System.currentTimeMillis();
			int limit = items.length;
			int j;
			Comparable temp;
			for (int i = 1; i < limit; i++)
			{
				temp = items[i];
				for (j = i - 1; j >= 0 && comparator.compare((Shape) items[j], (Shape) temp) < 0; j--)
				{
					items[j + 1] = items[j];
				}
				items[j + 1] = temp;

			}
			stop = System.currentTimeMillis();
		}

		long timeSpent = stop - start;

		System.out.println("Sorting time: " + timeSpent + "ms");

		// for (Comparable c : items) { System.out.println(((Shape) c).getArea()); }

	}

	/**
	 * Merge sort
	 * 
	 * @param items The array of all Shapes
	 * @param comparator The comparator
	 */
	private static void mergeSort(Comparable[] items, Comparator comparator)
	{
		long start = 0;
		long stop = 0;
		if (comparator == null)
		{
			start = System.currentTimeMillis();

			Comparable[] temp = new Comparable[items.length];

			for (int count = 1; count < items.length; count *= 2)
			{
				for (int leftStart = 0; leftStart < items.length; leftStart += 2 * count)
				{
					if (count <= items.length - leftStart)
					{
						int leftBound = leftStart + count;
						int rightStart = leftStart + count;

						int rightCount = 0;
						if (items.length - leftStart - count > count)
						{
							rightCount = count;
						}
						else
						{
							rightCount = items.length - leftStart - count;
						}
						int rightBound = rightStart + rightCount;

						int i = leftStart;
						int j = rightStart;
						int index = leftStart;

						while (i < leftBound && j < rightBound)
						{
							if (((Shape) items[j]).compareTo(((Shape) items[i])) > 0)
							{
								temp[index] = items[j];
								j++;
								index++;
							}
							else
							{
								temp[index] = items[i];
								i++;
								index++;
							}
						}

						while (i < leftBound)
						{
							temp[index] = items[i];
							i++;
							index++;
						}

						while (j < rightBound)
						{
							temp[index] = items[j];
							j++;
							index++;
						}
					}
				}

				for (int i = 0; i < temp.length; i++)
				{
					items[i] = temp[i];
				}
			}
			stop = System.currentTimeMillis();
		}
		else
		{
			start = System.currentTimeMillis();

			Comparable[] temp = new Comparable[items.length];

			for (int count = 1; count < items.length; count *= 2)
			{
				for (int leftStart = 0; leftStart < items.length; leftStart += 2 * count)
				{
					if (count <= items.length - leftStart)
					{
						int leftBound = leftStart + count;
						int rightStart = leftStart + count;

						int rightCount = 0;
						if (items.length - leftStart - count > count)
						{
							rightCount = count;
						}
						else
						{
							rightCount = items.length - leftStart - count;
						}
						int rightBound = rightStart + rightCount;

						int i = leftStart;
						int j = rightStart;
						int index = leftStart;

						while (i < leftBound && j < rightBound)
						{
							if (comparator.compare((Shape) items[j], (Shape) items[i]) > 0)
							{
								temp[index] = items[j];
								j++;
								index++;
							}
							else
							{
								temp[index] = items[i];
								i++;
								index++;
							}
						}

						while (i < leftBound)
						{
							temp[index] = items[i];
							i++;
							index++;
						}

						while (j < rightBound)
						{
							temp[index] = items[j];
							j++;
							index++;
						}
					}
				}

				for (int i = 0; i < temp.length; i++)
				{
					items[i] = temp[i];
				}
			}
			stop = System.currentTimeMillis();
		}
		long timeSpent = stop - start;

		System.out.println("Sorting time: " + timeSpent + "ms");
	}

	/**
	 * Quick sort manager to decide using comparator or comparable
	 * 
	 * @param items The array of all Shapes
	 * @param comparator The comparator
	 */
	private static void quickSort(Comparable[] items, Comparator comparator)
	{
		long start = 0;
		long stop = 0;

		Comparable[] temp = null;

		if (comparator == null)
		{
			start = System.currentTimeMillis();
			temp = quickSortComparable(items);
			stop = System.currentTimeMillis();
		}
		else
		{
			start = System.currentTimeMillis();
			temp = quickSortComparator(items, comparator);
			stop = System.currentTimeMillis();
		}

		for (int i = 0; i < temp.length; i++)
		{
			items[i] = temp[i];
		}

		long timeSpent = stop - start;

		System.out.println("Sorting time: " + timeSpent + "ms");
	}

	/**
	 * Quick sort using comparator
	 * 
	 * @param array The array of all Shapes
	 * @param comparator The comparator
	 * @return The Comparable array
	 */
	private static Comparable[] quickSortComparator(Comparable[] array, Comparator comparator)
	{
		if (array.length < 2)
		{
			return array;
		}
		else
		{
			Comparable[] result = new Comparable[array.length];
			int resultIndex = 0;

			Comparable[] biggerTemp = new Comparable[array.length];
			int biggerIndex = 0;

			Comparable[] smallerTemp = new Comparable[array.length];
			int smallerIndex = 0;

			int pivotIndex = 0;
			Shape pivot = (Shape) array[pivotIndex];

			for (int i = 1; i < array.length; i++)
			{
				if (comparator.compare((Shape) array[i], pivot) > 0)
				{
					biggerTemp[biggerIndex] = array[i];
					biggerIndex++;
				}
				else
				{
					smallerTemp[smallerIndex] = array[i];
					smallerIndex++;
				}
			}

			if (biggerIndex > 0)
			{
				Comparable[] bigger = new Comparable[biggerIndex];
				for (int i = 0; i < biggerIndex; i++)
				{
					bigger[i] = biggerTemp[i];
				}

				bigger = quickSortComparator(bigger, comparator);

				for (int i = 0; i < biggerIndex; i++)
				{
					result[resultIndex] = bigger[i];
					resultIndex++;
				}
			}

			result[resultIndex] = pivot;
			resultIndex++;

			if (smallerIndex > 0)
			{
				Comparable[] smaller = new Comparable[smallerIndex];

				for (int i = 0; i < smallerIndex; i++)
				{
					smaller[i] = smallerTemp[i];
				}

				smaller = quickSortComparator(smaller, comparator);

				for (int i = 0; i < smallerIndex; i++)
				{
					result[resultIndex] = smaller[i];
					resultIndex++;
				}
			}

			return result;
		}
	}

	/**
	 * Quick sort using comparable
	 * 
	 * @param array The array of all Shapes
	 * @return The Comparable array
	 */
	private static Comparable[] quickSortComparable(Comparable[] array)
	{
		if (array.length < 2)
		{
			return array;
		}
		else
		{
			Comparable[] result = new Comparable[array.length];
			int resultIndex = 0;

			Comparable[] biggerTemp = new Comparable[array.length];
			int biggerIndex = 0;

			Comparable[] smallerTemp = new Comparable[array.length];
			int smallerIndex = 0;

			int pivotIndex = 0;
			Shape pivot = (Shape) array[pivotIndex];

			for (int i = 1; i < array.length; i++)
			{
				if (((Shape) array[i]).compareTo(pivot) > 0)
				{
					biggerTemp[biggerIndex] = array[i];
					biggerIndex++;
				}
				else
				{
					smallerTemp[smallerIndex] = array[i];
					smallerIndex++;
				}
			}

			if (biggerIndex > 0)
			{
				Comparable[] bigger = new Comparable[biggerIndex];
				for (int i = 0; i < biggerIndex; i++)
				{
					bigger[i] = biggerTemp[i];
				}

				bigger = quickSortComparable(bigger);

				for (int i = 0; i < biggerIndex; i++)
				{
					result[resultIndex] = bigger[i];
					resultIndex++;
				}
			}

			result[resultIndex] = pivot;
			resultIndex++;

			if (smallerIndex > 0)
			{
				Comparable[] smaller = new Comparable[smallerIndex];

				for (int i = 0; i < smallerIndex; i++)
				{
					smaller[i] = smallerTemp[i];
				}

				smaller = quickSortComparable(smaller);

				for (int i = 0; i < smallerIndex; i++)
				{
					result[resultIndex] = smaller[i];
					resultIndex++;
				}
			}

			return result;
		}
	}

	/**
	 * Cocktail sort
	 * 
	 * @param items The array of all Shapes
	 * @param comparator The comparator
	 */
	private static void cocktailSort(Comparable[] items, Comparator comparator)
	{
		long start = 0;
		long stop = 0;
		
		if (comparator == null)
		{
			start = System.currentTimeMillis();
			
			int leftLimit = items.length;
			int rightLimit = 0;
			boolean swapDone = true;
			while (leftLimit > 1 && swapDone)
			{
				swapDone = false;
				for (int i = rightLimit; i < leftLimit - 1; i++)
				{
					swapDone = true;
					if (((Shape) items[i]).compareTo(((Shape) items[i + 1])) < 0)
					{
						Comparable temp = items[i];
						items[i] = items[i + 1];
						items[i + 1] = temp;
					}
				}
				leftLimit--;

				for (int i = leftLimit; i > rightLimit; i--)
				{
					swapDone = true;
					if (((Shape) items[i]).compareTo(((Shape) items[i - 1])) > 0)
					{
						Comparable temp = items[i];
						items[i] = items[i - 1];
						items[i - 1] = temp;
					}
				}
				rightLimit++;
			}
			stop = System.currentTimeMillis();
		}
		else
		{
			start = System.currentTimeMillis();
			
			int leftLimit = items.length;
			int rightLimit = 0;
			boolean swapDone = true;
			while (leftLimit > 1 && swapDone)
			{
				swapDone = false;
				for (int i = rightLimit; i < leftLimit - 1; i++)
				{
					swapDone = true;
					if (comparator.compare((Shape) items[i], (Shape) items[i + 1]) < 0)
					{
						Comparable temp = items[i];
						items[i] = items[i + 1];
						items[i + 1] = temp;
					}
				}
				leftLimit--;

				for (int i = leftLimit; i > rightLimit; i--)
				{
					swapDone = true;
					if (comparator.compare((Shape) items[i], (Shape) items[i - 1]) > 0)
					{
						Comparable temp = items[i];
						items[i] = items[i - 1];
						items[i - 1] = temp;
					}
				}
				rightLimit++;
			}
			stop = System.currentTimeMillis();
		}
		
		long timeSpent = stop - start;

		System.out.println("Sorting time: " + timeSpent + "ms");
	}

	
}
