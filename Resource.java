/*
 * A public class that extends from the Thing base class.
 */
public class Resource extends Thing
{
	// Basic constructor
	// Additionally we add an intial amount of resource
	public Resource(String nameValue, int amountValue, Logger debugValue, Ground groundValue)
	{
		super(nameValue, debugValue, GridType.RESOURCE);

		// Set the amount and inital amount.
		setInitalAmount(amountValue);

		setAmount(amountValue);

		// We must locate every child class of Thing at the end of the constructor.
		locate(groundValue);
	}

	// Constructor with x and y values to create a Resource with a specfic location
	// Only use this for debugging, or check the status of the position x,y first	
	// Additionally we add an intial amount of resource
	public Resource(int xValue, int yValue, String nameValue, int amountValue, Logger debugValue )
	{
		super(nameValue, debugValue, GridType.RESOURCE);

		// Set the amount and the inital amount.
		setInitalAmount(amountValue);
		setAmount(amountValue);

		// We must locate the thing.
		setPos(xValue, yValue);
	}

	/*
	 * Method and Variables to set the amount of resource left.
	 */

	private int amount;
	private int initalAmount;

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amountValue)
	{
		amount = amountValue;
		debug("setting amount to " + amountValue);
	}

	public int getInitalAmount()
	{
		return initalAmount;
	}

	// Private as I dont think we ever want to change this.
	private void setInitalAmount(int amountValue)
	{
		debug("setting inital amount to " + amountValue);
		initalAmount = amountValue;
	}

	// If we can, we need to reduce the amount by the given value
	// If we can not, then we return 0, otherwise return the new value.
	public int reduceAmount(int reduceValue)
	{
		int newAmount = amount-reduceValue;
		if(amount-reduceValue >= 0)
		{
			debug("reducing amount, " + amount + " by " + reduceValue + " to " + newAmount);
			amount = newAmount;
			return newAmount;
		}
		else
		{
			debug("Cannot reduce amount, " + amount + " by " + reduceValue);
			return 0;
		}
	}
	// END amount of resource stuff
}