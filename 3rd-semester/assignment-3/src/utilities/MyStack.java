package utilities;

import exceptions.EmptyStackException;

/**
 * MyStack implementation.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public class MyStack<E> implements StackADT<E>
{
	private MyArrayList<E> stack;

	public MyStack()
	{
		stack = new MyArrayList<E>();
	}

	/**
	 * push/add a element at the top of stack
	 * @param toPush The element to push
	 */
	@Override
	public void push(E toPush)
	{
		this.stack.add(0, toPush);
	}

	/**
	 * pop/remove a element at the top of stack
	 * @return Removed item
	 * @throws EmptyStackException If stack is empty
	 */
	@Override
	public E pop() throws EmptyStackException 
	{
		if(this.stack.isEmpty())
		{
			throw new EmptyStackException();
		}
		return this.stack.remove(0);
	}

	/**
	 * peek/get the element at the top of stack
	 * @return the element at the top of stack
	 * @throws EmptyStackException If stack is empty
	 */
	@Override
	public E peek() throws EmptyStackException 
	{
		if(this.stack.isEmpty())
		{
			throw new EmptyStackException();
		}
		return this.stack.get(0);
	}

	/**
	 * clear MyStack
	 */
	@Override
	public void clear()
	{
		this.stack.clear();
	}

	/**
	 * Check if MyStack is empty or not
	 * @return True if it is empty
	 */
	@Override
	public boolean isEmpty()
	{
		return this.stack.isEmpty();
	}

	/**
	 * Check if MyStack is equal to other stack
	 * @param toCompare the stack to compare
	 * @return True if they are equal
	 */
	@Override
	public boolean equals(StackADT<E> toCompare) throws NullPointerException, EmptyStackException
	{
		if (this.stack.size() != toCompare.size())
		{
			return false;
		}
		else
		{
			boolean check = true;
			Object[] temp = new Object[toCompare.size()];

			for (int i = 0; i < temp.length; i++)
			{
				temp[i] = toCompare.pop();
			}

			for (int i = 0; i < this.stack.size(); i++)
			{
				if (this.stack.get(i) != temp[i])
				{
					check = false;
				}
			}

			for (int i = temp.length - 1 ; i >= 0 ; i--)
			{
				toCompare.push((E) temp[i]);
			}

			return check;
		}
	}

	/**
	 * Returns an iterator over the elements in MyStack
	 * @return an iterator over the elements in MyStack
	 */
	@Override
	public Iterator<E> iterator()
	{
		return this.stack.iterator();
	}

	/**
	 * Returns an array containing all of the elements in MyStack
	 * @return the array that holds all elements
	 */
	@Override
	public Object[] toArray()
	{
		return this.stack.toArray();
	}

	/**
	 * Returns an array containing all of the elements in MyDLL
	 * @param toHold The array to hold the elements
	 * @return the array that holds all elements
	 */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		return this.stack.toArray(toHold);
	}

	/**
	 * Search the element in the stack
	 * @param toSearch The element to search
	 * @return the index of the element
	 */
	@Override
	public int search(E toSearch)
	{
		if (this.contains(toSearch))
		{
			int index = 0;
			for (int i = 0; i < this.stack.size(); i++)
			{
				if (this.stack.get(i).equals(toSearch))
				{
					index = i;
				}
			}

			return index + 1;
		}
		else
		{
			return -1;
		}
	}

	/**
	 * Check if MyStack contains the element
	 * @param toFind The element to find
	 * @return True if MyStack contains the element
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		return this.stack.contains(toFind);
	}

	/**
	 * Return the size of the stack
	 * @return The size of the stack
	 */
	@Override
	public int size()
	{
		return this.stack.size();
	}

}
