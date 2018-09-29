import org.junit.*;
import pojo.Books;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BooksTest {
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "BookShop";
    static final int id = 5;
    static final String title = "The Books";
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
        Books books = new Books(id, title, pages);
        Assert.assertNotNull(books);
        manager.persist(books);
        transaction.commit();

        System.out.println("Created and Persisted " + books);

    }

    @Test
    public void modify() {
        Books books = manager.find(Books.class, id);
        Assert.assertNotNull(books);
        System.out.println("Found " + books);

        transaction.begin();
        books.addPages(pages);
        transaction.commit();

        books = manager.find(Books.class, id);

        Assert.assertEquals(pages+pages, (int) books.getPages());
        System.out.println("Updated " + books);
    }

    @Test
    public void remove() {
        Books books = manager.find(Books.class, id);
        Assert.assertNotNull(books);

        transaction.begin();
        manager.remove(books);
        transaction.commit();

        books = manager.find(Books.class, id);
        Assert.assertNotNull(books);

        System.out.println("Removed " + id);
    }
}