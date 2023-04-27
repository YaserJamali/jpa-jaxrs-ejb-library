package tamin.library.model.service;


import com.google.gson.Gson;
import tamin.library.model.entity.Book;
import tamin.library.model.repository.BookRepository;
import tamin.library.model.service.BL.IsbnGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import java.io.Serializable;
import java.time.LocalDate;

@ApplicationScoped
@Singleton
public class BookServices extends Services<Book> implements Serializable {
    private static BookServices instance;

    private BookServices() {}

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
    public String remove(Long id) {
        return new Gson().toJson(BookRepository.getInstance().remove(id));
    }

    @Override
    public String findById(Long id) {
        return new Gson().toJson(BookRepository.getInstance().findById(id));
    }

    @Override
    public String findAll() {

        return new Gson().toJson(listWrap(BookRepository.getInstance().findAll()));
    }

    public String saveBook(String title, LocalDate publicationDate, String description, Double unitCost, Integer numberOfPages) {
        Book book = new Book();
        book.setIsbn(book.getIsbn())
                .setPublicationDate(publicationDate)
                .setIsbn(IsbnGenerator.getInstance().numberGenerator())
                .setNbOfPage(numberOfPages)
                .setUnitCost(unitCost)
                .setTitle(title)
                .setDescription(description);
        save(book);
        return new Gson().toJson(book);
    }


}
