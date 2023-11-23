public class SearchSortTester
{
    public static void main(String[] args)
    {
        int[] arr = {5, 1, 4, 2, 8};
        SearchSort test = new SearchSort(arr);
        System.out.println("index of 1 in the array nums (linearSearch): " + test.linearSearch(1));
        System.out.println("index of 1 in the array nums (linearSearchRecur): " + test.linearSearchRecur(1));
        System.out.println("index of 4 in the array sorted nums (binarySearch): " + test.binarySearch(4));
        System.out.println("index of 4 in the array sorted nums (binarySearchRecursive) " + test.binarySearchRecursive(4));
        System.out.print("bubbleSort test: ");
        test.bubbleSort(false);
        System.out.println();
        System.out.print("selectionSort test: ");
        test.selectionSort(false);
        System.out.println();
        System.out.print("insertionSort test: ");
        test.insertionSort(false);
        System.out.println();
        System.out.print("MergeSort Method: ");
        test.mergeSort(false);
        System.out.println();
    }
}
