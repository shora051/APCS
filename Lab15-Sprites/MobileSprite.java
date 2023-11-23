public class MobileSprite extends Sprite{
    private double vx;
    private double vy;
    public MobileSprite(double vx, double vy, double x, double y, int width, int height, String image){
        super(x,y,width,height,image);
        this.vx = vx;
        this.vy = vy;
    }
    public void step(World world){
        setX(getX()+vx);
        setY(getY()+vy);
    }
    public void bounced(){
        if(getX()<=0||getX()>=600){
            vx*=-1;
        }
        if (getY() <= 0||getY()>=600) {
            vy*=-1;
        }
    }
    public void setVY(double num)
    {
        vy = num;
    }
    public double getVY()
    {
        return vy;
    }
}
