import java.util.ArrayList;
import java.util.List;
public class ArrayListProbs
{
    //number 3
    public void makeListAndPrint(int num, int limit)
    {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < num; i++)
        {
            list.add((int)(Math.random()*limit) + 1);
        }
        System.out.println(list);
    }
    //number 4
    public ArrayList<Integer> addOne(ArrayList<Integer> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            list.set(i, list.get(i)+1);
        }
        return list;
    }
    //number 5
    public ArrayList<Integer> minToFront(ArrayList<Integer> list)
    {
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i) < min)
            {
                min = list.get(i);
            }
        }
        list.add(0, min);
        return list;
    }
    //number 6
    public ArrayList<String> removeDupes(ArrayList<String> list)
    {
        for(int i = list.size()-1; i > 0; i--)
        {
            if(list.get(i).equals(list.get(i-1)))
            {
                list.remove(i);
            }
        }
        return list;
    }
    //number 7
    public ArrayList<Integer> swapPairs(ArrayList<Integer> list)
    {
        int temp = 0;
        for(int i = 0; i < list.size()-1; i +=2)
        {
            temp = list.get(i);
            list.set(i, list.get(i+1));
            list.set(i+1, temp);
        }
        return list;
    }
    //number 8
    public ArrayList<String> removeLenN(ArrayList<String> list, int n)
    {
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).length() == n)
            {
                list.remove(i);
            }
        }
        return list;
    }
    //number 11
    public int dumbestPerson(ArrayList<Person> list)
    {
        int dumbest = Integer.MAX_VALUE;
        int indexOfDumb = 0;
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getIQ() < dumbest)
            {
                dumbest = list.get(i).getIQ();
                indexOfDumb = i;
            }
        }
        return indexOfDumb;
    }
    //number 13
    public Book highestPricedBook(ArrayList<Book> list)
    {
        double highestPrice = Double.MIN_VALUE;
        int index = 0;
        for(int i = 0; i < list.size(); i++)
        {
            if(highestPrice < list.get(i).getPrice())
            {
                highestPrice = list.get(i).getPrice();
                index = i;
            }
        }
        return list.get(index);   
    }
    //number 14
    public ArrayList<Book> banBook(ArrayList<Book> list, Book book)
    {
        String title = book.getTitle();
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i).getTitle().equals(title))
            {
                list.remove(i);
            }
        }
        return list;
    }
    //number 16
    public double bookstoreValue(Bookstore store)
    {
        double total = 0;
        for(int i = 0; i < store.numBooks(); i++)
        {
            total += store.getBook(i).getPrice();
        }
        return total;
    }
}
