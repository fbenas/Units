package engine;

/*
 * Public class Unit extends Thing parent class and implements
 * the Move interface
 *
 * The Unit class is a movable object that exists on a grid.
 * The Unit can move randomly, or move home, following it's
 * orginal path up to that point.
 * 
 * The unit interacts with home and resource objects, taking an amount
 * of resource from the latter and drops it off at the former.
 *
 * TODO: make units follow crumbs to a resource.
 */

import java.util.ArrayList;
import utils.GridType;

public class Unit extends Thing implements Move
{

	/* CONSTUCTORS */

	// Constructor sets the postion in the grid and stores the Home object
	// it was spawned from
	// The other parameters are used to call the parent class constructor
	public Unit(int xValue, int yValue, String nameValue, Home homeValue, Logger debugValue)
	{
		super(nameValue, debugValue, GridType.UNIT); // call parent constructor
		setHome(homeValue); // set the home that it spawned from
		carry = 0;
		setPos(xValue, yValue);
		logLocation(); // log it's location
	}
	/* END CONSTRUCTORS */

	/* PUBLIC */

	// Overrides the move method from the Move interface
	// Get a valid random move and log the move
	@Override
	public boolean move(Ground groundValue)
	{
		debug("moving...");

		Point p = getRandomValidMove(groundValue);
		setPos(p);
		logLocation();
		debug("move made.");
		return true;
	}

	// Overloaded method to move based on crumbs on the grid
	// TODO: finish this
	public boolean move(Ground groundValue, Crumb[][] crumbs)
	{
		debug("moving...");

		/* Crumb Specfic Code */
		//Check curren square for crumb
		int[] crumbWeights = crumbs[getX()][getY()].getCrumbs();
		
		Point p = getRandomValidMove(groundValue);
		setPos(p);
		logLocation();
		debug("move made.");
		return true;
	}

	// Method to make a single move towards the Unit's home.
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
		}
		else
		{
			debug("Reached last move in History.");
			outputHistory();
			System.out.println("FAILED AGAIN");
			System.exit(1);
		}
	}

	// Check if there is a resource in a surrounding sqaure from 
	// the Unit's current location
	// TODO: work out which interact method is better
	public boolean canInteract(Ground groundValue)
	{
		for(int i=-1; i<2; i++)
		{
			for(int j=-1; j<2; j++)
			{
				try
				{
					Resource r = (Resource)groundValue.getSpace(i,j);
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

	// Check if there is a resource in a surrounding sqaure from 
	// the Unit's current location
	// TODO: work out which interact method is better
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

	// Method to interact with a resource, and an amount of resource
	// returns the amount of resource it is now carrying
	// If parameter is 0 then uncarry what we have
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

	public ArrayList<Point> getHistory()
	{
		return history;
	}

	// Debug method for ptinting the Unit's history to the CLI bypassing the debugger
	public void outputHistory()
	{
		for(int i = 0; i < history.size(); i++)
		{
			System.out.println(history.get(i).getX() + "," + history.get(i).getY());
		}
	}	

	public Home getHome()
	{
		return home;
	}

	public void setHome(Home homeValue)
	{
		debug("setting home to " + homeValue.getName() + " at location " + homeValue.getX() +  "," + homeValue.getY());
		home = homeValue;
	}
	/* END PUBLIC */

	/* PROTECTED  */
	/* END PROTECTED*/

	/* PRIVATE*/

	private int carryAmount = 10; // The amount of resource a Unit can carry
	private Home home; // The Home object that the Unit spawned from
	private int carry; // the amount of resource the unit is currently carrying
	private ArrayList<Point> history = new ArrayList<Point>();	

	// Method to add the current location to the hisory array
	private void logLocation()
	{
		history.add(new Point(getPos()));
	}
	/* END PRIVATE */
}