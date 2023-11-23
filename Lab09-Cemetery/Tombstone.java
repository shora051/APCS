//Cemetery Lab
public class Tombstone
{
    private String name;
    private String burialDate;
    private int age;
    private String address;
    
    public Tombstone(String n, String burDate, int a, String addy)
    {
        name = n;
        burialDate = burDate;
        age = a; 
        address = addy;
    }
    public String getName()
    {
        return name;
    }
    public String getBurialDate()
    {
        return burialDate;
    }
    public int getAge()
    {
        return age;
    }
    public String getAddress()
    {
        return address;
    }
}
