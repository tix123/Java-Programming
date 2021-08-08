package utilities;

import java.lang.reflect.Array;

/**
 * MyDLL implementation.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public class MyDLL<E> implements ListADT<E>
{
	private MyDLLNode head;
	private int size;

	public MyDLL()
	{
		this.head = null;
		this.size = 0;
	}

	/**
	 * Return the size of MyDLL
	 * @return the size of  MyDLL
	 */
	@Override
	public int size()
	{
		return this.size;
	}

	/**
	 * Clear MyDLL
	 */
	@Override
	public void clear()
	{
		this.head = null;
		this.size = 0;
	}

	/**
	 * Insert/add an element at index position
	 * @param index the position to add
	 * @param toAdd the element to add
	 * @return True if add success
	 */
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		MyDLLNode newNode = new MyDLLNode(toAdd);
		MyDLLNode temp = head;
		boolean check = false;
		if (index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			if (index == 0)
			{
				newNode.setNext(head);
				head = newNode;
			}
			else
			{
				for (int i = 0; i < size; i++)
				{
					if (i == index - 1)
					{
						newNode.setNext(temp.getNext());
						temp.setNext(newNode);
					}
					else
					{
						temp = temp.getNext();
					}
				}
			}
			size++;
			check = true;
		}
		return check;
	}

	/**
	 * Append/add an element at the end of MyDLL
	 * @param toAdd the element to add
	 * @return True if add success
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		MyDLLNode newNode = new MyDLLNode(toAdd);
		MyDLLNode temp = head;

		if (size == 0)
		{
			head = newNode;
		}
		else
		{
			for (int i = 1; i < size; i++)
			{
				temp = temp.getNext();
			}

			temp.setNext(newNode);
		}
		size++;
		return true;
	}

	/**
	 * Append/add a list at the end of MyDLL
	 * @param toAdd the list to add
	 * @return True if add success
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
	{

		MyDLLNode temp = head;
		for (int i = 0; i < this.size - 1; i++)
		{
			temp = temp.getNext();
		}

		size += toAdd.size();

		for (int i = 0; i < toAdd.size(); i++)
		{
			MyDLLNode newNode = new MyDLLNode(toAdd.get(i));
			temp.setNext(newNode);
			temp = newNode;
		}

		return true;
	}

	/**
	 * get the element at index position
	 * @param index the position
	 * @return The element
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		MyDLLNode temp = head;

		if (index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			for (int i = 0; i < size; i++)
			{
				if (i == index)
				{
					return (E) temp.getData();
				}
				else
				{
					temp = temp.getNext();
				}
			}

			return null;
		}
	}

	/**
	 * Remove the element at index position
	 * @param index the position
	 * @return The removed element
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		MyDLLNode temp = head;
		Object removedItem = null;
		if (index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			if (index == 0)
			{
				removedItem = head.getData();
				head = head.getNext();
			}
			else
			{
				for (int i = 0; i < size; i++)
				{
					if (i == index - 1)
					{
						removedItem = temp.getNext().getData();
						temp.setNext(temp.getNext().getNext());
					}
					else
					{
						temp = temp.getNext();
					}
				}
			}
			size--;
		}
		return (E) removedItem;
	}

	/**
	 * Search and remove the element in MyDLL
	 * @param toRemove The element to remove
	 * @return The removed element
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		MyDLLNode temp = head;
		if (this.contains(toRemove))
		{
			for (int i = 0; i < this.size; i++)
			{
				if (temp.getData().equals(toRemove))
				{
					Object removedItem = this.remove(i);
					return (E) removedItem;
				}
				else
				{
					temp = temp.getNext();
				}
			}
			return null;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Set the element at index position
	 * @param index the position
	 * @param toChange the position
	 * @return The removed element
	 */
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
	{
		MyDLLNode temp = head;

		if (index >= this.size)
		{
			throw new IndexOutOfBoundsException();
		}

		for (int i = 0; i < size; i++)
		{
			if (i == index)
			{
				Object replaceItem = temp.getData();
				temp.setData(toChange);
				return (E) replaceItem;
			}
			else
			{
				temp = temp.getNext();
			}
		}

		return null;
	}

	/**
	 * Check if MyDLL is empty or not
	 * @return True if MyDLL is empty
	 */
	@Override
	public boolean isEmpty()
	{
		if (size == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Check if MyDLL contains the element
	 * @param toFind The element to find
	 * @return True if MyDLL contains the element
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		MyDLLNode temp = head;

		for (int i = 0; i < size; i++)
		{
			if (temp.getData().equals(toFind))
			{
				return true;
			}
			else
			{
				temp = temp.getNext();
			}
		}
		return false;
	}

	/**
	 * Returns an array containing all of the elements in MyDLL
	 * @param toHold The array to hold the elements
	 * @return the array that holds all elements
	 */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		if(size < toHold.length)
		{
			MyDLLNode tempNode = head;
			
			for(int i = 0; i < this.size; i++)
			{
				toHold[i] = (E) tempNode.getData();
				if (i != this.size - 1)
				{
					tempNode = tempNode.getNext();
				}
			}
			toHold[size] = null;
			return toHold;
		}
		else if(size > toHold.length)
		{
			E[] temp = (E[]) Array.newInstance(toHold.getClass().getComponentType(), size);
			MyDLLNode tempNode = head;
			for(int i = 0; i < this.size; i++)
			{
				temp[i] = (E) tempNode.getData();
				if (i != this.size - 1)
				{
					tempNode = tempNode.getNext();
				}
			}
			return temp;
		}
		else
		{
			MyDLLNode tempNode = head;
			
			for(int i = 0; i < this.size; i++)
			{
				toHold[i] = (E) tempNode.getData();
				if (i != this.size - 1)
				{
					tempNode = tempNode.getNext();
				}
			}
			return toHold;
		}
	}

	/**
	 * Returns an array containing all of the elements in MyDLL
	 * @return the array that holds all elements
	 */
	@Override
	public Object[] toArray()
	{
		MyDLLNode tempNode = head;
		Object[] temp = new Object[size];
		for (int i = 0; i < this.size; i++)
		{
			temp[i] = tempNode.getData();
			if (i != this.size - 1)
			{
				tempNode = tempNode.getNext();
			}
		}
		return temp;
	}

	/**
	 * Returns an iterator over the elements in MyDLL
	 * @return an iterator over the elements in MyDLL
	 */
	@Override
	public Iterator<E> iterator()
	{
		return new MyDLLIterator<E>(this);
	}

}
