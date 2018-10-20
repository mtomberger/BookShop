package pojo_bsp_5;

import javax.persistence.*;
import java.util.List;

// Store im Beispiel 5
@Entity
@Table(name = "store", schema = "bsp5")
public class Store {

    @Id
    private int id;

    private String town;

    private String street;

    @OneToMany(mappedBy = "store")
    private List<Book> books;

    public Store(String town, String street) {
        this.town = town;
        this.street = street;
    }

    public Store() {

    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTown() { return town;
    }

    public void setTown(String town) { this.town = town; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public List<Book> getBooks() { return books; }

    public void setBooks(List<Book> books) { this.books = books; }
}
