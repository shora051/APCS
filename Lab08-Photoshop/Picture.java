import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import java.util.Random;
/**
 * A class that represents a picture made up of a rectangle of {@link Pixel}s
 */
public class Picture {

    /** The 2D array of pixels that comprise this picture */
    private Pixel[][] pixels;

    /**
     * Creates a Picture from an image file in the "images" directory
     * @param picture The name of the file to load
     */
    public Picture(String picture) {
        File file = new File("./images/"+picture);
        BufferedImage image;
        if (!file.exists()) throw new RuntimeException("No picture at the location "+file.getPath()+"!");
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        pixels = new Pixel[image.getHeight()][image.getWidth()];
        for (int y = 0; y<pixels.length; y++) {
            for (int x = 0; x<pixels[y].length; x++) {
                int rgb = image.getRGB(x, y);
                /*
                 * For the curious - BufferedImage saves an image's RGB info into a hexadecimal integer
                 * The below extracts the individual values using bit-shifting and bit-wise ANDing with all 1's
                 */
                pixels[y][x] = new Pixel((rgb>>16)&0xff, (rgb>>8)&0xff, rgb&0xff);
            }
        }
    }

    /**
     * Creates a solid-color Picture of a given color, width, and height
     * @param red The red value of the color
     * @param green The green value of the color
     * @param blue The blue value of the color
     * @param height The height of the Picture
     * @param width The width of the Picture
     */
    public Picture(int red, int green, int blue, int height, int width) {
        pixels = new Pixel[height][width];
        for (int y = 0; y<pixels.length; y++) {
            for (int x = 0; x<pixels[y].length; x++) {
                pixels[y][x] = new Pixel(red, green, blue);
            }
        }
    }

    /**
     * Creates a solid white Picture of a given width and height
     * @param color The {@link Color} of the Picture
     * @param height The height of the Picture
     * @param width The width of the Picture
     */
    public Picture(int height, int width) {
        this(Color.WHITE, height, width);
    }

    /**
     * Creates a solid-color Picture of a given color, width, and height
     * @param color The {@link Color} of the Picture
     * @param width The width of the Picture
     * @param height The height of the Picture
     */
    public Picture(Color color, int height, int width) {
        this(color.getRed(), color.getGreen(), color.getBlue(), height, width);
    }

    /**
     * Creates a Picture based off of an existing {@link Pixel} 2D array
     * @param pixels A rectangular 2D array of {@link Pixel}s. Must be at least 1x1
     */
    public Picture(Pixel[][] pixels) {
        if (pixels.length==0 || pixels[0].length==0) throw new RuntimeException("Can't have an empty image!");
        int width = pixels[0].length;
        for (int i = 0; i<pixels.length; i++) if (pixels[i].length!=width)
            throw new RuntimeException("Pictures must be rectangles. pixels[0].length!=pixels["+i+"].length!");
        this.pixels = new Pixel[pixels.length][width];
        for (int i = 0; i<pixels.length; i++) {
            for (int j = 0; j<pixels[i].length; j++) {
                this.pixels[i][j] = new Pixel(pixels[i][j].getColor());
            }
        }
    }

    /**
     * Creates a Picture based off of an existing Picture
     * @param picture The Picture to copy
     */
    public Picture(Picture picture) {
        this(picture.pixels);
    }

    /**
     * Gets the width of the Picture
     * @return The width of the Picture
     */
    public int getWidth() {
        return pixels[0].length;
    }

    /**
     * Gets the height of the Picture
     * @return The height of the Picture
     */
    public int getHeight() {
        return pixels.length;
    }

    /**
     * Gets the {@link Pixel} at a given coordinate
     * @param x The x location of the {@link Pixel}
     * @param y The y location of the {@link Pixel}
     * @return The {@link Pixel} at the given location
     */
    public Pixel getPixel(int x, int y) {
        if (x>=getWidth() || y>=getHeight() || x<0 || y<0) throw new RuntimeException("No pixel at ("+x+", "+y+")");
        return pixels[y][x];
    }

