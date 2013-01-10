/*
 * A public class that extends from the Thing base class. 
 */
public class Home extends Thing
{
	/*
	 * Constructors
	*/

	// Basic constructor
	public Home(String nameValue, Debug debugValue)
	{
		super(nameValue, debugValue);
		// We must locate the thing
		locate();
	}

	// Constructor with x and y values to create a Unit with a specfic location
	public Home(int xValue, int yValue, String nameValue, Debug debugValue)
	{
		super(nameValue, debugValue);

		// We must locate the thing.
		setPos(xValue, yValue);
	}

	// END Constructors

	/*
	 * Methods and variables for storing resource
	*/
	
	private int amount = 0;
	
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int amountValue)
	{
		debug("Setting amount to " + amountValue);
		amount = amountValue;
	}
	public void increaseAmount(int increaseAmount)
	{
		int newAmount = amount+increaseAmount;
		debug("Increasing amount, " + amount + " by " + increaseAmount + " to " + newAmount);
		amount = newAmount;
	}
}
