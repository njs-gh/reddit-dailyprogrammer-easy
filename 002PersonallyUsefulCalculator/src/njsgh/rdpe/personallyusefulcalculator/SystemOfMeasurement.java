package njsgh.rdpe.personallyusefulcalculator;

import java.util.Arrays;

/**
 *  <p>Should be able to represent any System of Measurement, containing more than one unit, where there's some integer multiple to convert between units.</p>
 *  <p>IE: International System of Units, Length, ["km", "m", "cm", "mm"], [1000, 100, 10]</p>
 *  <p>Potential task, convert relations array to double.</p>
 *  
 *  @author <a href="njs-pm@protonmail.ch">Nicholas Seaborn</a>
 *  @see njsgh.rdpe.personallyusefulcalculator
 *  @version 2018.11.24
 */
public class SystemOfMeasurement
{
	private String systemOfMeasurementTitle, systemOfMeasurementContext;
	private String[] arrayOfUnitsUnabbrDesc, arrayOfUnitsAbbrDesc;
	private int[] relativeToNextUnitDown;
	
	private boolean titleAndContextCreatedCorrectly, relationsCreatedCorrectly, objectInstantiatedCorrectly;
	private String whichUnitsCreatedCorrectly;
	
	/**
	 * 
	 */
	public SystemOfMeasurement(String systemOfMeasurementTitle, String systemOfMeasurementContext, String[] arrayOfUnitsUnabbrDesc, String[] arrayOfUnitsAbbrDesc, int[] relativeToNextUnitDown)
	{
		this.systemOfMeasurementTitle = systemOfMeasurementTitle;
		this.systemOfMeasurementContext = systemOfMeasurementContext;
		this.arrayOfUnitsUnabbrDesc = arrayOfUnitsUnabbrDesc;
		this.arrayOfUnitsAbbrDesc = arrayOfUnitsAbbrDesc;
		this.relativeToNextUnitDown = relativeToNextUnitDown;
		
		titleAndContextCreatedCorrectly = checkTitleAndContextCreatedCorrectly();
		whichUnitsCreatedCorrectly = checkUnitsCreatedCorrectly();
		relationsCreatedCorrectly = checkRelationsCreatedCorrectly();
		objectInstantiatedCorrectly = checkObjectInstantiatedCorrectly();
	}
	
	/**
	 * 
	 */
	public SystemOfMeasurement(String systemOfMeasurementTitle, String systemOfMeasurementContext, String stringOfUnitsUnabbrDescCSV,  String stringOfUnitsAbbrDescCSV, String relativeToNextUnitDownCSV, String splitter)
	{
		this.systemOfMeasurementTitle = systemOfMeasurementTitle;
		this.systemOfMeasurementContext = systemOfMeasurementContext;
		arrayOfUnitsUnabbrDesc = stringOfUnitsUnabbrDescCSV.split(splitter);
		arrayOfUnitsAbbrDesc = stringOfUnitsAbbrDescCSV.split(splitter);
		relativeToNextUnitDown = stringArrayToIntArray(relativeToNextUnitDownCSV.split(splitter));
		
		titleAndContextCreatedCorrectly = checkTitleAndContextCreatedCorrectly();
		whichUnitsCreatedCorrectly = checkUnitsCreatedCorrectly();
		relationsCreatedCorrectly = checkRelationsCreatedCorrectly();
		objectInstantiatedCorrectly = checkObjectInstantiatedCorrectly();
	}
	
	/**
	 * 
	 */
	public double convertWithinSystem(double valueToConvert, String fromUnit, String toUnit)
	{
		if(objectInstantiatedCorrectly)
		{
			return valueToConvert * getRelativeToFromStrings(fromUnit, toUnit);
		} else return -1.0;
	}
	
	/**
	 * 
	 */
	public double getRelativeToFromStrings(String fromUnitString, String toUnitString)
	{
		if(objectInstantiatedCorrectly)
		{
			if(fromUnitString.equalsIgnoreCase(toUnitString))
			{
				return getRelativeToFromIndexes(getIndexOfUnitValue(fromUnitString), getIndexOfUnitValue(toUnitString));
			}else return 11.0;
		} else return 1.0;
	}
	
