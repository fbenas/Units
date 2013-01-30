/*
 * Main applciation for Units
 */

package engine;

import utils.Config;

public class UnitsApp
{
    /* CONSTUCTORS */

    // Constructior initates debugger object and then runs main program loop
    public UnitsApp(int runtimeLevelValue)
    {   
        config = new Config(runtimeLevelValue);
        go();

    }
    /* END CONSTRUCTORS */

    /* PUBLIC */

    // Entry Point of the Application
    // Takes in thedebugging level as a comman line parameter
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
    /* END PUBLIC */

    /* PROTECTED  */
    /* END PROTECTED*/

    /* PRIVATE*/
    private int debugLevel = 1; // debug level for this class
    private Ground ground;
    private Crumb[][] crumbs;
    private Config config;
    // Main game loop method
    private void go()
    {
        debug("Deployment","Creating main Ground");
        ground = new Ground(config.GRID_WIDTH,config.GRID_HEIGHT,"Ground",config);
        debug("Deployment","Ground " + ground.getName() + " created with size of " + ground.getX() + "," + ground.getY());

        debug("Deployment","Creating Crumb Array");
        crumbs = new Crumb[config.GRID_WIDTH][config.GRID_HEIGHT];
        debug("Deployment", "Crumb Array Created with size " + config.GRID_WIDTH + "," + config.GRID_HEIGHT);
        
        debug("Deployment", "Creating a Home");
        Home home = new Home(200,200,"H", config);
        ground.fillSpace(home, home.getPos());
        debug("Deployment", "Home, " + home.getName() + " created at " + home.getX() + "," + home.getY());    

        debug("Deployment", "Creating a Unit from home " + home.getName());
        Unit unit = home.spawnUnit("unit", ground);
        ground.fillSpace(unit, unit.getPos());
        debug("Deployment", "Unit, " + unit.getName() + " created at " + unit.getX() + "," + unit.getY());

        debug("Deployment", "Creating a Resource");
        Resource resource = new Resource(203,203,"R",config.RESOURCE_AMOUNT,config);
        debug("Deployment", "Resource, " + resource.getName() + " created at " + resource.getX() + "," + resource.getY());

        int i = 0;
        while( i < config.GAME_TIME )
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
                        unit.setCarry(config.CARRY_AMOUNT);
                        resource.reduceAmount(config.CARRY_AMOUNT);
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

            debug("Main Loop", "Decaying all crumbs");
            decayCrumbs();
        }
        debug("Main Loop", "Game Time Expired.");
        debug("UnitsApp","Resource, " + resource.getName() + ", ended with an amount of: " + resource.getAmount());
        debug("UnitsApp","Home, " + home.getName() + ", ended with an amount of: " + home.getAmount());
        debug("UnitsApp","Unit, " + unit.getName() + ", ended with an amount of: " + unit.getCarry());
        debug("UnitsApp","Exiting.");
    }

    // Only use this for debugging 
    // TODO: use functions in Thing class.
    private Point getRandomValidGridPos(Thing thing)
    {
        return new Point(201,201);
    }
    
    // Iterate through the array of crumbs and remove 1 to all the weightings
    private void decayCrumbs()
    {
        for(Crumb[] cc : crumbs)
        {
            for(Crumb c : cc)
            {
                if(c != null )
                {
                    for(int i = 0; i < 3; i++)
                    {
                        int x = c.getWeight(i);
                        if (x > 0)
                        {
                            c.setWeight(i,x-1);
                        }
                    }
                }
            }
        }
    }

    private void debug(String nameValue, String value)
    {
        config.DEBUG.debug(nameValue, value,1);
    }

    private void moveUnit(Unit unitValue)
    {        
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

        // Add a crumb to the ground
        Crumb crumb = new Crumb(5, unitValue.getPos());
    }
    /* END PRIVATE */
}
