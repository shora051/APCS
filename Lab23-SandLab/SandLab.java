import java.awt.*;
import java.util.*;

public class SandLab
{
    //add constants for particle types here
    public static final int EMPTY = 0;
    public static final int METAL = 1;
    public static final int SAND = 2;
    public static final int WATER = 3;
    //do not add any more fields!
    private int[][] grid;
    private SandDisplay display; //SandDisplay is the GUI class

    public SandLab(int numRows, int numCols)
    {
        String[] names = new String[4];

        names[EMPTY] = "Empty";
        names[METAL] = "Metal";
        names[SAND] = "Sand";
        names[WATER] = "Water";
        grid = new int[numRows][numCols];
        display = new SandDisplay("Falling Sand", numRows, numCols, names);
    }

    /** called when the user clicks on a location using the given tool */
    private void locationClicked(int row, int col, int tool)
    {
        grid[row][col] = tool;
    }

    /** copies each element of grid into the display */
    public void updateDisplay()
    {
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == EMPTY){
                    display.setColor(i, j, Color.BLACK);
                }
                else if(grid[i][j] == METAL){
                    display.setColor(i, j, Color.GRAY);
                }
                else if(grid[i][j] == SAND){
                    display.setColor(i, j, new Color(194, 178, 128));
                }
                else if(grid[i][j] == WATER){
                    display.setColor(i, j, new Color(9, 195, 219));
                }
            }
        }
    }

    /** called repeatedly, causes one random particle to maybe do something */
    public void step()
    {
        Random random = new Random();
        int randRow = random.nextInt(grid.length);
        int randCol = random.nextInt(grid[0].length);
        int randDirection = random.nextInt(3);
        // 0 is left, 1 is down, 2 is right -- will move one pixel
        if(grid[randRow][randCol] == SAND){
            if(randRow + 1 < grid.length && grid[randRow+1][randCol] == EMPTY){
                grid[randRow][randCol] = EMPTY;
                grid[randRow + 1][randCol] = SAND;
            }
            else if (randRow + 1 < grid.length && grid[randRow+1][randCol] == WATER){
                grid[randRow][randCol] = WATER;
                grid[randRow + 1][randCol] = SAND;
            }
        }
        else if(grid[randRow][randCol] == WATER){
            //left
            if(randDirection == 0 && randCol - 1 >= 0 && grid[randRow][randCol - 1] == EMPTY){
                grid[randRow][randCol] = EMPTY;
                grid[randRow][randCol - 1] = WATER;
            }
            //down
            else if(randDirection == 1 && randRow + 1 < grid.length && grid[randRow+1][randCol] == EMPTY){
                grid[randRow][randCol] = EMPTY;
                grid[randRow+1][randCol] = WATER;
            }
            //right
            else if(randDirection == 2 && randCol + 1 < grid[0].length && grid[randRow][randCol + 1] == EMPTY){
                grid[randRow][randCol] = EMPTY;
                grid[randRow][randCol + 1] = WATER;
            }
        }
    }

    //do not modify!
    public void run()
    {
        while (true)
        {
            for (int i = 0; i < display.getSpeed(); i++)
                step();

            updateDisplay();

            display.repaint();

            display.pause(1);  //wait for redrawing and for mouse

            int[] mouseLoc = display.getMouseLocation();

            if (mouseLoc != null)  //test if mouse clicked
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
        }
    }
}