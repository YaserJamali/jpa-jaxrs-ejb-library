package tamin.library.model.repository;


import tamin.library.model.entity.Author;
import tamin.library.utiles.Loggable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;

@ApplicationScoped
public class AuthorRepository implements CRUD<Author, String, Long> {
    private static AuthorRepository instance;
    @PersistenceContext(unitName = "JPA")
    @Produces
    private EntityManager manager;


    private AuthorRepository() {
    }

    public static AuthorRepository getInstance() {
        if (instance == null) {
            instance = new AuthorRepository();
        }
        return instance;
    }


    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class, IllegalArgumentException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    @Inject
    public Author save(Author author) {
        manager.persist(author);
        return author;
    }

    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class, IllegalArgumentException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Author update(Author author) {
        manager.merge(author);
        return author;
    }

    @Override
    @Loggable
    public List<Author> findByName( String name1) {
        TypedQuery<Author> query = manager.createNamedQuery(Author.FIND_AUTHOR_NAME, Author.class);
        query.setParameter("name", "%" + name1 + "%");
        List<Author> list = query.getResultList();
        return list;
    }


    @Override
    @Loggable
    public Author findById(Long id) {
        Author author = manager.find(Author.class, id);
        return author;
    }


    @Override
    @Loggable
    public List<Author> findAll() {
        TypedQuery<Author> query = manager.createNamedQuery(Author.FIND_ALL_AUTHORS, Author.class);
        List<Author> authorList = query.getResultList();
        return authorList;
    }


    @Override
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    @Loggable
    public Author remove(Long id) {
        Author author = manager.find(Author.class, id);
        manager.remove(author);
        return author;
    }
}
