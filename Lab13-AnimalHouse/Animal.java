import java.util.ArrayList;
public class Animal
{
    private String name;
    private int birthyear;
    static private int currentYear = 2022;
    private Animal friend;
    private ArrayList<Toy> collection;
    
    public Animal(String n, int by)
    {
        name = n;
        birthyear = by;
        collection = new ArrayList<>();
    }
    public void addToy(Toy t)
    {
        collection.add(t);
    }
    public void setFriend(Animal a)
    {
        friend = a;
    }
    public int getAge()
    {
        return currentYear - birthyear;
    }
    public String toString()
    {
       String str = "Hello, I am " + name + ", I am " + this.getAge() + " years old";
       if(friend != null)
       {
           str += "\nI have a friend named: " + friend.name;
       }
       else
       {
           str += "\nI have no friends";
       }
       str += "\nI have the following toys: " + collection;
       return str;
    }
    public ArrayList<Toy> getToys()
    {
        return collection;
    }
}
