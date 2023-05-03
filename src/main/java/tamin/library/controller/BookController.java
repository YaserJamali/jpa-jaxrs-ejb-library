package tamin.library.controller;

import com.google.gson.Gson;
import tamin.library.service.BookServices;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;

@Path("book")
@Stateless
public class BookController {

    @Inject
    private BookServices services;

    public BookController() {
        services = BookServices.getInstance();
    }


    @GET
    @Produces("application/json")
    @Path("/saveBook")
    public String saveBook(
            @QueryParam("title") String title,
            @QueryParam("publicationDate") String publicationDate,
            @QueryParam("description") String description,
            @QueryParam("unitCost") double unitCost,
            @QueryParam("numberOfPages") Integer numberOfPages) {

        return new Gson().toJson(services.saveInstance(title, LocalDate.parse(publicationDate), description, unitCost, numberOfPages));
    }

    @GET
    @Produces("application/json")
    @Path("/updateBook")
    public String updateBook(
            @QueryParam("id") Long id,
            @QueryParam("title") String title,
            @QueryParam("publicationDate") String publicationDate,
            @QueryParam("description") String description,
            @QueryParam("unitCost") double unitCost,
            @QueryParam("numberOfPages") Integer numberOfPages) {

        return services.updateInstance(id, title, LocalDate.parse(publicationDate), description, unitCost, numberOfPages);
    }

    @GET
    @Produces("application/json")
    @Path("/searchByTitle")
    public String findByName(@QueryParam("title") String title) {
        return services.findByName(title);
    }

    @GET
    @Produces("application/json")
    @Path("/searchById")
    public String findById(@QueryParam("id") Long id) {

        return services.findById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/findAll")
    public String findAll() {
        return services.findAll();
    }

    @GET
    @Produces("application/json")
    @Path("/removeById")
    public String removeById(@QueryParam("id") Long id) {

        return services.remove(id);
    }

    @GET
    @Produces("application/json")
    @Path("/searchByDate")
    public String findByDate(@QueryParam("date") String date) {

        return services.findByDate(LocalDate.parse(date));
    }


    @GET
    @Produces("application/json")
    @Path("/raiseUntCost")
    public String raiseUntCost(
            @QueryParam("id") Long id,
            @QueryParam("raise") double raise) {

        return services.raiseUnitCost(id, raise);
    }


}
