/**
 * 
 */
package njsgh.rdpe.personallyusefulcalculator;

import java.util.Arrays;

/**
 * @author bia
 *
 */
public class SystemOfUnits
{
	private String systemOfUnitsTitle, systemOfUnitsContext;
	private String[] arrayOfUnitsUnabbrDesc, arrayOfUnitsAbbrDesc;
	private int[] relativeToNextUnitDown;
	
	private boolean systemTitleAndContextCreatedCorrectly;
	private String whichUnitsCreatedCorrectly;
	private boolean relationsCreatedCorrectly;
	private boolean objectCreatedCorrectly;
	
	/**
	 * 
	 */
	public SystemOfUnits(String systemOfUnitsTitle, String systemOfUnitsContext, String[] arrayOfUnitsUnabbrDesc, String[] arrayOfUnitsAbbrDesc, int[] relativeToNextUnitDown)
	{
		this.systemOfUnitsTitle = systemOfUnitsTitle;
		this.systemOfUnitsContext = systemOfUnitsContext;
		this.arrayOfUnitsUnabbrDesc = arrayOfUnitsUnabbrDesc;
		this.arrayOfUnitsAbbrDesc = arrayOfUnitsAbbrDesc;
		this.relativeToNextUnitDown = relativeToNextUnitDown;
		
		systemTitleAndContextCreatedCorrectly = checkSystemTitleAndContextCreatedCorrectly();
		whichUnitsCreatedCorrectly = checkUnitsCreatedCorrectly();
		relationsCreatedCorrectly = checkRelationsCreatedCorrectly();
		objectCreatedCorrectly = checkObjectCreatedCorrectly();
	}
	
	public SystemOfUnits(String titleToSystemOfUnits, String systemOfUnitsContext, String stringOfUnitsUnabbrDescCSV,  String stringOfUnitsAbbrDescCSV, String relativeToNextUnitDownCSV, String splitter)
	{
		this.systemOfUnitsTitle = titleToSystemOfUnits;
		this.systemOfUnitsContext = systemOfUnitsContext;
		arrayOfUnitsUnabbrDesc = stringOfUnitsUnabbrDescCSV.split(splitter);
		arrayOfUnitsAbbrDesc = stringOfUnitsAbbrDescCSV.split(splitter);
		relativeToNextUnitDown = stringArrayToIntArray(relativeToNextUnitDownCSV.split(splitter));
		
		systemTitleAndContextCreatedCorrectly = checkSystemTitleAndContextCreatedCorrectly();
		whichUnitsCreatedCorrectly = checkUnitsCreatedCorrectly();
		relationsCreatedCorrectly = checkRelationsCreatedCorrectly();
		objectCreatedCorrectly = checkObjectCreatedCorrectly();
	}

	public double convertWithinSystem(double valueToConvert, String fromUnit, String toUnit)
	{
		if(objectCreatedCorrectly)
		{
			return valueToConvert * getFactorFromStrings(fromUnit, toUnit);
		} else return -1.0;
	}
	
	public double getFactorFromStrings(String fromUnitString, String toUnitString)
	{
		if(objectCreatedCorrectly)
		{
			if(fromUnitString.equalsIgnoreCase(toUnitString))
			{
				return getFactorFromIndexes(getIndexOfUnitValue(fromUnitString), getIndexOfUnitValue(toUnitString));
			}else return 1.0;
		} else return -1.0;
	}
	
	public double getFactorFromIndexes(int fromUnitIndex, int toUnitIndex)
	{	
		if(fromUnitIndex != toUnitIndex)
		{
			boolean convertingDownwardsInScale = fromUnitIndex < toUnitIndex;
			double returnFactor = 1;
			if(convertingDownwardsInScale)
			{
				for(int index = fromUnitIndex; index < toUnitIndex; index++)
				{
					returnFactor *= relativeToNextUnitDown[index];
				}
				return returnFactor;
			}else 
			{
				for(int index = fromUnitIndex-1; index >= toUnitIndex; index--)
				{
					returnFactor /= relativeToNextUnitDown[index];
				}
				return returnFactor;
			}
		}else return 1.0;
	}
	
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
	 * @return the titleToSystemOfUnits
	 */
	public String getSystemOfUnitsTitle()
	{
		return systemOfUnitsTitle;
	}
	
	/**
	 * @return the quantityName
	 */
	public String getSystemOfUnitsContext()
	{
		return systemOfUnitsContext;
	}

