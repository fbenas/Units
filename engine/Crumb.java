
package engine;

/*
 * Crumb class acts as an array of pheramones stored on any one
 * Grid square.
 *
 * The "crumbs" are left behind by the Units as they move from
 * a resource towards home. They point to the next gri square
 * and decay over time.
 * 
 * Other Units can detect these crums and have a probability of
 * following the crumbs at each step.
 *
 * A maximum of 3 crumbs can be set for any one grid square.
 */

import utils.GridType;
import utils.Config;

public class Crumb
{
    /* CONSTUCTORS */

    // A consructor to create a Crumb class and add one 
    // set of weight and nextMove values to the array
    // ssing a Point object to describe the position.
    public Crumb(int weightValue, Point nextMoveValue, Config configValue)
    {
		config = configValue;
        weight = new int[3]; // Max size of 3
        nextMove = new Point[3]; // Max size of 3
        addCrumb(weightValue, nextMoveValue); // Add the crumb
    }

    // A consructor to create a Crumb class and add one 
    // set of weight and nextMove values to the array
    // ssing two integers, xValue and yValue to describe the position.
    public Crumb(int weightValue, int xValue, int yValue)
    {
        weight = new int[3]; // Max size of 3
        nextMove = new Point[3]; // Max size of 3
        addCrumb(weightValue, new Point(xValue, yValue)); // Add the crumb
    }
    /* END CONSTRUCTORS */

    /* PUBLIC */

    // Set the nextMove value of the specfied element in the array
    public void setNextMove(int indexValue, Point nextMoveValue)
    {
        // Check the specfied index is valid; 0 <= index < 3
        if(indexValue < 3 && indexValue >= 0)
        {
            nextMove[indexValue] = nextMoveValue;
        }
    }

    // Get the nextMove of the specfied index.
    // If the index is out of range, or the weighting for that crum part
    // is 0 or less, then we return an empty Point
    public Point getNextMove(int indexValue)
    {
        // Check the specfied index is valid; 0 <= index < 3
        // and that the weighting for that index is larger than 0.
        if(indexValue < 3 && indexValue >=0 && weight[indexValue] > 0)
        {
            return nextMove[indexValue];
        }
        else
        {
            // Index is not valid or there is no weighting left for
            // the index so return an empty Point. 
            return new Point();
        }
    
    }

    // Set the weight value of the specfied element in the array
    public void setWeight(int indexValue, int weightValue)
    {
        // Check the specfied index is valid; 0 <= index < 3
        if(indexValue < 3 && indexValue >=0)
        {
            if(weightValue <= 0)
            {
                // the weight is less than 0 so we set it to 0
                weight[indexValue] = 0;
                // Set the nextMove to an empty Point
                nextMove[indexValue] = new Point(); 
            }
            else
            {
                // Set the specified element's weight value 
                weight[indexValue] = weightValue;
            }
        }
    }

    // Get the weight of the specfied index.
    // If the index is out of range, or the weighting for that crum part
    // is 0 or less, then we return 0.
    public int getWeight(int indexValue)
    {
        // Check the specfied index is valid; 0 <= index < 3
        // and that the weighting for that index is larger than 0.
        if(indexValue < 3 && indexValue >=0 && weight[indexValue] > 0)
        {
            // index is valid and the index has a weight above 0
            // so return the specified weight.
            return weight[indexValue];
        }
        else
        {
            // Index is not valid or there is no weighting left for
            // the index so return an 0. 
            return 0;
        }
    }
    
    // Get the weightings for all elements in the array.
    public int[] getCrumbs()
    {
        return weight;
    }

    // Attempt to add a new weight and nextMove to the crumb.
    // The values will be added to the first element that has a weighting of 0.
    // If there are all ready three crumb parts with weightings then 
    // return false.
    // Otherwise we return true.
    public boolean addCrumb(int weightValue, Point nextMoveValue)
    {
        // Loop through the arrays
        for(int i=0; i < 3; i++)
        {
            // Check the specific weight value
            if(weight[i] <= 0)
            {
                // The crum part has 0 or less weight so add the new crum here
                setWeight(i,weightValue);
                setNextMove(i,nextMoveValue);
                // The crum part has been added so return true.
                return true;
            }
        }
        // We have itererated through the whole array and found no zero-weighted crum
        // So return false.
        return false;
    }
    /* END PUBLIC */

    /* PROTECTED  */
    /* END PROTECTED*/

    /* PRIVATE*/

    private int[] weight; // The strength of the pheramone or crumb
    private Point[] nextMove; // The next grid square
	private Config config;
    /* END PRIVATE */
}
