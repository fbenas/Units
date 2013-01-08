/*
 * A public class that extends from the Thing base class. 
 */
public class Home extends Thing
{
	/*
	 * Constructors
	*/

	// Basic constructor
	public Home(String nameValue, Debug debugValue)
	{
		super(nameValue, debugValue);
		// We must locate the thing
		locate();
	}

	// Constructor with x and y values to create a Unit with a specfic location
	public Home(int xValue, int yValue, String nameValue, Debug debugValue)
	{
		super(nameValue, debugValue);

		// We must locate the thing.
		super.setX(xValue);
		super.setY(yValue);
	}

	// END Constructors
}