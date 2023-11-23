public class BouncingSprite extends MobileSprite {
    public BouncingSprite(double vx, double vy, double x, double y, int width, int height, String image) {
        super(vx, vy, x, y, width, height, image);
        bounce();
    }

    public void bounce() {
        if (nearEdge())
            super.bounced();

    }
    public void step(World world){
        super.step(world);
        if(nearEdge()){
            bounce();
        }
    }
    public boolean nearEdge(){
        if((getX() >= 600 || getY() >= 600||getX()<=0|| getY()<=0)){
            return true;
        }
        return false;
    }
    
}
