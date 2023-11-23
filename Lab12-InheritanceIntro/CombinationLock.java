import java.util.*;
public class CombinationLock extends Lock
{
    Scanner input = new Scanner(System.in);
    private String combination;
    
    public CombinationLock()
    {
        super();
        combination = "";
    }
    public CombinationLock(String combo)
    {
        super();
        combination = combo;
    }
    @Override
    public void open()
    {
        System.out.print("Enter combination: ");
        String combo = input.nextLine();
        System.out.println();
        if(combination.equals(combo))
        {
            super.open();
        }
    }
    @Override
    public String toString()
    {
        return super.toString() + "\n" + "Combination = " + combination + "\n";
    }
    public void setCombination(String c)
    {
        combination = c;
    }
    public String getCombination()
    {
        return combination;
    }
}
