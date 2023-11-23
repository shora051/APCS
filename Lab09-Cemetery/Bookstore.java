import java.util.ArrayList;
import java.util.List;
public class Bookstore
{
    //number 15A
    private ArrayList<Book> inventory;
    
    //number 15B
    public Bookstore()
    {
        inventory = new ArrayList<>();
    }
    //number 15C
    public void addBook(Book b)
    {
        inventory.add(b);
    }
    //number 15D
    public int numBooks()
    {
        return inventory.size();
    }
    //number15E
    public Book getBook(int index)
    {
        if(index < 0 || index >= inventory.size())
        {
            return null;
        }
        else
        {
            return inventory.get(index);
        }
    }
}
