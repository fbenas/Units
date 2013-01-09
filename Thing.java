/*
 * A public class that acts as a base class for all things on the ground.
 */

import java.awt.Point;

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
		pos = new Point();
	}

	// END Constructors

	/*
	 * Thing location methods and variables
	 * set and get x and y coordinates
	*/

	private Point pos; // x and y is the positon of the thing on the ground.

	// Choose a starting position for the new thing.
	public void locate()
	{
		// Default is random, override this to change location behaviour
		setPos(getRandomGroundPos(),getRandomGroundPos());
	}

	public int getX()
	{
		return (int) pos.getX();
	}

	public int getY()
	{
		return (int) pos.getY();
	}
	public Point getPos()
	{
		return pos;
	}
	public void setPos( int xValue, int yValue )
	{
		pos.setLocation(xValue, yValue);
		debug("setting new position to " + xValue + "," + yValue);
	}
	public void setPos( Point p )
	{
		pos = p;
		debug("setting new position to " + p.getX() + "," + p.getY());
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

	// Translates a Thing's local move to a valid board move.
	protected Point translateMove(int value)
	{
		switch(value)
		{
			case 0:
				return new Point(-1,-1);
			case 1:
				return new Point(0,-1);
			case 2:
				return new Point(1,-1);
			case 3:
				return new Point(-1,0);
			case 4:
				return new Point(1,0);
			case 5:
				return new Point(-1,1);
			case 6:
				return new Point(0,1);
			case 7:
				return new Point(1,1);			
			default:
				return null;
		}
	}

	protected int getRandomMove()
	{
		Double rand = Math.random() * 7;
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
		debug.debug(this.getClass().getName() + "-" + name, value, debugLevel);
	}
}
