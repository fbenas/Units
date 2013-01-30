package engine;

/*
 * public class that acts either as a base class for all objects
 * to be placed on a grid or if instatiated directly, as an empty
 * grid space.
 *
 * Nothing contains a name, a type, a Logger object and a debug level.
 * Get and set methods are used in order to access these values publicly
 *
 * The debug function allows for access to the Debugger Object and hence
 * is used to output debugging and testing information.
 */

import utils.GridType;

public class Nothing
{
	/* CONSTUCTORS */

	// Constructor for Nothing, passing only a debugger object
	// This is the recomened constuctor if instatiating the object directly
	// as all values are set automatically.
	public Nothing(Logger debugValue)
	{
		debugger = debugValue;
		debugLevel = 2;
 		name = "nothing";
		type = GridType.EMPTY;
	}

	// Constructor for Nothing passing in name, type, a debug object and debug level.
	// This is the recomended construcror that extended Classes of this class 
	// should call in their constructors.
	// All values must be passed to the object, nothing is set automatically.
	public Nothing(String nameValue, GridType typeValue, int debugLevelValue, Logger debugValue)
	{
		type = typeValue;
		debugger = debugValue;
		debugLevel = debugLevelValue;
		if(nameValue != null && nameValue != "")
		{
			name = nameValue;
		}
	}
	/* END CONSTRUCTORS */

	/* PUBLIC */

	// Return the type of this Object
	// This will be either the type of the child class that has extended this class
	// or "nothing" if this object has been istantiated directly. However other values
	// can be added to GridType if and when needed.
	public GridType getType()
	{
		return type;
	}

	// Return the name of this object
	public String getName()
	{
		return name;
	}

	// Set the name of this object
	public void setName(String nameValue)
	{
		debug("changing name of " + name +  " to " + nameValue);
		name = nameValue;
	}
	/* END PUBLIC */

	/* PROTECTED  */

	protected final GridType type; 	// The type of Object
	protected Logger debugger; 		// The debugging object

	// Output a string to the cli via the debugging object.
	// This method adds the type and name to the given string
	// so tracking of the information can be performed.
	protected void debug( String value )
	{
		debugger.debug(getType() + "-" + name, value, debugLevel);
	}

	/* END PROTECTED*/

	/* PRIVATE*/

	private final int debugLevel; 	// The level that this class 
									// will output information at
	
	private String name;			// The name of the Object, used 
									// for debugging purposes
	/* END PRIVATE */
}