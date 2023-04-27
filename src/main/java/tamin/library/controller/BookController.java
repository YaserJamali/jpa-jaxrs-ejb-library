package tamin.library.controller;

import tamin.library.model.service.BookServices;

import java.time.LocalDate;

public class BookController {
    private static BookController instance;

    private BookController() {

    }

    public static BookController getInstance() {
        if (instance == null) {
            instance = new BookController();
        }
        return instance;
    }


    public String saveBook(String title, LocalDate publicationDate, String description, Double unitCost, Integer numberOfPages) {
        return BookServices.getInstance().saveBook(title, publicationDate, description, unitCost, numberOfPages);
    }
}
