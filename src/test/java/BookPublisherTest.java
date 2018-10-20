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
public class BookPublisherTest {
    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "manyToManyBsp6";
    static final String TITLE = "Der Process";
    static final int PAGES = 251;

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
        books.setId(book_id);
        identifier.setId(ident_id);

        books.setIdentifier(identifier);
        identifier.setBook(books);

        Assert.assertNotNull(books);
        manager.persist(books);
        manager.persist(identifier);

        transaction.commit();
        book_id = (int) books.getId();
        ident_id = identifier.getId();
        System.out.println("Created and Persisted " + books);
        System.out.println("Created Identifier: " + books.getIdentifier());
        System.out.println("Der ISBN von " + books.getTitle() + " ist " + books.getIdentifier().getIsbn());

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
