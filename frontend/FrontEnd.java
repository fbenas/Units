package frontend;

// Public class FrontEnd
// This class will be constructed with grid width and length
// A blank grid is then created and must be populated using 
// udpateGridContents().
// The Grid can then be displayed grapically using the printGridContents()
// method.

//Import the grid type class
import utils.GridType;

public class FrontEnd
{

	// Private variables
	private int gridX;
	private int gridY;
	private GridType[][] gridContents;

	// Constructor to create the grid
	public FrontEnd(int gridXValue, int gridYValue)
	{
		gridX = gridXValue;
		gridY = gridYValue;
		createGridContents();
	}

	// Insantaite the int[][] and fill it with "EMPTY" values
	private void createGridContents()
	{
		gridContents = new GridType[gridX][gridY];
		for(int i =0; i<gridX; i++)
		{
			for(int j =0; j<gridY; j++)
			{
				gridContents[i][j] = GridType.EMPTY;
			}
		}
	}

	// Public method to allow for external updating of the grid.
	public void updateGrid(GridType[][] gridValue)
	{
		gridContents = gridValue;
	}

	// Public method to allow for external printing of the grid
	public void printGridContents()
	{
		
	}

	// Private method to allow for creating of the Graphical user Interface
	// This will contain everything but the contents of the grid
	private void createGUI()
	{

	}
}