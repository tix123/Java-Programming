package utilities;

import exceptions.EmptyQueueException;

/**
 * MyQueue implementation.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public class MyQueue<E> implements QueueADT<E>
{
	private MyDLL<E> queue;

	public MyQueue()
	{
		queue = new MyDLL<E>();
	}

	/**
	 * enqueue/add a element at the end of queue
	 * 
	 * @param toEnqueue The element to enqueue
	 */
	@Override
	public void enqueue(E toEnqueue)
	{
		this.queue.add(toEnqueue);
	}

	/**
	 * dequeue/remove a element at the head of queue
	 * 
	 * @return Removed item
	 */
	@Override
	public E dequeue() throws EmptyQueueException
	{
		return this.queue.remove(0);
	}

	/**
	 * peek/get the element at the head of queue
	 * 
	 * @return the element at the head of queue
	 */
	@Override
	public E peek() throws EmptyQueueException
	{
		return this.queue.get(0);
	}

	/**
	 * Check if MyQueue is empty or not
	 * 
	 * @return True if it is empty
	 */
	@Override
	public boolean isEmpty()
	{
		return this.queue.isEmpty();
	}

	/**
	 * dequeueAll/clear MyQueue
	 */
	@Override
	public void dequeueAll()
	{
		this.queue.clear();
	}

	/**
	 * Check if MyQueue is equal to other queue
	 * 
	 * @param toCompare the queue to compare
	 * @return True if they are equal
	 */
	@Override
	public boolean equals(QueueADT<E> toCompare) throws NullPointerException
	{
		if (this.queue.size() != toCompare.size())
		{
			return false;
		}
		else
		{
			boolean check = true;
			Object[] temp = new Object[toCompare.size()];

			for (int i = 0; i < temp.length; i++)
			{
				try
				{
					temp[i] = toCompare.dequeue();
				}
				catch (EmptyQueueException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int i = 0; i < this.queue.size(); i++)
			{
				if (this.queue.get(i) != temp[i])
				{
					check = false;
				}
			}

			for (int i = temp.length - 1; i >= 0; i--)
			{
				toCompare.enqueue((E) temp[i]);
			}

			return check;
		}
	}

	/**
	 * Returns an iterator over the elements in MyQueue
	 * 
	 * @return an iterator over the elements in MyQueue
	 */
	@Override
	public Iterator<E> iterator()
	{
		return this.queue.iterator();
	}

	/**
	 * Returns an array containing all of the elements in MyQueue
	 * 
	 * @return the array that holds all elements
	 */
	@Override
	public Object[] toArray()
	{
		return this.queue.toArray();
	}

	/**
	 * Returns an array containing all of the elements in MyQueue
	 * 
	 * @param toHold The array to hold the elements
	 * @return the array that holds all elements
	 */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		return this.queue.toArray(toHold);
	}

	/**
	 * Return the size of the queue
	 * 
	 * @return The size of the queue
	 */
	@Override
	public int size()
	{
		return this.queue.size();
	}

}
