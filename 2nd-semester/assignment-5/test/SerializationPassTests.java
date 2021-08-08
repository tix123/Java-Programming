import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.*;

import contracts.LinkedListADT;
import managers.SLL;
import problemdomain.User;

/**
 * @author HSIAOYU CHEN
 * @version 04-04-2020
 */
class SerializationPassTests
{
	/**
	 * References the linked list that is manipulated in each test.
	 */
	private LinkedListADT linkedList;
	private User userA ;
	private User userB ;
	private User userC;
	private User userD ;
	
	@BeforeEach
	void init()
	{
		try
		{
			// Create object from your implemented linked list here.
			linkedList = new SLL();
			userA = new User(1, "Joe Blow", "jblow@gmail.com", "password");
			userB = new User(2, "Joe Schmoe", "joe.schmoe@outlook.com", "abcdef");
			userC = new User(3, "Colonel Sanders", "chickenlover1890@gmail.com", "kfc5555");
			userD = new User(4, "Ronald McDonald", "burgers4life63@outlook.com", "mcdonalds999");
		}
		catch (NotSerializableException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Test serialization to file.
	 */
	@Test // test-to-pass
	void testSerialization()
	{
		try
		{
			linkedList.append(userA);
			linkedList.append(userB);
			linkedList.append(userC);
			linkedList.append(userD);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.bin"));
			oos.writeObject(linkedList);
			oos.close();
			File f = new File("users.bin");
			assertTrue(f.exists());
		}
		catch (NotSerializableException e)
		{
			System.out.println(e.getMessage());
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * Test deserialization from file.
	 */
	@Test // test-to-pass
	void testDeserialization()
	{
		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.bin"));
			LinkedListADT linkedListInput = (LinkedListADT) ois.readObject();
			assertTrue(((User) linkedListInput.retrieve(0)).equals(userA));
			assertTrue(((User) linkedListInput.retrieve(1)).equals(userB));
			assertTrue(((User) linkedListInput.retrieve(2)).equals(userC));
			assertTrue(((User) linkedListInput.retrieve(3)).equals(userD));
			ois.close();
		}
		catch (NotSerializableException e)
		{
			System.out.println(e.getMessage());
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
	}

	@AfterEach
	void tearDown()
	{
		linkedList.clear();
	}

}
