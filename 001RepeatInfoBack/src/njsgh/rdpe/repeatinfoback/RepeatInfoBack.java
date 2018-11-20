package njsgh.rdpe.repeatinfoback;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  Represents a single user interaction, taking their three problem-specified details, repeating them back and/or writing them to an external file.
 *  
 *  @author <a href="njs-pm@protonmail.ch">Nicholas Seaborn</a>
 *  @see njsgh.rdpe.repeatinfoback
 *  @version 2018.11.20
 */
public class RepeatInfoBack
{
	private String userActualName;
	private int userActualAge;
	private String userRedditUsername;
	
	/**
	 * <p>Class construction, accepts no inputs, and procedurally asks the user via command line for values to assign to global variables.</p>
	 * <p>No validation on inputted data occurs, bar error handling in case the user fails to enter an integer for their age, in which case, it's defaulted to 0.</p>
	 */
	public RepeatInfoBack()
	{
		Scanner readInFromConsole = new Scanner(System.in);
		
		System.out.println("Please enter your actual name: ");
		userActualName = readInFromConsole.nextLine();
		System.out.println("Please enter your acutal age: ");
		try
		{
			userActualAge = readInFromConsole.nextInt();
		}catch(InputMismatchException e)
		{
			System.out.println("Integer not entered; setting your age to 0.");
		}
		readInFromConsole.nextLine();
		System.out.println("Please enter your username: ");
		userRedditUsername = readInFromConsole.nextLine();
		
		readInFromConsole.close();
	}
	
	/**
	 * Class constructor, accepting three strings for instance variable assignment, with no validation.
	 * @param userActualName
	 * @param userActualAge
	 * @param userRedditUsername
	 */
	public RepeatInfoBack(String userActualName, int userActualAge, String userRedditUsername)
	{
		this.userActualName = userActualName;
		this.userActualAge = userActualAge;
		this.userRedditUsername = userRedditUsername;
	}

	/**
	 *
	 */
	public void printFormattedToConsole()
	{
		System.out.println(getFormattedInfoString());
	}
	
	/**
	 *
	 */
	public void writeCSVOutToFile(String directoryAndFileDotExt, boolean appendToExistingFile)
	{
		try
		{
			FileWriter writeOutToFile = new FileWriter(directoryAndFileDotExt, appendToExistingFile);
			
			writeOutToFile.write(userActualName + "," + userActualAge + "," + userRedditUsername + System.getProperty("line.separator"));
			
			writeOutToFile.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 *
	 */
	public String getFormattedInfoString()
	{
		return "your name is " + userActualName + ", you are " + userActualAge + " years old, and your username is " + userRedditUsername;
	}
	
	/**
	 * Returns the instance variable holding the user's actual name.
	 * 
	 * @return userActualName
	 */
	public String getUserActualName()
	{
		return userActualName;
	}

	/**
	 * @param userActualName the userActualName to set
	 */
	public void setUserActualName(String userActualName)
	{
		this.userActualName = userActualName;
	}

	/**
	 * Returns the instance variable holding the user's actual age.
	 * 
	 * @return the userActualAge
	 */
	public int getUserActualAge()
	{
		return userActualAge;
	}

	/**
	 * @param userActualAge the userActualAge to set
	 */
	public void setUserActualAge(int userActualAge)
	{
		this.userActualAge = userActualAge;
	}
	
	/**
	 * Returns the instance variable holding the user's Reddit username.
	 * 
	 * @return the userRedditUsername
	 */
	public String getUserRedditUsername()
	{
		return userRedditUsername;
	}
	
	/**
	 * @param userRedditUsername the userRedditUsername to set
	 */
	public void setUserRedditUsername(String userRedditUsername)
	{
		this.userRedditUsername = userRedditUsername;
	}
}
