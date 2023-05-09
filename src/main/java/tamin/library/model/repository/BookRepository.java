package tamin.library.model.repository;


import tamin.library.model.entity.Book;
import tamin.library.utiles.Loggable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.time.LocalDate;
import java.util.List;

@Singleton
@RequestScoped
public class BookRepository implements CRUD<Book, String, Long> {
    private static BookRepository instance;


    @PersistenceContext(unitName = "JPA")
    @Produces
    private EntityManager manager;


    private BookRepository() {
    }


    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class, IllegalArgumentException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Book save(Book book) {
        manager.persist(book);
        return book;

    }

    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class, IllegalArgumentException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Book update(Book book) {
        manager.merge(book);
        return book;

    }
    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = manager.createNamedQuery(Book.FIND_BY_TITLE, Book.class);
        query.setParameter("bookTitle", "%" + name + "%");
        List<Book> list = query.getResultList();
        return list;
    }


    @Override
    public Book findById(Long id) {
        Book book = manager.find(Book.class, id);
        return book;
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = manager.createNamedQuery(Book.FIND_ALL_BOOKS, Book.class);
        List<Book> list = query.getResultList();
        return list;
    }


    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Book remove(Long id) {
        Book book = manager.find(Book.class, id);
        manager.remove(book);
        return book;

    }

    public Book raiseUnitCost(Long id, Double raise) {
        Book book = manager.find(Book.class, id);
        book.setUnitCost(book.getUnitCost() + raise);
        return book;

    }


    public List<Book> findByDate(LocalDate date) {
        TypedQuery<Book> query = manager.createQuery("select b from bookEntity b where publicationDate < ?1", Book.class);
        query.setParameter(1, date);
        query.setMaxResults(1);
        List<Book> list = query.getResultList();
        return list;
    }

}
