import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Path
{
    private Point[] points;
    private double  minX, minY; //min X and Y values, for setting canvas scale
    private double  maxX, maxY; //maxes

    /** construct a path from a given file */
    public Path(String fileName)
    {
        double x = 0;
        double y = 0;
        try{
            Scanner scan = new Scanner(new File(fileName));
            int numOfPoints = scan.nextInt();
            points = new Point[numOfPoints];
            for(int r = 0; r < numOfPoints; r++)
            {
                x = scan.nextDouble();
                y = scan.nextDouble();
                points[r] = new Point(x, y);
                x = 0;
                y = 0;
            }
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    public double getMinX()
    {
        minX = Double.MAX_VALUE;
        for(int i = 0; i < points.length; i++)
        {
            if(points[i].getX() < minX)
            {
                minX = points[i].getX();
            }
        }
        return minX;
    }
    public double getMaxX()
    {
        maxX = Double.MIN_VALUE;
        for(int i = 0; i < points.length; i++)
        {
            if(points[i].getX() > maxX)
            {
                maxX = points[i].getX();
            }
        }
        return maxX;
    }
    public double getMinY()
    {
        minY = Double.MAX_VALUE;
        for(int i = 0; i < points.length; i++)
        {
            if(points[i].getY() < minY)
            {
                minY = points[i].getY();
            }
        }
        return minY;
    }
    public double getMaxY()
    {
        maxY = Double.MIN_VALUE;
        for(int i = 0; i < points.length; i++)
        {
            if(points[i].getY() > maxY)
            {
                maxY = points[i].getY();
            }
        }
        return maxY;
    }
    public Point getPoint(int ptNum)
    {
        return points[ptNum];
    }
    /** returns the distance traveled going point to point, in order given in file */
    public double getDistance()
    {
        double totalDist = 0;
        for(int i = 0; i < points.length-1; i++)
        {
            totalDist += points[i].getDistance(points[i+1]);
        }
        return totalDist;
    }
    public int getNumPoints() 
    {
        return points.length;
    }
    @Override
    public String toString()
    {
        return Arrays.toString(points);
    }
}
