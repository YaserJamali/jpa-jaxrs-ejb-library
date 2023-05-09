package tamin.library.service;


import com.google.gson.Gson;
import tamin.library.model.entity.Book;
import tamin.library.model.repository.BookRepository;
import tamin.library.utiles.NumberGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@ApplicationScoped
@Singleton
public class BookServices implements Services<Book, String, Long> {
    private static BookServices instance;

    @Inject
    private BookRepository repository;


    @PersistenceContext
    @Produces
    private NumberGenerator generator;


    private BookServices() {
    }

    public static BookServices getInstance() {
        if (instance == null) {
            instance = new BookServices();
        }
        return instance;
    }


    @Override
    public void save(Book book) {
        repository.save(book);
    }

    @Override
    public void update(Book book) {
        repository.update(book);
    }

    @Override
    public String findByName(String title) {
        if (title != null) {
            String temp = title.toLowerCase();
            return new Gson().toJson(repository.findByName(temp));
        }
        return new Gson().toJson("Please Enter A Valid Title ");
    }

    @Override
    public String findById(Long id) {
        if (id != null) {
            return new Gson().toJson(repository.findById(id));
        }
        return new Gson().toJson("ERROR: Please Give An ID");
    }

    @Override
    public String findAll() {

        return new Gson().toJson((repository.findAll()));
    }

    @Override
    public String remove(Long id) {
        if (id != null && repository.findById(id) != null) {
            return new Gson().toJson(repository.remove(id));
        } else return new Gson().toJson("There Is No Book With This ID: " + id);
    }


    public String saveInstance(String title, LocalDate publicationDate, String description, Double unitCost, Integer numberOfPages) {
        if (title != null && publicationDate != null && description != null && unitCost != null && numberOfPages != null) {

            Book book = new Book();
            book.setIsbn(book.getIsbn())
                    .setPublicationDate(publicationDate)
                    .setIsbn(generator.numberGenerator())
                    .setNbOfPage(numberOfPages)
                    .setUnitCost(unitCost)
                    .setTitle(title)
                    .setDescription(description);
            save(book);

            return new Gson().toJson(book);
        }
        return new Gson().toJson("Please Fill All Fields");
    }

    public String updateInstance(Long id, String title, LocalDate publicationDate, String description, Double unitCost, Integer numberOfPages) {
        if (id != null) {
            Book book = repository.findById(id);
            if (book != null) {
                if (title != null && publicationDate != null && description != null && unitCost != null && numberOfPages != null) {
                    book.setIsbn(book.getIsbn())
                            .setPublicationDate(publicationDate)
                            .setIsbn(generator.numberGenerator())
                            .setNbOfPage(numberOfPages)
                            .setUnitCost(unitCost)
                            .setTitle(title)
                            .setDescription(description);
                    repository.update(book);
                    return new Gson().toJson(book);
                }
            }
            return new Gson().toJson("There Is No Book With This ID: " + id);
        }
        return new Gson().toJson("ERROR: ID IS NULL ");
    }



    public String raiseUnitCost(Long id, Double raise) {
        if (id != null && raise != null) {
            if (repository.findById(id) != null) {
                return new Gson().toJson(repository.raiseUnitCost(id, raise));
            }
            return new Gson().toJson("This ID: " + id + " IS NOT EXIST ANYMORE");
        }
        return new Gson().toJson("Please Fil All Expectations");
    }

    public String findByDate(LocalDate date) {
        if (date != null) {
            return new Gson().toJson(repository.findByDate(date));
        }
        return new Gson().toJson("Please Fill Date");
    }


}
