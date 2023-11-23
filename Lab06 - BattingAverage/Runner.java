import java.util.*;
import java.io.*;
public class Runner
{
    public static void main (String[] args)
    {
       try{
        Scanner input = new Scanner(new File("players.txt"));
        int p = input.nextInt();
        Team liberty = new Team(p);
        
        for(int i = 0; i < p; i++)
        {
            String name = input.next();
            int jerseyNum = input.nextInt();
            int numAtBat = input.nextInt();
            int numOfHits = input.nextInt();
            Player temp = new Player(name, jerseyNum, numAtBat, numOfHits);
            liberty.addPlayer(temp, i);
        }
        
        liberty.printTeamStats();
        input.close();
    }
    catch(FileNotFoundException e){
        e.printStackTrace();
    }
    
    
    
    
    }
}
