package problemdomain;

import java.io.*;

/**
 * Represents a user.
 * @author ElMenshawy
 * @version 14-03-2020
 *
 */
public class User implements Serializable
{
	private int id;
	
	private String name;
	
	private String email;
	
	private transient String password;
	
	/**
	 * Initializes a User object.
	 * @param id ID
	 * @param name Name
	 * @param email E-mail address
	 * @param password Password
	 * @exception NotSerializableException Thrown when it is unable to locate the class type.
	 */
	public User(int id, String name, String email, String password) throws NotSerializableException
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public boolean isValidPassword(String password) 
	{
		if (password == null && this.password == null)
			return true;
		else if (password == null || this.password == null)
			return false;
		else
			return password.equals(this.password);
	}
	
	public boolean equals(Object obj) 
	{
		if (!(obj instanceof User))
			return false;
		
		User other = (User) obj;
		return          id == other.id && 
			   name.equals(other.name) &&
			   email.equals(other.email);
	}
}