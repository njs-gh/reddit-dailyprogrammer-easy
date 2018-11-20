package njsgh.rdpe.repeatinfoback;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


/**
 *  
 *  @author <a href="njs-prm@protonmail.ch">Nicholas Seaborn</a>
 *  @see njsgh.rdpe.repeatinfoback
 *  @version 2018.11.20
 */
public class RepeatInfoBack
{
	private String userActualName, userActualAge, userChosenUsername;
	
	/**
	 * Class constructor, accepting three strings for global variable assignment, with no validation.
	 * @param userActualName
	 * @param userActualAge
	 * @param userChosenUsername
	 */
	public RepeatInfoBack(String userActualName, String userActualAge, String userChosenUsername)
	{
		this.userActualName = userActualName;
		this.userActualAge = userActualAge;
		this.userChosenUsername = userChosenUsername;
	}

	/**
	 * Class construction, accepts no inputs, and procedurally asks the user via command line for values to assign to global variables.
	 * @param userActualName 
	 * @param userActualAge
	 * @param userChosenUsername
	 * @return 
	 */
	public RepeatInfoBack()
	{
		Scanner readInFromConsole = new Scanner(System.in);
		
		System.out.println("Please enter your actual name: ");
		this.userActualName = readInFromConsole.nextLine();
		System.out.println("Please enter your acutal age: ");
		this.userActualAge = readInFromConsole.nextLine();
		System.out.println("Please enter your username: ");
		this.userChosenUsername = readInFromConsole.nextLine();
		
		readInFromConsole.close();
	}
	
	/**
	 * @return the userActualName
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
	 * @return the userActualAge
	 */
	public String getUserActualAge()
	{
		return userActualAge;
	}

	/**
	 * @param userActualAge the userActualAge to set
	 */
	public void setUserActualAge(String userActualAge)
	{
		this.userActualAge = userActualAge;
	}

	/**
	 * @return the userChosenUsername
	 */
	public String getUserChosenUsername()
	{
		return userChosenUsername;
	}

	/**
	 * @param userChosenUsername the userChosenUsername to set
	 */
	public void setUserChosenUsername(String userChosenUsername)
	{
		this.userChosenUsername = userChosenUsername;
	}

	/**
	 * @author bia
	 *
	 */
	public void repeatInfoBackToConsole()
	{
		System.out.println("your name is " + this.userActualName + ", you are " + this.userActualAge + " years old, and your username is " + this.userChosenUsername);
	}
	
	/**
	 * @author bia
	 *
	 */
	public void writeInfoOutToCSV(boolean appendToExistingFile)
	{
		try
		{
			FileWriter writeOutToFile = new FileWriter("repeatInfoBack.csv", appendToExistingFile);
			writeOutToFile.write(this.userActualName + "," + this.userActualAge + "," + this.userChosenUsername + System.getProperty("line.separator"));
			writeOutToFile.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
