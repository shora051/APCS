public class Truck extends Vehicle
{
    //private String type;
    //private int year;
    //private double price;
    private int towing;
    
    public Truck(String t, int y, double p, int lbs)
    {
        super(t,y,p);
        towing = lbs;
    }
    public boolean canTowBoat()
    {
        if(towing >= 2000)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public String getInfo()
    {
        return super.getInfo() + ", " + towing + " lbs. towing, $" + super.getPrice();
    }
}
