package utilities;

import java.io.Serializable;

import exceptions.EmptyStackException;

/**
 * Interface of Stack.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public interface StackADT<E> extends Serializable
{
	/**
	 * Push a element to the top of the stack.
	 * 
	 * pre condition: An element sent from the caller
	 * post condition: The element is added into stack
	 * 
	 * @param toPush The element to push 
	 */
	public void push(E toPush);

	/**
	 * Removes and return the element at the top of the stack
	 * 
	 * pre condition: None
	 * post condition: The top element to be returned
	 * 
	 * @return Element removed from top of the stack
	 * @throws EmptyStackException Thrown if stack is nothing to pop.
	 */
	public E pop() throws EmptyStackException;

	/**
	 * Returns the element at the top of the stack
	 * 
	 * pre condition: None
	 * post condition: The top element to be returned
	 * 
	 * @return Element of the top of the stack
	 * @throws EmptyStackException Thrown if stack is nothing to peek at.
	 */
	public E peek() throws EmptyStackException;

	/**
	 * Clear everything from stack
	 * 
	 * pre condition: None
	 * post condition: The stack to be cleared
	 * 
	 */
	public void clear();

	/**
	 * Checks if stack is empty.
	 * 
	 * pre condition: None
	 * post condition: Determine the stack is empty or not 
	 * 
	 * @return True is stack has nothing in it.
	 */
	public boolean isEmpty();

	/**
	 * Compare to other stack.
	 * 
	 * pre condition: The other stack sent from the caller
	 * post condition: Determine if this stack equal to the other stack
	 * 
	 * @param toCompare The stack to compare.
	 * @return True if they are equal.
	 * @throws NullPointerException If the stack is null
	 * @throws EmptyStackException If the stack is empty
	 */
	public boolean equals(StackADT<E> toCompare) throws NullPointerException, EmptyStackException;

	/**
	 * Returns an iterator over the elements in this stack, in proper sequence.
	 * 
	 * pre condition: None
	 * post condition: The iterator to be returned
	 * 
	 * @return An iterator over the elements in this stack, in proper sequence.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Return an array containing all of the items in this stack.
	 * 
	 * pre condition: None
	 * post condition: The object array to be returned
	 * 
	 * @return an array containing all of the items in this stack.
	 */
	public Object[] toArray();
	
	/**
	 * Return an array containing all of the items in this stack.
	 * 
	 * pre condition: The other generic array sent from the caller
	 * post condition: The generic array to be returned
	 * 
	 * @param toHold Target array.
	 * @return An array containing all of the items in this stack.
	 * @throws NullPointerException If the array is null
	 * @throws EmptyStackException If the stack is empty
	 */
	public E[] toArray(E[] toHold) throws NullPointerException, EmptyStackException;
	
	/**
	 * Search for element in stack.
	 * 
	 * pre condition: The element sent from the caller
	 * post condition: The element index to be returned
	 * 
	 * @param toSearch Target element.
	 * @return Index in stack. The first element is 1.
	 */
	public int search(E toSearch);

	/**
	 * Returns true if this stack contains the specified element.
	 * 
	 * pre condition: The element sent from the caller
	 * post condition: Determine if the element contains in the stack or not
	 * 
	 * @param toFind Target element.
	 * @return True if target element is contained.
	 * @throws NullPointerException If the array is null
	 */
	public boolean contains(E toFind) throws NullPointerException;

	/**
	 * The size of this stack.
	 * 
	 * pre condition: None
	 * post condition: The size of stack to be returned
	 * 
	 * @return The size of this stack.
	 */
	public int size();
 
}
