/*
 * A public class that acts as a base class for all things on the ground.
 */

public class Thing extends Nothing
{
	/*
	 * Constructors
	*/

	// Basic constructor, setting name and the debugger
	public Thing(String nameValue, Logger debugValue, GridType typeValue)
	{
		super(nameValue, typeValue, 2, debugValue);
		pos = new Point();
	}

	// END Constructors

	/*
	 * Thing location methods and variables
	 * set and get x and y coordinates
	*/

	protected Point pos; // x and y is the positon of the thing on the ground.

	// Choose a starting position for the new thing.
	public void locate(Ground movesValue)
	{
		// Find a random valid space
		setPos(getRandomValidSpace(movesValue));
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
		pos = new Point(p);
		debug("setting new position to " + pos.getX() + "," + pos.getY());
	}
	// END location methods

	/*
	 * Helper methods for a Thing
	 * Perhaps place these in another helper class, if there's lots.
	*/

	// Return a valid random move (method assumes there is one.)
	protected Point getRandomValidMove(Ground movesValue)
	{

		while(true)
		{
			// Get any random step
			Point step = getRandomMove();

			// Check that the move is not 0,0
			if(step.getX() == 0 && step.getY() == 0)
			{
				continue;
			}
			// Add the current pos to the step to get our prospective position
			Point uncheckedMove = step.add(pos);

			// Check the validity of that square
			if(movesValue.getSpace(uncheckedMove) != null && movesValue.getSpace(uncheckedMove).getType() == GridType.EMPTY)
			{
				// Move is valid
				return step;
			}
		}
	}

	// Return a valid random board position
	protected Point getRandomValidSpace(Ground movesValue)
	{

		while(true)
		{
			// Get any random space
			Point uncheckedMove = getRandomSpace(movesValue.getX(), movesValue.getY());

			// Check the validity of that space
			if(movesValue.getSpace(uncheckedMove) != null && movesValue.getSpace(uncheckedMove).getType() == GridType.EMPTY)
			{
				// Move is valid
				return uncheckedMove;
			}
		}
	}

	// Returns a valid grid sqaure based on the grid size xValue and yValue
	private Point getRandomSpace(int xValue, int yValue)
	{
		Point rand = new Point(); 
		// Math.random returns a number between 0 and 1 inclusive
		// So minus 1.
		xValue--;
		yValue--;
		rand.setX((int)Math.rint(Math.random() * xValue));
		rand.setY((int)Math.rint(Math.random() * yValue));
		return rand;
	}

	// Returns a move of one ranging from -1,-1 to 1,1 
	private Point getRandomMove()
	{
		// Gets an  int between -1 and 1
		int x = (int)Math.rint(Math.random()*2)-1;
		int y = (int)Math.rint(Math.random()*2)-1;
		return new Point(x,y);
	}

}
