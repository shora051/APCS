public class Motorcycle extends Vehicle
{
    private String streetLegal;
    
    public Motorcycle(String t, int y, double p, String sl)
    {
        super(t, y, p);
        streetLegal = sl;
    }
    @Override
    public String getInfo()
    {
        return super.getInfo() + ", " + streetLegal;
    }
}
