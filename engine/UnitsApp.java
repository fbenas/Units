/*
 * Main applciation for Units
 */

package engine;

import utils.Config;
import utils.AnimObject;
import utils.GridType;
import frontend.FrontEnd;
import java.util.ArrayList;

public class UnitsApp
{
    /* CONSTUCTORS */

    // Constructior initates debugger object and then runs main program loop
    public UnitsApp(int runtimeLevelValue)
    {   
        config = new Config(runtimeLevelValue);
        gogo();

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
    private Config config;
    private FrontEnd frontEnd;
    private Space[][] gridSpace;
    private ArrayList<Unit> unitArray = new ArrayList<Unit>();

    // new main loop using space
    private void gogo()
    {
        //Set up the space[]
        gridSpace = new Space[config.GRID_WIDTH][config.GRID_HEIGHT];
        initaliseSpace();
        initaliseHomes();
        initaliseResources();
        spawnUnits();

        if(config.ANIM_FLAG)
        {
            frontEnd = new FrontEnd(config, convertForAnim());
            threadWait();
        }

        int counter = 0;

        while(counter < config.GAME_TIME)
        {
            counter++;
            // decay all crumbs
            decayCrumbs();
            // for each unit, make a move
            for(Unit u : unitArray)
            {
                if(u.getCarry()>0) // check if unit is carrying
                {
                    if(isNearHome(u)) // Check if unit can interact with a home
                    {
                        // interact with home
                         u.getHome().increaseAmount(u.getCarry());
                         u.setCarry(0);
                         u.resetHistory();
                    }
                    else
                    {
                        // move towards home
                        moveUnitHome(u);
                    }
                }
                else 
                {
                    Resource r = isNearResource(u);
                    if(r != null) // check if unit can interact with a resource
                    {
                        if(r.getAmount() > 0) // check if the resource has stock
                        {
                            u.setCarry(config.CARRY_AMOUNT);
                            r.reduceAmount(config.CARRY_AMOUNT);
                            if(u.getHistory().size()>0)
                            {
                                u.getHistory().remove(u.getHistory().size()-1);
                            }
                        }
                    }
                    else
                    {
                        // move ranomly...
                        moveUnit(u);
                    }
                }
            }

            // update animation
            if(config.ANIM_FLAG)
            {
                frontEnd.updateGrid(convertForAnim());
                threadWait();
            }
        }

        Resource resource = (Resource) gridSpace[9][9].getThing();
        Home home = (Home) gridSpace[0][0].getThing();
        Unit unit = unitArray.get(0);
        debug("Main Loop", "Game Time Expired.");
        debug("UnitsApp","Resource, " + resource.getName() + ", ended with an amount of: " + resource.getAmount());
        debug("UnitsApp","Home, " + home.getName() + ", ended with an amount of: " + home.getAmount());
        debug("UnitsApp","Unit, " + unit.getName() + ", ended with an amount of: " + unit.getCarry());
        debug("UnitsApp","Exiting.");
    }

    private boolean isNearHome(Unit unitValue)
    {
        Point u = unitValue.getPos();
        Point h = unitValue.getHome().getPos();

        if(Math.abs(u.getX()-h.getX()) < 2 && Math.abs(u.getY()-h.getY()) < 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private Resource isNearResource(Unit unitValue)
    {
        for(int i=-1; i<2; i++)
        {
            for(int j=-1; j<2; j++)
            {
                int xValue = unitValue.getX();
                int yValue = unitValue.getY();
                if(xValue+i> 0 && xValue+i < config.GRID_WIDTH && yValue+j > 0 && yValue+j < config.GRID_HEIGHT)
                {
                    Nothing castableNothing = gridSpace[xValue+i][yValue+j].getThing();
                    if(castableNothing.getType() == GridType.RESOURCE)
                    {
                        Resource r = (Resource)castableNothing;
                        if(r.getAmount() >= config.CARRY_AMOUNT)
                        {
                            return r;
                        }
                    }
                }
            }
        }
        return null;
    }

    private AnimObject[][] convertForAnim()
    {
        AnimObject[][] anim = new AnimObject[config.GRID_WIDTH][config.GRID_HEIGHT];
        for(int i=0; i<config.GRID_WIDTH; i++)
        {
            for(int j=0; j<config.GRID_WIDTH; j++)
            {
                GridType things = gridSpace[i][j].getThing().getType();
                int weights = convertCrumbs(i,j);
                int units = gridSpace[i][j].getUnitCount();
                anim[i][j] = new AnimObject(things,weights,units);
            }
        }
        return anim;
    }

    private void spawnUnits()
    {
        Home h = (Home)(gridSpace[0][0].getThing());
        unitArray.add(h.spawnUnit("U1",gridSpace));
        unitArray.add(h.spawnUnit("U2",gridSpace));
        unitArray.add(h.spawnUnit("U3",gridSpace));
        unitArray.add(h.spawnUnit("U4",gridSpace));
        unitArray.add(h.spawnUnit("U5",gridSpace));
        unitArray.add(h.spawnUnit("U6",gridSpace));
        unitArray.add(h.spawnUnit("U7",gridSpace));
        unitArray.add(h.spawnUnit("U8",gridSpace));
        placeUnits();
    }

    private void placeUnits()
    {
        for(Unit u : unitArray)
        {
            gridSpace[u.getX()][u.getY()].addUnit(u);
        }
    }

    private void initaliseHomes()
    {
        gridSpace[0][0].setThing((Thing) new Home(0,0,"H1", config));
    }

    private void initaliseResources()
    {
        gridSpace[9][9].setThing((Thing)(new Resource(9,9,"R1",config.RESOURCE_AMOUNT,config)));
    }

    private void initaliseSpace()
    {
        for(int i=0; i < config.GRID_WIDTH; i++)
        {
            for(int j=0; j< config.GRID_HEIGHT; j++)
            {
                gridSpace[i][j] = new Space(config);
            }
        }
    }
    
    // Iterate through the array of crumbs and remove 1 to all the weightings
    private void decayCrumbs()
    {
       for(Space[] gridRow : gridSpace)
       {
            for(Space s : gridRow)
            {
                s.decayWeights();
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
        unitValue.move(gridSpace);
        
        // Update grid
        gridSpace[p.getX()][p.getY()].removeUnit(unitValue);
        gridSpace[unitValue.getX()][unitValue.getY()].addUnit(unitValue);
    }

    private void moveUnitHome(Unit unitValue)
    {
        // Get units old position
        Point p = new Point(unitValue.getPos());

        // Move unit Home
        unitValue.moveHome();
        
        // Update grid
        gridSpace[p.getX()][p.getY()].removeUnit(unitValue);
        gridSpace[unitValue.getX()][unitValue.getY()].addUnit(unitValue);

        // Add a crumb to the ground
        if(config.LEARN_FLAG)
        {
            // Our new space should be updated as we are heading backwards
            // Update the weight of the position of old-new. 
            gridSpace[unitValue.getX()][unitValue.getY()].addWeight(p.getX()-unitValue.getX()+1,p.getY()-unitValue.getY()+1);
        }
    }

    private void threadWait()
    {
        try
        {
            Thread.currentThread().sleep(config.GAME_DELAY);
        }
        catch( Exception e)
        {
            System.out.println(e);
        }
    }

	private int convertCrumbs(int xValue, int yValue)
	{
        int total = 0;
        // Get a weight for the grid position based 

/*
        total += gridSpace[xValue-1][yValue-1].getWeight(2,2);
        total += gridSpace[xValue-1][yValue].getWeight(2,1);
        total += gridSpace[xValue-1][yValue+1].getWeight(2,0);
        total += gridSpace[xValue][yValue-1].getWeight(1,2);
        total += gridSpace[xValue][yValue].getWeight(1,1);
        total += gridSpace[xValue][yValue+1].getWeight(1,2);
        total += gridSpace[xValue+1][yValue-1].getWeight(0,2);
        total += gridSpace[xValue+1][yValue].getWeight(0,1);
        total += gridSpace[xValue+1][yValue+1].getWeight(0,0);
*/
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if(xValue+i-1 > 0 && xValue+i-1 < config.GRID_WIDTH && yValue+j-1 > 0 && yValue+j-1 < config.GRID_HEIGHT)
                {
                    total += gridSpace[xValue+i-1][yValue+j-1].getWeight(Math.abs(i-2),Math.abs(j-2));
                }
            }
        }

        return total;
	}
    /* END PRIVATE */
}
