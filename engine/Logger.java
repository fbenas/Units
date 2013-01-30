package engine;

/*
 * Public class Logger is used to output debug information
 * The use of 'debug levels' across a system mean that 
 * debug information can be built up, starting with high-level
 * information, with a level of 1. 
 * 
 * More specific or detailed inforamtion can then be shown
 * by increasing the integer. 
 * 
 * Tabs are used so that each level is indented resulting
 * in an easier to read and understand output.
 */

public class Logger
{
	/* CONSTUCTORS */

	// Constructor for creating an instance of the class
	// with a specific output debug level in mind.
	// A level of x will disregard any information with
	// a level larger than x.
	public Logger(int levelValue)
	{
		level = levelValue;
	}

	// A public method to set the debug level during runtime,
	// this can be used if one wants to overide the value set in 
	// the constructor for this object.
	public void setLevel(int levelValue)
	{
		level = levelValue;
	}
	/* END CONSTRUCTORS */

	/* PUBLIC */

	// public method to output information to the Command Line Interface
	// Adding an additional tab to each "deeper" level
	// A name is required so that each piece of inforamtion can be tracked
	// back to it's source.
	public static void debug(String name, String value, int levelValue)
	{
		// Only show stuff on required level and above
		String tabs = "";
		// loop through the levels that will be outputted and an additional
		// tab for each level.
		for(int i =1; i<levelValue; i++)
		{
			tabs += "\t";
		}
		
		// Check the required output debug level
		if(levelValue <= level)
		{
			// Print the debug inforamtion to the cli.
			System.out.println(tabs + name + ": " + value);
		}
	}
	/* END PUBLIC */

	/* PROTECTED  */
	/* END PROTECTED*/

	/* PRIVATE*/
	
	private static int level;
	/* END PRIVATE */



}