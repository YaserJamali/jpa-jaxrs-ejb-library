package tamin.library.controller;


import com.google.gson.Gson;
import tamin.library.model.service.AuthorServices;

public class AuthorController {
    private static AuthorController instance;

    private AuthorController() {}

    public static AuthorController getInstance() {
        if (instance == null) {
            instance = new AuthorController();
        }
        return instance;
    }

    public String findAll() throws Exception {
        return new Gson().toJson(AuthorServices.getInstance().findAll());
    }

}
