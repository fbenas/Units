
public class Unit
{
		// Application wide vars
	// THESE SHOULD MOVE TO A STATIC CLASS.
	private int groundX = 50;
	private int groundY = 50;
	
	private Debug debug;
	private int debugLevel = 2;
	/*
	 * Constructors
	*/

	// Blank Constructor for unit
	public Unit(String nameValue, Debug debugValue)
	{
		debug = debugValue;
		if(nameValue != null && nameValue != "")
			name = nameValue;
		// we must set x and y
		setX(getRandomGroundPos()); 
		setY(getRandomGroundPos());
	}

	// Constructor with x and y values to create a Unit with a specfic location
	public Unit(int xValue, int yValue, String nameValue, Debug debugValue)
	{
		debug = debugValue;
		if(nameValue != null && nameValue != "")
			name = nameValue;
		setX(xValue);
		setY(yValue);
	}

	// END Constructors

	/*
	 * Unit location methods and variables
	 * set and get x and y coordinates
	*/

	private int x;
	private int y;	 // x and y is the positon of the unit on the ground.

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
	
	/*
	 * Unit movement methods and variables
	 * 
	*/

	// Move method gets to decide how to move, then make the required movements
	public boolean move()
	{
		// future: have states, and a case statement to decide what type of movemet to do
		// random movment, follow breadcrums, head home... etc
		setX(getRandomMove(x)+x);
		setY(getRandomMove(y)+y);
		return true;
	}

	/*
	 * Helper methods for Unit
	*/

	private int getRandomMove(int valueA)
	{
		Double rand = 1 - (Math.random() * 2);
		return (int)Math.rint(rand);
	}
	// This method gets a valid x,y coordinate
	private int getRandomGroundPos()
	{
		Double rand = Math.random() * groundX;
		return (int)Math.rint(rand);
	}

	// END Helper methods


	// Name for debugging purposes
	private String name = "default"; // name for usefull debug info

	public void debug(String value)
	{
		debug.debug(name, value, debugLevel);
	}
	public String getName()
	{
		return name;
	}
	public void setName(String value)
	{
		name = value;
	}
}