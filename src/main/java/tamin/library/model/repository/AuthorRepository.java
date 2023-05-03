package tamin.library.model.repository;


import tamin.library.model.entity.Author;
import tamin.library.model.util.JPA;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class AuthorRepository extends CRUD<Author, String, Long> {
    private static AuthorRepository instance;

    @Inject
    private EntityManager manager;

    @PersistenceContext
    @Produces
    private EntityTransaction transaction;

    @Inject
    private JPA jpa;


    private AuthorRepository() {
        jpa = JPA.getInstance();
    }

    public static AuthorRepository getInstance() {
        if (instance == null) {
            instance = new AuthorRepository();
        }
        return instance;
    }


    @Override
    @Transactional
    public Author save(Author author) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(author);
        transaction.commit();
        manager.close();
        return author;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(author);
        transaction.commit();
        manager.close();
        return author;
    }

    @Override
    public List<Author> findByName( String name1) {
        manager = jpa.getEntityManager();
        TypedQuery<Author> query = manager.createNamedQuery(Author.FIND_AUTHOR_NAME, Author.class);
        query.setParameter("name", "%" + name1 + "%");
        List<Author> list = query.getResultList();
        manager.close();
        return list;
    }



    @Override
    public Author findById(Long id) {
        manager = jpa.getEntityManager();
        Author author = manager.find(Author.class, id);
        manager.close();
        return author;
    }


    @Override
    public List<Author> findAll() {
        manager = jpa.getEntityManager();
        TypedQuery<Author> query = manager.createNamedQuery(Author.FIND_ALL_AUTHORS, Author.class);
        List<Author> authorList = query.getResultList();
        manager.close();
        return authorList;
    }


    @Override
    @Transactional
    public Author remove(Long id) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        Author author = manager.find(Author.class, id);
        manager.remove(author);
        transaction.commit();
        manager.close();
        return author;
    }


}
