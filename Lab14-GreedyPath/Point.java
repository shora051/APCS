public class Point 
{
    private double  x, y;
    private boolean visited;
    
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
        visited = false; 
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public void setVisit(boolean v)
    {
        visited = v;
    }
    public boolean getVisit()
    {
        return visited;
    }
    /** get the Euclidean distance between two points */
    public double getDistance(Point other)
    {
        //TODO
        double dist = Math.sqrt((Math.pow(other.getX() - this.getX(), 2)) + (Math.pow(other.getY() - this.getY(), 2)));
        return dist;
    }
    
    @Override
    public String toString()
    {
        //TODO
        return "X-value: " + x + " Y-value: " + y;
    }
}
