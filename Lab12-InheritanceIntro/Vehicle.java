public class Vehicle
{
    private String type;
    private int year;
    private double price;
    
    public Vehicle(String t, int y, double p)
    {
        type = t;
        year = y;
        price = p;
    }
    public String getType()
    {
        return type;
    }
    public int getYear()
    {
        return year;
    }
    public double getPrice()
    {
        return price;
    }
    public String getInfo()
    {
        return year + " " + type;
    }
}
