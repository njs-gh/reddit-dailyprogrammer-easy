package njsgh.rdpe.repeatinfoback;

/**
 *
 *
 */
public class Main
{

	/**
	 * @param args
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
