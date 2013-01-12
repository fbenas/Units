/*
 * A public class that extends from the Thing base class.
 * It implements the Move class, hence it can move on the ground.
 */

// Imports
import java.util.ArrayList;

public class Unit extends Thing implements Move
{
	// Application wide vars
	// THESE SHOULD MOVE TO A STATIC CLASS.
	private int carryAmount = 10;
	private int groundX = 50;
	private int groundY = 50;

	/*
	 * Constructors
	*/

	// Constructor with x and y values to create a Unit with a specfic location
	public Unit(int xValue, int yValue, String nameValue, Home homeValue, Logger debugValue)
	{
		super(nameValue, debugValue, "unit");

		//Unit specific, we must set the home.
		setHome(homeValue);
		carry = 0;
		setPos(xValue, yValue);
		logLocation();
	}

	// END Constructors

	/* 
	 * Methods and variables for Unit to move
	 */

	// Override the move method from the Move interface
	// This will have to take parameters for checking surroundings
	
	@Override
	public boolean move(Ground movesValue)
	{
		debug("moving...");
		Point p = getRandomValidSpace(movesValue);
		p.add(getX(),getY());
		setPos(p);
		logLocation();
		debug("move made.");
		return true;
	}

	// Method to make a single move towards the Unit's home.
	// This will probably need to be private.
	public void moveHome()
	{
		// get last move made from history
		int movePointer = history.size()-1;
		if(movePointer >= 0)
		{
			setPos(new Point(history.get(movePointer)));
			if(movePointer > 0)
			{
				history.remove(movePointer);
			}
			//history.trimToSize();
		}
		else
		{
			debug("Reached last move in History.");
			outputHistory();
			System.out.println("FAILED AGAIN");
			System.exit(1);
		}
	}

	// END of movement methods and variables

	/*
	 * Interaction methods and variables
	 */

	public boolean canInteract(int xValue, int yValue)
	{
		if(Math.abs(xValue-getX()) < 2 && Math.abs(yValue-getY()) < 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	// TThis overload is better for many Resources 
	public boolean canInteract(Ground groundValue)
	{
		Ground moves = getLocalMoves(groundValue, getPos(), "Unit-Resource");
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				try
				{
					Resource r = (Resource)moves.getSpace(i,j);
					if(r.getAmount() >= carryAmount)
					{
						return true;
					}
				}
				catch (NullPointerException e)
				{
					// do nothing
				}
			}
		}
		return false;
	}

	// This overlaod is better for a small number of resources
	public boolean canInteract(Point pValue)
	{
		Point p = new Point(pValue);
		p.sub(getPos());
		if(Math.abs(p.getX()) < 2 && Math.abs(p.getY()) < 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private int carry;
	// Method to interact with a resource, and take some.
	// returns the amount of resource it is now carrying
	// If parameter is 0 then uncarry what we have.
	// This will probably need to be private
	public int setCarry(int carryValue)
	{
		if(carry == 0 && carryValue != 0)
		{
			carry = carryValue;
			debug("Unit is now carrying " + carry);
			return carry;
		}
		else if(carry != 0 && carryValue == 0)
		{
			debug("Unit has unloaded " + carry);
			carry = 0;
			return carry;
		}
		else
		{
			debug("cannot carry amount, " + carryValue + ". Unit already has " + carry);
			return carryValue;
		}
	}

	public int getCarry()
	{
		return carry;
	}

	private ArrayList<Point> history = new ArrayList<Point>();	

	public ArrayList<Point> getHistory()
	{
		return history;
	}

	private void logLocation()
	{
		history.add(new Point(getPos()));
	}

	public void outputHistory()
	{
		for(int i = 0; i < history.size(); i++)
		{
			System.out.println(history.get(i).getX() + "," + history.get(i).getY());
		}
	}	
		

	// END Helper methods

	// int to store where the unit was created
	private Home home;

	public Home getHome()
	{
		return home;
	}
	public void setHome(Home homeValue)
	{
		debug("setting home to " + homeValue.getName() + " at location " + homeValue.getX() +  "," + homeValue.getY());
		home = homeValue;
	}
}
