/*
 * A public class that acts as a base class for all things on the ground.
 */
public class Thing
{
	// Application wide vars
	// THESE SHOULD MOVE TO A STATIC CLASS.
	private int groundX = 50;
	private int groundY = 50;
	
	// information for the dubugger
	private Debug debug;
	private int debugLevel = 2;

	/*
	 * Constructors
	*/

	// Basic constructor, setting name and the debugger
	public Thing(String nameValue, Debug debugValue)
	{
		debug = debugValue;
		if(nameValue != null && nameValue != "")
			name = nameValue;
	}

	// END Constructors

	/*
	 * Thing location methods and variables
	 * set and get x and y coordinates
	*/

	private int x;
	private int y;	 // x and y is the positon of the thing on the ground.

	// Choose a starting position for the new thing.
	public void locate()
	{
		// Default is random, override this to change location behaviour
		setX(getRandomGroundPos()); 
		setY(getRandomGroundPos());
	}

	public void setX( int value )
	{
		x = value;
		debug("setting x to " + value);
	}
	public int getX()
	{
		return x;
	}
	public void setY( int value )
	{
		y = value;
		debug("setting y to " + value);
	}
	public int getY()
	{
		return y;
	}

	// END location methods

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

	/*
	 * Helper methods for a Thing
	 * Perhaps place these in another helper class, if there's lots.
	*/

	protected int getRandomMove(int valueA)
	{
		Double rand = 1 - (Math.random() * 2);
		return (int)Math.rint(rand);
	}
	// This method gets a valid x,y coordinate
	protected int getRandomGroundPos()
	{
		Double rand = Math.random() * groundX;
		return (int)Math.rint(rand);
	}

	// Method for adding debug information from a Thing
	protected void debug(String value)
	{
		debug.debug(name, value, debugLevel);
	}
}