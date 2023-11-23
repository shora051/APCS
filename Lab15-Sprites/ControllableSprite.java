import java.awt.event.KeyEvent;

public class ControllableSprite extends MobileSprite {
    public ControllableSprite(double vx, double vy, double x, double y, int width, int height, String image) {
        super(vx, vy, x, y, width, height, image);
    }

    public void step(World world) 
    {
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                if(getY() + 5 <= 600)
                {
                    setY(getY() + 5);
                }
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                if(getY() - 5 >= 0){
                setY(getY() - 5);
            }
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                if(getX() + 5 <= 600) {
                setX(getX() + 5);
            }
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                 if(getX() - 5 >= 0){
                setX(getX() - 5);
            }
            }
    }
}

