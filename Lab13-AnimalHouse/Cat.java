public class Cat extends Animal
{
    private int numLives;
    
    public Cat(String n, int by, int nL)
    {
        super(n, by);
        numLives = nL;
    }
    public Cat(String n, int by)
    {
        this(n, by, 9);
    }
    @Override
    public String toString()
    {
        return super.toString() + "\n I have " + numLives + " lives";
    }
}
