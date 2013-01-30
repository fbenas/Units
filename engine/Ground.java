package engine;

/*
 * public class Ground extends parent class Nothing.
 * 
 * The ground is essentially a 2D array of Nothing objects
 * and forms the basis of the grid that all objects are placed
 * on.
 *
 */

import utils.GridType;

public class Ground extends Nothing
{

	/* CONSTUCTORS */

	// Uses x and y values to create the grid, and passes the other parameters
	// to the parent class constructor
	// Once created, we clear the grid
	public Ground(int xValue, int yValue, String nameValue, Logger debugValue)
	{
		super(nameValue, GridType.GROUND, 3, debugValue);
		debug("Creating ground with dimensions: " + xValue + "," + yValue);
		x = xValue;
		y = yValue;
		ground = new Nothing[x][y];
		clearGround();
		debug("Created empty ground with dimensions: " + xValue + "," + yValue);
	}
	/* END CONSTRUCTORS */

	/* PUBLIC */

	// Clear every space in the grid
	public void clearGround()
	{
		debug("Clearing ground");
		for(int i=0; i < x; i++)
		{
			for (int j=0; j < y; j++)
			{
				clearSpace(i,j);
			}
		}
	}

	// fill the given space with the given Thing
	public boolean fillSpace(Thing thingValue, int xValue, int yValue)
	{
		if(ground[xValue][yValue].getType() == GridType.EMPTY)
		{
			ground[xValue][yValue] = (Nothing) thingValue;
			debug("Added new " + thingValue.getType() + " at position " + thingValue.getX() + "," + thingValue.getY() + " with name " + thingValue.getName());
			return true;
		}
		else
		{
			debug("Could not add " + thingValue.getType() + " at position " + thingValue.getX() + "," + thingValue.getY());
			return false;
		}
	}

	// fill the given space with the given Thing
	public boolean fillSpace(Thing thingValue, Point pValue)
	{
		return fillSpace(thingValue, pValue.getX(), pValue.getY());
	}

	// Set the given space to empty
	public void clearSpace(int xValue, int yValue)
	{
		if(ground[xValue][yValue] == null || ground[xValue][yValue].getType() != GridType.EMPTY)
		{
			ground[xValue][yValue] = new Nothing(debugger);
			debug("Cleared space " + xValue + "," + yValue);
		}
		else
		{
			debug("Not Clearing: " + xValue + "," + yValue);
		}
	}

	// Set the given space to empty
	public void clearSpace(Point pValue)
	{
		clearSpace(pValue.getX(), pValue.getY());
	}

	// Set the given space to blocked,
	// i.e. it lies outside the grid
	public void blockSpace(int xValue, int yValue)
	{
		ground[xValue][yValue] = null;
		debug("Blocked space " + xValue + "," + yValue);
	}

	// Set the given space to blocked,
	// i.e. it lies outside the grid
	public void blockSpace(Point pValue)
	{
		blockSpace(pValue.getX(), pValue.getY());
	}
	
	// return the Nothing object that is found at the given x and y values
	public Nothing getSpace(int xValue, int yValue) 
	{	
		try
		{
			return ground[xValue][yValue];
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}

	// return the Nothing object that is found at the given point
	public Nothing getSpace(Point pValue)
	{
		return getSpace(pValue.getX(), pValue.getY());
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	// Move the Object that is placed in a grid square to a the new 
	// grid square, which will be already updated in it's object.
	// Then clear the old space
	public void moveSpace(Point oldSpaceValue, Point newSpaceValue)
	{
		Nothing castableNothing = getSpace(oldSpaceValue);

		// If it's not castable then output the debug info
		if(castableNothing.getType() == GridType.EMPTY)
		{
			debug("Nothing: " + castableNothing.getName() + " cannot be cast as a Thing. Found " + castableNothing.getType());
			debugGround();
			debug("Exiting...");
			System.exit(1);
		}
		else
		{
			Thing thingToMove = (Thing)castableNothing;
			// Clear the old space
			clearSpace(oldSpaceValue);
			// fill the new space with the Thing.
			fillSpace(thingToMove, newSpaceValue);
		}
	}

	// A debugging method to output any important Objects placed on the grid.
	// i.e. This method ignores Nothing Objects.
	public void debugGround()
	{
		System.out.println("****Printing Grid****");
		for(int i=0; i < x; i++)
		{
			for(int j=0; j < y; j++)
			{
				Nothing no = getSpace(i,j);
				if(no.getName() != "nothing")
				{
					Thing a = (Thing)no;
			    	System.out.println("Found " + a.getType() + " at " + i+ "," +j);
				}
			}
		}
		System.out.println("****Printed Grid****");
	}	
	/* END PUBLIC */

	/* PROTECTED  */
	/* END PROTECTED*/

	/* PRIVATE*/

	private final int x; // the width of the grid in squares
	private final int y; // the height of the grid in squares
	private Nothing[][] ground; // The 2D array of objects that make up the grid
	/* END PRIVATE */
}
