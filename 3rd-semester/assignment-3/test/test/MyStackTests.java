package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.EmptyStackException;
import utilities.*;

/**
 * Test case for MyStack.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
class MyStackTests
{
	private StackADT<Integer> stack;
	
	/**
	 * Create a references in each test.
	 * @throws Exception if any
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		stack = new MyStack<Integer>();
	}

	/**
	 * Remove references after each test.
	 * @throws Exception if any
	 */
	@AfterEach
	void tearDown() throws Exception
	{
		stack = null;
	}

	/**
	 * test push function
	 */
	@Test
	void testPush()
	{
		this.stack.push(0);
		int expected = 1;
		this.stack.push(expected);
		int actual = 0;
		try
		{
			actual = this.stack.peek();
		}
		catch (EmptyStackException e)
		{
			fail("Exception catched");
		}
		assertEquals(expected,actual);
	}

	/**
	 * test pop function
	 */
	@Test
	void testPop()
	{
		int expected = 1;
		this.stack.push(expected);
		int actual = 0;
		try
		{
			actual = this.stack.pop();
		}
		catch (EmptyStackException e)
		{
			fail("Exception catched");
		}
		assertEquals(expected,actual);
		assertTrue(this.stack.isEmpty());
	}
	
	/**
	 * test isEmpty function
	 */
	@Test
	void testIsEmptyValid()
	{
		assertTrue(this.stack.isEmpty());	
		this.stack.push(1);
		assertFalse(this.stack.isEmpty());
	}
	
	/**
	 * test size function
	 */
	@Test
	void testSizeValid()
	{
		assertEquals(0,this.stack.size());	
		this.stack.push(1);
		assertEquals(1,this.stack.size());
	}
	
	/**
	 * test clear function
	 */
	@Test
	void testClearValid()
	{
		this.stack.push(1);
		this.stack.clear();
		assertEquals(0, this.stack.size());
		try
		{
			int actual = this.stack.peek();
			fail("The stack is not empty");
		}
		catch (IndexOutOfBoundsException | NullPointerException | EmptyStackException e)
		{

		}
	}
	
	/**
	 * test search function
	 */
	@Test
	void testSearchValid()
	{
		this.stack.push(2);
		this.stack.push(1);
		assertEquals(1,this.stack.search(1));
		assertEquals(2,this.stack.search(2));
	}
	
	/**
	 * test contains function
	 */
	@Test
	void testContainsValid()
	{
		this.stack.push(1);
		assertTrue(this.stack.contains(1));
	}
	
	/**
	 * test equals function
	 */
	@Test
	void testEqualsValid()
	{
		this.stack.push(2);
		this.stack.push(1);
		
		MyStack<Integer> toCompare = new MyStack<Integer>();
		toCompare.push(2);
		toCompare.push(1);
		
		try
		{
			assertTrue(this.stack.equals(toCompare));
			assertEquals(1, toCompare.pop());
			assertEquals(2, toCompare.pop());
		}
		catch (NullPointerException | EmptyStackException e)
		{
			fail("Exception catched");
		}
	}
	
	/**
	 * test to array function
	 */
	@Test
	void testToArrayValid()
	{
		int expected1 = 1;
		int expected2 = 2;
		this.stack.push(expected1);
		this.stack.push(expected2);
		Object[] newArray =  this.stack.toArray();
		int actual2 = (int) newArray[0];
		int actual1 = (int) newArray[1];
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	/**
	 * test toArray function with target array
	 */
	@Test
	void testToArrayValidWithArray()
	{
		int expected1 = 1;
		this.stack.push(expected1);

		Integer[] toHold = new Integer[1];
		toHold[0] = 3;

		try
		{
			toHold = this.stack.toArray(toHold);
		}
		catch (NullPointerException | EmptyStackException e)
		{
			fail("Exception catched");
		}

		int actual1 = toHold[0];
		assertEquals(expected1, actual1);
	}
}
