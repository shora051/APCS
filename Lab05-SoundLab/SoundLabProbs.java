public class SoundLabProbs
{
    //Number 2
    public SoundLabProbs()
    {
    }
    //Number 3
    public int lastIndexOf(int[] nums, int value)
    {
        int indexNum = -1;
        for(int i = nums.length-1; i >= 0; i--)
        {
            if(nums[i] == value && indexNum < i)
            {
                indexNum = i;
            }
        }
        return indexNum;
    }
    //Number 4 = Riddle (SKIP)
    //Number 5
    public int range(int[] nums)
    {
        int largest = 0;
        int smallest = 0;
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] > largest)
            {
                largest = nums[i];
            }
            if(nums[i] < smallest)
            {
                smallest = nums[i];
            }
        }
        return largest - smallest;
    }
    //Number 6
    public int minDifference(int[] nums)
    {
        int difference = 0;
        int diffNumber = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++)
        {
            if(i != nums.length-1)
            {
                difference = Math.abs(nums[i] - nums[i+1]);
                if(difference < diffNumber)
                {
                    diffNumber = difference;
                }
            }
        }
        return diffNumber;
    }
    //Number 7
     public String reverseWords(String str)
    {
        String words[] = str.split("\\s");
        String reversed = " ";
        for(int i = words.length - 1; i >= 0; i--)
        {
             reversed += words[i] + " ";
        }            
        return reversed;
    }
    //Number 8
    public int priceIsRight(int[] bids, int price)
    {
        int closestPrice = Integer.MIN_VALUE;
        int priceDifference = -1;
        for(int i = 0; i < bids.length; i++)
        {
            if((bids[i] > priceDifference) && (price - bids[i] >= 0))
            {
                priceDifference = bids[i];
            }
        }
        return priceDifference;
    }
    //Number 9
    public int[] productExceptSelf(int[] nums)
    {
        int result[] = new int[nums.length];
        int yourProduct = 1;
        for(int i = 0; i < nums.length; i++)
        {
            for(int j = 0; j < nums.length; j++)
            {
                if(i != j)
                {
                    yourProduct *= nums[j];
                    result[i] = yourProduct;
                }
            }
            yourProduct = 1;
        }
        return result;
    }
}
