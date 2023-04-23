package tamin.library.controller;

import com.google.gson.Gson;
import tamin.library.model.service.BookServices;

public class PersonControllerTest {
    public  String find() throws Exception {
       return new Gson().toJson(BookServices.getBookServices().findAll());
    }
}
