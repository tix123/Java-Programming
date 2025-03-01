package contracts;

/**
 * Abstract data type for a linked list.
 * 
 * @author ElMenshawy 
 * @version 14-03-2020
 *
 */
public interface LinkedListADT 
{
	/**
	 * Checks if the list is empty.
	 * @return True if it is empty.
	 */
	boolean isEmpty();

	/**
	 * Clears the list.
	 */
	void clear();
	
	/**
	 * Adds to the end of the list.
	 * @param data Data to append.
	 */
	void append(Object data);

	/**
	 * Prepends (adds to beginning) data to the list.
	 * @param data Data to store as a first element.
	 */
	void prepend(Object data);
	
	/**
	 * Adds a new element at a specific position.
	 * @param data Data to insert at specific index.
	 * @param index Index to add a new element at.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or past the size of the list.
	 */
	void insert(Object data, int index) throws IndexOutOfBoundsException;

	/**
	 * Replaces the data  at index.
	 * @param data Data to replace at specific index.
	 * @param index Index of the element to replace.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or larger than size - 1 of list.
	 */
	void replace(Object data, int index) throws IndexOutOfBoundsException;

	/**
	 * Gets the number of elements in the list.
	 * @return Size of list (0 meaning empty)
	 */
	int size();

	/**
	 * Removes element at index from list, reducing the size.
	 * @param index Index of the element to remove.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or past the size - 1.
	 */
	void delete(int index) throws IndexOutOfBoundsException;
	
	/**
	 * Gets the data at the specified index.
	 * @param index Index of the element to get.
	 * @return Data in element or null if it was not found.
	 * @exception IndexOutOfBoundsException Thrown if index is negative or larger than size - 1 of the list.
	 */
	Object retrieve(int index) throws IndexOutOfBoundsException;

	/**
	 * Gets the first index of element containing data.
	 * @param data Matched data to find at the first index of.
	 * @return First of index of the element with matching data or -1 if not found.
	 */
	int indexOf(Object data);

	/**
	 * Go through elements and check if we have one with data.
	 * @param data Data to search for.
	 * @return True if element exists with value.
	 */
	boolean contains(Object data);
}