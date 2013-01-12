/*
 * A public class that acts as a base class for all things on the ground.
 */

public class Thing extends Nothing
{
	// Application wide vars
	// THESE SHOULD MOVE TO A STATIC CLASS.
	private int groundX = 50;
	private int groundY = 50;
	
	/*
	 * Constructors
	*/

	// Basic constructor, setting name and the debugger
	public Thing(String nameValue, Logger debugValue, String typeValue)
	{
		super(nameValue, typeValue, 3, debugValue);
		pos = new Point();
	}

	// END Constructors

	/*
	 * Thing location methods and variables
	 * set and get x and y coordinates
	*/

	private Point pos; // x and y is the positon of the thing on the ground.

	// Choose a starting position for the new thing.
	public void locate(GridSquareStatus[] movesValue)
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
		pos = new Point(xValue, yValue);
		debug("setting new position to " + (int)xValue + "," + (int)yValue);
	}

	public void setPos( Point p )
	{
		pos = p;
		debug("setting new position to " + p.getX() + "," + p.getY());
	}
	// END location methods

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

	// This method gets a valid x,y coordinate
	protected int getRandomGroundPos()
	{
		Double rand = Math.random() * groundX;
		return (int)Math.rint(rand);
	}
}
