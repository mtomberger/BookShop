package pojo;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "author")
public class Author {
    @SequenceGenerator (name = "authorIDSeqGen", sequenceName = "author_ID_Seq", allocationSize = 1)
    @Id
    @GeneratedValue (generator="authorIDSeqGen")
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String contraction;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

    public Author() {
    }

    public Author(String firstName, String lastName, String contraction, Date birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contraction = contraction;
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContraction() {
        return contraction;
    }

    public void setContraction(String contraction) {
        this.contraction = contraction;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getId() { return id; }
}
