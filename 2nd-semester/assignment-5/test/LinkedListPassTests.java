import static org.junit.jupiter.api.Assertions.*;

import java.io.NotSerializableException;

import org.junit.jupiter.api.*;

import contracts.*;
import managers.SLL;


/**
 * @author ElMenshawy
 * @version 14-03-2020
 *
 */
public class LinkedListPassTests 
{
	/**
	 * References the linked list that is manipulated in each test.
	 */
	private LinkedListADT linkedList;
	
	@BeforeEach
	void init() 
	{
		try{
			// Create object from your implemented linked list here.
			linkedList = new SLL();
		}
		catch(NotSerializableException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Test the linked list is empty.
	 */
	@Test //test-to-pass
	void testIsEmpty() 
	{
		assertTrue(linkedList.isEmpty());
		assertEquals(0, linkedList.size());
	}
	
	/**
	 * Tests appending elements to the linked list.
	 */
	@Test  //test-to-pass
	void testAppendNode() 
	{
		linkedList.append("a");
		linkedList.append("b");
		linkedList.append("c");
		linkedList.append("d");
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, linkedList.size());

		// Test the first node value is a
		assertEquals("a", linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", linkedList.retrieve(1));
		
		// Test the third node value is c
		assertEquals("c", linkedList.retrieve(2));
		
		// Test the fourth node value is d
		assertEquals("d", linkedList.retrieve(3));
	}

	/**
	 * Tests prepending nodes to linked list.
	 */
	@Test //test-to-pass
	void testPrependNodes() 
	{
		linkedList.prepend("a");
		linkedList.prepend("b");
		linkedList.prepend("c");
		linkedList.prepend("d");
		
		/**
		 * Linked list should now be:
		 * 
		 * d -> c -> b -> a
		 */
		
		// Test the linked list is not empty.
		assertFalse(linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, linkedList.size());

		// Test the first node value is d
		assertEquals("d", linkedList.retrieve(0));

		// Test the second node value is c
		assertEquals("c", linkedList.retrieve(1));
		
		// Test the third node value is b
		assertEquals("b", linkedList.retrieve(2));
		
		// Test the fourth node value is a
		assertEquals("a", linkedList.retrieve(3));
	}
	
	/**
	 * Tests inserting node at valid index.
	 */
	@Test //test-to-pass
	void testInsertNode() 
	{
		linkedList.append("a");
		linkedList.append("b");
		linkedList.append("c");
		linkedList.append("d");
		
	try {
			 linkedList.insert("e", 2);
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> e -> c -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(linkedList.isEmpty());
		
		// Test the size is 5
		assertEquals(5, linkedList.size());

		// Test the first node value is a
		assertEquals("a", linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", linkedList.retrieve(1));
		
		// Test the third node value is e
		assertEquals("e", linkedList.retrieve(2));
		
		// Test the fourth node value is c
		assertEquals("c", linkedList.retrieve(3));
		
		// Test the fifth node value is d
		assertEquals("d", linkedList.retrieve(4));
	 }
	 catch(IndexOutOfBoundsException e){
		System.out.println(e.getMessage());
	 }
 }
	
	/**
	 * Tests replacing existing nodes data.
	 */
	@Test //test-to-pass
	void testReplaceNode() 
	{
		linkedList.append("a");
		linkedList.append("b");
		linkedList.append("c");
		linkedList.append("d");
		
	 try {
	    	linkedList.replace("e", 2);
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> e -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, linkedList.size());

		// Test the first node value is a
		assertEquals("a", linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", linkedList.retrieve(1));
		
		// Test the third node value is e
		assertEquals("e", linkedList.retrieve(2));
		
		// Test the fourth node value is d
		assertEquals("d", linkedList.retrieve(3));
	 }
	catch(IndexOutOfBoundsException e){
		System.out.println(e.getMessage());
	 }
}
	
	/**
	 * Tests deleting node from linked list.
	 */
	@Test //test-to-pass
	void testDeleteNode() 
	{
		linkedList.append("a");
		linkedList.append("b");
		linkedList.append("c");
		linkedList.append("d");
	
	try {
	     linkedList.delete(2);
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(linkedList.isEmpty());
		
		// Test the size is 3
		assertEquals(3, linkedList.size());

		// Test the first node value is a
		assertEquals("a", linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", linkedList.retrieve(1));
		
		// Test the third node value is d
		assertEquals("d", linkedList.retrieve(2));
	 }
	catch(IndexOutOfBoundsException e){
		System.out.println(e.getMessage());
	 }
  }
	
	/**
	 * Tests finding and retrieving node in linked list.
	 */
	@Test  //test-to-pass
	void testFindNode() 
	{
		linkedList.append("a");
		linkedList.append("b");
		linkedList.append("c");
		linkedList.append("d");
		
		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */
	try {
		  boolean contains = linkedList.contains("b");
		  assertTrue(contains);
		
		  int index = linkedList.indexOf("b");
		  assertEquals(1, index);
		
		  String value = (String)linkedList.retrieve(1);
		  assertEquals("b", value);
	   }
	catch(IndexOutOfBoundsException e){
		System.out.println(e.getMessage());
	  }
   }
	
	@AfterEach
	void tearDown() 
	{
		linkedList.clear();
	}
}