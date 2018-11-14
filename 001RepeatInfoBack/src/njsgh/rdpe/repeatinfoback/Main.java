/**
 * 
 */
package njsgh.rdpe.repeatinfoback;

/**
 * @author bia
 *
 */
public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		RepeatInfoBack user = new RepeatInfoBack();
		user.repeatInfoBackToConsole();
		user.writeInfoOutToCSV(true);
		
		RepeatInfoBack anotherUser = new RepeatInfoBack("Chris", "25", "css-gh");
		anotherUser.repeatInfoBackToConsole();
		anotherUser.writeInfoOutToCSV(false);
		
		user.writeInfoOutToCSV(true);
		anotherUser.setUserActualName("Sam");
		anotherUser.setUserActualAge("27");
		anotherUser.setUserChosenUsername("sam-gh");
		anotherUser.repeatInfoBackToConsole();
		anotherUser.writeInfoOutToCSV(true);
	}
}
