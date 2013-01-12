public class Ground extends Nothing
{
	private final int x;
	private final int y;

	private Nothing[][] ground;
	private Logger debug;

	public Ground(int xValue, int yValue, String nameValue, Logger debugValue)
	{
		super(nameValue, "ground", 2, debugValue);
		debug = debugValue;
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
		for(Nothing[] col : ground)
		{
			for (Nothing stuff : col)
			{
				stuff = new Nothing(debug);
			}
		}
	}

	public boolean fillSpace(Thing thingValue)
	{
		if(ground[thingValue.getX()][thingValue.getY()].getType() == "empty")
		{
			ground[thingValue.getX()][thingValue.getY()] = (Nothing) thingValue;
			debug("Added new " + thingValue.getType() + " at position " + thingValue.getX() + "," + thingValue.getY() + " with name " + thingValue.getName());
			return true;
		}
		else
		{
			debug("Could not add " + thingValue.getType() + " at position " + thingValue.getX() + "," + thingValue.getY());
			return false;
		}
	}

	public void clearSpace(int xValue, int yValue)
	{
		ground[xValue][yValue] = new Nothing(debug);
	}

	public void blockSpace(int xValue, int yValue)
	{
		ground[xValue][yValue] = null;
	}

}