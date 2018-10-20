import enums.BookGenre;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pojo_bsp_4.Book;
import pojo_bsp_4.Identifier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// Test für Beispiel 4
public class BookIdentifierTest {
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "identifierBsp4";
    static int book_id = 1;
    static int ident_id = 1;

    static final String TITLE = "Der Process";
    static final int PAGES = 251;
    static final String ISBN = "978-3150096765";
    static final String QR_CODE = "EINQRCODE";


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

        Identifier identifier = new Identifier(ISBN, QR_CODE);
        Book books = new Book(TITLE, PAGES, BookGenre.Fantasy);

        books.setIdentifier(identifier);
        identifier.setBook(books);

        Assert.assertNotNull(books);
        manager.persist(books);
        transaction.commit();
        book_id = (int) books.getId();
        ident_id = identifier.getId();
        System.out.println("Created and Persisted " + books);
        System.out.println("Created Identifier: " + books.getIdentifier());

    }

    @Test
    public void modify() {
        Book books = manager.find(Book.class, book_id);
        Assert.assertNotNull(books);
        System.out.println("Found " + books);

        transaction.begin();
        books.addPages(PAGES);
        books.setGenre(BookGenre.Fiction);
        transaction.commit();

        books = manager.find(Book.class, book_id);

        Assert.assertEquals(PAGES + PAGES, (int) books.getPages());
        Assert.assertEquals(BookGenre.Fiction, books.getGenre());
        System.out.println("Updated " + books);
    }

    @Test
    public void remove() {
        Book books = manager.find(Book.class, book_id);
        Identifier identifier = manager.find(Identifier.class, ident_id);

        Assert.assertNotNull(books);

        transaction.begin();
        manager.remove(books);
        manager.remove(identifier);
        transaction.commit();

        identifier = manager.find(Identifier.class, ident_id);
        books = manager.find(Book.class, book_id);

        Assert.assertNull(books);
        Assert.assertNull(identifier);

        System.out.println("Removed Book:" + book_id);
        System.out.println("Removed Ident:" + ident_id);

    }
}
