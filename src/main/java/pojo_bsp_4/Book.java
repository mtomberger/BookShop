package pojo_bsp_4;

import enums.BookGenre;

import javax.persistence.*;

// Book im Beispiel 4
@Entity
@Table(name = "buch", schema = "bsp4")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int pages;
    @Enumerated
    @Column(name = "category", columnDefinition = "smallint")
    private BookGenre genre;

    @OneToOne
    private Identifier identifier;

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

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }
}

