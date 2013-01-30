package engine;

/*
 * Point class holds an x and y value specfiying
 * an exact location on a 2 dimensioal grid.
 *
 * Point has simple set and get methods for x and y
 * seperatly as well as returning an object of type
 * Point itslef.
 *
 * Point can be constructed with a point or sperate
 * x and y values.
 *
 * Point has subtract and addition methods to easily
 * add an int to either x, y or both using a Point.
 */
public class Point
{

    /* CONSTUCTORS */

    // Constructor for creating a new Point using
    // given x and y int values
    public Point(int xValue, int yValue)
    {
        x = xValue;
        y = yValue;
    }

    // Constructor for creating a Point from another,
    // given Point, copying the x and y values across.
    public Point(Point pValue)
    {
        x = pValue.getX();
        y = pValue.getY();
    }

    // Constructor for creating an empty Point. This 
    // is used for better handling of a null Point.
    public Point()
    {
        // Set to negative one as all common use will involve
        // positive integers
        x = -1;
        y = -1;
    }
    /* END CONSTRUCTORS */

    /* PUBLIC */

    // return the x value
    public int getX()
    {
        return x;
    }

    // return the y value
    public int getY()
    {
        return y;
    }

    // set the x value of the Point
    public void setX(int xValue)
    {
        x = xValue;
    }

    // set the y value of the Point
    public void setY(int yValue)
    {
        y = yValue;
    }

    // add a given x and y value to the point.
    // Return this Point.
    public Point add(int xValue, int yValue)
    {
        x += xValue;
        y += xValue;
        return this;
    }

    // subtract a given x and y value from the point.
    // Return this Point.
    public Point sub(int xValue, int yValue)
    {
        x -= xValue;
        y -= yValue;
        return this;
    }

    // Add a given Point to this point
    public Point add(Point pValue)
    {
        x += pValue.getX(); // add the x part
        y += pValue.getY(); // add the y part
        return this; // return this Point
    }

    // Subtract a given Point from this Point
    public Point sub(Point pValue)
    {
        x -= pValue.getX(); // subtract the x part
        y -= pValue.getY(); // subtract the y part
        return this; // return this Point
    }
    /* END PUBLIC */

    /* PROTECTED  */
    /* END PROTECTED*/

    /* PRIVATE*/

    private int x; // specific location in the x-axis
    private int y; // specific location in the y-axis
    /* END PRIVATE */
}