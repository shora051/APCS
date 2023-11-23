import java.util.*;

public class HorsingAround
{
    public static int totalWidth(ArrayList<Animal> animals)
    {
        int totalWidth = 0;
        for(int i = 0; i < animals.size(); i++)
        {
            totalWidth += animals.get(i).getWidth();
        }
        return totalWidth;
    }

    public static Animal tallestAnimal(ArrayList<Animal> animals)
    {
        int height = animals.get(0).getHeight();
        Animal tallest = animals.get(0);
        for(int i = 1; i < animals.size(); i++)
        {
            if(animals.get(i).getHeight() > height)
            {
                height = animals.get(i).getHeight();
                tallest = animals.get(i);
            }
        }
        return tallest;
    }

    public static int countYourChickens(ArrayList<Animal> animals)
    {
        int count = 0;
        for(int i = 0; i < animals.size(); i++)
        {
            if(animals.get(i).getName().equals("chicken"))
            {
                count++;
            }
        }
        return count++;
    }

    public static ArrayList<String> inventory(ArrayList<Animal> animals)
    {
        ArrayList<String> inventory = new ArrayList<String>();
        for(int i = 0; i < animals.size(); i++)
        {
            inventory.add(animals.get(i).getName());
        }
        return inventory;
    }

    public static void pestControl(ArrayList<Animal> animals)
    {
        for(int i = animals.size()-1; i >= 0; i--)
        {
            if(animals.get(i).getName().equals("mouse"))
            {
                animals.remove(i); 
            }
        }
    }

    public static void horsingAround(ArrayList<Animal> animals)
    {
        for(int i = 0; i < animals.size(); i++)
        {
            if(i % 2 == 0)
            {
                animals.add(i, new Animal("horse"));
            }
        }
    }

    public static void feelingSheepish(ArrayList<Animal> animals)
    {
        for(int i = 0; i < animals.size(); i++)
        {
            if(animals.get(i).getName().equals("sheep") && (i != 0 && i != animals.size()-1))
            {
                animals.set(i - 1, new Animal("sheep"));
                animals.set(i + 1, new Animal("sheep"));
                i++;
            }
            if((animals.get(i).getName().equals("sheep")) && (i == 0))
            {
                animals.set(i+1, new Animal("sheep"));
            }
            if((animals.get(i).getName().equals("sheep")) && (i == animals.size()-1))
            {
                animals.set(i-1, new Animal("sheep"));
            }
        }
    }
    
    public static void selectionSort(ArrayList<Animal> animals)
    {
        for(int i = 0; i < animals.size(); i++)
        {
            Animal min = animals.get(i);
            int minIndex = i;
            for(int j = i + 1; j < animals.size(); j++)
            {
                Animal f = animals.get(j);
                if(f.getHeight() > min.getHeight())
                {
                    min = f;
                    minIndex = j;
                }
            }
            Animal temp = animals.get(i);
            animals.set(i, min);
            animals.set(minIndex, temp);
        }
        
    }
}