/**
 *  Latest Author: Nicholas Seaborn
 *  Latest Author Email: njs-prm@protonmail.ch
 *  
 *  Section Name: Class, RepeatInfoBack
 *  Version: 2018.11.14
 *  License: Public Domain
 *  Stage: Completed, Untested, Reliable, SemiDocumented
 *  
 *  URL: https://old.reddit.com/r/dailyprogrammer/comments/pih8x/easy_challenge_1/
 *  Description: 
 *  	Create a program that will ask the users name, age, and reddit username. Have it tell them the information back, in the format:
 *  	your name is (blank), you are (blank) years old, and your username is (blank)
 *  For extra credit, have the program log this information in a file to be accessed later.
 */

package njsgh.rdpe.repeatinfoback;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author bia
 *
 */
public class RepeatInfoBack
{
	private String userActualName, userActualAge, userChosenUsername;
	
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
	 * @param userActualName
	 * @param userActualAge
	 * @param userChosenUsername
	 */
	public RepeatInfoBack(String userActualName, String userActualAge, String userChosenUsername)
	{
		super();
		this.userActualName = userActualName;
		this.userActualAge = userActualAge;
		this.userChosenUsername = userChosenUsername;
	}

	/**
	 * @param userActualName
	 * @param userActualAge
	 * @param userChosenUsername
	 */
	public RepeatInfoBack()
	{
		super();
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
