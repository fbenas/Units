/*
 * A public class that extends from the Thing base class. 
 */
public class Home extends Thing
{
	/*
	 * Constructors
	*/

	// Basic constructor
	public Home(String nameValue, Logger debugValue, Ground groundValue)
	{
		super(nameValue, debugValue, GridType.HOME);
		// We must locate the thing
		locate(groundValue);
	}

	// Constructor with x and y values to create a Unit with a specfic location
	// Use only for debugging, if used normally we need to check the x and y pos is clear!
	public Home(int xValue, int yValue, String nameValue, Logger debugValue)
	{
		super(nameValue, debugValue, GridType.HOME);

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

	// Spawn a unit one valid move away from this location
	public Unit spawnUnit(String nameValue, Ground groundValue)
	{
		// Get a random move
		Point p = getRandomValidMove(groundValue);
		// return the new Unit
		return new Unit(p.getX(),p.getY(),nameValue,this,debugger);
	}

}
