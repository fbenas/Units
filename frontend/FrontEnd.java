package frontend;

/* Public class FrontEnd
 * This class will be constructed with grid width and length
 * A blank grid is then created and must be populated using 
 * udpateGridContents().
 * The Grid can then be displayed grapically using the printGridContents()
 * method.
 */

import utils.GridType;
import utils.AnimObject;
import javax.swing.JFrame;
import utils.Config;

public class FrontEnd
{

    /* CONSTUCTORS */

    // Constructor to create the grid
    public FrontEnd(Config configValue, AnimObject[][] gridContentsValue)
    {
        gridContents = gridContentsValue;
		config = configValue;
        gridX = config.GRID_WIDTH;
        gridY = config.GRID_HEIGHT;
        drawGrid = config.DRAW_GRID_FLAG;
        createGUI();
    }
    /* END CONSTRUCTORS */

    /* PUBLIC */

    // Public method to allow for external updating of the grid.
    public void updateGrid(AnimObject[][] gridContentsValue)
    {
        gridContents = gridContentsValue;
        gui.redraw(gridContents);
    }
    /* END PUBLIC */

    /* PROTECTED  */
    /* END PROTECTED*/

    /* PRIVATE*/

    private int gridX;
    private int gridY;
    private AnimObject[][] gridContents;
    private boolean drawGrid;
    private JFrame mainFrame;
    private DrawGUI gui;
	private Config config;

    // Private method to allow for creating of the Graphical user Interface
    // This will contain everything but the contents of the grid
    private void createGUI()
    {
        mainFrame = new JFrame("Units");
        gui = new DrawGUI(config, gridContents);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(gui);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
    /* END PRIVATE */
}
