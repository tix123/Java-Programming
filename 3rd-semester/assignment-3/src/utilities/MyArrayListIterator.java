package utilities;

import java.util.NoSuchElementException;

/**
 * Iterator for MyArrayList
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public class MyArrayListIterator<E> implements Iterator<E>
{
	private int position;
	private MyArrayList<E> myArrayList;
	
	public MyArrayListIterator(MyArrayList<E> myArrayList)
	{
		this.myArrayList = myArrayList;
	}
	
	/**
	 * Returns <code>true</code> if the iteration has more elements. 
	 * (In other words, returns <code>true</code> if <code>next()</code> 
	 * would return an element rather than throwing an exception.)
	 * 
	 * @return <code>true</code> if the iterator has more elements.
	 */
	public boolean hasNext()
	{
		return this.position < this.myArrayList.size() - 1;
	}
	
	
	/**
	 * Returns the next element in the iteration.
	 * 
	 * @return The next element in the iteration.
	 * @throws NoSuchElementException
	 * 			If the iteration has no more elements.
	 */
	public E next() throws NoSuchElementException
	{
		if(!this.hasNext())
		{
			throw new NoSuchElementException();
		}
		
		this.position++;
		
		E item = this.myArrayList.get(this.position);
		
		return item;
	}
}
