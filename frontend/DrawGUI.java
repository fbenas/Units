package frontend;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import utils.GridType;

public class DrawGUI extends JPanel {

    int gridWidth;
    int gridHeight;
    int squareWidth;
    boolean drawGrid;
    GridType[][] grid;

    public DrawGUI(int gridWidth, int gridHeight, int squareWidth, boolean drawGridValue, GridType[][] gridValue) {
        this.setPreferredSize(new Dimension(900, 900));
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.squareWidth = squareWidth;
        grid = gridValue;
        drawGrid = drawGridValue;
   }

    public void redraw(GridType[][] gridValue) {
        grid = gridValue;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.setColor(Color.WHITE);
        g.fillRect(20, 20, gridWidth*squareWidth, gridHeight*squareWidth);
        g.setColor(Color.BLACK);
        g.drawRect(20, 20, gridWidth*squareWidth, gridHeight*squareWidth);

        if(drawGrid)
        {
            for (int i = 0; i < gridWidth; i++) {
                for (int j = 0; j < gridHeight; j++) {
                        g.setColor(Color.BLACK);
                        int screenX = i*squareWidth+20;
                        int screenY = j*squareWidth+20;
                        g.drawRect(screenX,screenY,squareWidth,squareWidth);
                }
            }
        }

        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                int screenX;
                int screenY;
                switch(grid[i][j])
                {
                    case EMPTY:
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
}
