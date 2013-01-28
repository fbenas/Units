public class Nothing
{
	protected final GridType type;
	protected Logger debugger;
	private final int debugLevel;
	
	public Nothing(Logger debugValue)
	{
		debugger = debugValue;
		debugLevel = 2;
 		name = "nothing";
		type = GridType.EMPTY;

	}

	public Nothing(String nameValue, GridType typeValue, int debugLevelValue, Logger debugValue)
	{
		type = typeValue;
		debugger = debugValue;
		debugLevel = debugLevelValue;
		if(nameValue != null && nameValue != "")
		{
			name = nameValue;
		}
	}

	public GridType getType()
	{
		return type;
	}

	// name variable and methods
	private String name;

	public String getName()
	{
		return name;
	}
	public void setName(String nameValue)
	{
		debug("changing name of " + name +  " to " + nameValue);
		name = nameValue;
	}

	// Ony works for 3by3 grids with pValue at the center
	// Use a parameter for name, incase we are creating a new ground from ground.
	protected Ground getLocalMoves(Ground groundValue, Point pValue, String nameValue)
	{
		Ground moves = new Ground(3,3,nameValue,debugger);
	
		for(int i= 0; i< 3; i++)
		{
			for(int j=0; j< 3; j++)
			{
				if(i==1 && j==1)
				{
					continue;
				}
				Point p = new Point(pValue);
				// -1 to get the surrounding squares
				p.add(i-1,j-1);

				try
				{
					//get the object
					Nothing thing = groundValue.getSpace(p);

					// Cast to Thing or run clearSpace
					if(thing.getType() == GridType.EMPTY)
					{
						// add a Nothing object into local grid
						moves.clearSpace(i,j);
					}
					else
					{
						// Add the Thing to the local grid
						moves.fillSpace((Thing)thing, i,j);
					}		
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					// Block the space
					moves.blockSpace(i,j);
				}
			}
		}
		return moves;
	}

	// Method for adding debug information from a Thing
	protected void debug( String value )
	{
		debugger.debug(getType() + "-" + name, value, debugLevel);
	}


}