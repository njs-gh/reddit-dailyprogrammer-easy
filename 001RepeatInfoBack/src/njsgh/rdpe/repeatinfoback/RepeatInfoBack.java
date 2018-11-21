package njsgh.rdpe.repeatinfoback;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  <p>Represents a single user interaction, taking their three problem-specified details, repeating them back and/or writing them to an external file.</p>
 *  <p>Potential task, extend code to validate data: actual name of only alphanumeric, age within some range.</p>
 *  <p>Potential task, investigate, compare, implement a better file write methods.</p>
 *  <p>Potential task, order methods by precedence.</p>
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
	 * <p>Class construction, accepts no inputs, and procedurally asks the user via command line for values to assign to instance variables.</p>
	 * <p>No validation on inputted data occurs, bar error handling in case the user fails to enter an integer for their age, in which case, it's defaulted to 0.</p>
	 * <p>Accomplishes core feature.</p>
	 * <p>Potential task, extend code to validate data.</p>
	 * 
	 * @see njsgh.rdpe.repeatinfoback
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
	 * <p>Class constructor, accepting three strings for instance variable assignment, with no validation.</p>
	 * <p>Accomplishes core feature.</p>
	 * <p>Potential task, extend code to validate data.</p>
	 * 
	 * @param userActualName The user's actual name.
	 * @param userActualAge The user's actual age.
	 * @param userRedditUsername The user's chosen Reddit user name.
	 * @see njsgh.rdpe.repeatinfoback
	 */
	public RepeatInfoBack(String userActualName, int userActualAge, String userRedditUsername)
	{
		this.userActualName = userActualName;
		this.userActualAge = userActualAge;
		this.userRedditUsername = userRedditUsername;
	}

	/**
	 * <p>Prints the user's details in the problem-statement defined format to console.</p>
	 * <p>Accomplishes core feature.</p>
	 *
	 *@see njsgh.rdpe.repeatinfoback
	 */
	public void printFormattedToConsole()
	{
		System.out.println(getFormattedInfoString("your name is ", ", you are ", " years old, and your username is "));
	}
	
	/**
	 * <p>Accomplishes the "extra credit" task by writing out user's details to a specified file.</p>
	 * <p>Potential task, investigate, compare, implement a better file write methods.</p>
	 * 
	 * @param directoryAndFileDotExt What location to write the CSV to.
	 * @param appendToExistingFile If the location already exists, true append CSV at the end.
	 * @see njsgh.rdpe.repeatinfoback
	 */
	public void writeCSVOutToFile(String directoryAndFileDotExt, boolean appendToExistingFile)
	{
		try
		{
			FileWriter writeOutToFile = new FileWriter(directoryAndFileDotExt, appendToExistingFile);
			
			writeOutToFile.write(getFormattedInfoString("", ",", ",") + System.getProperty("line.separator"));
			
			writeOutToFile.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>Returns a string containing the instance variables, interleaved with the three input strings.</p>
	 * <p>Potential task, complete method removal.</p>
	 * 
	 * @param sepOne First separator, the start of the return string.
	 * @param sepTwo Second separator.
	 * @param sepThree Third separator.
	 * @return String formatted with separators interleaved. 
	 * @see njsgh.rdpe.repeatinfoback
	 */
	public String getFormattedInfoString(String sepOne, String sepTwo, String sepThree)
	{
		return sepOne + userActualName + sepTwo + userActualAge + sepThree + userRedditUsername;
	}
	
	/**
	 * <p>Returns the instance variable holding the user's actual name.</p>
	 * 
	 * @return userActualName
	 */
	public String getUserActualName()
	{
		return userActualName;
	}

	/**
	 * <p>Sets the instance variable to the sole input String, no validation enforced.</p>
	 * <p>Potential task, extend code to validate data.</p>
	 * 
	 * @param userActualName Assigned to instance variable.
	 */
	public void setUserActualName(String userActualName)
	{
		this.userActualName = userActualName;
	}

	/**
	 * <p>Returns the instance variable holding the user's actual age.</p>
	 * 
	 * @return userActualAge
	 */
	public int getUserActualAge()
	{
		return userActualAge;
	}

	/**
	 * <p>Sets the instance variable to the sole input String, no validation enforced.</p>
	 * <p>Potential task, extend code to validate data.</p>
	 * 
	 * @param userActualAge Assigned to instance variable.
	 */
	public void setUserActualAge(int userActualAge)
	{
		this.userActualAge = userActualAge;
	}
	
	/**
	 * <p>Returns the instance variable holding the user's Reddit username.</p>
	 * 
	 * @return userRedditUsername
	 */
	public String getUserRedditUsername()
	{
		return userRedditUsername;
	}
	
	/**
	 * <p>Sets the instance variable to the sole input String, no validation enforced.</p>
	 * <p>Potential task, extend code to validate data.</p>
	 * 
	 * @param userRedditUsername Assigned to instance variable.
	 */
	public void setUserRedditUsername(String userRedditUsername)
	{
		this.userRedditUsername = userRedditUsername;
	}
}
