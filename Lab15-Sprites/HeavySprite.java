public class HeavySprite extends BouncingSprite
{
    public HeavySprite(double x, double y, int width, int height, String image, double velX, double velY)
    {
        super(x,y,velX, velY,width,height,image);
    }
    
    @Override
    public void step(World world)
    {
        super.step(world);
        super.setVY(super.getVY()-0.1);
    }
}
