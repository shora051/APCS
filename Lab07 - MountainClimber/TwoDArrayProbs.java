import java.util.*;
public class TwoDArrayProbs
{
    private int[][] nums;
    public TwoDArrayProbs(int[][] arr)
    {
        nums = arr;
    }
    public int sum()
    {
        int sum = 0;
        for(int r = 0; r < nums.length; r++)
        {
            for(int c = 0; c < nums[r].length; c++)
            {
                sum += nums[r][c];
            }
        }
        return sum;
    }
    public boolean isSquare()
    {
        int numOfrows = nums.length;
        int numOfCols = nums[0].length;
        if(numOfrows == numOfCols)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void addMatrix(int[][] other)
    {
        for(int r = 0; r < nums.length; r++)
        {
            for(int c = 0; c < nums[r].length; c++)
            {
                nums[r][c] += other[r][c];
            }
        }
        print();
    }
    private void print()
    {
        //Method 1
        /*for(int[] row: nums)
        {
            for(int cell : row)
            {
                System.out.print(cell + " ");
            }
            System.out.println();
        }*/
        //Both ways work!!!
        System.out.println(Arrays.deepToString(nums));
    }
    public int columnSum(int col)
    {
        int colSum = 0;
        
        for(int r = 0; r < nums.length ; r++)
        {
            if(nums[r].length-1 >= col) //checks to see if there are enough columns in that certain row
            {
                colSum += nums[r][col];
            }
        }
        
        return colSum;
    }
    public boolean isColumnMagic()
    {
        int highestColLength = 0; //seeing the number of column sums you have to calculate
        int temp = columnSum(0); //Gets the sum of column index 0
        int currentColSum = 0; //calculates the sum of current column it is traversing
        
        for(int r = 0; r < nums.length; r++)
        {
            if(nums[r].length > highestColLength)
            {
                highestColLength = nums[r].length;
            }
        }
        for(int c = 0; c < highestColLength; c++)
        {
            currentColSum = columnSum(c);
            if(currentColSum == temp)
            {
                currentColSum = 0;
                continue;
            }
            else
            {
                return false;
            }
        }
        return true;
    }
    public int diagDifference()
    {
        int diagonalOne = 0;
        int c = 0;
        for(int r = 0; r < nums.length; r++)
        {
            diagonalOne += nums[r][c];
            c++;
        }
        int c2 = nums[0].length - 1;
        int diagonalTwo = 0;
        for(int r = 0; r < nums.length; r++)
        {
            diagonalTwo += nums[r][c2];
            c2--;
        }
        
        return (Math.abs(diagonalTwo - diagonalOne));
        
    }
}
