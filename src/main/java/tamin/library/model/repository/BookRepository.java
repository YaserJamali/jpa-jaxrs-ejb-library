package tamin.library.model.repository;


import tamin.library.model.entity.Book;
import tamin.library.model.util.JPA;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
@RequestScoped
public class BookRepository extends CRUD<Book> {
    private static BookRepository instance;

    private BookRepository() {

    }


    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    @Override
    public Book save(Book book) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        if (book.getId() == null) {
            manager.persist(book);
        }
        manager.merge(book);
        transaction.commit();
        manager.close();
        return book;
    }

//    @Override
//    public Book edit(Book book) {
//        EntityManager manager = JPA.getInstance().getEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();
//        manager.merge(book);
//        transaction.commit();
//        manager.close();
//        return book;
//    }

    @Override
    public Book remove(Long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Book book = manager.find(Book.class, id);
        manager.remove(book);
        transaction.commit();
        manager.close();
        return book;
    }

    @Override
    public Book findById(Long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        Book book = manager.find(Book.class, id);
        manager.close();
        return book;
    }

    @Override
    public List<Book> findAll() {
        EntityManager manager = JPA.getInstance().getEntityManager();
        Query query = manager.createQuery("select b from bookEntity b ");
        List<Book> bookList = query.getResultList();
        manager.close();
        return bookList;
    }

    public Book raiseUnitCost(long id, double raise) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Book book = manager.find(Book.class, id);
        book.setUnitCost(book.getUnitCost() + raise);
        transaction.commit();
        manager.close();
        return book;

    }

    //
    public List<Book> findBookByTitle(String title1) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        Query query = manager.createQuery("select b from bookEntity b where  lower(title) like ?1 order by id", Book.class);
        query.setParameter(1, "%" + title1);
        List<Book> bookList = query.getResultList();
        return bookList;

    }
//    public Book removeByTitle(String title1) {
//        EntityManager manager = JPA.getJpa().getEntityManager();
//        Query query = manager.createQuery("select b from bookEntity b where  lower(title) =?1 order by id", Book.class);
//      Book book  query.setParameter(1, "%"+title1);
//
//       manager.remove(book);
//        return book;
//
//    }

    public List<Book> findByDate(LocalDate date) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        //  Query query= manager.createQuery("select b from bookEntity b where publicationDate < :pubDate",Book.class);
        TypedQuery<Book> query = manager.createQuery("select b from bookEntity b where publicationDate < ?1", Book.class);
        query.setParameter(1, date);
        query.setMaxResults(1);

        List<Book> list = query.getResultList();

        manager.close();
        return list;
    }


}
