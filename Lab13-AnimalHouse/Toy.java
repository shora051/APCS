public class Toy
{
    private String name;
    
    public Toy(String n)
    {
        name = n;
    }
    @Override
    public String toString()
    {
        return "A " + name;
    }
    @Override 
    public boolean equals(Object obj)
    {
        Toy other = (Toy) obj;
        return this.name.equals(other.name);
    }
}
