package pojo_bsp_6;

import enums.BookGenre;
import javax.persistence.*;
import java.util.ArrayList;

// Book im Beispiel 6
@Entity
@Table(name="publisher",schema = "bsp6")
public class Publisher {
    @Id
    private int id;
    private String name;
    @Column(name="description")
    private String desc;
    @ManyToMany(mappedBy = "publisher")
    private ArrayList<Book> books = new ArrayList();

    public Publisher() {
    }

    public Publisher(String title, int pages, BookGenre genre) {
        this.id = id;
    }
    public void addBook(Book b){
        if(b!=null){
            books.add(b);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }
}

