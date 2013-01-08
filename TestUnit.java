/*
 * Test applciation to check everything is hunky dory
 */
public class TestUnit
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
		// Test The Home class
		testFour();
		// Test creation of unit from a home.
		testFive();
		// Test creation of a resource.
		testSix();
		// Test Unit carrying and unloading
		testSeven();
		// Spoof surroundings so we can test its decsion making
		testEight();
		// Test Unit interacting with ground boundaries
		testNine();
		// Test Unit interacting with a resource
		testTen();
		//Test Unit interacting with home
		testEleven();
		// Test Unit moving randomly then interacting with a resource
		testTwelve();
		// Test Unit returning home
		testThirteen();
		// Test Unit moving randomly, interacting with a resource and then
		// returning home
		testFourteen();
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
		Home home = new Home("HOME",debug);
		Unit unitA = new Unit("A",home,debug);
		Unit unitB = new Unit(0,0,"B",home,debug);
		Unit unitC = new Unit(10,5,"C",home,debug);

		Unit[] bla = {unitA, unitB, unitC};

		for(Unit unit : bla)
		{
			if(unit.getName() == "B")
			{
				unit.setPos(100,100);
				break;
			}
		}
		debug("Completed", "Test One");
	}

	private void testTwo()
	{
		debug("Starting...", "Test Two");
		Home home = new Home("HOME",debug);
		Unit unitD = new Unit(20,20,"D",home, debug);
		unitD.move();
		debug("Completed", "Test Two");	
	}

	private void testThree()
	{
		debug("Starting...", "Test Three");
		Home home = new Home("HOME",debug);
		Unit unitE = new Unit("E",home, debug);
		int counter = 0;
		while (counter < 5)
		{
			unitE.move();
			counter++;
			debug("Move" + counter, "Test Three");
		}
		debug("Completed", "Test Three");	
	}

	public void testFour()
	{
		debug("Starting...", "Test Four");
		Home homeA = new Home("A",debug);
		Home homeB = new Home(20,20,"B",debug);
		Home homeC = new Home(0,0,"C",debug);
		homeC.setPos(10,10);
		debug("Completed", "Test Four");
	}

	public void testFive()
	{
		debug("Starting...", "Test Five");
		Home homeD = new Home("D",debug);
		Unit unitF = new Unit("F",homeD,debug);
		debug("Completed", "Test Five");
	}

	public void testSix()
	{
		debug("Starting...", "Test Six");
		Resource resourceA = new Resource("A",100,debug);
		Resource resourceB = new Resource(20,20,"B",100,debug);
		resourceA.setName("New Name");
		resourceA.setAmount(5);

		// Usually we need to check if this has worked.
		// returns new amount if successful otherwise returns 0.
		resourceB.reduceAmount(10);
		resourceA.reduceAmount(10);
		
		Home homeE = new Home("E", debug);
		homeE.setAmount(10);
		homeE.increaseAmount(10);
		debug("Completed", "Test Six");
	}
	
	public void testSeven()
	{
		debug("Starting...", "Test Seven");
		Home home = new Home("Home",debug);
		Unit unitG = new Unit("G", home, debug);
		unitG.setCarry(10);
		unitG.setCarry(10);
		unitG.setCarry(0);
		unitG.setCarry(10);
		debug("Completed", "Test Seven");
	}

	public void testEight()
	{
		debug("Starting...", "Test Eight");

		debug("Completed", "Test Eight");
	}

	public void testNine()
	{
		debug("Starting...", "Test Nine");

		debug("Completed", "Test Nine");
	}

	public void testTen()
	{
		debug("Starting...", "Test Ten");

		debug("Completed", "Test Ten");
	}

	public void testEleven()
	{
		debug("Starting...", "Test Eleven");

		debug("Completed", "Test Eleven");
	}

	public void testTwelve()
	{
		debug("Starting...", "Test Tweleve");

		debug("Completed", "Test Tweleve");
	}

	public void testThirteen()
	{
		debug("Starting...", "Test Thirteen");

		debug("Completed", "Test Thirteen");
	}

	public void testFourteen()
	{
		debug("Starting...", "Test Fourteen");

		debug("Completed", "Test Fourteen");
	}
	public void debug(String value, String test)
	{
		debug.debug(test, value, debugLevel);
	}
}
