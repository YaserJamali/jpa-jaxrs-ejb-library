package tamin.library.controller;


import com.google.gson.Gson;
import tamin.library.model.entity.Book;
import tamin.library.model.entity.CD;
import tamin.library.model.service.AuthorServices;
import tamin.library.model.service.BookServices;
import tamin.library.model.service.CdServices;

import java.time.LocalDate;

public class PersonController {

    private static PersonController instance;

    private PersonController() {

    }

    public static PersonController getInstance() {
        if (instance == null) {
            synchronized (PersonController.class) {
                if (instance == null) {
                    instance = new PersonController();
                }
            }
        }
        return instance;
    }

    public String findAll() throws Exception {
        return new Gson().toJson(AuthorServices.getAuthorServices().findAll());
    }

    //CD(String title, String description, Float unitCost, Float totalDuration, String genre)
    public static String saveCd(String title, String description, double unitCost, double totalDuration, String genre) {
        CD cd = new CD();
        cd.setTitle(title).setTotalDuration(totalDuration).setUnitCost(unitCost).setGenre(genre).setDescription(description);
        CdServices.getCdServices().save(cd);
        return new Gson().toJson(cd);
    }
    public static String saveBook(String title,LocalDate publicationDate,  String description, double unitCost,Integer numberOfPages) {
        Book book=new Book();
        book.setIsbn(book.getIsbn()).setPublicationDate(publicationDate).setNbOfPage(numberOfPages).setUnitCost(unitCost).setTitle(title).setDescription(description);
        BookServices.getBookServices().save(book);
        return new Gson().toJson(book);
    }
}
