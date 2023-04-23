package tamin.library.controller.api;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import tamin.library.model.repository.AuthorRepository;


import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Api {

    public static void main(String[] args) throws Exception {
        ResourceConfig config= new PackagesResourceConfig("tamin.library.controller.api");
        HttpServer server= HttpServerFactory.create("http://localhost:80/",config);
        server.start();

    }
}
