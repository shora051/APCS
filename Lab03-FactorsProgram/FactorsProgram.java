import java.util.*;
public class FactorsProgram
{
    public static void main(String[] args)
    {
        //Printing Factors (Part 1)
        System.out.println("Printing Factors - Part 1");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int userInput = input.nextInt();
        System.out.println();
        int factorCounter = 0;
        String oneFactor = new String();
        String containsFactors = new String();
        while(userInput != 0)
        {
            for(int i = 2; i < userInput; i++)
            {
                if(userInput % i == 0)
                {
                    oneFactor = String.valueOf(i);
                    containsFactors += oneFactor + " ";
                    factorCounter++;
                }
                else
                {
                    continue;
                }
            }
            System.out.println("There are " + factorCounter + " factors for the number " + userInput + ": " + containsFactors);
            System.out.println();
            System.out.print("Enter a number: ");
            userInput = input.nextInt();
            oneFactor = "";
            containsFactors = "";
            factorCounter = 0;
            System.out.println();
        }
        
        //Prime Factorization (Part 2)
        System.out.println("Prime Factorization-Part 2");
        System.out.print("Enter a number: ");
        int userInput2 = input.nextInt();
        String oneFactor2 = new String();
        String containsFactors2 = new String();
        while(userInput2 != 0)
        {
            System.out.print("The prime factorization for " + userInput2 + ": ");
            for(int i = 2; i < userInput2; i++)
            {
                while(userInput2 % i == 0)
                {
                    oneFactor2 = String.valueOf(i);
                    containsFactors2 += oneFactor2 + " ";
                    userInput2 /= i;
                }
            }
            if(userInput2 > 2)
            {
                System.out.print(userInput2 + " ");
            }
            System.out.print(containsFactors2);
            System.out.println();
            System.out.print("Enter a number: ");
            userInput2 = input.nextInt();
            oneFactor2 = "";
            containsFactors2 = "";
            System.out.println();
        }
    }
}
