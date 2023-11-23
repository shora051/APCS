import java.util.ArrayList;
import java.util.List;
public class TestCemetery
{
    public static void main(String[] args)
    {
        Cemetery stMaryMagdalene = new Cemetery("cemetery.txt");
        ArrayList<Tombstone> list = stMaryMagdalene.getTombstones();
        double sum = 0;
        int numOfPeople = 0;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getAddress().indexOf("Little Carter Lane") >= 0)
            {
                double age = (list.get(i).getAge()); 
                sum += age;
                numOfPeople++;
            }
        }
        sum /= 365;
        double avg = Math.round((sum/numOfPeople) * 10)/10.0;
        System.out.println("Average age of people that lived on Little Carter Lane >>> " + avg);
    }
}
