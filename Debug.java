/*
 * Public class to print debug information in a meaningfull way to the console.
 */
public class Debug
{
	// debug levels:
	// 1 = application (top level)
	// 2 = Things
	// 
	public Debug(int levelValue)
	{
		level = levelValue;
	}

	private int level;

	public void setLevel(int levelValue)
	{
		level = levelValue;
	}
	public void debug(String name, String value, int levelValue)
	{
		// Only show stuff on required level and above
		String tabs = "";
		for(int i =1; i<levelValue; i++)
			tabs += "\t";
		if(levelValue <= level)
			System.out.println(tabs + name + ": " + value);
	}

	//END Debug methods and variables

}