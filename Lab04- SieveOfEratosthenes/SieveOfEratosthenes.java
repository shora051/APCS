//`Name: Sahil Hora
//Period: 2A
public class SieveOfEratosthenes
{
    public static void main(String[] args)
    {
        int num = 1000;
        boolean[] arr = new boolean[num+1];
        for(int i = 2; i <= num; i++)
        {
            arr[i] = true;
        }
        
        for(int x = 2; (int) Math.pow(x, 2)<= 1000; x++)
        {
            if(arr[x] == true)
            {
                for(int y = (int) Math.pow(x, 2); y <= 1000;  y += x)
                {
                    arr[y] = false;
                }
            }
        }
        
        int rowsOfFifteen = 0; 
        for(int k = 0; k <= 1000; k++)
        {
            if(arr[k] == true)
            {
                System.out.print(k + " ");
                rowsOfFifteen++;
            }
            if(rowsOfFifteen == 15)
            {
                System.out.println();
                rowsOfFifteen = 0;
            }
        }
    }
}
