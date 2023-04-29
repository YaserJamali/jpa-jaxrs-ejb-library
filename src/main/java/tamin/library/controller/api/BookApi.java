package tamin.library.controller.api;

import tamin.library.model.service.BookServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;

@Path("book")
public class BookApi {


    @GET
    @Produces("application/json")
    @Path("/saveBook")
    public String saveBook(
            @QueryParam("title") String title,
            @QueryParam("publicationDate") String publicationDate,
            @QueryParam("description") String description,
            @QueryParam("unitCost") double unitCost,
            @QueryParam("numberOfPages") Integer numberOfPages) {

        return BookServices.getInstance().saveInstance(title, LocalDate.parse(publicationDate), description, unitCost, numberOfPages);
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

        return BookServices.getInstance().updateInstance(id, title, LocalDate.parse(publicationDate), description, unitCost, numberOfPages);
    }

    @GET
    @Produces("application/json")
    @Path("/searchByTitle")
    public String findByTitle(@QueryParam("title") String title) {
        return BookServices.getInstance().findByTitle(title);
    }

    @GET
    @Produces("application/json")
    @Path("/searchById")
    public String findById(@QueryParam("id") Long id) {

        return BookServices.getInstance().findById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/removeById")
    public String removeById(@QueryParam("id") Long id) {

        return BookServices.getInstance().remove(id);
    }
    @GET
    @Produces("application/json")
    @Path("/searchByDate")
    public String findByDate(@QueryParam("date") String date) {

        return BookServices.getInstance().findByDate(LocalDate.parse(date));
    }


    @GET
    @Produces("application/json")
    @Path("/raiseUntCost")
    public String raiseUntCost(
            @QueryParam("id") Long id,
            @QueryParam("raise") double raise) {

        return BookServices.getInstance().raiseUnitCost(id, raise);
    }

    @GET
    @Produces("application/json")
    @Path("/findAll")
    public String findAll() {
        return BookServices.getInstance().findAll();
    }
}
