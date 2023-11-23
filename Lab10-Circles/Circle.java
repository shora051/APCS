import java.awt.Color;
public class Circle
{
    private int x;
    private int y; 
    private int radius;
    private Color color;
    private int dx;
    private int dy;
    
    public Circle(int xVal, int yVal, int r, Color c)
    {
        x = xVal;
        y = yVal;
        radius = r; 
        color = c;
    }
    
    public Circle(int xVal, int yVal, int r, Color c, int uRight, int uUp)
    {
        x = xVal;
        y = yVal;
        radius = r; 
        color = c;
        dx = uRight;
        dy = uUp;
    }
    
    public void draw()
    {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(x, y, radius);
    }
    
    public static void main(String[] args)
    {
        new Circle(0, 0, 1, new Color(255, 0, 0)).draw();
    }
    
    public int getRadius()
    {
        return radius;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public boolean overlaps(Circle other)
    {
        double distance = Math.sqrt(Math.pow(other.getX() - this.getX(), 2) + (Math.pow(other.getY() - this.getY(), 2)));
        if(distance < (other.getRadius() + this.getRadius()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void setVelocity(int x, int y)
    {
        dx = x;
        dy = y;
    }
    public void update()
    {
        bounce();
        x += dx;
        y += dy;
    }
    public void bounce()
    {
        if(x + radius >= 600 || x - radius <= 0)
        {
            dx *= -1;
        }
        if(y + radius >= 600 || y - radius <= 0)
        {
            dy *= -1;
        }
    }
    
}
