public class Nothing
{
	protected final String type;
	private Logger debug;
	private final int debugLevel;
	
	public Nothing(Logger debugValue)
	{
		debug = debugValue;
		debugLevel = 3;
 		name = "nothing";
		type = "empty";

	}

	public Nothing(String nameValue, String typeValue, int debugLevelValue, Logger debugValue)
	{
		type = typeValue;
		debug = debugValue;
		debugLevel = debugLevelValue;
		if(nameValue != null && nameValue != "")
		{
			name = nameValue;
		}
	}

	public String getType()
	{
		return type;
	}

	// name variable and methods
	private String name;

	public String getName()
	{
		return name;
	}
	public void setName(String nameValue)
	{
		debug("changing name of " + name +  " to " + nameValue);
		name = nameValue;
	}

	// Method for adding debug information from a Thing
	protected void debug( String value )
	{
		debug.debug(getType() + "-" + name, value, debugLevel);
	}
}