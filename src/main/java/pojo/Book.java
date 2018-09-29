package pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "buch")
public class Books {
    @Id
    private int id;
    private String title;
    private int pages;

    public Books() {
    }

    public Books(int id, String title, int pages) {
        this.id = id;
        this.title = title;
        this.pages = pages;
    }

    public void addPages(int pages)
    {
        this.pages += pages;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
