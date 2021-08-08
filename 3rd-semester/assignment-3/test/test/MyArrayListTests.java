package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.ListADT;
import utilities.MyArrayList;

/**
 * Test case for MyArrayList.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
class MyArrayListTests
{

	private ListADT<Integer> list;

	/**
	 * Create a references in each test.
	 * @throws java.lang.Exception if any
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		list = new MyArrayList<Integer>();
	}

	/**
	 * Remove references after each test.
	 * @throws java.lang.Exception if any
	 */
	@AfterEach
	void tearDown() throws Exception
	{
		this.list = null;
	}

	/**
	 * Test Append(add) 
	 */
	@Test
	void testAppendValid()
	{
		int expected1 = 1;
		int expected2 = 2;
		this.list.add(expected1);
		this.list.add(expected2);
		assertFalse(this.list.isEmpty());

		int actual1 = this.list.get(0);
		int actual2 = this.list.get(1);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	/**
	 * Test Append(add) if invalid
	 */
	@Test
	void testAppendInvalid()
	{
		assertTrue(this.list.isEmpty());

		try
		{
			int actual = this.list.get(0);
			fail("List is not empty");
		}
		catch (IndexOutOfBoundsException | NullPointerException e)
		{

		}
	}

	/**
	 * Test Clear function
	 */
	@Test
	void testClearValid()
	{
		this.list.add(1);
		this.list.clear();
		assertEquals(0, this.list.size());
		try
		{
			int actual = this.list.get(0);
			fail("The list is not empty");
		}
		catch (IndexOutOfBoundsException | NullPointerException e)
		{

		}
	}

	/**
	 * Test Clear function if invalid
	 */
	@Test
	void testClearInvalid()
	{
		this.list.add(1);
		assertNotEquals(0, this.list.size());
	}

	/**
	 * Test Insert(add) function
	 */
	@Test
	void testInsertValid()
	{
		int expected1 = 2;
		int expected2 = 3;
		this.list.add(1);
		this.list.add(0, expected1);
		this.list.add(2, expected2);

		int actual1 = this.list.get(0);
		int actual2 = this.list.get(2);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	/**
	 * Test Insert(add) function if invalid
	 */
	@Test
	void testInsertInvalid()
	{
		int expected = 2;
		this.list.add(1);
		try
		{
			this.list.add(2, expected);
			fail("Test fail");
		}
		catch (IndexOutOfBoundsException | NullPointerException e)
		{

		}
	}

	/**
	 * test AddAll function
	 */
	@Test
	void testAddAllValid()
	{
		ListADT<Integer> toAdd = new MyArrayList<Integer>();
		int expected1 = 2;
		int expected2 = 3;

		toAdd.add(expected1);
		toAdd.add(expected2);

		this.list.add(1);
		this.list.addAll(toAdd);

		int actual1 = this.list.get(1);
		int actual2 = this.list.get(2);

		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	/**
	 * test AddAll function if invalid
	 */
	@Test
	void testAddAllInvalid()
	{
		ListADT<Integer> list2 = null;
		try
		{
			this.list.addAll(list2);
			fail("Test fail");
		}
		catch (IndexOutOfBoundsException | NullPointerException e)
		{

		}
	}

	/**
	 * Test toArray function if target array is bigger
	 */
	@Test
	void testToArrayValid1()
	{
		int expected = 1;
		this.list.add(expected);

		Integer[] toHold = new Integer[2];
		toHold[0] = 2;
		toHold[1] = 3;

		toHold = this.list.toArray(toHold);

		int actual = toHold[0];

		assertEquals(expected, actual);
		assertNull(toHold[1]);
	}

	/**
	 * Test toArray function if target array is smaller
	 */
	@Test
	void testToArrayValid2()
	{
		int expected1 = 1;
		int expected2 = 2;
		this.list.add(expected1);
		this.list.add(expected2);

		Integer[] toHold = new Integer[1];
		toHold[0] = 3;

		toHold = this.list.toArray(toHold);

		int actual1 = toHold[0];
		int actual2 = toHold[1];
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	/**
	 * Test toArray function if they are the same size
	 */
	@Test
	void testToArrayValid3()
	{
		int expected1 = 1;
		this.list.add(expected1);

		Integer[] toHold = new Integer[1];
		toHold[0] = 3;

		toHold = this.list.toArray(toHold);

		int actual1 = toHold[0];
		assertEquals(expected1, actual1);
	}

	/**
	 * Test remove function
	 */
	@Test
	void testRemoveValid()
	{
		this.list.add(1);
		this.list.add(2);
		this.list.add(3);

		int actual1 = this.list.remove(1);
		int actual2 = this.list.get(1);

		assertEquals(2, actual1);
		assertNotEquals(2, actual2);
	}

	/**
	 * Test remove function with target element
	 */
	@Test
	void testElementRemoveValid()
	{
		this.list.add(1);
		this.list.add(2);
		this.list.add(3);

		Integer expected1 = 1;
		int actual1 = this.list.remove(expected1);

		assertEquals(expected1, actual1);
	}

	/**
	 * test set function
	 */
	@Test
	void testSetValid()
	{
		this.list.add(1);
		this.list.add(2);
		this.list.add(3);

		int expected1 = 4;
		int actual2 = this.list.set(1, expected1);
		int actual1 = this.list.get(1);

		assertEquals(expected1, actual1);
		assertEquals(2, actual2);
	}

	/**
	 * test Contains function
	 */
	@Test
	void testContainsValid()
	{
		this.list.add(1);
		this.list.add(2);
		this.list.add(3);

		boolean contain = this.list.contains(2);

		assertTrue(contain);
	}
	
	/**
	 * test isEmpty function
	 */
	@Test
	void testIsEmptyValid()
	{
		assertTrue(this.list.isEmpty());	
		this.list.add(1);
		assertFalse(this.list.isEmpty());
	}
	
	/**
	 * test size function
	 */
	@Test
	void testSizeValid()
	{
		assertEquals(0,this.list.size());	
		this.list.add(1);
		assertEquals(1,this.list.size());
	}
	
	/**
	 * Test toArray function
	 */
	@Test
	void testToArrayValid()
	{
		int expected = 1;
		this.list.add(expected);
		Object[] newArray =  this.list.toArray();
		int actual = (int) newArray[0];
		assertEquals(expected, actual);
	}
}
