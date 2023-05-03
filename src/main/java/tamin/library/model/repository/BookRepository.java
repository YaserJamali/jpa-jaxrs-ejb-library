package tamin.library.model.repository;


import tamin.library.model.entity.Book;
import tamin.library.model.util.JPA;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Singleton
public class BookRepository extends CRUD<Book, String, Long> {
    private static BookRepository instance;


    @Inject
    private EntityManager manager;

    @PersistenceContext
    @Produces
    private EntityTransaction transaction;

    @Inject
    private JPA jpa;

    private BookRepository() {
        jpa = JPA.getInstance();
    }


    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    @Override
    @Transactional
    public Book save(Book book) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(book);
        transaction.commit();
        manager.close();
        return book;

    }

    @Override
    @Transactional
    public Book update(Book book) {
        manager = JPA.getInstance().getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(book);
        transaction.commit();
        manager.close();
        return book;

    }
    @Override
    public List<Book> findByName(String name) {
        manager = jpa.getEntityManager();
        TypedQuery<Book> query = manager.createNamedQuery(Book.FIND_BY_TITLE, Book.class);
        query.setParameter("bookTitle", "%" + name + "%");
        List<Book> list = query.getResultList();
        manager.close();
        return list;
    }


    @Override
    public Book findById(Long id) {
        manager = jpa.getEntityManager();
        Book book = manager.find(Book.class, id);
        manager.close();
        return book;
    }

    @Override
    public List<Book> findAll() {
        manager = jpa.getEntityManager();
        TypedQuery<Book> query = manager.createNamedQuery(Book.FIND_ALL_BOOKS, Book.class);
        List<Book> list = query.getResultList();
        manager.close();
        return list;
    }



    @Override
    @Transactional
    public Book remove(Long id) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        Book book = manager.find(Book.class, id);
        manager.remove(book);
        transaction.commit();
        manager.close();
        return book;

    }

    public Book raiseUnitCost(Long id, Double raise) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        Book book = manager.find(Book.class, id);
        book.setUnitCost(book.getUnitCost() + raise);
        transaction.commit();
        manager.close();
        return book;

    }


    public List<Book> findByDate(LocalDate date) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        TypedQuery<Book> query = manager.createQuery("select b from bookEntity b where publicationDate < ?1", Book.class);
        query.setParameter(1, date);
        query.setMaxResults(1);

        List<Book> list = query.getResultList();

        manager.close();
        return list;
    }

}
