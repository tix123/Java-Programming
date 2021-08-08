package managers;

import contracts.*;
import problemdomain.*;

import java.io.*;

/**
 * @author HSIAOYU CHEN
 * @version 04-04-2020
 */
public class SLL implements LinkedListADT,Serializable
{
	/**
	 * The first node in the linked list.
	 */
	private Node head;

	/**
	 * The number of nodes in the linked list.
	 */
	private int size;

	public SLL() throws NotSerializableException
	{
		this.head = null;
		this.size = 0;
	}

	/**
	 * Prepends (adds to beginning) data to the list.
	 * @param data Data to store as a first element.
	 */
	@Override
	public void prepend(Object data)
	{
		// Create a new node object to prepend.
		Node newNode = new Node(data);

		// Set next of new node to head
		// This must be done before we change the head.
		newNode.setNext(head);

		// Set head to new node
		head = newNode;

		// Increment the size
		size++;
	}

	/**
	 * Adds to the end of the list.
	 * @param data Data to append.
	 */
	@Override
	public void append(Object data)
	{
		Node newNode = new Node(data);

		Node temp = head;

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
	}

	/**
	 * Checks if the list is empty.
	 * @return True if it is empty.
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
	 * Clears the list.
	 */
	@Override
	public void clear()
	{
		this.head = null;
		this.size = 0;
	}

	/**
	 * Adds a new element at a specific position.
	 * @param data Data to insert at specific index.
	 * @param index Index to add a new element at.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or past the size of the list.
	 */
	@Override
	public void insert(Object data, int index) throws IndexOutOfBoundsException
	{
		Node newNode = new Node(data);
		Node temp = head;

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
		}
	}

	/**
	 * Replaces the data  at index.
	 * @param data Data to replace at specific index.
	 * @param index Index of the element to replace.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or larger than size - 1 of list.
	 */
	@Override
	public void replace(Object data, int index) throws IndexOutOfBoundsException
	{
		Node newNode = new Node(data);
		Node temp = head;

		if (index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			if (index == 0)
			{
				newNode.setNext(head.getNext());
				head = newNode;
			}
			else
			{
				for (int i = 0; i < size; i++)
				{
					if (i == index - 1)
					{
						newNode.setNext(temp.getNext().getNext());
						temp.setNext(newNode);
					}
					else
					{
						temp = temp.getNext();
					}
				}
			}
		}
	}

	/**
	 * Gets the number of elements in the list.
	 * @return Size of list (0 meaning empty)
	 */
	@Override
	public int size()
	{
		return this.size;
	}

	/**
	 * Removes element at index from list, reducing the size.
	 * @param index Index of the element to remove.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or past the size - 1.
	 */
	@Override
	public void delete(int index) throws IndexOutOfBoundsException
	{
		Node temp = head;

		if (index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		else
		{
			if (index == 0)
			{
				head = head.getNext();
			}
			else
			{
				for (int i = 0; i < size; i++)
				{
					if (i == index - 1)
					{
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
	}

	/**
	 * Gets the data at the specified index.
	 * @param index Index of the element to get.
	 * @return Data in element or null if it was not found.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or larger than size - 1 of the list.
	 */
	@Override
	public Object retrieve(int index) throws IndexOutOfBoundsException
	{
		Node temp = head;

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
					return temp.getData();
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
	 * Gets the first index of element containing data.
	 * @param data Matched data to find at the first index of.
	 * @return First of index of the element with matching data or -1 if not found.
	 */
	@Override
	public int indexOf(Object data)
	{
		Node temp = head;

		for (int i = 0; i < size; i++)
		{
			if (temp.getData().equals(data))
			{
				return i;
			}
			else
			{
				temp = temp.getNext();
			}
		}

		return -1;
	}

	/**
	 * Go through elements and check if we have one with data.
	 * @param data Data to search for.
	 * @return True if element exists with value.
	 */
	@Override
	public boolean contains(Object data)
	{
		Node temp = head;

		for (int i = 0; i < size; i++)
		{
			if (temp.getData().equals(data))
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

}