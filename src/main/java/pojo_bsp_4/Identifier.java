package pojo_bsp_4;

import pojo_bsp_4.Book;

import javax.persistence.*;

// Identifier in Beispiel 4
@Entity
@Table(name = "identifier", schema = "bsp4")
public class Identifier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String isbn;

    private String qrCode;

    @OneToOne
    private Book book;

    public Identifier() {

    }

    public Identifier(String isbn, String qrCode) {
        this.isbn = isbn;
        this.qrCode = qrCode;
    }

    public int getId() { return id; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getQrCode() { return qrCode; }

    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public Book getBook() { return book; }

    public void setBook(Book book) { this.book = book; }
}