    /**
     * Sets the {@link Pixel} at a given coordinate
     * @param x The x location of the {@link Pixel}
     * @param y The y location of the {@link Pixel}
     * @param pixel The new {@link Pixel}
     */
    public void setPixel(int x, int y, Pixel pixel) {
        if (x>=getWidth() || y>=getHeight() || x<0 || y<0) throw new RuntimeException("No pixel at ("+x+", "+y+")");
        if (pixel==null) throw new NullPointerException("Pixel is null"); //guard is required because pixel's value isn't used in this method
        pixels[y][x] = pixel;
    }

    /**
     * Opens a {@link PictureViewer} to view this Picture
     * @return the {@link PictureViewer} viewing the Picture
     */
    public PictureViewer view() {
        return new PictureViewer(this);
    }

    /**
     * Save the image on disk as a JPEG
     * Call programmatically on a Picture object, it will prompt you to choose a save location
     * In the save dialogue window, specify the file AND extension (e.g. "lilies.jpg")
     * Extension must be .jpg as ImageIO is expecting to write a jpeg
     */
    public void save()
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
        
        BufferedImage image = new BufferedImage(this.pixels[0].length, this.pixels.length, BufferedImage.TYPE_INT_RGB);

        for (int r = 0; r < this.pixels.length; r++)
            for (int c = 0; c < this.pixels[0].length; c++)
                image.setRGB(c, r, this.pixels[r][c].getColor().getRGB());

        //user's Desktop will be default directory location
        JFileChooser chooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");

        chooser.setDialogTitle("Select picture save location / file name");

        File file = null;

        int choice = chooser.showSaveDialog(null);

        if (choice == JFileChooser.APPROVE_OPTION)
            file = chooser.getSelectedFile();

        //append extension if user didn't read save instructions
        if (!file.getName().endsWith(".jpg") && !file.getName().endsWith(".JPG") && !file.getName().endsWith(".jpeg") && !file.getName().endsWith(".JPEG"))
            file = new File(file.getAbsolutePath() + ".jpg");
        
