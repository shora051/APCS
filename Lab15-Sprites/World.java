import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Sprite> sprites;
    private int width;
    private int height;

    /**
     * construct a world 600x600
     */
    public World() {
        this(600, 600);
    }

    public World(int h, int w) {
        height = h;
        width = w;

        sprites = new ArrayList<>();

        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear(Color.BLACK);
        addSprites(1,1,50,50,"square.png");
    }
    private void addSprites(double x, double y, int width, int height, String image){
       Sprite s = new StationarySprite(x,y,width,height,image);
       Sprite t = new BouncingSprite(10,5,300,300,50,50,"square.png");
       Sprite c = new ControllableSprite(100,20,200,200,50,50,"CooperKupp.png");
       sprites.add(s);
       sprites.add(t);
       sprites.add(c);
    }
    /**
     * add a sprite to the simulation
     */
    public void add(Sprite sprite) {
        this.sprites.add(sprite);
    }

    /**
     * ask all sprites in simulation to update themselves
     */
    public void stepAll() {
        for (int i = 0; i < sprites.size(); i++)
            this.sprites.get(i).step(this);
    }

    /**
     * get the width of the world
     */
    public int getWidth() {
        return width;
    }

    /**
     * get the height of the world
     */
    public int getHeight() {
        return height;
    }

    /**
     * get the number of sprites in the simulation currently
     */
    public int getNumSprites() {
        return sprites.size();
    }

    /**
     * get the sprite at the given index
     */
    public Sprite getSprite(int index) {
        return sprites.get(index);
    }

    /**
     * run the simulation, i.e. the main game loop
     */
    public void run() {
        while (true) {
            this.stepAll();
            this.drawAll();

            StdDraw.show(10); //don't worry about warning if using Eclipse
            StdDraw.clear(Color.BLACK);
        }
    }


    /**
     * draw all sprites in the simulation at their current positions
     */
    public void drawAll() {
        for (Sprite sprite : this.sprites)
            sprite.draw();
    }

    public static void main(String[] args) {
        World world = new World(600, 600);
        world.add(new BouncingSprite(12,12,50,50,123,12,"circle.png"));
        world.add(new BouncingSprite(12,8,15,2,123,12,"circle.png"));
//        world.addSprites(20,20,10,10,"circle.png");
//        world.addSprites(300,300,50,50,"circle.png");
        world.run();
    }
}
