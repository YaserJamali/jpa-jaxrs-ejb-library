package tamin.library.view;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import tamin.library.model.repository.AuthorRepository;
import tamin.library.model.repository.CdRepository;
import tamin.library.model.service.BookServices;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
//        ResourceConfig config= new PackagesResourceConfig("tamin.library.controller.api");
//        HttpServer server= HttpServerFactory.create("http://localhost:80/",config);
//        server.start();

//      System.out.println(PersonController.findAll());
//        PersonService.getInstance().save(new Person("ali","jamali","0080398413"));
//        System.out.println(AuthorRepository.getAuthorRepository().findAll());
        System.out.println(BookServices.getBookServices().Test1());
    }
}
