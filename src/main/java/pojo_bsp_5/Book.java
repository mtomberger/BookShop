package pojo_bsp_5;

import enums.BookGenre;
import pojo_bsp_4.Identifier;

import javax.persistence.*;

// Book im Beispiel 5
@Entity
@Table(name = "buch", schema = "bsp5")
public class Book {

    @Id
    private int id;
    private String title;
    private int pages;
    @Enumerated
    @Column(name = "category", columnDefinition = "smallint")
    private BookGenre genre;

    @ManyToOne
    @JoinColumn(name = "fk_buch_store")
    private Store store;

    public Book() {
    }

    public Book(String title, int pages, BookGenre genre) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.genre = genre;
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

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public void setId(int id) { this.id = id; }

    public Store getStore() { return store; }

    public void setStore(Store store) { this.store = store; }
}
