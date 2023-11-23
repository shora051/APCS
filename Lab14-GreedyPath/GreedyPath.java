import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class GreedyPath extends Path
{
    private Point[] gPoints;
    
    public GreedyPath(String filename) throws IOException
    {
        super(filename);
        gPoints = new Point[super.getNumPoints()];
        findPath();
    }
    
     private void findPath() 
     {
        gPoints[0] = super.getPoint(0);
        super.getPoint(0).setVisit(true);
        Point main;
        int closeInd = 0;

        for (int i = 0; i < gPoints.length - 1; i++) 
        {
            main = gPoints[i];
            double minDistance = Integer.MAX_VALUE;
            Point closest = null;
            for (int j = 0; j < super.getNumPoints(); j++) {
                Point compare = super.getPoint(j);
                if (super.getPoint(j).getVisit()) {
                    continue;
                }
                double dist = main.getDistance(compare);
                if (dist < minDistance) {
                    minDistance = dist;
                    closest = compare;
                    closeInd = j;
                }

            }

            super.getPoint(closeInd).setVisit(true);
            gPoints[i + 1] = closest;

        }

    }
    
    @Override
    public double getDistance()
    {
        double totalDist = 0;
        for(int i = 0; i < gPoints.length-1; i++)
        {
            totalDist += gPoints[i].getDistance(gPoints[i+1]);
        }
        return totalDist;
    }
    
    @Override
    public Point getPoint(int i)
    {
        return gPoints[i];
    }
    
    @Override
    public String toString()
    {
        return Arrays.toString(gPoints);
    }
}
