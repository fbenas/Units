package engine;

/*
 * Public class Resource extends the parent class Thing. 
 *
 * Resource are created with a given amount of resource.
 * Unit's interact with the resource when they move to
 * one of the 8 grid squares that surround that resource.
 *
 * By interacting with a resource they take some amount of
 * the resource and hence the objects resource value is
 * reduced.
 * 
 * The Resource is placed on the grid.
 */

import utils.GridType;
import utils.Config;

public class Resource extends Thing
{

    /* CONSTUCTORS */

    // A constuctor to set the inital amount of resource using amountValue
    // and to place the Resource in a random, valid position on the grid
    // The other parameters are used to call the parent constuctor.
    public Resource(String nameValue, int amountValue, Config configValue, Ground groundValue)
    {
        super(nameValue, configValue, GridType.RESOURCE); // call parent constuctor
        setInitalAmount(amountValue); // Set the inital amount
        setAmount(amountValue); // Set the current amount
        locate(groundValue); // Spawn the Resource on the grid at a random valid position
    }

    // A constuctor to set the inital amount of resource using amountValue
    // and to place the Resource in a given positon on the grid using the 
    // xValue and yValue parameters.
    // The other parameters are used to call the parent constuctor.
    public Resource(int xValue, int yValue, String nameValue, int amountValue, Config configValue )
    {
        super(nameValue, configValue, GridType.RESOURCE); // call parent constuctor
        setInitalAmount(amountValue); // Set the inital amount
        setAmount(amountValue); // Set the current amount
        setPos(xValue, yValue); // Spawn the Resource at the given position on the grid
    }
    /* END CONSTRUCTORS */

    /* PUBLIC */

    // return the current amount of resource
    public int getAmount()
    {
        return amount;
    }

    // set the current amount of resource
    public void setAmount(int amountValue)
    {
        amount = amountValue;
        debug("setting amount to " + amountValue);
    }

    // get the intial amount of resource
    public int getInitalAmount()
    {
        return initalAmount;
    }

    // Check the current amount of resource and reduce it by
    // the given amount if there is enough left
    public int reduceAmount(int reduceValue)
    {
        int newAmount = amount-reduceValue; // get the possible new amount
        if(amount-reduceValue >= 0) // check this amount is more than zero
        {
            debug("reducing amount, " + amount + " by " + reduceValue + " to " + newAmount);
            amount = newAmount; // set the current amount to the new lower amount
            return newAmount; // return the new amount
        }
        else
        {
            debug("Cannot reduce amount, " + amount + " by " + reduceValue);
            amount = 0; // set the current amount back to 0
            return 0; // return 0 
        }
    }
    /* END PUBLIC */

    /* PROTECTED  */
    /* END PROTECTED*/

    /* PRIVATE*/
    private int amount; // the current amount of resource
    private int initalAmount; // the inital amount of resource

    // set the inital amount of resource
    private void setInitalAmount(int amountValue)
    {
        debug("setting inital amount to " + amountValue);
        initalAmount = amountValue;
    }
    /* END PRIVATE */
}