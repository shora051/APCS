import java.util.Arrays;
public class SearchSort
{
    private int[] nums;
    
    public SearchSort(int[] nums)
    {
        this.nums = nums;
    }
    
    public SearchSort(int size)
    {
        nums = new int[size];
        initArray();
    }
    
    public void initArray()
    {
        for(int i = 0; i < nums.length; i++)
        {
            nums[i] = (int) (Math.random()*1000 +1);
        }
    }
    
    private void swap(int i, int j)
    {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public int linearSearch(int key)
    {
        int index = -1;;
        for(int i = 0; i < nums.length; i++)
        {
            if(nums[i] == key)
            {
                index = i;
            }
        }
        return index;
    }
    
    public int linearSearchRecur(int key)
    {
        return linearSearchRecur(key, nums.length-1);
    }
    private int linearSearchRecur(int key, int index)
    {
        if (nums[index] == key)
        {
            return index;
        }
        else
        {
            return 0 + linearSearchRecur(key, index - 1);
        }
    }
    
    public int binarySearch(int key)
    {
        Arrays.sort(nums);
        int right = nums.length - 1;
        int left = 0;
        int mid = 0;
        
        while(left <= right)
        {
            mid = (left + right) / 2;
            if(nums[mid] == key)
            {
                return mid;
            }
            else if(key > nums[mid])
            {
                left = mid + 1;
            }
            else
            {
                right = mid -1;
            }
        }
        return -1;
    }
    
    public int binarySearchRecursive(int key)
    {
        return binarySearchRecursive(key, 0, nums.length-1);
    }
    public int binarySearchRecursive(int key, int left, int right)
    {
        Arrays.sort(nums);
        int mid = left + ((right - left) / 2);
        if(nums[mid] == key)
        {
            return mid;
        }
        else if(key < nums[mid])
        {
            right = mid - 1;;
            return binarySearchRecursive(key, left, right);
        }
        else if(key > nums[mid])
        {
            left = mid + 1;
            return binarySearchRecursive(key, left, right);
        }
        else
        {
            return -1;
        }
    }
    
    public void bubbleSort(boolean print)
    {
        int lastUnsorted = nums.length - 1;
        while(print == false)
        {
            print = true;
            for(int i = 0; i < lastUnsorted; i++)
            {
                if(nums[i] > nums[i+1])
                {
                    int temp = nums[i];
                    nums[i] = nums[i+1];
                    nums[i+1] = temp;
                    print = false;
                }
            }
            lastUnsorted--;
        }
        if(print == true)
        {
            System.out.println(Arrays.toString(nums));
        }
    }
    
    public void selectionSort(boolean print)
    {
        int firstUnsorted = 0;
        int min;
        while(print == false)
        {
            print = true;
            min = Integer.MAX_VALUE;
            int indOfMin = 0;
            for(int i = firstUnsorted; i < nums.length; i++)
            {
                if(nums[i] < min)
                {
                    min = nums[i];
                    indOfMin = i;
                }
            }
            if(min < nums[firstUnsorted])
            {
                swap(firstUnsorted, indOfMin);
                print = false;
            }
            firstUnsorted++;
        }
        if(print == true)
        {
            System.out.println(Arrays.toString(nums));
        }
    }
    
    public void insertionSort(boolean print)
    {
        int key;
        while(print == false)
        {
            print = true;
            for(int i = 1; i < nums.length; i++)
            {
                key = nums[i];
                for(int j = i-1; j >= 0; j--)
                {
                    if(key < nums[j])
                    {
                        int temp = nums[j];
                        nums[j] = nums[j+1]; //which is nums[i]
                        nums[j+1] = temp; //which changes nums[i] is = to nums[i+1] 
                        print = false;
                    }
                }
            }
        }
        if(print == true)
        {
            System.out.println(Arrays.toString(nums));
        }
    }
    
    public void mergeSort(boolean print)
    {
        if(print == true)
        {
            System.out.println(Arrays.toString(nums));
        }
        else
        {
            mergeSort(nums, nums.length);
            mergeSort(true);
        }
    }
    private void mergeSort(int[] a, int n)
    {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];
    
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);
    
        merge(a, l, r, mid, n - mid);
    }
    public static void merge(int[] a, int[] l, int[] r, int left, int right) 
    {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) 
        {
            if (l[i] <= r[j]) 
            {
                a[k++] = l[i++];
            }
            else 
            {
                a[k++] = r[j++];
            }
        }
        while (i < left) 
        {
            a[k++] = l[i++];
        }
        while (j < right) 
        {
            a[k++] = r[j++];
        }
    }
}


