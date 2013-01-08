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
	private int resourceAmount = 100;
	private int gameTime = 10000;

	// This must be set up, either true or false.
	private Debug debugger;
	public UnitsApp(int runtimeLevelValue)
	{
		debugger = new Debug(runtimeLevelValue);
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
		debug("Deployment", "Creating a Home");	
		Home home = new Home("H", debugger);
		debug("Deployment", "Home, " + home.getName() + " created at " + home.getX() + "," + home.getY());	

		debug("Deployment", "Creating a Unit");	
		Unit unit = new Unit("U",home, debugger);
		debug("Deployment", "Unit, " + unit.getName() + " created at " + unit.getX() + "," + unit.getY());

		debug("Deployment", "Creating a Resource");	
		Resource resource = new Resource("R",resourceAmount,debugger);
		debug("Deployment", "Home, " + resource.getName() + " created at " + resource.getX() + "," + resource.getY());

		int i = 0;
		while( i < gameTime )
		{
			debug("Main Loop", "Time = " + i);
			i++;
			// Check if unit is carrying a load
			if( unit.getCarry() == 0 )
			{
				debug("Main Loop", "Unit, " + unit.getName()  + ", is carrying nothing");
				if( unit.canInteract(resource.getPos()) )
				{
					System.out.println("FUCK YEA");
					debug("Main Loop", "Unit, " + unit.getName()  + ", is in proximty of a resource");
					if(resource.getAmount() > 0)
					{
						debug("Main Loop", "Unit, " + unit.getName()  + ", has picked up a load");
						unit.setCarry(carryAmount);
						resource.reduceAmount(carryAmount);
					}
				}
				else
				{
					// Move Randomly
					debug("Main Loop", "Unit, " + unit.getName()  + ", will make a random move");
					unit.move();
				}
			}
			else
			{
				debug("Main Loop", "Unit, " + unit.getName()  + ", is carrying a load");
				if ( unit.canInteract(unit.getHome().getPos()) )
				{
					debug("Main Loop", "Unit, " + unit.getName()  + ", is in proximty of its home");
					unit.getHome().increaseAmount(unit.getCarry());
					unit.setCarry(0);
				}
				else
				{
					debug("Main Loop", "Unit, " + unit.getName()  + ", will move toward home");
					unit.moveHome();
				}
			}
		}
	}

	private void debug(String name, String value)
	{
		debugger.debug(name, value,1);
	}
}