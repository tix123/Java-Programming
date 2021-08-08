package utilities;

import java.io.Serializable;

import exceptions.EmptyQueueException;

/**
 * Interface of Queue.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public interface QueueADT<E> extends Serializable
{
	/**
	 * Add to the tail of the queue
	 * 
	 * pre condition: The element sent from the caller
	 * post condition: The element is added into the queue
	 * 
	 * @param toEnqueue The element to enqueue
	 */
	public void enqueue(E toEnqueue);
	
	/**
	 * Remove from the head of the queue
	 * 
	 * pre condition: None
	 * post condition: The first element to be returned
	 * 
	 * @return The first element of the queue
	 * @throws EmptyQueueException If the queue is empty
	 */
	public E dequeue() throws EmptyQueueException;
	
	/**
	 * Look at the element at the head of the queue
	 * 
	 * pre condition: None
	 * post condition: The first element of the queue to be returned 
	 * 
	 * @return The first element of the queue
	 * @throws EmptyQueueException If the queue is empty
	 */
	public E peek() throws EmptyQueueException;
	
	/**
	 * Checks if queue is empty.
	 * 
	 * pre condition: None
	 * post condition: Determine the queue is empty or not 
	 * 
	 * @return True is queue has nothing in it.
	 */
	public boolean isEmpty();
	
	/**
	 * Clear all elements from the queue
	 * 
	 * pre condition: None
	 * post condition: The queue to be cleared
	 * 
	 */
	public void dequeueAll();
	
	/**
	 * Compare to other queue.
	 * 
	 * pre condition: The other queue sent from the caller
	 * post condition: Determine if this queue equal to the other stack
	 * 
	 * @param toCompare The queue to compare.
	 * @return True if they are equal.
	 * @throws NullPointerException If the queue is null
	 */
	public boolean equals(QueueADT<E> toCompare) throws NullPointerException;

	/**
	 * Returns an iterator over the elements in this queue, in proper sequence.
	 * 
	 * pre condition: None
	 * post condition: The iterator to be returned
	 * 
	 * @return An iterator over the elements in this queue, in proper sequence.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Return an array containing all of the items in this queue.
	 * 
	 * pre condition: None
	 * post condition: The object array to be returned 
	 * 
	 * @return an array containing all of the items in this queue.
	 */
	public Object[] toArray();
	
	/**
	 * Return an array containing all of the items in this queue.
	 * 
	 * pre condition: The other generic array sent from the caller
	 * post condition: The generic array to be returned
	 * 
	 * @param toHold Target array.
	 * @return An array containing all of the items in this queue.
	 * @throws NullPointerException If the array is null
	 */
	public E[] toArray(E[] toHold) throws NullPointerException;
	
	/**
	 * The size of this queue.
	 * 
	 * pre condition: None
	 * post condition: The size of stack to be returned
	 * 
	 * @return The size of this queue.
	 */
	public int size();
	
	
}
