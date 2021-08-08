package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.EmptyQueueException;
import exceptions.EmptyStackException;
import utilities.MyQueue;
import utilities.MyStack;
import utilities.QueueADT;

/**
 * Test case for MyQueue.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
class MyQueueTests
{
	private QueueADT<Integer> queue;
	
	/**
	 * Create a references in each test.
	 * @throws Exception if any
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		this.queue = new MyQueue<Integer>();
	}

	/**
	 * Remove references after each test.
	 * @throws Exception if any
	 */
	@AfterEach
	void tearDown() throws Exception
	{
		this.queue = null;
	}

	/**
	 * test enqueue function
	 */
	@Test
	void testEnqueueValid()
	{
		int expected = 1;
		this.queue.enqueue(expected);
		int actual = 0;
		try
		{
			actual = this.queue.peek();
		}
		catch (EmptyQueueException e)
		{
			fail("Exception catched");
		}
		assertEquals(expected,actual);		
	}

	/**
	 * test dequeue function
	 */
	@Test
	void testDequeueValid()
	{
		int expected = 1;
		this.queue.enqueue(expected);
		int actual = 0;
		try
		{
			actual = this.queue.dequeue();
		}
		catch (EmptyQueueException e)
		{
			fail("Exception catched");
		}
		assertEquals(expected,actual);	
	}
	
	
	/**
	 * test dequeue function if invalid
	 */
	@Test
	void testDequeueInvalid()
	{
		assertTrue(this.queue.isEmpty());
		try
		{
			int actual = this.queue.dequeue();
			fail("The queue is not empty");
		}
		catch (IndexOutOfBoundsException | EmptyQueueException e)
		{
			
		}
	}
	
	/**
	 * test isEmpty function
	 */
	@Test
	void testIsEmptyValid()
	{
		assertTrue(this.queue.isEmpty());	
		this.queue.enqueue(1);
		assertFalse(this.queue.isEmpty());
	}
	
	/**
	 * test dequeueAll function
	 */
	@Test
	void testDequeueAllValid()
	{
		this.queue.enqueue(1);
		this.queue.dequeueAll();
		try
		{
			int actual = this.queue.peek();
			fail("The queue is not empty");
		}
		catch (IndexOutOfBoundsException | NullPointerException | EmptyQueueException e)
		{

		}
	}
	
	/**
	 * test toArray function
	 */
	@Test
	void testToArrayValid()
	{
		int expected1 = 1;
		int expected2 = 2;
		this.queue.enqueue(expected1);
		this.queue.enqueue(expected2);
		Object[] newArray =  this.queue.toArray();
		int actual1 = (int) newArray[0];
		int actual2 = (int) newArray[1];
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	/**
	 * Test toArray function if target array is bigger
	 */
	@Test
	void testToArrayValid1()
	{
		int expected = 1;
		this.queue.enqueue(expected);

		Integer[] toHold = new Integer[2];
		toHold[0] = 2;
		toHold[1] = 3;

		toHold = this.queue.toArray(toHold);

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
		this.queue.enqueue(expected1);
		this.queue.enqueue(expected2);

		Integer[] toHold = new Integer[1];
		toHold[0] = 3;

		toHold = this.queue.toArray(toHold);

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
		this.queue.enqueue(expected1);

		Integer[] toHold = new Integer[1];
		toHold[0] = 3;

		try
		{
			toHold = this.queue.toArray(toHold);
		}
		catch (NullPointerException e)
		{
			fail("Exception catched");
		}

		int actual1 = toHold[0];
		assertEquals(expected1, actual1);
	}
	
	/**
	 * test equals function
	 */
	@Test
	void testEqualsValid()
	{
		this.queue.enqueue(2);
		this.queue.enqueue(1);
		
		MyQueue<Integer> toCompare = new MyQueue<Integer>();
		toCompare.enqueue(2);
		toCompare.enqueue(1);
		
		try
		{
			assertTrue(this.queue.equals(toCompare));
			assertEquals(1, toCompare.dequeue());
			assertEquals(2, toCompare.dequeue());
		}
		catch (NullPointerException | EmptyQueueException e)
		{
			fail("Exception catched");
		}
	}
}
