import enums.BookGenre;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo_bsp_5.Book;
import pojo_bsp_5.Store;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;

// Test für Beispiel 5
public class BookStoreTest {
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "identifierBsp5";
    static int book1_id = 11;
    static int book2_id = 12;

    static int store_id = 1;

    static final String TITLE = "Der Process";
    static final int PAGES = 251;

    static final String TITLE_2 = "Die Verwandlung";
    static final int PAGES_2 = 158;

    static final String TOWN = "Graz";
    static final String STREET = "Herrengasse";

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

        Store store = new Store(TOWN, STREET);
        Book books1 = new Book(TITLE, PAGES, BookGenre.Classics);
        Book books2 = new Book(TITLE_2, PAGES_2, BookGenre.Classics);

        store.setId(store_id);
        store.setBooks(Arrays.asList(books1, books2));
        manager.persist(store);

        books1.setId(book1_id);
        books2.setId(book2_id);
        books1.setStore(store);
        books2.setStore(store);

        manager.persist(books1);
        manager.persist(books2);

        transaction.commit();
        book1_id = (int) books1.getId();
        book2_id = (int) books2.getId();
        store_id = store.getId();

        System.out.println("Created and Persisted " + books1 + books2);
        System.out.println("Created Store: " + store);

        Store newStore = manager.find(Store.class, store_id);
        System.out.println("Im Store " + TOWN + " gibt es die Buecher " + newStore.getBooks().get(0).getTitle() + " und " + newStore.getBooks().get(1).getTitle());

    }

    @Test
    public void remove() {
        Book books1 = manager.find(Book.class, book1_id);
        Book books2 = manager.find(Book.class, book2_id);
        Store store = manager.find(Store.class, store_id);

        Assert.assertNotNull(books1);
        Assert.assertNotNull(books2);
        Assert.assertNotNull(store);

        transaction.begin();
        manager.remove(books1);
        manager.remove(books2);
        manager.remove(store);
        transaction.commit();

        store = manager.find(Store.class, store_id);
        books1 = manager.find(Book.class, book1_id);
        books2 = manager.find(Book.class, book2_id);

        Assert.assertNull(books1);
        Assert.assertNull(books2);
        Assert.assertNull(store);

        System.out.println("Removed Book:" + book2_id);
        System.out.println("Removed Book:" + book1_id);
        System.out.println("Removed Store:" + store_id);

    }
}
