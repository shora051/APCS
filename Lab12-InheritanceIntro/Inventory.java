import java.util.ArrayList;
public class Inventory
{
    private ArrayList<Vehicle> inventory;
    
    public Inventory()
    {
        inventory = new ArrayList<>();
    }
    public void addVehicle(Vehicle v)
    {
        inventory.add(v);
    }
    public void listInventory()
    {
        for(Vehicle v : inventory)
        {
            System.out.println(v.getInfo());
        }
        
    }
}
