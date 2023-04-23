package tamin.library.controller;

public class BookController {
    private static BookController instance ;

    private BookController() {

    }

    public static BookController getInstance() {
        if (instance == null) {
            synchronized (BookController.class) {
                if (instance == null) {
                    instance = new BookController();
                }
            }
        }
        return instance;
    }
}
