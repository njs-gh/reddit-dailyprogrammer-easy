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
	private SystemOfMeasurement convertFrom, convertTo;
	private boolean sameContext;
	private double baseUnitFactor;
	
	
	/**
	 * 
	 */
	public BaseUnitConversion(SystemOfMeasurement convertFrom, SystemOfMeasurement convertTo, double baseUnitFactor)
	{
		this.convertFrom = convertFrom;
		this.convertTo = convertTo;
		this.sameContext = this.convertFrom.getSystemOfMeasurementContext().equals(this.convertTo.getSystemOfMeasurementContext());
		this.baseUnitFactor = baseUnitFactor;
	}
	
	public double convertBetweenSystems(double valueToConvert, String fromUnit, String toUnit)
	{ 
		if(sameContext)
		{
			double convertFromToSystemBase = valueToConvert * convertFrom.getRelativeToFromIndexes(convertFrom.getIndexOfUnitValue(fromUnit), convertFrom.getRelativeToNextUnitDown().length);
			double convertBaseToBase = convertFromToSystemBase * baseUnitFactor;
			double convertBaseToTargetUnit = convertBaseToBase * convertTo.getRelativeToFromIndexes(convertTo.getRelativeToNextUnitDown().length, convertTo.getIndexOfUnitValue(toUnit));
			return convertBaseToTargetUnit;
		}else return -1.0;
	}
}
