/*
 * A public class that extends from the Thing base class.
 * It implements the Move class, hence it can move on the ground.
 */

// Imports
import java.util.ArrayList;
import java.awt.Point;

public class Unit extends Thing implements Move
{
	// Application wide vars
	// THESE SHOULD MOVE TO A STATIC CLASS.
	private int groundX = 50;
	private int groundY = 50;
	
	// information for the dubugger
	private Debug debug;

	/*
	 * Constructors
	*/

	// Basic constructor
	public Unit(String nameValue, Home homeValue, GridSquareStatus[] movesValue, Debug debugValue)
	{
		super(nameValue, debugValue);

		// Unit specific, we must set the home.
		setHome(homeValue);
		carry = 0;
		//We must locate every child class of Thing at the end of the constructor.
		moves = movesValue;
		locate();
		logLocation();
	}

	// Constructor with x and y values to create a Unit with a specfic location
	public Unit(int xValue, int yValue, String nameValue, Home homeValue, Debug debugValue)
	{
		super(nameValue, debugValue);

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

	private GridSquareStatus[] moves;

	// Override the move method from the Move interface
	// This will have to take parameters for checking surroundings
	
	@Override
	public boolean move(GridSquareStatus[] movesValue)
	{
		super.debug("moving...");
		// future: have states, and a case statement to decide what type of movemet to do
		// random movment, follow breadcrums, head home... etc
		moves = movesValue;
		Point p = super.translateMove(getValidRandomMove());
		setPos(getX()+(int)p.getX(),getY()+(int)p.getY());
		logLocation();
		debug("move made.");
		return true;
	}

	private int getValidRandomMove()
	{
		int i = 0;
		while(i < 100)
		{
			int move = super.getRandomMove();
			if(validMove(move))
			{
				return move;
			}
			i++;
		}
		debug("NO valid random move found");
		return -1;
	}

	private boolean validMove(int moveValue)
	{
		// check moves has been filled in.
		if(moves == null)
		{
			debug("Moves has not been instantiated. Deal with this.");
			System.exit(1);
		}
		for(int i = 0; i < moves.length; i++)
		{
			if(moves[i] == GridSquareStatus.EMPTY)
			{
				return true;
			}
		}
		return false;
	}
	// Method to make a single move towards the Unit's home.
	// This will probably need to be private.
	public void moveHome()
	{
		// get last move made from history
		int movePointer = history.size()-1;
		if(movePointer >= 0)
		{
			setPos(history.get(movePointer));
			history.remove(movePointer);
			history.trimToSize();
		}
		else
		{
			debug("Reached last move in History.");
			System.exit(1);
		}
	}

	// END of movement methods and variables

	/*
	 * Interaction methods and variables
	 */

	public boolean canInteract(int xValue, int yValue)
	{
		if(Math.abs(xValue-super.getX()) < 2 && Math.abs(yValue-super.getY()) < 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean canInteract(Point pValue)
	{
		return canInteract((int)pValue.getX(), (int)pValue.getY());
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


	public void locate(GridSquareStatus[] movesValue)
	{
		moves = movesValue;
		locate();
	}
	// Override the locate method so that we can make sure the unit
	// is locatated near home
	// Parameter must containg moves from home.
	@Override
	public void locate()
	{
		int newPos = getValidRandomMove();
		Point p = super.translateMove(newPos);
		setPos((int)p.getX()+home.getX(), (int)p.getY()+home.getY());
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
		super.debug("setting home to " + homeValue.getName() + " at location " + homeValue.getX() +  "," + homeValue.getY());
		home = homeValue;
	}
}
