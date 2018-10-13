import junit.framework.TestCase;
import org.junit.*;
import pojo.Book;
import enums.BookGenre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BookTest {
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BookShop.simple";
    static int id = 0;
    static final String title = "The Book";
    static final int pages = 100;

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
        Book books = new Book(title, pages, BookGenre.Fantasy);
        Assert.assertNotNull(books);
        manager.persist(books);
        transaction.commit();
        id = (int) books.getId();
        System.out.println("Created and Persisted " + books);

    }

    @Test
    public void modify() {
        Book books = manager.find(Book.class, id);
        Assert.assertNotNull(books);
        System.out.println("Found " + books);

        transaction.begin();
        books.addPages(pages);
        books.setGenre(BookGenre.Fiction);
        transaction.commit();

        books = manager.find(Book.class, id);

        Assert.assertEquals(pages+pages, (int) books.getPages());
        Assert.assertEquals(BookGenre.Fiction, books.getGenre());
        System.out.println("Updated " + books);
    }

    @Test
    public void remove() {
        Book books = manager.find(Book.class, id);
        Assert.assertNotNull(books);

        transaction.begin();
        manager.remove(books);
        transaction.commit();

        books = manager.find(Book.class, id);
        Assert.assertNull(books);

        System.out.println("Removed " + id);
    }
}