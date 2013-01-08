/*
 * A public class that extends from the Thing base class.
 * It implements the Move class, hence it can move on the ground.
 */
public class Unit extends Thing implements Move
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

	// Basic constructor
	public Unit(String nameValue, Home homeValue, Debug debugValue)
	{
		super(nameValue, debugValue);

		// Unit specific, we must set the home.
		setHome(homeValue);
		carry = 0;
		//We must locate every child class of Thing at the end of the constructor.
		locate();
	}

	// Constructor with x and y values to create a Unit with a specfic location
	public Unit(int xValue, int yValue, String nameValue, Home homeValue, Debug debugValue)
	{
		super(nameValue, debugValue);

		//Unit specific, we must set the home.
		setHome(homeValue);
		carry = 0;
		super.setX(xValue);
		super.setY(yValue);
	}

	// END Constructors

	/* 
	 * Methods and variables for Unit to move
	 */

	// Override the move method from the Move interface
	// This will have to take parameters for checking surroundings
	
	@Override
	public boolean move()
	{
		super.debug("moving...");
		// future: have states, and a case statement to decide what type of movemet to do
		// random movment, follow breadcrums, head home... etc
		setX(super.getRandomMove(super.getX())+super.getX());
		setY(super.getRandomMove(super.getY())+super.getY());
		debug("move made.");
		return true;
	}

	// Method to make a single move towards the Unit's home.
	// This will probably need to be private.
	public void returnHome()
	{
		// Find a clever way to move towards home
	}

	// Method to make a single random move.
	// This will probably need to be private.
	public void moveRandom()
	{
		// Make a single random move
	}

	// END of movement methods and variables

	/*
	 * Interaction methods and variables
	 */

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

	// Override the locate method so that we can make sure the unit
	// is locatated near home
	@Override
	public void locate()
	{
		Double rand = Math.random();
		int pos = (int)Math.rint(rand*7); // 7 gives us 8 posibilites (0-7)
		
		switch(pos)
		{
			case 0:
				super.setX(home.getX()-1);
				super.setY(home.getY()-1);
				break;
			case 1:
				super.setX(home.getX());
				super.setY(home.getY()-1);
				break;
			case 2:
				super.setX(home.getX()+1);
				super.setY(home.getY()-1);
				break;
			case 3:
				super.setX(home.getX()-1);
				super.setY(home.getY());
				break;
			case 4:
				super.setX(home.getX()+1);
				super.setY(home.getY());
				break;
			case 5:
				super.setX(home.getX()-1);
				super.setY(home.getY()+1);
				break;
			case 6:
				super.setX(home.getX());
				super.setY(home.getY()+1);
				break;
			case 7:
				super.setX(home.getX()+1);
				super.setY(home.getY()+1);
				break;				
			default:
				break;
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
		super.debug("setting home to: " + homeValue.getName() + " at location " + homeValue.getX() +  "," + homeValue.getY());
		home = homeValue;
	}

}