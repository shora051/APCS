public class Dog extends Animal
{
    private boolean goodBoy;
    
    public Dog(String n, int by, boolean good)
    {
        super(n, by);
        goodBoy = good;
    }
    @Override
    public String toString()
    {
        return super.toString() + "\n I am a good boy (" + goodBoy + ")";
    }
}
