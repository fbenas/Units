public class Ground extends Nothing
{
	private final int x;
	private final int y;

	private Nothing[][] ground;

	public Ground(int xValue, int yValue, String nameValue, Logger debugValue)
	{
		super(nameValue, "ground", 3, debugValue);
		debug("Creating ground with dimensions: " + xValue + "," + yValue);
		x = xValue;
		y = yValue;
		ground = new Nothing[x][y];
		clearGround();
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
		if(ground[xValue][xValue].getType() == "empty")
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
		ground[xValue][yValue] = new Nothing(debugger);
	}

	public void clearSpace(Point pValue)
	{
		clearSpace(pValue.getX(), pValue.getY());
	}

	public void blockSpace(int xValue, int yValue)
	{
		ground[xValue][yValue] = null;
	}

	public void blockSpace(Point pValue)
	{
		blockSpace(pValue.getX(), pValue.getY());
	}

	public Nothing getSpace(int xValue, int yValue) 
	{	
		return ground[xValue][yValue];
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

}