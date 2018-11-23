package njsgh.rdpe.personallyusefulcalculator;

import java.util.Arrays;

/**
 * @author bia
 *
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
	 * @return the titleToSystemOfMeasurement
	 */
	public String getSystemOfMeasurementTitle()
	{
		return systemOfMeasurementTitle;
	}
	
	/**
	 * @return the quantityName
	 */
	public String getSystemOfMeasurementContext()
	{
		return systemOfMeasurementContext;
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
	 * 
	 * @return
	 */
	public boolean checkTitleAndContextCreatedCorrectly()
	{
		return !systemOfMeasurementTitle.isBlank() && !systemOfMeasurementContext.isBlank();
	}
	
	/**
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
	 * 
	 * @return
	 */
	public boolean checkRelationsCreatedCorrectly()
	{
		return !whichUnitsCreatedCorrectly.equals("INCCONST") && relativeToNextUnitDown.length != 0 && (relativeToNextUnitDown.length == arrayOfUnitsUnabbrDesc.length-1 || relativeToNextUnitDown.length == arrayOfUnitsAbbrDesc.length-1);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean checkObjectInstantiatedCorrectly()
	{
		return checkTitleAndContextCreatedCorrectly() && !whichUnitsCreatedCorrectly.equals("INCCONST") && relationsCreatedCorrectly;
	}
	
	/**
	 * 
	 * @param notQuiteCSV
	 * @return
	 */
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
		return "SystemOfMeasurement [systemOfMeasurementTitle=" + systemOfMeasurementTitle
				+ ", systemOfMeasurementContext=" + systemOfMeasurementContext + ", arrayOfUnitsUnabbrDesc="
				+ Arrays.toString(arrayOfUnitsUnabbrDesc) + ", arrayOfUnitsAbbrDesc="
				+ Arrays.toString(arrayOfUnitsAbbrDesc) + ", relativeToNextUnitDown="
				+ Arrays.toString(relativeToNextUnitDown) + ", titleAndContextCreatedCorrectly="
				+ titleAndContextCreatedCorrectly + ", relationsCreatedCorrectly=" + relationsCreatedCorrectly
				+ ", objectInstantiatedCorrectly=" + objectInstantiatedCorrectly + ", whichUnitsCreatedCorrectly="
				+ whichUnitsCreatedCorrectly + "]";
	}
}
