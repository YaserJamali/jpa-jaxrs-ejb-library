package tamin.library.model.repository;


import tamin.library.model.entity.Author;
import tamin.library.model.util.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class AuthorRepository extends CRUD<Author> {
    private static AuthorRepository instance;

    private AuthorRepository() {

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
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(author);
        transaction.commit();
        manager.close();
        return author;
    }

    @Override
    public Author update(Author author) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(author);
        transaction.commit();
        manager.close();
        return author;
    }


    @Override
    public Author remove(Long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Author author = manager.find(Author.class, id);
        manager.remove(author);
        transaction.commit();
        manager.close();
        return author;
    }

    @Override
    public Author findById(Long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        if (manager.find(Author.class, id) == null) {
            return null;
        }
        Author author = manager.find(Author.class, id);
        manager.close();
        return author;
    }

    @Override
    public List<Author> findAll() {
        EntityManager manager = JPA.getInstance().getEntityManager();
        Query query = manager.createQuery("select a from authorEntity a ");
        List<Author> authorList = query.getResultList();
        manager.close();
        return authorList;
    }

    public Author updateInstance(Long id, String name, String family, LocalDate brithDay, LocalDate deathDay, String bio) {
        Author author = findById(id);
        author.setName(name)
                .setFamily(family)
                .setDateOfBirth(brithDay)
                .setDateOfDeath(deathDay)
                .setBio(bio)
                .setAge(Period.between(brithDay, deathDay).getYears());
        return author;
    }

    public Author saveInstance(String name, String family, LocalDate brithDay, LocalDate deathDay, String bio) {
        Author author = new Author();
        author.setName(name)
                .setFamily(family)
                .setDateOfBirth(brithDay)
                .setDateOfDeath(deathDay)
                .setBio(bio)
                .setAge(Period.between(brithDay, deathDay).getYears());

        return author;
    }
}