        try {
            ImageIO.write(image, "jpg", file);
            System.out.println("File created at " + file.getAbsolutePath());
        }
        catch (IOException e) {
            System.out.println("Can't write to location: " + file.toString());
        }
        catch (NullPointerException|IllegalArgumentException e) {
            System.out.println("Invalid directory choice");
        }
    }
    
    /** return a copy of the reference to the 2D array of pixels that comprise this picture */
    public Pixel[][] getPixels() {
        return pixels;
    }


    /********************************************************
     *************** STUDENT METHODS BELOW ******************
     ********************************************************/

    /** remove all blue tint from a picture */
    public void zeroBlue()
    {
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                pixels[r][c].setBlue(0);
            }
        }
    }

    /** remove everything BUT blue tint from a picture */
    public void keepOnlyBlue()
    {
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                pixels[r][c].setGreen(0);
                pixels[r][c].setRed(0);
            }
        }
    }

    /** invert a picture's colors */
    public void negate()
    {
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                pixels[r][c].setRed(255 - pixels[r][c].getRed());
                pixels[r][c].setGreen(255 - pixels[r][c].getGreen());
                pixels[r][c].setBlue(255 - pixels[r][c].getBlue());
            }
        }
    }

    /** simulate the over-exposure of a picture in film processing */
    public void solarize(int threshold)
    {
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                if(pixels[r][c].getRed() < threshold)
                {
                    pixels[r][c].setRed(255 - pixels[r][c].getRed());
                }
                if(pixels[r][c].getGreen() < threshold)
                {
                    pixels[r][c].setGreen(255 - pixels[r][c].getGreen());
                }
                if(pixels[r][c].getBlue() < threshold)
                {
                    pixels[r][c].setBlue(255 - pixels[r][c].getBlue());
                }
            }
        }
    }

    /** convert an image to grayscale */
    public void grayscale()
    {
        int average = 0;
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                average = (pixels[r][c].getRed() + pixels[r][c].getBlue() + pixels[r][c].getGreen())/3;
                pixels[r][c].setRed(average);
                pixels[r][c].setGreen(average);
                pixels[r][c].setBlue(average);
            }
            average = 0;
        }
    }

    /** change the tint of the picture by the supplied coefficients */
    public void tint(double red, double blue, double green)
    {
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                if(((int)(pixels[r][c].getRed() * red)) <= 255)
                {
                    pixels[r][c].setRed((int)(pixels[r][c].getRed() * red));
                }
                if(((int)(pixels[r][c].getBlue() * blue)) <= 255)
                {
                    pixels[r][c].setBlue((int)(pixels[r][c].getBlue() * blue));
                }
                if(((int)(pixels[r][c].getGreen() * green)) <= 255)
                {
                    pixels[r][c].setGreen((int)(pixels[r][c].getGreen() * green));
                }
            }
        }
    }
    
    /** reduces the number of colors in an image to create a "graphic poster" effect */
    public void posterize(int span)
    {
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                pixels[r][c].setRed(pixels[r][c].getRed()/span * span);
                pixels[r][c].setBlue(pixels[r][c].getBlue()/span * span);
                pixels[r][c].setGreen(pixels[r][c].getGreen()/span * span);
            }
        }
    }

    /** mirror an image about a vertical midline, left to right */
    public void mirrorVertical()
    {
        Pixel leftPixel  = null;
        Pixel rightPixel = null;

        int width = pixels[0].length;

        for (int r = 0; r < pixels.length; r++)
        {
            for (int c = 0; c < width / 2; c++)
            {
                leftPixel  = pixels[r][c];
                rightPixel = pixels[r][(width - 1) - c];

                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /** mirror about a vertical midline, right to left */
    public void mirrorRightToLeft()
    {
        Pixel leftPixel2  = null;
        Pixel rightPixel2 = null;

        int width = pixels[0].length;

        for (int r = 0; r < pixels.length; r++)
        {
            for (int c = 0; c < width / 2; c++)
            {
                rightPixel2  = pixels[r][c];
                leftPixel2 = pixels[r][(width - 1) - c];

                rightPixel2.setColor(leftPixel2.getColor());
            }
        }
    }

    /** mirror about a horizontal midline, top to bottom */
    public void mirrorHorizontal()
    {
        Pixel upPixel = null;
        Pixel downPixel = null;
        
        int length = pixels.length;
        
        for(int r = 0; r < length / 2; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                upPixel = pixels[(length-1)-r][c];
                downPixel = pixels[r][c];
                
                downPixel.setColor(upPixel.getColor());
            }
        }
    }

    /** flip an image upside down about its bottom edge */
    public void verticalFlip()
    {       
        Pixel normPixel = null;
        Pixel newPixel = null;
        Pixel tempPixel = null;
        int width3 = pixels[0].length;
        
        for(int c = 0; c < width3; c++)
        {
            for(int r = 0; r < pixels.length/2; r++)
            {
                normPixel = pixels[r][c];
                newPixel = pixels[(pixels.length -1) - r][c];
                tempPixel = new Pixel(pixels[(pixels.length - 1)-r][c].getColor());
                newPixel.setColor(normPixel.getColor());
                normPixel.setColor(tempPixel.getColor());
            }
        }
    }

    /** fix roof on greek temple */
    public void fixRoof()
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for(int r = 0; r < 145; r++)
        {
            for(int c = 0; c < width / 2; c++)
            {
                leftPixel = pixels[r][c];
                rightPixel = pixels[r][(width-1) - c];
                
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /** detect and mark edges in an image */
    public void edgeDetection(int dist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Color rightColor = null;
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length-1; c++)
            {
                leftPixel = pixels[r][c];
                rightPixel = pixels[r][c+1];
                rightColor = rightPixel.getColor();
                if(leftPixel.colorDistance(rightColor) > dist)
                {
                    leftPixel.setColor(Color.BLACK);
                }
                else
                {
                    leftPixel.setColor(Color.WHITE);
                }
            }
        }
        Pixel upPixel = null; 
        Pixel downPixel = null;
        Color downColor = null;
        for(int c = 0; c < pixels[0].length; c++)
        {
            for(int r = 0; r < pixels.length-1; r++)
            {
                upPixel = pixels[r][c];
                downPixel = pixels[r+1][c];
                downColor = downPixel.getColor();
                if(upPixel.colorDistance(downColor) > dist)
                {
                    upPixel.setColor(Color.BLACK);
                }
                else
                {
                    upPixel.setColor(Color.WHITE);
                }
            }
        }
    }


    /** copy another picture's pixels into this picture, if a color is within dist of param Color */
    public void chromakey(Picture other, Color color, int dist)
    {
        Pixel picOne = null;
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                picOne = pixels[r][c];
                if(picOne.colorDistance(color) <= dist)
                {
                    pixels[r][c].setColor(other.pixels[r][c].getColor());
                }
            }
        }
    }

    /** steganography encode (hide the message in msg in this picture) */
    public void encode(Picture msg)
    {
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                if((pixels[r][c].getRed() % 2) != 0)
                {
                    pixels[r][c].setRed(pixels[r][c].getRed() - 1);
                }
            }
        }
        Pixel nextPixel = null;
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length-1; c++)
            {
               nextPixel = msg.pixels[r][c+1];
               if(nextPixel.colorDistance(Color.BLACK) <= 50)
               {
                   pixels[r][c].setRed(pixels[r][c].getRed() + 1);
               }
            }
        }
    }

    /** steganography decode (return a new Picture containing the message hidden in this picture) */
    public Picture decode()
    {
        //TODO
        Picture hiddenMessage = new Picture(pixels.length, pixels[0].length);
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                if(pixels[r][c].getRed() % 2 != 0)
                {
                    hiddenMessage.pixels[r][c] = pixels[r][c];
                    hiddenMessage.pixels[r][c].setColor(Color.BLACK);
                }
            }
        }
        return hiddenMessage;
    }

    /** perform a simple blur using the colors of neighboring pixels */
    public Picture simpleBlur()
    {
        Picture smooth = new Picture(pixels.length, pixels[0].length);
        Pixel upPixel = null; 
        Pixel downPixel = null;
        Pixel rightPixel = null;
        Pixel leftPixel = null;
        Pixel current = null;
        int[][] redValue = new int[pixels.length][pixels[0].length];
        int[][] blueValue = new int[pixels.length][pixels[0].length];
        int[][] greenValue = new int[pixels.length][pixels[0].length];
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length; c++)
            {
                current = pixels[r][c];
                redValue[r][c] += current.getRed();
                blueValue[r][c] += current.getBlue();
                greenValue[r][c] += current.getGreen();
            }
        }
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length - 1; c++)
            {
                rightPixel = pixels[r][c+1];
                redValue[r][c] += rightPixel.getRed();
                blueValue[r][c] += rightPixel.getBlue();
                greenValue[r][c] += rightPixel.getGreen();
            }
        }
        for(int r = 0; r < pixels.length -1; r++)
        {
            for(int c = 0; c < pixels[r].length ; c++)
            {
                downPixel = pixels[r+1][c];
                redValue[r][c] += downPixel.getRed();
                blueValue[r][c] += downPixel.getBlue();
                greenValue[r][c] += downPixel.getGreen();
            }
        }
        for(int r = pixels.length -1; r > 0; r--)
        {
            for(int c = 0; c < pixels[0].length ; c++)
            {
                upPixel = pixels[r-1][c];
                redValue[r][c] += upPixel.getRed();
                blueValue[r][c] += upPixel.getBlue();
                greenValue[r][c] += upPixel.getGreen();
            }
        }
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = pixels[r].length -1; c > 0 ; c--)
            {
                leftPixel = pixels[r][c-1];
                redValue[r][c] += leftPixel.getRed();
                blueValue[r][c] += leftPixel.getBlue();
                greenValue[r][c] += leftPixel.getGreen();
            }
        }
        for(int r = 0; r < pixels.length; r++)
        {
            for(int c = 0; c < pixels[r].length ; c++)
            {
                redValue[r][c] /= 5;
                blueValue[r][c] /= 5;
                greenValue[r][c] /= 5;
                smooth.pixels[r][c].setRed(redValue[r][c]);
                smooth.pixels[r][c].setBlue(blueValue[r][c]);
                smooth.pixels[r][c].setGreen(greenValue[r][c]);
            }
        }
        return smooth;
    }

    /** perform a blur using the colors of pixels within radius of current pixel */
    public Picture blur(int radius)
    {
        Picture blurred = new Picture(pixels.length, pixels[0].length);
        for (int r = 0; r < pixels.length - radius; r++)
        {
            for (int c = 0; c < pixels[r].length - radius; c++)
            {
                int redSum = 0;
                int greenSum = 0;
                int blueSum = 0;
                int radCount = 1;
                int redTrack = 0;
                int blueTrack = 0;
                int greenTrack = 0;
                if (r == 0 && c == 0)
                {
                    while (radCount <= radius)
                    {
                        redSum += pixels[r][c + radCount].getRed();
                        redTrack++;
                        greenSum += pixels[r][c + radCount].getGreen();
                        greenTrack++;
                        blueSum += pixels[r][c + radCount].getBlue();
                        blueTrack++;
                        radCount++;
                    }
                    radCount = 1;
                    while (radCount <= radius)
                    {
                        redSum += pixels[r + radCount][c].getRed();
                        redTrack++;
                        greenSum += pixels[r + radCount][c].getGreen();
                        greenTrack++;
                        blueSum += pixels[r + radCount][c].getBlue();
                        blueTrack++;
                        radCount++;
                    }
                    radCount = 1;
                    while (radCount <= radius)
                    {
                        redSum += pixels[r + radCount][c + radCount].getRed();
                        redTrack++;
                        greenSum += pixels[r + radCount][c + radCount].getGreen();
                        greenTrack++;
                        blueSum += pixels[r + radCount][c + radCount].getBlue();
                        blueTrack++;
                        radCount++;
                    }
                   
                    blurred.pixels[r][c] = new Pixel(redSum/redTrack,greenSum/greenTrack,blueSum/blueTrack);
                }
                else if (c == 0)
                {
                    for (int i = 1; i <= radius && r - i >= 0; i++)
                    {
                        redSum += pixels[r-i][c].getRed();
                        redTrack++;
                        greenSum += pixels[r-i][c].getGreen();
                        greenTrack++;
                        blueSum += pixels[r-i][c].getBlue();
                        blueTrack++;
                        redSum += pixels[r+i][c].getRed();
                        redTrack++;
                        greenSum += pixels[r+i][c].getGreen();
                        greenTrack++;
                        blueSum += pixels[r+i][c].getBlue();
                        blueTrack++;
                    }
                    for (int i = 1; i <= radius; i++)
                    {
                        redSum += pixels[r][c + i].getRed();
                        redTrack++;
                        greenSum += pixels[r][c + i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r][c + i].getBlue();
                        blueTrack++;
                    }
                    for (int i = 1; i <= radius && r - i >= 0; i++)
                    {
                        redSum += pixels[r-i][c + i].getRed();
                        redTrack++;
                        greenSum += pixels[r-i][c + i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r-i][c + i].getBlue();
                        blueTrack++;
                        redSum += pixels[r+i][c + i].getRed();
                        redTrack++;
                        greenSum += pixels[r+i][c + i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r+i][c + i].getBlue();
                        blueTrack++;
                    }
                    blurred.pixels[r][c] = new Pixel(redSum/redTrack,greenSum/greenTrack,blueSum/blueTrack);
                }
                else if ( r== 0)
                {
                    for (int i = 1; i <= radius && c - i >= 0; i++)
                    {
                        redSum += pixels[r][c - i].getRed();
                        redTrack++;
                        greenSum += pixels[r][c - i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r][c - i].getBlue();
                        blueTrack++;
                        redSum += pixels[r][c + i].getRed();
                        redTrack++;
                        greenSum += pixels[r][c + i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r][c + i].getBlue();
                        blueTrack++;
                    }
                    for (int i = 1; i <= radius; i++)
                    {
                        redSum += pixels[r+i][c].getRed();
                        redTrack++;
                        greenSum += pixels[r+i][c].getGreen();
                        greenTrack++;
                        blueSum += pixels[r+i][c].getBlue();
                        blueTrack++;
                    }
                    for (int i = 1; i <= radius && c - i >= 0 && r - 1 >= 0; i++)
                    {
                        redSum += pixels[r-i][c + i].getRed();
                        redTrack++;
                        greenSum += pixels[r-i][c + i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r-i][c + i].getBlue();
                        blueTrack++;
                        redSum += pixels[r+i][c + i].getRed();
                        redTrack++;
                        greenSum += pixels[r+i][c + i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r+i][c + i].getBlue();
                        blueTrack++;
                    }
                    blurred.pixels[r][c] = new Pixel(redSum/redTrack,greenSum/greenTrack,blueSum/blueTrack);
                }
                else
                {
                    for (int i = 1; i <= radius && r - i >= 0; i++)
                    {
                        redSum += pixels[r-i][c].getRed();
                        redTrack++;
                        greenSum += pixels[r-i][c].getGreen();
                        greenTrack++;
                        blueSum += pixels[r-i][c].getBlue();
                        blueTrack++;
                        redSum += pixels[r+i][c].getRed();
                        redTrack++;
                        greenSum += pixels[r+i][c].getGreen();
                        greenTrack++;
                        blueSum += pixels[r+i][c].getBlue();
                        blueTrack++;
                    }
                    for (int i = 1; i <= radius && c - i >= 0; i++)
                    {
                        redSum += pixels[r][c - i].getRed();
                        redTrack++;
                        greenSum += pixels[r][c - i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r][c - i].getBlue();
                        blueTrack++;
                        redSum += pixels[r][c + i].getRed();
                        redTrack++;
                        greenSum += pixels[r][c + i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r][c + i].getBlue();
                        blueTrack++;
                    }
                    for (int i = 1; i <= radius && c - i >= 0 && r - i >= 0; i++)
                    {
                        redSum += pixels[r-i][c + i].getRed();
                        redTrack++;
                        greenSum += pixels[r-i][c + i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r-i][c + i].getBlue();
                        blueTrack++;
                        redSum += pixels[r+i][c + i].getRed();
                        redTrack++;
                        greenSum += pixels[r+i][c + i].getGreen();
                        greenTrack++;
                        blueSum += pixels[r+i][c + i].getBlue();
                        blueTrack++;
                    }
                    blurred.pixels[r][c] = new Pixel(redSum/redTrack,greenSum/greenTrack,blueSum/blueTrack);
                }
            }
        }

        return blurred;
    }
      
    /**
     * Simulate looking at an image through a pane of glass
     * @param dist the "radius" of the neighboring pixels to use
     * @return a new Picture with the glass filter applied
     */
    public Picture glassFilter(int dist) 
    {
          //TODO
        for (int r = 0; r < getHeight(); r++) {
            for (int c = 0; c < getWidth(); c++) {
                Random rng = new Random();

                while (true) {
                    int x = rng.nextInt(dist * 2) - dist;
                    int y = rng.nextInt(dist * 2) - dist;

                    if (c + x < getWidth() && c + x >= 0 && r + y < getHeight() && r + y >= 0) {
                        pixels[r][c] = pixels[r + y][c + x];
                        break;
                    }
                }
            }
        }
        Picture p = new Picture(getHeight(), getWidth());
        p.pixels = pixels;
        return p; 
    }
    public static int random(int a, int b)
    {
        return a + (int)(Math.random() * (b-a+1));
    }
}
