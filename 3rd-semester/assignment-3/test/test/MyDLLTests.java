package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utilities.ListADT;
import utilities.MyArrayList;
import utilities.MyDLL;

/**
 * Test case for MyDLL.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
class MyDLLTests
{
	private ListADT<Integer> list;
	
	/**
	 * Create a references in each test.
	 * @throws Exception if any
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		list = new MyDLL<Integer>();
	}

	/**
	 * Remove references after each test.
	 * @throws Exception if any
	 */
	@AfterEach
	void tearDown() throws Exception
	{
		this.list = null;
	}

	/**
	 * Test append(add) function
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
	 * test clear function
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
	 * test Insert(add) function
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
	 * test contains function
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
	 * test remove function
	 */
	@Test
	void testRemoveValid()
	{
		this.list.add(1);
		this.list.add(2);
		this.list.add(3);

		int index = 1;
		int actual1 = this.list.remove(index);
		int actual2 = this.list.get(index);

		assertEquals(2, actual1);
		assertEquals(3, actual2);
	}
	
	/**
	 * test remove function with target element
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
		
		assertEquals(2, this.list.get(0));
		assertEquals(3, this.list.get(1));
		assertEquals(2, this.list.size());
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
	 * test addAll function
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
	 * test toArray function
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

}
