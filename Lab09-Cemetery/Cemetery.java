import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Cemetery
{
    private ArrayList<Tombstone> tombstones;
    
    public Cemetery(String fileName)
    {
        try
        {
            Scanner scanner = new Scanner(new File(fileName));
            tombstones = new ArrayList<>();
            while (scanner.hasNextLine())
            {
                Scanner input = new Scanner(scanner.nextLine());
                String name = "";
                String bdate = "";
                String age = "";
                String address = "";
                while (input.hasNextInt() != true)
                {
                    name += input.next() + " ";
                }
                for (int i = 1; i <= 3; i++)
                {
                    if (input.hasNext())
                    {
                        bdate += input.next();
                    }
                }
                if (input.hasNext())
                {
                    age += input.next();
                }
                while (input.hasNext())
                {
                    address += input.nextLine();
                }
                int newAge = parseAge(age);
                tombstones.add(new Tombstone (name, bdate, newAge, address));
            }
                       
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    /** 
	 * convert the ageString to a number of days; age can 
	 * take a variety of forms in the data file
	 */
	public static int parseAge(String ageString)
	{
		if (ageString.contains("d")) { //age supplied in days
			ageString = ageString.replaceAll("d", "");
			return Integer.parseInt(ageString);
		}
		
		int result = 0;
		
		boolean done = true;

		try { result = Integer.parseInt(ageString); } //is the String a whole number of years?
		
		catch (NumberFormatException n) { done = false; }
		
		if (done) //successfully parsed as an int, return value
			return 365 * result; //ignoring leap years
		
		double ageDouble = 0;
		
		done = true;
		
		try { ageDouble = Double.parseDouble(ageString); } //is the String a floating point number of years?
		
		catch (NumberFormatException n) { done = false; }
		
		if (done) { //successfully parse as a double, String doesn't contain any text
			return (int)(ageDouble * 365); //ignoring leap years, using 30 for days in a month
		}
		
		if (ageString.contains("w")) { //age is supplied in weeks, return appropriately
			ageString = ageString.replaceAll("w", "");
			return Integer.parseInt(ageString) * 7;
		}
		
		return 0;
	}
	
	public ArrayList<Tombstone> getTombstones()
	{
	    return tombstones;
	}
}
