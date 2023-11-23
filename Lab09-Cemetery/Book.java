public class Book
{
    //12A
    private String title;
    private String author;
    private double price;
    
    //12B
    public Book(String bTitle, String bAuthor, double p)
    {
        title = bTitle;
        author = bAuthor;
        price = p;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public double getPrice()
    {
        return price;
    }
    
    //12C
    public String toString()
    {
        return title + ", by " + author + ". Cost: $" + price;
    }
}
