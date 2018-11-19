/**
 * 
 */
package njsgh.rdpe.personallyusefulcalculator;

/**
 * @author bia
 *
 */
public class BaseUnitConversion
{
	private SystemOfUnits convertFrom, convertTo;
	private boolean sameContext;
	private double baseUnitFactor;
	
	
	/**
	 * 
	 */
	public BaseUnitConversion(SystemOfUnits convertFrom, SystemOfUnits convertTo, double baseUnitFactor)
	{
		this.convertFrom = convertFrom;
		this.convertTo = convertTo;
		sameContext = convertFrom.getSystemOfUnitsContext().equals(convertTo.getSystemOfUnitsContext());
		this.baseUnitFactor = baseUnitFactor;
	}
	
	public double convertBetweenSystems(double valueToConvert, String fromUnit, String toUnit)
	{ 
		if(sameContext)
		{
			double convertFromToSystemBase = valueToConvert * convertFrom.getFactorFromIndexes(convertFrom.getIndexOfUnitValue(fromUnit), convertFrom.getRelativeToNextUnitDown().length);
			double convertBaseToBase = convertFromToSystemBase * baseUnitFactor;
			double convertBaseToTargetUnit = convertBaseToBase * convertTo.getFactorFromIndexes(convertTo.getRelativeToNextUnitDown().length, convertTo.getIndexOfUnitValue(toUnit));
			return convertBaseToTargetUnit;
		}else return -1.0;
	}
}