	/**
	 * 
	 */
	public double getRelativeToFromIndexes(int fromUnitIndex, int toUnitIndex)
	{	
		if(fromUnitIndex != toUnitIndex)
		{
			boolean convertingDownwardsInScale = fromUnitIndex < toUnitIndex;
			double returnRelativeTo = 1;
			if(convertingDownwardsInScale)
			{
				for(int index = fromUnitIndex; index < toUnitIndex; index++)
				{
					returnRelativeTo *= relativeToNextUnitDown[index];
				}
				return returnRelativeTo;
			}else 
			{
				for(int index = fromUnitIndex-1; index >= toUnitIndex; index--)
				{
					returnRelativeTo /= relativeToNextUnitDown[index];
				}
				return returnRelativeTo;
			}
		}else return 1.0;
	}
	
	/**
	 * 
	 */
	public int getIndexOfUnitValue(String unitToFind)
	{
		int searchInUnabbrForIndex = getIndexOfUnitValue(unitToFind, arrayOfUnitsUnabbrDesc);
		if(searchInUnabbrForIndex != -1)
		{
			return searchInUnabbrForIndex;
		}else
		{
			return getIndexOfUnitValue(unitToFind, arrayOfUnitsAbbrDesc);
		}
	}
	
	/**
	 * 
	 */
	public int getIndexOfUnitValue(String unitToFind, String[] inThisArray)
	{
		for(int index = 0; index < inThisArray.length; index++)
		{
			if(inThisArray[index].equals(unitToFind))
			{
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * @param titleToSystemOfMeasurement the titleToSystemOfMeasurement to set
	 */
	public void setSystemTitleAndContext(String SystemOfMeasurementTitle, String SystemOfMeasurementContext)
	{
		this.systemOfMeasurementTitle = SystemOfMeasurementTitle;
		this.systemOfMeasurementContext = SystemOfMeasurementContext;
		titleAndContextCreatedCorrectly = checkTitleAndContextCreatedCorrectly();
		objectInstantiatedCorrectly = checkObjectInstantiatedCorrectly();
	}
	
	/**
	 * @param arrayOfUnitsUnabbrDesc the arrayOfUnitsUnabbrDesc to set
	 */
	public void setArrayOfUnitsUnabbrDesc(String[] arrayOfUnitsUnabbrDesc)
	{
		this.arrayOfUnitsUnabbrDesc = arrayOfUnitsUnabbrDesc;
		whichUnitsCreatedCorrectly = checkUnitsCreatedCorrectly();
		objectInstantiatedCorrectly = checkObjectInstantiatedCorrectly();
	}
	
	/**
	 * @param arrayOfUnitsAbbrDesc the arrayOfUnitsAbbrDesc to set
	 */
	public void setArrayOfUnitsAbbrDesc(String[] arrayOfUnitsAbbrDesc)
	{
		this.arrayOfUnitsAbbrDesc = arrayOfUnitsAbbrDesc;
		whichUnitsCreatedCorrectly = checkUnitsCreatedCorrectly();
		objectInstantiatedCorrectly = checkObjectInstantiatedCorrectly();
		
	}
	
	/**
	 * @param relativeToNextUnitDown the relativeToNextUnitDown to set
	 */
	public void setRelativeToNextUnitDown(int[] relativeToNextUnitDown)
	{
		this.relativeToNextUnitDown = relativeToNextUnitDown;
		relationsCreatedCorrectly = checkRelationsCreatedCorrectly(); 
		objectInstantiatedCorrectly = checkObjectInstantiatedCorrectly();
		
	}
	
	/**
	 * <p>Method to check </p>
	 * 
	 * @param unsanitisedStringArray
	 * @return
	 */
	public int[] stringArrayToIntArray(String[] unsanitisedStringArray)
	{
		int[] buildArrayToReturn = new int[unsanitisedStringArray.length];
		for(int index = 0; index < unsanitisedStringArray.length; index++)
		{
			try
			{
				buildArrayToReturn[index] = Integer.parseInt(unsanitisedStringArray[index]);
			} catch (NumberFormatException e)
			{
				return new int[0];
			}
		}
		return buildArrayToReturn;
	}
	
	/**
	 * <p>Method to check </p>
	 * 
	 * @return
	 */
	public boolean checkTitleAndContextCreatedCorrectly()
	{
		return !systemOfMeasurementTitle.isBlank() && !systemOfMeasurementContext.isBlank();
	}
	
	/**
 	 * <p>Method to check </p>
	 * 
	 * @param testDupesArrayOfUnits
	 * @return
	 */
	public boolean checkUnitsDistinct(String[] testDupesArrayOfUnits)
	{
		String[] workingCopy = Arrays.copyOf(testDupesArrayOfUnits, testDupesArrayOfUnits.length);
		Arrays.sort(workingCopy);
		for(int index = 0; index<workingCopy.length-1; index++)
		{
			if(workingCopy[index].equalsIgnoreCase(workingCopy[index+1]))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <p>Method to check </p>
	 * 
	 * @return
	 */
	public String checkUnitsCreatedCorrectly()
	{
		if((arrayOfUnitsUnabbrDesc.length == 0 || arrayOfUnitsUnabbrDesc.length == 1) && (arrayOfUnitsAbbrDesc.length == 0 || arrayOfUnitsAbbrDesc.length == 1))
		{
			return "INCCONST";
		}else if(arrayOfUnitsUnabbrDesc.length > 1 && (arrayOfUnitsAbbrDesc.length == 0 || arrayOfUnitsAbbrDesc.length == 1))
		{
			if(checkUnitsDistinct(arrayOfUnitsUnabbrDesc))
			{
				return "UNABBR";
			}else return "INCCONST";
			
		}else if(arrayOfUnitsAbbrDesc.length > 1 && (arrayOfUnitsUnabbrDesc.length == 0 || arrayOfUnitsUnabbrDesc.length == 1))
		{
			if(checkUnitsDistinct(arrayOfUnitsAbbrDesc))
			{
				return "ABBR";
			}else return "INCCONST";
		}else if(arrayOfUnitsUnabbrDesc.length == arrayOfUnitsAbbrDesc.length)
		{
			if(checkUnitsDistinct(arrayOfUnitsUnabbrDesc) && checkUnitsDistinct(arrayOfUnitsAbbrDesc))
			{
				return "BOTH";
			}else return "INCCONST";
		}else return "INCCONST";
	}
	
	/**
	 * <p>Method to check </p>
	 * 
	 * @return
	 */
	public boolean checkRelationsCreatedCorrectly()
	{
		return !whichUnitsCreatedCorrectly.equals("INCCONST") && relativeToNextUnitDown.length != 0 && (relativeToNextUnitDown.length == arrayOfUnitsUnabbrDesc.length-1 || relativeToNextUnitDown.length == arrayOfUnitsAbbrDesc.length-1);
	}
	
	/**
	 * <p>Method to check if the System of Measurement object has been instantiated correctly.</p>
	 * <p>This is achieved by calling all other "check" methods, and returns true if those other methods do.</p>
	 * 
	 * @return True, if object has been instantiated correctly, such that successful use can occur.
	 */
	public boolean checkObjectInstantiatedCorrectly()
	{
		return checkTitleAndContextCreatedCorrectly() && !checkUnitsCreatedCorrectly().equals("INCCONST") && checkRelationsCreatedCorrectly();
	}
	
	/**
	 * <p>Removes the pre and proceeding opening/closing square bracket, typically found with the return from primitiveArray.toString().</p>
	 * <p>Check first if the sole parameter is empty, if it's length is greater than 2 (ie, avoid "[]") and if it begins with [ and ends with ].</p>
	 * <p>Failing this, the input parameter is returned.</p>
	 * <p>Not used within class itself, useful in the case where object-constructive strings are needed to be retrieved.</p>
	 * 
	 * @param notQuiteCSV "[result,of,array.toString()]"
	 * @return perhapsCSV "result,of,array.toString()"
	 */
	public static String removeLeadAndTrailSquareBrackets(String notQuiteCSV)
	{
		String perhapsCSV = (!notQuiteCSV.isBlank() && notQuiteCSV.length() > 2 && notQuiteCSV.indexOf("[") == 0 && notQuiteCSV.lastIndexOf("[") == notQuiteCSV.length()) ? notQuiteCSV.substring(1, notQuiteCSV.length()-1) : notQuiteCSV;
		return perhapsCSV;
	}
	
	/**
	 * <p>Eclipse source-generated Object/Class.toString().</p>
	 * <p>Not used within class itself, useful to expose class state for use/debugging outside.<p>
	 * 
	 * @return String Current object state.
	 */
	@Override
	public String toString()
	{
		return "SystemOfMeasurement [systemOfMeasurementTitle=" + systemOfMeasurementTitle
				+ ", systemOfMeasurementContext=" + systemOfMeasurementContext + ", arrayOfUnitsUnabbrDesc="
				+ Arrays.toString(arrayOfUnitsUnabbrDesc) + ", arrayOfUnitsAbbrDesc="
				+ Arrays.toString(arrayOfUnitsAbbrDesc) + ", relativeToNextUnitDown="
				+ Arrays.toString(relativeToNextUnitDown) + ", titleAndContextCreatedCorrectly="
				+ titleAndContextCreatedCorrectly + ", relationsCreatedCorrectly=" + relationsCreatedCorrectly
				+ ", objectInstantiatedCorrectly=" + objectInstantiatedCorrectly + ", whichUnitsCreatedCorrectly="
				+ whichUnitsCreatedCorrectly + "]";
	}
	
	/**
	 * <p>Returns the instance variable holding the title to the System of Measurement.</p>
	 * 
	 * @return the systemOfMeasurementTitle
	 */
	public String getSystemOfMeasurementTitle()
	{
		return systemOfMeasurementTitle;
	}
	
	/**
	 * <p>Returns the instance variable holding the context to the System of Measurement.</p>
	 * 
	 * @return systemOfMeasurementContext
	 */
	public String getSystemOfMeasurementContext()
	{
		return systemOfMeasurementContext;
	}
	
	/**
	 * <p>Returns the instance variable holding the String array of unabbreviated units.</p>
	 * 
	 * @return arrayOfUnitsUnabbrDesc
	 */
	public String[] getArrayOfUnitsUnabbrDesc()
	{
		return arrayOfUnitsUnabbrDesc;
	}
	
	/**
	 * <p>Returns the instance variable holding the String array of abbreviated units.</p>
	 * 
	 * @return arrayOfUnitsAbbrDesc
	 */
	public String[] getArrayOfUnitsAbbrDesc()
	{
		return arrayOfUnitsAbbrDesc;
	}
	
	/**
	 * <p>Returns the instance variable holding the integer array of unit relations.</p>
	 * 
	 * @return relativeToNextUnitDown
	 */
	public int[] getRelativeToNextUnitDown()
	{
		return relativeToNextUnitDown;
	}
	
	
	/**
	 * <p>Returns the instance variable holding whether the System of Measurement title and context have been created correctly. .</p>
	 * 
	 * @return titleAndContextCreatedCorrectly
	 */
	public boolean isTitleAndContextCreatedCorrectly()
	{
		return titleAndContextCreatedCorrectly;
	}

	/**
	 * <p>Returns the instance variable holding which units have been declared correctly.</p>
	 * 
	 * @return whichUnitsCreatedCorrectly Values: "INCCNST", "UNABBR", "ABBR", "BOTH".
	 */
	public String getWhichUnitsCreatedCorrectly()
	{
		return whichUnitsCreatedCorrectly;
	}
	
	/**
	 * <p>Returns the instance variable holding whether the unit's relations have been created correctly.</p>
	 * 
	 * @return relationsCreatedCorrectly
	 */
	public boolean isRelationsCreatedCorrectly()
	{
		return relationsCreatedCorrectly;
	}
	
	/**
	 * <p>Returns the instance variable holding whether the object has been instantiated correctly, and is in compliance for use with object methods.</p>
	 * 
	 * @return objectInstantiatedCorrectly
	 */
	public boolean isObjectInstantiatedCorrectly()
	{
		return objectInstantiatedCorrectly;
	}
}
