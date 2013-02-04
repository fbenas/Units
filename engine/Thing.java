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
import utils.Config;

public class Thing extends Nothing
{
    /* CONSTUCTORS */

    // Constructor that calls the parent class constructor and sets
    // an empty point ready to be initalized correctly by a child class
    public Thing(String nameValue, Config configValue, GridType typeValue)
    {
        super(nameValue, typeValue, 2, configValue);
        pos = new Point();
    }
    /* END CONSTRUCTORS */

    /* PUBLIC */

    // Choose a starting position for the new thing
    public void locate(Space[][] gridSpaceValue)
    {
        // Find a random valid space
        setPos(getRandomValidSpace(gridSpaceValue));
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
    protected Point getRandomValidMove(Space[][] gridSpaceValue)
    {
        // Loop until we find a valid square
        while(true)
        {
            // Get any random step
            Point step;
            if(config.LEARN_FLAG)
            {
                step = getWeightedMove(gridSpaceValue[this.getX()][this.getY()].getWeights());
            }
            else
            {
                step = getRandomMove();
            }

            // Check that the move is not 0,0 REMOVE THIS WHEN WE USE CRUMBS
            if(step.getX() != 0 || step.getY() != 0)
            {

                // Add the current pos to the step to get our prospective position
                Point uncheckedMove = new Point(step.getX() + pos.getX(),step.getY() + pos.getY());

                // Check the validity of that space
                if( uncheckedMove.getX() >= 0 
                    && uncheckedMove.getX() < config.GRID_WIDTH
                    && uncheckedMove.getY() >= 0 
                    && uncheckedMove.getY() < config.GRID_HEIGHT
                    && gridSpaceValue[uncheckedMove.getX()][uncheckedMove.getY()].getThing().getType() != GridType.HOME 
                    && gridSpaceValue[uncheckedMove.getX()][uncheckedMove.getY()].getThing().getType() != GridType.RESOURCE)
                {
                    return uncheckedMove;
                }
            }
        }
    }

    // Return a valid random board position
    protected Point getRandomValidSpace(Space[][] gridSpaceValue)
    {
        while(true)
        {
            // Get any random space
            Point uncheckedMove = getRandomSpace(config.GRID_WIDTH, config.GRID_HEIGHT);

            // Check the validity of that space
            if( gridSpaceValue[uncheckedMove.getX()][uncheckedMove.getY()].getThing().getType() != GridType.HOME 
                && gridSpaceValue[uncheckedMove.getX()][uncheckedMove.getY()].getThing().getType() != GridType.RESOURCE)
            {
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

    // Returns a move of one ranging from -1,-1 to 1,1 
    private Point getWeightedMove(int[][] weightsValue)
    {
        // sum weights of possible moves
        int total =0;
        for(int[] row : weightsValue)
        {
            for (int w : row)
            {
                total += w;
            }
        }

        //fix to stop it just choosing -1-1 all the time.
        if (total < 50)
        {
            return getRandomMove();
        }
        int random = (int)Math.rint(Math.random() * total);
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                random -= weightsValue[i][j];
                if(random <= 0)
                {
                    return new Point(i-1,j-1);
                }
            }
        }    
        return null;
        // Nothing seems good enough eh?
    }
    /* END PRIVATE */
}