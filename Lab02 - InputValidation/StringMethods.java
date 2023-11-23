import java.util.*;
public class StringMethods
{
    //Number 3
    public StringMethods()
    {
    }
    //Number 6
    public void unusualHello(String name)
    {
        System.out.println("Hello " + name + ", you dummy");
    }
    //Number 7
    public String stringRipper(String str)
    {
        String newString = new String();
        newString += str.charAt(0);
        newString += str.charAt(str.length()-1);
        return newString;
    }
    //Number 8
    public boolean evenFooBar(String s)
    {
        int counterfoo = 0;
        int counterbar = 0;
        for(int i = 0; i < s.length()-2; i++)
        {
          String altered = s.substring(i, i + 3);
          if(altered.equals("foo"))
            {
                counterfoo++;
            }
            else if(altered.equals("bar"))
            {
                counterbar++;
            }
        }
        if(counterfoo == counterbar)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //Number 10
    public int sumString(String str)
    {
        Scanner console = new Scanner(str);
        int sum = 0;
        while(console.hasNext())
        {
            if(console.hasNextInt())
            {
                sum += console.nextInt();
            }
            else
            {
                console.next();
            }
        }
        return sum;
    }
    
    //Number 11
    public String decode(String key, String code)
    {
        String newKey = new String();
        Scanner input = new Scanner(code);
        int index = 0;
        while(input.hasNext())
        {
            if(input.hasNextInt())
            {
                index = input.nextInt();
                newKey += key.charAt(index);
            }
            else
            {
                input.next();
            }
        }
        return newKey;
    }
    
    
}
