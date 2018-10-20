package pojo_bsp_4;

import javax.persistence.*;

// Identifier in Beispiel 4
@Entity
@Table(name = "identifier", schema = "bsp4")
public class Identifier {
    @Id
    private int id;

    private String isbn;

    private String qrCode;

    @OneToOne(mappedBy = "identifier")
    private Book book;

    public Identifier() {
    }

    public Identifier(String isbn, String qrCode) {
        this.isbn = isbn;
        this.qrCode = qrCode;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getQrCode() { return qrCode; }

    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public Book getBook() { return book; }

    public void setBook(Book book) { this.book = book; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
