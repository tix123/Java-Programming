
package utilities;

import java.io.Serializable;

/**
 * Node for MyDLL.
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/13/2020
 */
public class MyDLLNode implements Serializable
{
	private Object data;
	
	private MyDLLNode next;
	
	/**
	 * Creates a new node.
	 * @param data Data in a node.
	 */
	public MyDLLNode(Object data) 
	{
		this.data = data;
	}
	
	/**
	 * Gets the data in the node.
	 * @return Object data
	 */
	public Object getData() 
	{
		return data;
	}
	
	/**
	 * Sets the data in the node.
	 * @param data New data to set.
	 */
	public void setData(Object data) 
	{
		this.data = data;
	}
	
	/**
	 * Gets the next node or null (if there is no next node).
	 * @return Next node.
	 */
	public MyDLLNode getNext() 
	{
		return next;
	}
	
	/**
	 * Sets the next node.
	 * @param next Next node to set.
	 */
	public void setNext(MyDLLNode next) 
	{
		this.next = next;
	}

}
