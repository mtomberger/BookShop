package pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    private long id;
    private String title;
    private int pages;

    public Book() {

    }

    public Book(long id, String title, int pages) {
        this.id = id;
        this.title = title;
        this.pages = pages;
    }

    public void addPages(int pages)
    {
        this.pages += pages;
    }

    /*Getters and Setters*/

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
