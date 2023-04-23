package tamin.library.controller;


public class AuthorController {
    private static AuthorController instance ;

    private AuthorController() {

    }

    public static AuthorController getInstance() {
        if (instance == null) {
            synchronized (AuthorController.class) {
                if (instance == null) {
                    instance = new AuthorController();
                }
            }
        }
        ;
        return instance;
    }
}
