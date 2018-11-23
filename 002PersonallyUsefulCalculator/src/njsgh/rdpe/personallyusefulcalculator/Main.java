/**
 *  Latest Author: Nicholas Seaborn
 *  Latest Author Email: njs-prm@protonmail.ch
 *  
 *  Section Name: Class, PersonallyUsefulCalculator
 *  Version: 2018.11.14
 *  License: Public Domain
 *  Stage: Implementation, Untested, Unreliable, Undocumented
 *  
 *  URL: https://old.reddit.com/r/dailyprogrammer/comments/pjbj8/easy_challenge_2/
 *  Description: 
 *  	An important part of programming is being able to apply your programs, so your challenge for today is to create a calculator application that has use in your life.
 *  	It might be an interest calculator, or it might be something that you can use in the classroom. For example, if you were in physics class, you might want to make a F = M * A calc.
 *  	EXTRA CREDIT: make the calculator have multiple functions! Not only should it be able to calculate F = M * A, but also A = F/M, and M = F/A!
 */

package njsgh.rdpe.personallyusefulcalculator;

import java.util.Arrays;

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
		SystemOfMeasurement iSU = new SystemOfMeasurement("International System of Units", "Length", "", "km, m, cm, mm", "1000, 100, 10", ", ");
		SystemOfMeasurement bI = new SystemOfMeasurement("Britsh Imperial", "Length", "league, mile, furlong, chain, yard, foot, inch, thou", "", "3, 8, 10, 22, 3, 12, 1000", ", ");
		System.out.println(iSU.toString());
		System.out.println(bI.toString());
		System.out.println(iSU.convertWithinSystem(1, "km", "mm"));
		System.out.println(iSU.convertWithinSystem(1, "mm", "km"));
		System.out.println(bI.convertWithinSystem(1, "league", "foot"));
		System.out.println(bI.convertWithinSystem(3, "foot", "chain"));
		
		BaseUnitConversion bIToISU= new BaseUnitConversion(bI, iSU, 0.0254);
		System.out.println(bIToISU.convertBetweenSystems(1, "mile", "km"));
		
	}
}
