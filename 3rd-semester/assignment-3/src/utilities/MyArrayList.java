package utilities;

import java.lang.reflect.Array;

/**
 * MyArrayList implementation.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public class MyArrayList<E> implements ListADT<E>
{
	private int size;
	private Object[] data;

	public MyArrayList()
	{
		size = 0;
		data = new Object[0];
	}

	/**
	 * Return the size of MyArrayList
	 * @return the size of MyArrayList
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Clear MyArrayList
	 */
	@Override
	public void clear()
	{
		for (int i = 0; i < size; i++)
		{
			data[i] = null;
		}
		size = 0;
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
		if (index > size)
		{
			throw new IndexOutOfBoundsException();
		}

		size++;
		if (index == size - 1)
		{
			Object[] temp = new Object[size];
			for (int i = 0; i < data.length; i++)
			{
				temp[i] = data[i];
			}
			temp[index] = toAdd;
			data = temp;
		}
		else if (index < size - 1)
		{
			Object[] temp = new Object[size];

			for (int i = 0; i <= index; i++)
			{
				temp[i] = data[i];
			}

			for (int i = index; i < data.length; i++)
			{
				temp[i + 1] = data[i];
			}
			temp[index] = toAdd;
			data = temp;
		}
		else
		{
			return false;
		}
		return true;
	}

	/**
	 * Append/add an element at the end of MyArrayList
	 * @param toAdd the element to add
	 * @return True if add success
	 */
	@Override
	public boolean add(E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		size++;

		Object[] temp = new Object[size];
		for (int i = 0; i < data.length; i++)
		{
			temp[i] = data[i];
		}
		temp[size - 1] = toAdd;
		data = temp;

		return true;

	}

	/**
	 * Append/add a list at the end of MyArrayList
	 * @param toAdd the list to add
	 * @return True if add success
	 */
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
	{
		size += toAdd.size();

		Object[] temp = new Object[size];
		for (int i = 0; i < data.length; i++)
		{
			temp[i] = data[i];
		}

		for (int i = 0; i < toAdd.size(); i++)
		{
			temp[i + data.length] = toAdd.get(i);
		}
		data = temp;

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
		return (E) data[index];
	}

	/**
	 * Remove the element at index position
	 * @param index the position
	 * @return The removed element
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index >= size)
		{
			throw new IndexOutOfBoundsException();
		}

		Object removedItem = data[index];

		size--;

		Object[] temp = new Object[size];

		for (int i = 0; i < index; i++)
		{
			temp[i] = data[i];
		}

		for (int i = index + 1; i < data.length; i++)
		{
			temp[i - 1] = data[i];
		}

		data = temp;

		return (E) removedItem;
	}

	/**
	 * Search and remove the element in MyArrayList
	 * @param toRemove The element to remove
	 * @return The removed element
	 */
	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		if (this.contains(toRemove))
		{
			int index = 0;
			for(int i = 0; i < data.length; i++)
			{
				if(data[i].equals(toRemove))
				{
					index= i;
				}
			}
			
			Object removedItem = this.remove(index);
			
			return (E) removedItem;
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
		if (index >= size)
		{
			throw new IndexOutOfBoundsException();
		}

		Object replacedItem = data[index];

		data[index] = toChange;

		return (E) replacedItem;
	}

	/**
	 * Check if MyArrayList is empty or not
	 * @return True if MyArrayList is empty
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
	 * Check if MyArrayList contains the element
	 * @param toFind The element to find
	 * @return True if MyArrayList contains the element
	 */
	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		for (int i = 0; i < data.length; i++)
		{
			if (data[i].equals(toFind))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns an array containing all of the elements in MyArrayList
	 * @param toHold The array to hold the elements
	 * @return the array that holds all elements
	 */
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		if(size < toHold.length)
		{
			for(int i = 0; i < data.length; i++)
			{
				toHold[i] = (E) data[i];
			}
			toHold[size] = null;
			return toHold;
		}
		else if(size > toHold.length)
		{
			E[] temp = (E[]) Array.newInstance(toHold.getClass().getComponentType(), size);
			for(int i = 0; i < data.length; i++)
			{
				temp[i] = (E) data[i];
			}
			return temp;
		}
		else
		{
			for(int i = 0; i < data.length; i++)
			{
				toHold[i] = (E) data[i];
			}
			return toHold;
		}
	}

	/**
	 * Returns an array containing all of the elements in MyArrayList
	 * @return the array that holds all elements
	 */
	@Override
	public Object[] toArray()
	{
		Object[] temp = new Object[size];
		for (int i = 0; i < data.length; i++)
		{
			temp[i] = data[i];
		}
		return temp;
	}

	/**
	 * Returns an iterator over the elements in MyArrayList
	 * @return an iterator over the elements in MyArrayList
	 */
	@Override
	public Iterator<E> iterator()
	{
		return new MyArrayListIterator<E>(this);
	}

}
