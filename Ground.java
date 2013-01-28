public class Ground extends Nothing
{
	private final int x;
	private final int y;

	private Nothing[][] ground;

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

	// Fill the ground array with empty objects.
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

	// Need to pass coordinates as we are using two different coordinate systems
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

	public boolean fillSpace(Thing thingValue, Point pValue)
	{
		return fillSpace(thingValue, pValue.getX(), pValue.getY());
	}

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

	public void clearSpace(Point pValue)
	{
		clearSpace(pValue.getX(), pValue.getY());
	}

	public void blockSpace(int xValue, int yValue)
	{
		ground[xValue][yValue] = null;
		debug("Blocked space " + xValue + "," + yValue);
	}

	public void blockSpace(Point pValue)
	{
		blockSpace(pValue.getX(), pValue.getY());
	}

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

	// Method should not be allowed to pass a point
	// that holds an empty space. It has to be a Thing. 
	public void moveSpace(Point oldSpaceValue, Point newSpaceValue)
	{
		// Check the type of the Nothing Object, If it is
		// not a Thing then throw an exception
		Nothing castableNothing = getSpace(oldSpaceValue);

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
}