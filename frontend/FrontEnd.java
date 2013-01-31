package frontend;

/* Public class FrontEnd
 * This class will be constructed with grid width and length
 * A blank grid is then created and must be populated using 
 * udpateGridContents().
 * The Grid can then be displayed grapically using the printGridContents()
 * method.
 */

import utils.GridType;
import javax.swing.JFrame;

public class FrontEnd
{

    /* CONSTUCTORS */

    // Constructor to create the grid
    public FrontEnd(int gridXValue, int gridYValue, boolean drawGridValue, GridType[][] gridValue)
    {
        gridContents = gridValue;
        gridX = gridXValue;
        gridY = gridYValue;
        drawGrid = drawGridValue;
        //createGridContents(); // USED FOR DEBUGGING
        createGUI();
    }
    /* END CONSTRUCTORS */

    /* PUBLIC */

/*
    public static void main(String[] args)
    {
        new FrontEnd(40,40,true);
    }
*/ // USED FOR DEBUGGING

    // Public method to allow for external updating of the grid.
    public void updateGrid(GridType[][] gridValue)
    {
        gridContents = gridValue;
        gui.redraw(gridContents);
    }
    /* END PUBLIC */

    /* PROTECTED  */
    /* END PROTECTED*/

    /* PRIVATE*/

    private int gridX;
    private int gridY;
    private GridType[][] gridContents;
    private boolean drawGrid;
    private JFrame mainFrame;
    private DrawGUI gui;

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
        gridContents[3][3] = GridType.HOME;
        gridContents[30][30] = GridType.RESOURCE;
        gridContents[4][4] = GridType.UNIT;
    }

    // Private method to allow for creating of the Graphical user Interface
    // This will contain everything but the contents of the grid
    private void createGUI()
    {
        mainFrame = new JFrame("Units");
        gui = new DrawGUI(gridX,gridY, 20, drawGrid, gridContents);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(gui);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
    /* END PRIVATE */
}