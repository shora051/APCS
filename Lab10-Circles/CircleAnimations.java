import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleAnimations
{
    private ArrayList<Circle> circles; //the circles to animate
    private int               size;    //canvas width and height (will be square)
    private Random            rng;     //use to make random numbers

    /** create a drawing pane of a particular size */
    public CircleAnimations(int s) {
        circles = new ArrayList<>();
        size    = s;
        rng     = new Random();

        //don't mess with this
        StdDraw.setCanvasSize(size, size); //set up drawing canvas
        StdDraw.setXscale(0, size);        //<0, 0> is bottom left.  <size-1, size-1> is top right
        StdDraw.setYscale(0, size);
    }
    
    public void drawCircles()
    {
        for(int i = 0; i < circles.size(); i++)
        {
            circles.get(i).draw();
        }
    }
    
    public void addCircles()
    {
        for(int i = 1; i <= 3; i++)
        {
            int radius = rng.nextInt(75) + 1;
            int red = rng.nextInt(256);
            int green = rng.nextInt(256);
            int blue = rng.nextInt(256);
            int xVal = rng.nextInt(size+1);
            int yVal = rng.nextInt(size+1);
            circles.add(new Circle(xVal, yVal, radius, new Color(red, green, blue)));
        }
        drawCircles();
    }
    
    public void addCircles(int number)
    {
        for(int i = 1; i <= number; i++)
        {
            int radius = rng.nextInt(75)+1;
            int red = rng.nextInt(256);
            int green = rng.nextInt(256);
            int blue = rng.nextInt(256);
            int xVal = rng.nextInt(size+1);
            int yVal = rng.nextInt(size+1);
            circles.add(new Circle(xVal, yVal, radius, new Color(red, green, blue)));
        }
        drawCircles();
    }
    
    public void noOverlapping(int number)
    {
        for(int i = 1; i <= number; i++)
        {
            int radius = rng.nextInt(75) + 1;
            int red = rng.nextInt(256);
            int green = rng.nextInt(256);
            int blue = rng.nextInt(256);
            int xVal = rng.nextInt(size+1);
            int yVal = rng.nextInt(size+1);
            Circle circ = new Circle(xVal, yVal, radius, new Color(red, green, blue));
            if(checkOverlap(circ))
            {
                circles.add(circ);
            }
            else
            {
                i--;
            }
        }
        drawCircles();
    }
    public boolean checkOverlap(Circle other)
    {
        for(int i = 0; i < circles.size(); i++)
        {
            double distance = Math.sqrt(Math.pow(other.getX() - circles.get(i).getX(), 2) + (Math.pow(other.getY() - circles.get(i).getY(), 2)));
            if(distance < (circles.get(i).getRadius() + other.getRadius()))
            {
                return false;
            }
            else
            {
                continue;
            }
            
        }
        return true;
    }
    
    public void movingCircles()
    {
        addCircles(10);
        for(int i = 0; i < circles.size(); i++)
        {
            int initialXVelocity = rng.nextInt(5)+1;
            int initialYVelocity = rng.nextInt(5)+1;
            circles.get(i).setVelocity(initialXVelocity, initialYVelocity);
        }
        while(true)
        {
            for(int i = 0; i < circles.size();i++)
            {
                circles.get(i).draw();
            }
            for(int j = 0; j < circles.size(); j++)
            {
                circles.get(j).update();
            }
            StdDraw.show(10);
            StdDraw.clear();
        }
    }
    
    public void removeClicked()
    {
        addCircles(10);
        while (circles.size() != 0)
        {
            drawCircles();
            if (StdDraw.isMousePressed())
            {
                for (int i = 0; i < circles.size(); i++)
                {
                    if ((StdDraw.mouseX()<= (circles.get(i).getX() + circles.get(i).getRadius()) && StdDraw.mouseX() >= (circles.get(i).getX() - circles.get(i).getRadius())) && (StdDraw.mouseY() <= (circles.get(i).getY() + circles.get(i).getRadius()) && StdDraw.mouseY() >= (circles.get(i).getY() - circles.get(i).getRadius())))
                    {
                        circles.remove(circles.get(i));
                        continue;
                    }
                }
                StdDraw.clear();
            }
        }
    }
}
