/*
 * Main applciation for Units
 */

public class UnitsApp
{
	// Application wide vars
	// THESE SHOULD MOVE TO A STATIC CLASS.	
	private int groundX = 400;
	private int groundY = 400;
	private int minMove = 10;
	private int debugLevel = 1;
	private int carryAmount = 10;
	private int resourceAmount = 10000;
	private int gameTime = 1000;
	private Ground ground;

	// This must be set up, either true or false.
	private Logger debugger;
	public UnitsApp(int runtimeLevelValue)
	{
		debugger = new Logger(runtimeLevelValue);
		debug("Constructor", "Debugger set with debugging value of " + runtimeLevelValue);	
		go();

	}
	// Class to test the Unit class.
	public static void main(String[] args)
	{
		int runtimeLevel;
		try 
		{ 
        	runtimeLevel = Integer.parseInt(args[0]); 
        }
    	catch(Exception e) {
    			runtimeLevel = 0;
        }
		new UnitsApp(runtimeLevel);
	}

	private void go()
	{
		debug("Deployment","Creating main Ground");
		ground = new Ground(groundX,groundY,"Ground",debugger);
		debug("Deployment","Ground " + ground.getName() + " created with size of " + ground.getX() + "," + ground.getY());

		debug("Deployment", "Creating a Home");	
		Home home = new Home(200,200,"H", debugger);
		ground.fillSpace(home, home.getPos());
		debug("Deployment", "Home, " + home.getName() + " created at " + home.getX() + "," + home.getY());	

		debug("Deployment", "Creating a Unit from home " + home.getName());		
		Unit unit = home.spawnUnit("unit", ground);
		ground.fillSpace(unit, unit.getPos());
		//Unit unit = new Unit(randomPoint.getX(), randomPoint.getY(), "U",home, debugger);
		debug("Deployment", "Unit, " + unit.getName() + " created at " + unit.getX() + "," + unit.getY());

		debug("Deployment", "Creating a Resource");	
		Resource resource = new Resource(203,203,"R",resourceAmount,debugger);
		debug("Deployment", "Resource, " + resource.getName() + " created at " + resource.getX() + "," + resource.getY());

		int i = 0;
		while( i < gameTime )
		{
			debug("Main Loop", "Time = " + i);
			i++;
			// Check if unit is carrying a load
			if( unit.getCarry() == 0 )
			{
				debug("Main Loop", "Unit, " + unit.getName()  + ", is carrying nothing");
				if( unit.canInteract(new Point(resource.getPos())) )
				{
					debug("Main Loop", "Unit, " + unit.getName()  + ", is in proximty of a resource");
					if(resource.getAmount() > 0)
					{
						debug("Main Loop", "Unit, " + unit.getName()  + ", has picked up a load");
						unit.setCarry(carryAmount);
						resource.reduceAmount(carryAmount);
						if(unit.getHistory().size()>0)
						{
							unit.getHistory().remove(unit.getHistory().size()-1);
						}
					}
				}
				else
				{
					// Move Randomly
					debug("Main Loop", "Unit, " + unit.getName()  + ", will make a random move");
					moveUnit(unit);
					debug("Main Loop","Unit, " + unit.getName() + " has made a move, and grid is up-to-date");
				}
			}
			else
			{
				debug("Main Loop", "Unit, " + unit.getName()  + ", is carrying a load");
				if ( unit.canInteract(new Point(unit.getHome().getPos())) )
				{
					debug("Main Loop", "Unit, " + unit.getName()  + ", is in proximty of its home");
					unit.getHome().increaseAmount(unit.getCarry());
					unit.setCarry(0);
					debug("Main Loop", "Unit" + unit.getName() + ", has dropped it's load at home.");
				}
				else
				{
					debug("Main Loop", "Unit, " + unit.getName()  + ", will move toward home. " + unit.getHistory().size() + " moves left" );
					moveUnitHome(unit);
				}
			}
		}
		debug("Main Loop", "Game Time Expired.");
		debug("UnitsApp","Resource, " + resource.getName() + ", ended with an amount of: " + resource.getAmount());
		debug("UnitsApp","Home, " + home.getName() + ", ended with an amount of: " + home.getAmount());
		debug("UnitsApp","Unit, " + unit.getName() + ", ended with an amount of: " + unit.getCarry());
		debug("UnitsApp","Exiting.");
	}

	// We should use the thing we are moving to find the space.
	// NEEDS A CHANGE
	private Point getRandomValidGridPos(Thing thing)
	{
		// Find an empty space on the grid.
		return new Point(201,201);
	}

	private void debug(String name, String value)
	{
		debugger.debug(name, value,1);
	}

	private void moveUnit(Unit unitValue)
	{
		//round moves = getMoves(unitValue.getPos(), unitValue.getName() + "'s local moves");
		
		// Get units old position
		Point p = new Point(unitValue.getPos());

		// Move unit
		unitValue.move(ground);
		
		// Update grid
		ground.moveSpace(p,unitValue.getPos());
	}

	private void moveUnitHome(Unit unitValue)
	{
		// Get units old position
		Point p = new Point(unitValue.getPos());

		// Move unit Home
		unitValue.moveHome();
		
		// Update grid
		ground.moveSpace(p,unitValue.getPos());
	}
}
