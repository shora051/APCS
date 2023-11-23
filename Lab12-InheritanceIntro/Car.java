public class Car extends Vehicle
{
    //private String type;
    //private int year;
    //private double price;
    private double mpg;
    
    public Car(String t, int y, double p, double gas)
    {
        super(t, y, p);
        mpg = gas;
    }
    public boolean greatMPG()
    {
        if(mpg >= 36)
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
        return super.getInfo() + ", " + mpg + " mpg, $" + super.getPrice();
    }
}
