package tamin.library.model.repository;



import tamin.library.model.entity.Author;
import tamin.library.model.entity.Book;
import tamin.library.model.util.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public class AuthorRepository extends CRUD<Author>{
    private static  AuthorRepository instance;
    private AuthorRepository(){

    }
    public static AuthorRepository getAuthorRepository() {
        if (instance == null) {
            synchronized (AuthorRepository.class) {
                if (instance == null) {
                    instance = new AuthorRepository();
                }
            }
        }
        ;
        return instance;
    }

//    public static AuthorRepository getAuthorRepository() {
//        return authorRepository;
//    }

    @Override
    @Transactional
    public Author save(Author author) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        if(author.getId()==null){
        manager.persist(author);}
        manager.merge(author);
        transaction.commit();
        manager.close();
        return author;
    }

//    @Override
//    public Author edit(Author author) {
//        EntityManager manager = JPA.getInstance().getEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();
//        manager.merge(author);
//        transaction.commit();
//        manager.close();
//        return author;
//    }

    @Override
    public Author remove(long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Author author=manager.find(Author.class,id);
        manager.remove(author);
        transaction.commit();
        manager.close();
        return author;
    }

    @Override
    public Author findById(long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        Author author=manager.find(Author.class,id);
        manager.close();
        return author;
    }

    @Override
    public List<Author> findAll() {
       EntityManager manager=JPA.getInstance().getEntityManager();
        Query query=manager.createQuery("select a from authorEntity a ");
        List<Author> authorList=query.getResultList();
        manager.close();
        return authorList;
    }
    public Author addAuthorAndBooks(Set<Book> booksList, Author author) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        author.setBookList(booksList);
        save(author);
        transaction.commit();
        manager.close();
        return author;

    }
}
