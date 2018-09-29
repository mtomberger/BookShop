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
}
