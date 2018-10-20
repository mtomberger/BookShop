package pojo_bsp_6;

import javax.persistence.*;
import java.util.ArrayList;

// Book im Beispiel 6
@Entity
@Table(name = "buch", schema = "bsp6")
public class Book {
    @Id
    private int id;
    private String title;
    private int pages;
    @ManyToMany(mappedBy = "books")
    private ArrayList<Book> publisher = new ArrayList();

    public Book() {
    }

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    public void addPublisher(Publisher p){
        if(p!=null){
            publisher.add(p);
        }
    }
    public void addPages(int pages)
    {
        this.pages += pages;
    }

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

    public void setId(int id) { this.id = id; }
}

