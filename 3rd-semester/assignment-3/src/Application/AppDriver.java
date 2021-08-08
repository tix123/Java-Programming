package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import exceptions.EmptyQueueException;
import exceptions.EmptyStackException;
import utilities.*;

/**
 * Application driver for XML parser
 * 
 * @author HSIAO YU CHEN (Sean Chen)
 * @version 11/20/2020
 */
public class AppDriver
{
	public static void main(String[] args) throws FileNotFoundException
	{

		MyStack<String> stack = new MyStack<String>();
		MyQueue<String> errorQ = new MyQueue<String>();
		MyQueue<String> extrasQ = new MyQueue<String>();
		String errorReport = "";

		//Get file name
		String fileName = args[0];

		// Open the file
		File file = new File(fileName);
		Scanner inFile = new Scanner(file);

		// Ignore prolog tag
		inFile.nextLine();

		// Read from the file
		while (inFile.hasNext())
		{

			String line = inFile.nextLine().trim();

			while (line.contains("<") && line.contains(">"))
			{
				int tagStartIndex = line.indexOf("<");
				int tagEndIndex = line.indexOf(">");
				String tag = line.substring(tagStartIndex, tagEndIndex + 1);

				// If it is a self-closing tag
				if (isSelfClosingTag(tag))
				{
					break; // Ignore
				}

				// If it is a ending Tag
				if (isEndTag(tag))
				{
					String tagLabel;
					
					// Extract tag label
					tagLabel = tag.substring(2, tag.length() - 1);

					try
					{
						// If matches top of stack, pop stack and all is well
						if (!stack.isEmpty() && tagLabel.equals(stack.peek()))
						{
							stack.pop();
						}
						// Else if matches head of errorQ, dequeue and ignore
						else if (!errorQ.isEmpty() && tagLabel.equals(errorQ.peek()))
						{
							errorQ.dequeue();
						}
						// Else if stack is empty, add to errorQ
						else if (stack.isEmpty())
						{
							errorQ.enqueue(tagLabel);
						}
						// Else
						else
						{
							// Search stack for matching Start_Tag. If stack has match
							if (stack.contains(tagLabel))
							{
								// Pop each E from stack into errorQ until match, report as error
								MyStack<String> errorStack = new MyStack<>();
								int index = stack.search(tagLabel);
								for (int i = 1; i <= index; i++)
								{
									errorStack.push(stack.pop());
								}

								String correctTag = errorStack.pop();

								while (!errorStack.isEmpty())
								{
									String errorTag = errorStack.pop();
									errorReport += "Error: The <" + errorTag + "> tag wasn't closed before the <"
											+ correctTag + "> tag. \n";
									errorQ.enqueue(errorTag);
								}

							}
							// Else
							else
							{
								// Add E to extrasQ
								extrasQ.enqueue(tagLabel);
							}
						}
					}
					catch (EmptyStackException | EmptyQueueException e)
					{
						e.printStackTrace();
					}

				}
				// If it is a starting Tag
				else
				{
					// Extract tag label
					String tagLabel;
					if (tag.contains(" "))
					{
						tagLabel = tag.substring(1, tag.indexOf(" "));
					}
					else
					{
						tagLabel = tag.substring(1, tag.length() - 1);
					}

					// Push on stack
					stack.push(tagLabel);
				}

				String subLine = line.substring(tagEndIndex + 1, line.length());
				line = subLine;

				// check the rest of line
				if (line.contains("<") && !line.contains(">"))
				{
					errorReport += "Error: Unexpected < \n";
				}

				if (!line.contains("<") && line.contains(">"))
				{
					errorReport += "Error: Unexpected > \n";
				}
			}

		}

		// If stack is not empty, pop each E into errorQ
		while (!stack.isEmpty())
		{
			try
			{
				errorQ.enqueue(stack.pop());
			}
			catch (EmptyStackException e)
			{
				e.printStackTrace();
			}
		}

		while (!errorQ.isEmpty() || !extrasQ.isEmpty())
		{
			// If either queue is empty (but not both), report each E in both queues as an error
			if (errorQ.isEmpty() && !extrasQ.isEmpty())
			{
				while (!extrasQ.isEmpty())
				{
					try
					{
						String errorTag = extrasQ.dequeue();
						errorReport += "Error: The </" + errorTag + "> tag does not have a match.\n";
					}
					catch (EmptyQueueException e)
					{
						e.printStackTrace();
					}

				}
			}

			if (!errorQ.isEmpty() && extrasQ.isEmpty())
			{
				while (!errorQ.isEmpty())
				{
					try
					{
						String errorTag = errorQ.dequeue();
						errorReport += "Error: The <" + errorTag + "> tag does not have a match. \n";
					}
					catch (EmptyQueueException e)
					{
						e.printStackTrace();
					}

				}
			}

			// If both queues are not empty,
			if (!errorQ.isEmpty() && !extrasQ.isEmpty())
			{
				// Peek both queues, if they don’t match, dequeue from errorQ and report as error
				try
				{
					if (!errorQ.peek().equals(extrasQ.peek()))
					{
						String errorTag = errorQ.dequeue();
						errorReport += "Error: The <" + errorTag + "> tag does not have a match. \n";
					}
					// Else dequeue from both
					else
					{
						errorQ.dequeue();
						extrasQ.dequeue();
					}
				}
				catch (EmptyQueueException e)
				{
					e.printStackTrace();
				}

			}
		}

		// Print result
		if (errorReport.length() == 0)
		{
			System.out.println("No errors found.");
		}
		else
		{
			System.out.println(errorReport);
		}
	}

	/**
	 * Check if it is a self-closing tag or not
	 * 
	 * @param tag The tag to check
	 * @return True if it is a self-closing tag
	 */
	private static boolean isSelfClosingTag(String tag)
	{
		// Check if it's self closing (i.e. <tag />)
		if (tag.startsWith("<") && tag.endsWith("/>"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Check if it is a ending tag or not
	 * 
	 * @param tag The tag to check
	 * @return True if it is a ending tag
	 */
	private static boolean isEndTag(String tag)
	{
		// Check if it's a ending tag (i.e. </tag>)
		if (tag.startsWith("</") && tag.endsWith(">"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
