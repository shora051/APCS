//Name: Sahil Hora
//Period: 2A
import java.util.*;
//Number 1
public class Tester
{
    //Number 2
    public static void main(String[] args)
    {
        //Number 3
        System.out.print("Hello again, world!");
        //Number 4
        //this is a comment!
        //Number 5
        int numApples = 20;
        //Number 6
        final int PRICE_OF_APPLES = 125;
        System.out.println();
        //Number 7
        System.out.println("The total for " + numApples + " apples:\n" + (numApples * PRICE_OF_APPLES) + " cents");
        //Number 8
        int cost = numApples * PRICE_OF_APPLES;
        if(cost >= 2000)
            System.out.println("Thank you valued customer");
        //Number 9
        for(int n = 10; n >= 1; n--)
        {
            System.out.print(n + " ");
        }
        System.out.println();
        //Number 10
        for(int n = 150; n <= 300; n+=3)
        {
            System.out.print(n + " ");
        }
        System.out.println();
        //Number 11
        int sum = 0;
        for(int n = 1; n <= 100; n++)
        {
            sum +=n;
        }
        System.out.println(sum);
        System.out.println();
        //Number 12
        System.out.println("Riddle answer: Silence");
        //Number 13
        Scanner console = new Scanner(System.in);
        System.out.print("Enter the value of the double num: ");
        //Number 14
        double num = console.nextDouble();
        System.out.println();
        //Number 15
        System.out.println("The square root of num is: " + Math.sqrt(num));
        //Number 16
        System.out.print("num^3 = " + Math.pow(num, 3));
        System.out.println();
        //Number 17
        System.out.println("Enter the values of num1 and num2");
        System.out.print("num1: ");
        int num1 = console.nextInt();
        System.out.println();
        System.out.print("num2: ");
        int num2 = console.nextInt();
        System.out.println();
        if((num2 - num1) > 10 || (num2 - num1) < -10)
        {
            System.out.println("Not within 10");
        }
        else 
        {
            System.out.println("Within 10");
        }
        //Number 18
        System.out.print("Enter an integer: ");
        int input = console.nextInt();
        System.out.println();
        int inputcount = 0;
        int sum2 = 0;
        while(input != 0)
        {
            sum2 += input;
            System.out.print("Enter an integer: ");
            input = console.nextInt();
            System.out.println();
            inputcount++;
        }
        System.out.println();
        System.out.println("sum = " + sum2);
        double avg = (double)sum2/(double)inputcount;
        System.out.println("average = " + avg);
        //Number 19
        double[] areas = new double[20];
        //Number 20
        areas[1] = 4.56;
        //Number 21
        int length = areas.length;
        //Number 22
        boolean[] array = {true, true, false, false, true};
        //Number 23
        System.out.println("Riddle Answer: Tombstone");
        //Number 29
        simpleMethod();
        System.out.println();
        
        int a = 3;
        int b = 5;
        System.out.println(sum(a,b));
        System.out.println();
        
        int n = 7;
        System.out.println(sumTon(n));
        System.out.println();
        
        int n2 = 6;
        triangle(n2);
        System.out.println();
        
        String s = "Heritage";
        System.out.println(altCaps(s));
        System.out.println();
        //Number 31
        Player one = new Player();
        Player two = new Player("LeBron James", 23);
        System.out.println(one.playerInfo());
        System.out.println(two.playerInfo());
        System.out.println();
        
        //Geo_location: Number 3
        GeoLocation obj1 = new GeoLocation(33.12396, -96.79873);
        System.out.println(obj1.toString());
        Place fisdAdmin = new Place("Frisco ISD Administration Building ", "5515 Ohio Dr, Frisco, TX 75035", obj1);
        System.out.println(fisdAdmin.toString());
        Place heritage= new Place("Heritage High School", "14040 Eldorado Pkwy, Frisco, TX 75035", 33.15385, -96.78778);
        System.out.println(heritage.toString());
        System.out.println(fisdAdmin.distanceTo(heritage));
    }
    //Number 24
    public static void simpleMethod()
    {
        System.out.println("This is a method");
    }
    //Number 25
    public static int sum(int a, int b)
    {
        return (a+b);
    }
    //Number 26
    public static int sumTon(int n)
    {
        int sum3 = 0;
        for(int i = 3; i <= n; i++)
        {
            if((i%3 == 0) || (i%5 == 0))
            {
                sum3 += i;
            }
        }
        return sum3;
    }
    //Number 27
    public static void triangle(int n)
    {
        for(int row = 1; row <= n; row++)
        {
            for(int col = 1; col <= row; col++)
            {
                System.out.print(row);
            }
            System.out.println();
        }
    }
    //Number 28
    public static String altCaps(String s)
    {
        String altered = ""; 
        for(int i = 0; i < s.length(); i++)
        {
            char inp = s.charAt(i);
            if(Character.isLetter(s.charAt(i)))
            {
                if(i % 2 == 0)
                {
                    altered += Character.toUpperCase(inp);
                }
                else
                {
                    altered += Character.toLowerCase(inp);
                }
            }
            else
            {
                altered += s.charAt(i);
            }
        }
        return altered;
    }
}

//Number 30
class Player
{
    private String name;
    private int number;
    public Player()
    {
        name = "Default";
        number = -1;
    }
    public Player(String n, int num)
    {
        name = n;
        number = num;
    }
    public String playerInfo()
    {
        return ("Player: " + name + ",# " + number);
    }
}

//Geo-Location: Number 1
class GeoLocation 
{
    //Part 1A
    private double latitude;
    private double longitude;
    
    //Part 1B
    public GeoLocation(double lati, double longi)
    {
        latitude = lati;
        longitude = longi;
    }
    public String toString()
    {
        return ("latitude: " + latitude + ", longitude: " + longitude);
    }
    public double distanceTo(GeoLocation other)
    {
        double lat1 = Math.toRadians(latitude);
        double long1 = Math.toRadians(longitude);
        double lat2 = Math.toRadians(other.latitude);
        double long2 = Math.toRadians(other.longitude);
        double theCos = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long1-long2);
        double arclength = Math.acos(theCos);
        return arclength * 3963.1676;
    }
}

//Geo-Location: Number 2
class Place 
{
    //Part 2A
    private String name;
    private String address;
    private GeoLocation location;
    
    //Part 2B
    public Place (String n, String a, double lati, double longi)
    {
        name = n;
        address = a;
        location = new GeoLocation(lati, longi);
    }
    public Place(String n, String a, GeoLocation loc)
    {
        name = n;
        address = a;
        location = loc;
    }
    public double distanceTo(Place other)
    {
        return location.distanceTo(other.location);
    }
    public String toString() 
    {
        return (name + " \n" + address + " \n" + location.toString());
    }
}
