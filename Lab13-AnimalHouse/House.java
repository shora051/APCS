import java.util.ArrayList;
public class House
{
    private ArrayList<Animal> animals;
    
    public House()
    {
        animals = new ArrayList<>();
    }
    public void printAnimals()
    {
        for(Animal a : animals)
        {
            System.out.println(a.toString());
        }
    }
    public void add(Animal a)
    {
        animals.add(a);
    }
    public void cleanHouse()
    {
        for(Animal a : animals)
        {
            ArrayList<Toy> toys = a.getToys();
            for(int i = 0; i < toys.size(); i++)
        {
            for(int j = 0; j < toys.size(); j++)
            {
                if(i != j)
                {
                    if(toys.get(i).equals(toys.get(j)))
                    {
                        toys.remove(j);
                        j--;
                    }
                }
            }
        }
        }
        
    }
}
