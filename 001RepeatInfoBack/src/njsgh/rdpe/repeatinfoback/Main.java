package njsgh.rdpe.repeatinfoback;

/**
 * <p>Used as entry point for application, otherwise to demonstrate functionality to potential users.</p>
 * 
 *  @author <a href="njs-pm@protonmail.ch">Nicholas Seaborn</a>
 *  @see njsgh.rdpe.repeatinfoback
 *  @version 2018.11.20
 */
public class Main
{

	/**
	 * <p>Complete functionality demonstration for RepeatInfoBack, including console interaction.</p>
	 * 
	 * @param args Declared but not used.
	 * @see njsgh.rdpe.repeatinfoback.RepeatInfoBack
	 */
	public static void main(String[] args)
	{
		RepeatInfoBack userCommandLine = new RepeatInfoBack();
		userCommandLine.printFormattedToConsole();
		userCommandLine.writeCSVOutToFile("bin/repeatInfoBack.csv", true);
		
		RepeatInfoBack constructedUser = new RepeatInfoBack("Paul", 25, "pdm-gh");
		constructedUser.printFormattedToConsole();
		constructedUser.writeCSVOutToFile("bin/repeatInfoBack.csv", false);
		
		constructedUser.setUserActualName("Scott");
		constructedUser.setUserActualAge(27);
		constructedUser.setUserRedditUsername("st-gh");
		constructedUser.printFormattedToConsole();
		constructedUser.writeCSVOutToFile("bin/repeatInfoBack.csv", true);
	}
}
