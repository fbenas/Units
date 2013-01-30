package engine;

/*
 * Public class Home extends the parent class Thing. 
 *
 * Home acts as a spawn point for Unit objects and as a hold
 * for any resource a Unit collects.
 *
 * The home is placed on the grid and can spawn a unit to
 * any of the surrounding squares, this choice is random.
 *
 * A unit will interact with the Home object from any of the
 * surrounding squares.
 */

import utils.GridType;

public class Home extends Thing
{
	/* CONSTUCTORS */

	// Constructor to randomly place a Home on the grid provided in grounValue
	// The nameValue and debugValue parameters are passed to the parent class's
	// constructor
	public Home(String nameValue, Logger debugValue, Ground groundValue)
	{
		super(nameValue, debugValue, GridType.HOME); // Call the parent constructor
		locate(groundValue); // Position the Home randomly on the grid.
	}

	// Constructor to place a Home on the grid provided in grounValue
	// by using the given xValue and yValue
	// The nameValue and debugValue parameters are passed to the parent class's
	// constructor
	// TODO: check the validity of the position.	
	public Home(int xValue, int yValue, String nameValue, Logger debugValue)
	{
		super(nameValue, debugValue, GridType.HOME); // Call the parent constructor
		setPos(xValue, yValue); // set the postion of the Home object.
	}
	/* END CONSTRUCTORS */

	/* PUBLIC */

	// set the amount of resource the Unit contains
	public void setAmount(int amountValue)
	{
		debug("Setting amount to " + amountValue);
		amount = amountValue;
	}
	
	// Increase the amount of resource the unit contains
	public void increaseAmount(int increaseAmount)
	{
		int newAmount = amount+increaseAmount;
		debug("Increasing amount, " + amount + " by " + increaseAmount + " to " + newAmount);
		amount = newAmount;
	}

	// Spawn a unit one valid move away from the Home's location
	public Unit spawnUnit(String nameValue, Ground groundValue)
	{
		Point p = getRandomValidMove(groundValue); // Get a random move
		return new Unit(p.getX(),p.getY(),nameValue,this,debugger); // return the new Unit
	}

	// Return the amount of resource the unit contain
	public int getAmount()
	{
		return amount;
	}
	/* END PUBLIC */

	/* PROTECTED  */
	/* END PROTECTED*/

	/* PRIVATE*/

	private int amount = 0; // The amount of resource the unit contains
	/* END PRIVATE */
}