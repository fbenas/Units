package frontend;

/*
 * Public clas DrawGUI extends parent calss JPanel
 *
 * This class creates and prints the GUI to the screen
 * It will iterate through a GridType array and print
 * the different objects needed for each different Type.
 *
 */

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import utils.GridType;
import utils.Config;
import utils.AnimObject;

public class DrawGUI extends JPanel 
{

    /* CONSTUCTORS */

    public DrawGUI(Config configValue, AnimObject[][] gridContentsValue)
	{
		config = configValue;

    	setPreferredSize(new Dimension(config.PANEL_X, config.PANEL_Y));
        gridWidth = config.GRID_WIDTH;
        gridHeight = config.GRID_HEIGHT;
        squareWidth = config.SQUARE_WIDTH;
        drawGrid = config.DRAW_GRID_FLAG;

        gridContents = gridContentsValue;
	}
    /* END CONSTRUCTORS */

    /* PUBLIC */

	public void redraw(AnimObject[][] gridContentsValue)
	{
        gridContents = gridContentsValue;
        repaint();
    }

    @Override
    public void paint(Graphics g)
	{
		// Fill and Draw the background of the Ground.
		g.setColor(Color.WHITE);
        g.fillRect(20, 20, gridWidth*squareWidth, gridHeight*squareWidth);
        g.setColor(Color.BLACK);
        g.drawRect(20, 20, gridWidth*squareWidth, gridHeight*squareWidth);

		// Loop through the gridType array
        for (int i = 0; i < gridWidth; i++)
		{
            for (int j = 0; j < gridHeight; j++)
			{
                int screenX;
                int screenY;
       			if(drawGrid) // if we want the grid to show
       			{
	                g.setColor(Color.BLACK);
                    screenX = i*squareWidth+20;
                    screenY = j*squareWidth+20;
					g.drawRect(screenX,screenY,squareWidth,squareWidth);
				}

				// Swithc on the gridType
                switch(gridContents[i][j].gridType)
                {
                    case EMPTY: // Empty first as it is most common
                        break;

                    case UNIT:
                        screenX = i*squareWidth+20;
                        screenY = j*squareWidth+20;
                        g.setColor(Color.BLACK);
                        g.fillOval(screenX,screenY,squareWidth/2,squareWidth/2);
                        break;

                    case HOME:
                        screenX = i*squareWidth+20;
                        screenY = j*squareWidth+20;
                        g.setColor(Color.GREEN);
                        g.fillRect(screenX,screenY,squareWidth,squareWidth);
                        break;

                    case RESOURCE:
                        screenX = i*squareWidth+20;
                        screenY = j*squareWidth+20;
                        g.setColor(Color.RED);
                        g.fillRect(screenX,screenY,squareWidth,squareWidth);
                        break;
                }
            }
        }
    }
    /* END PUBLIC */

    /* PROTECTED */
    /* END PROTECTED*/

    /* PRIVATE*/

    private int gridWidth;
    private int gridHeight;
    private int squareWidth;
    private boolean drawGrid;
    private AnimObject[][] gridContents;
	private Config config;
    /* END PRIVATE */
}
