import org.junit.*;
import pojo.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BookTest {
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BookShop";
    static final int id = 1;
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
        Book book = new Book(id, title, pages);
        Assert.assertNotNull(book);
        manager.persist(book);
        transaction.commit();

        System.out.println("Created and Persisted " + book);

    }

    @Test
    public void modify() {
        Book book = manager.find(Book.class, id);
        Assert.assertNotNull(book);
        System.out.println("Found " + book);

        transaction.begin();
        book.addPages(pages);
        transaction.commit();

        book = manager.find(Book.class, id);

        Assert.assertEquals(pages+pages, (int) book.getPages());
        System.out.println("Updated " + book);
    }

    @Test
    public void remove() {
        Book book = manager.find(Book.class, id);
        Assert.assertNotNull(book);

        transaction.begin();
        manager.remove(book);
        transaction.commit();

        book = manager.find(Book.class, id);
        Assert.assertNotNull(book);

        System.out.println("Removed " + id);
    }
}