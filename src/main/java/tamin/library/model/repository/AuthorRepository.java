package tamin.library.model.repository;


import com.google.gson.Gson;
import javafx.util.converter.LocalDateStringConverter;
import tamin.library.model.entity.Author;
import tamin.library.model.util.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

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
        if (author.getId() == null) {
            manager.persist(author);
        }
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
        if (manager.find(Author.class, id) != null) {
            manager.remove(author);
            transaction.commit();
            manager.close();
            return author;
        } else return null;
    }

    @Override
    public Author findById(Long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
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


    //    public String saveInstance(String name, String family, LocalDate brithDay, LocalDate deathDay, String bio, Language language, Set<Book> books) {
//        Author author=new Author();
//        author.setName(name);
//        author.setFamily(family);
//        author.setDateOfBirth(brithDay);
//        author.setDateOfDeath(deathDay);
//        author.setBio(bio);
//        author.setLanguage(language);
//        author.setBookList(books);
//        save(author);
//        return new Gson().toJson(author);
//
//    }
    public String updateInstance(Long id, String name, String family, LocalDate brithDay, LocalDate deathDay, String bio) {
        if (name != null && family != null && bio != null && brithDay != null) {
            Author author = new Author();
            author.setId(id);
            author.setName(name);
            author.setFamily(family);
            author.setDateOfBirth(brithDay);
            author.setDateOfDeath(deathDay);
            author.setBio(bio);
//            author.setLanguage(language);
//            author.setBookList(books);
            if (deathDay == null) {
                author.setAge(Period.between(brithDay, LocalDate.now()).getYears());
            } else author.setAge(Period.between(brithDay, deathDay).getYears());
            save(author);
            return new Gson().toJson(author);
        }
        return new Gson().toJson("Invalid Information OR The Information Is Incomplete");

    }

    public String saveInstance(String name, String family, LocalDate brithDay, LocalDate deathDay, String bio) {

        if (name != null && family != null && bio != null ) {
            Author author = new Author();

            author.setName(name);
            author.setFamily(family);
            author.setDateOfBirth(brithDay);
            author.setDateOfDeath(deathDay);
            author.setBio(bio);
            author.setAge(Period.between(brithDay,deathDay).getYears());
            save(author);
            return new Gson().toJson(author);
        }
        return new Gson().toJson("Invalid Information OR The Information Is Incomplete");

    }

}
