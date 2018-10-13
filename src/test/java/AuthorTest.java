import enums.BookGenre;
import org.junit.*;
import pojo.Author;
import pojo.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthorTest {
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "sequence";
    static int id = 0;
    static final String firstName = "Franz";
    static final String lastName  = "Kafka";
    static final String contraction  = "Kfk.";
    static final Date birthdate = parseDate("03.07.1883");

    @BeforeClass
    public static void setup() {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        Assert.assertNotNull(factory);
        manager = factory.createEntityManager();
        Assert.assertNotNull(manager);

        transaction = manager.getTransaction();
    }

    @AfterClass
    public static void teardown() {
        if (manager == null)
            return;

        manager.close();
        factory.close();
    }

    @Test
    public void create() {
        transaction.begin();
        Author authors = new Author(firstName, lastName, contraction, birthdate);
        Assert.assertNotNull(authors);
        manager.persist(authors);
        transaction.commit();
        id = authors.getId();
        System.out.println("Created and Persisted " + authors);

    }

    @Test
    public void modify() {
        Author author = manager.find(Author.class, id);
        Assert.assertNotNull(author);
        System.out.println("Found " + author);

        transaction.begin();
        author.setContraction("Kaf.");
        transaction.commit();

        author = manager.find(Author.class, id);

        Assert.assertNotEquals(author.getContraction(), contraction);
        System.out.println("Updated " + author);
    }

    @Test
    public void remove() {
        Author author = manager.find(Author.class, id);
        Assert.assertNotNull(author);

        transaction.begin();
        manager.remove(author);
        transaction.commit();

        author = manager.find(Author.class, id);
        Assert.assertNull(author);

        System.out.println("Removed " + id);
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
