package dev.uublabs.services.Model;

/**
 * Created by Admin on 11/22/2017.
 */

public class Book
{
    private String title;
    private String genre;

    public Book()
    {

    }

    public Book(String title, String genre)
    {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
