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
		super(nameValue, typeValue, 2, debugValue);
		pos = new Point();
	}

	// END Constructors

	/*
	 * Thing location methods and variables
	 * set and get x and y coordinates
	*/

	private Point pos; // x and y is the positon of the thing on the ground.

	// Choose a starting position for the new thing.
	public void locate(Ground movesValue)
	{
		// Default is random, override this to change location behaviour
		Point rand = getRandomValidSpace(movesValue);
		setPos(rand);
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

	// Return a valid random move (method assumes there is one.)
	protected Point getRandomValidSpace(Ground movesValue)
	{
		while(true)
		{
			Point rand = getRandomSpace(movesValue.getX()-1,movesValue.getY()-1);
			try
			{
				if(movesValue.getSpace(rand.getX(), rand.getY()).getType() == "empty")
				{
					return rand;
				}
			}
			catch(NullPointerException e)
			{
				// Do Nothing
			}
		
		}
	}

	// Get a random point
	private Point getRandomSpace(int xValue, int yValue)
	{
		Point rand = new Point(); 
		rand.setX((int)Math.rint(Math.random() * xValue));
		rand.setY((int)Math.rint(Math.random() * yValue));
		return rand;
	}
}