	/**
	 * @return the arrayOfUnitsUnabbrDesc
	 */
	public String[] getArrayOfUnitsUnabbrDesc()
	{
		return arrayOfUnitsUnabbrDesc;
	}

	/**
	 * @return the arrayOfUnitsAbbrDesc
	 */
	public String[] getArrayOfUnitsAbbrDesc()
	{
		return arrayOfUnitsAbbrDesc;
	}

	/**
	 * @return the relativeToNextUnitDown
	 */
	public int[] getRelativeToNextUnitDown()
	{
		return relativeToNextUnitDown;
	}

	/**
	 * @param titleToSystemOfUnits the titleToSystemOfUnits to set
	 */
	public void setSystemTitleAndContext(String systemOfUnitsTitle, String systemOfUnitsContext)
	{
		this.systemOfUnitsTitle = systemOfUnitsTitle;
		this.systemOfUnitsContext = systemOfUnitsContext;
		systemTitleAndContextCreatedCorrectly = checkSystemTitleAndContextCreatedCorrectly();
		objectCreatedCorrectly = checkObjectCreatedCorrectly();
	}

	/**
	 * @param arrayOfUnitsUnabbrDesc the arrayOfUnitsUnabbrDesc to set
	 */
	public void setArrayOfUnitsUnabbrDesc(String[] arrayOfUnitsUnabbrDesc)
	{
		this.arrayOfUnitsUnabbrDesc = arrayOfUnitsUnabbrDesc;
		whichUnitsCreatedCorrectly = checkUnitsCreatedCorrectly();
		objectCreatedCorrectly = checkObjectCreatedCorrectly();
	}

	/**
	 * @param arrayOfUnitsAbbrDesc the arrayOfUnitsAbbrDesc to set
	 */
	public void setArrayOfUnitsAbbrDesc(String[] arrayOfUnitsAbbrDesc)
	{
		this.arrayOfUnitsAbbrDesc = arrayOfUnitsAbbrDesc;
		whichUnitsCreatedCorrectly = checkUnitsCreatedCorrectly();
		objectCreatedCorrectly = checkObjectCreatedCorrectly();
		
	}

	/**
	 * @param relativeToNextUnitDown the relativeToNextUnitDown to set
	 */
	public void setRelativeToNextUnitDown(int[] relativeToNextUnitDown)
	{
		this.relativeToNextUnitDown = relativeToNextUnitDown;
		relationsCreatedCorrectly = checkRelationsCreatedCorrectly(); 
		objectCreatedCorrectly = checkObjectCreatedCorrectly();
		
	}
	
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
	
	public boolean checkSystemTitleAndContextCreatedCorrectly()
	{
		return !systemOfUnitsTitle.isBlank() && !systemOfUnitsContext.isBlank();
	}
	
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
	
	public boolean checkRelationsCreatedCorrectly()
	{
		return !whichUnitsCreatedCorrectly.equals("INCCONST") && relativeToNextUnitDown.length != 0 && (relativeToNextUnitDown.length == arrayOfUnitsUnabbrDesc.length-1 || relativeToNextUnitDown.length == arrayOfUnitsAbbrDesc.length-1);
	}
	
	public boolean checkObjectCreatedCorrectly()
	{
		return checkSystemTitleAndContextCreatedCorrectly() && !whichUnitsCreatedCorrectly.equals("INCCONST") && relationsCreatedCorrectly;
	}
	
	public static String removeLeadAndTrailSquareBrackets(String notQuiteCSV)
	{
		notQuiteCSV = !notQuiteCSV.isBlank() ? notQuiteCSV.substring(notQuiteCSV.indexOf("[")+1, notQuiteCSV.lastIndexOf("]")) : notQuiteCSV;
		return notQuiteCSV;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "SystemOfUnits [systemOfUnitsTitle=" + systemOfUnitsTitle + ", systemOfUnitsContext="
				+ systemOfUnitsContext + ", arrayOfUnitsUnabbrDesc=" + Arrays.toString(arrayOfUnitsUnabbrDesc)
				+ ", arrayOfUnitsAbbrDesc=" + Arrays.toString(arrayOfUnitsAbbrDesc) + ", relativeToNextUnitDown="
				+ Arrays.toString(relativeToNextUnitDown) + ", systemTitleAndContextCreatedCorrectly="
				+ systemTitleAndContextCreatedCorrectly + ", whichUnitsCreatedCorrectly=" + whichUnitsCreatedCorrectly
				+ ", relationsCreatedCorrectly=" + relationsCreatedCorrectly + ", objectCreatedCorrectly="
				+ objectCreatedCorrectly + "]";
	}
}
