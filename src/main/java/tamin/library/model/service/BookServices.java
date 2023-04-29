package tamin.library.model.service;


import com.google.gson.Gson;
import tamin.library.model.entity.Book;
import tamin.library.model.repository.BookRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.io.Serializable;
import java.time.LocalDate;

@ApplicationScoped
@Singleton
public class BookServices extends Services<Book> implements Serializable {
    private static BookServices instance;

    private BookServices() {
    }

    public static BookServices getInstance() {
        if (instance == null) {
            instance = new BookServices();
        }
        return instance;
    }


    @Override
    public String save(Book book) {
        return new Gson().toJson(BookRepository.getInstance().save(book));
    }

    @Override
    public Book update(Book book) {
        return BookRepository.getInstance().update(book);
    }

    @Override
    public String remove(Long id) {
        if (id != null && BookRepository.getInstance().findById(id) != null) {
            return new Gson().toJson(BookRepository.getInstance().remove(id));
        } else return new Gson().toJson("There Is No Book With This ID: " + id);
    }

    @Override
    public String findById(Long id) {
        if (id != null) {
            return new Gson().toJson(BookRepository.getInstance().findById(id));
        }
        return new Gson().toJson("ERROR: Please Give An ID");
    }

    @Override
    public String findAll() {

        return new Gson().toJson((BookRepository.getInstance().findAll()));
    }


    public String saveInstance(String title, LocalDate publicationDate, String description, Double unitCost, Integer numberOfPages) {
        if (title != null && publicationDate != null && description != null && unitCost != null && numberOfPages != null) {
            return new Gson().toJson(save(BookRepository.getInstance().saveInstance(title, publicationDate, description, unitCost, numberOfPages)));
        }
        return new Gson().toJson("Please Fill All Fields");
    }

    public String updateInstance(Long id, String title, LocalDate publicationDate, String description, Double unitCost, Integer numberOfPages) {
        if (id != null && BookRepository.getInstance().findById(id) != null) {
            if (title != null && publicationDate != null && description != null && unitCost != null && numberOfPages != null) {

                return new Gson().toJson(update(BookRepository.getInstance().updateInstance(id, title, publicationDate, description, unitCost, numberOfPages)));
            }
            return new Gson().toJson("There Is No Such A Book For Editing");
        }
        return new Gson().toJson("There Is No Book With This ID: " + id);
    }

    public String findByTitle(String title) {
        if (title != null) {
            String temp = title.toLowerCase();
            return new Gson().toJson(BookRepository.getInstance().findBookByTitle(temp));
        }
        return new Gson().toJson("Please Enter A Valid Title ");
    }

    public String raiseUnitCost(Long id, Double raise) {
        if (id != null && raise != null) {
            if (BookRepository.getInstance().findById(id) != null) {
                return new Gson().toJson(BookRepository.getInstance().raiseUnitCost(id, raise));
            }
            return new Gson().toJson("This ID: " + id + " IS NOT EXIST ANYMORE");
        }
        return new Gson().toJson("Please Fil All Expectations");
    }

    public String findByDate(LocalDate date) {
        if (date != null) {
            return new Gson().toJson(BookRepository.getInstance().findByDate(date));
        }
        return new Gson().toJson("Please Fill Date");
    }


}
