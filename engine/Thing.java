package engine;

/*
 * Public Thing class extends parent class Nothing
 *
 * A Thing object represents an object that can be placed
 * on a cartesian grid.
 *
 * This class contains methods to place and move that object as
 * well as getting random possible movements on a grid.
 *
 * This class shouldn't be instantiated directly
 */

import utils.GridType;

public class Thing extends Nothing
{
	/* CONSTUCTORS */

	// Constructor that calls the parent class constructor and sets
	// an empty point ready to be initalized correctly by a child class
	public Thing(String nameValue, Logger debugValue, GridType typeValue)
	{
		super(nameValue, typeValue, 2, debugValue);
		pos = new Point();
	}
	/* END CONSTRUCTORS */

	/* PUBLIC */

	// Choose a starting position for the new thing
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
	/* END PUBLIC */

	/* PROTECTED  */

	protected Point pos;

	// Return a valid random move, where a move is defined by any surrounding
	// grid sqaure of the current Thing's position
	// TODO: add function to return if no available move is found
	protected Point getRandomValidMove(Ground groundValue)
	{
		// Loop until we find a valid square
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
			if(groundValue.getSpace(uncheckedMove) != null && groundValue.getSpace(uncheckedMove).getType() == GridType.EMPTY)
			{
				return step;
			}
		}
	}

	// Return a valid random board position
	// TODO: add function to return if no available space is found
	protected Point getRandomValidSpace(Ground groundValue)
	{
		while(true)
		{
			// Get any random space
			Point uncheckedMove = getRandomSpace(groundValue.getX(), groundValue.getY());

			// Check the validity of that space
			if(groundValue.getSpace(uncheckedMove) != null && groundValue.getSpace(uncheckedMove).getType() == GridType.EMPTY)
			{
				// Move is valid
				return uncheckedMove;
			}
		}
	}
	/* END PROTECTED*/

	/* PRIVATE*/

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
	/* END PRIVATE */
}