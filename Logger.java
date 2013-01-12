/*
 * Public class to print debug information in a meaningfull way to the console.
 */
public class Logger
{
	// Logger levels:
	// 1 = application (top level)
	// 2 = Ground
	// 3 = Things (and the base class Noting)
	private static int level;
	public Logger(int levelValue)
	{
		level = levelValue;
	}

	public void setLevel(int levelValue)
	{
		level = levelValue;
	}
	public static void debug(String name, String value, int levelValue)
	{
		// Only show stuff on required level and above
		String tabs = "";
		for(int i =1; i<levelValue; i++)
			tabs += "\t";
		if(levelValue <= level)
			System.out.println(tabs + name + ": " + value);
	}

	//END Logger methods and variables

}