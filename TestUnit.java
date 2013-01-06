
class TestUnit
{
	// Application wide vars
	// THESE SHOULD MOVE TO A STATIC CLASS.
	private int groundX = 400;
	private int groundY = 400;
	private int minMove = 10;
	private int debugLevel = 1;
	// This must be set up, either true or false.
	Debug debug;
	public TestUnit(int runtimeLevelValue)
	{
		debug = new Debug(runtimeLevelValue);
		// Test set and get X,Y and Name.
		testOne();
		// Test some simple movement.
		testTwo();
		// Test Multiple moves in a loop
		testThree();
	}
	// Class to test the Unit class.
	public static void main(String[] args)
	{
		int runtimeLevel;
		try { 
        	runtimeLevel = Integer.parseInt(args[0]); 
    	} catch(Exception e) {
    		runtimeLevel = 0;
        }
		new TestUnit(runtimeLevel);
	}

	private void testOne()
	{
		debug("Starting...", "Test One");
		Unit unitA = new Unit("A",debug);
		Unit unitB = new Unit(0,0,"B",debug);
		Unit unitC = new Unit(10,5,"C",debug);

		Unit[] bla = {unitA, unitB, unitC};

		for(Unit unit : bla)
		{
			if(unit.getName() == "B")
			{
				unit.setX(100);
				unit.setY(100);
				break;
			}
		}
		debug("Completed", "Test One");
	}

	private void testTwo()
	{
		debug("Starting...", "Test Two");
		Unit unitD = new Unit(20,20,"D",debug);
		unitD.move();
		debug("Completed", "Test Two");	
	}

	private void testThree()
	{
		debug("Starting...", "Test Three");
		Unit unitE = new Unit("E",debug);
		int counter = 0;
		while (counter < 100)
		{
			unitE.move();
			counter++;
			debug("Move" + counter, "Test Three");
		}
		debug("Completed", "Test Three");	
	}

	public void debug(String value, String test)
	{
		debug.debug(test, value, debugLevel);
	}
}