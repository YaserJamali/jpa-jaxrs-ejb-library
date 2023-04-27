package tamin.library.controller.api;

import tamin.library.controller.BookController;

import javax.ws.rs.*;
import java.time.LocalDate;
@Path("book")
public class BookApi {


    @GET
    @Produces("application/json")
    @Path("/saveBook")
    public String saveBook(@QueryParam("title")String title,
                           @QueryParam("publicationDate") String publicationDate,
                           @QueryParam("description") String description,
                           @QueryParam("cost") double unitCost,
                           @QueryParam("numberOfPages") Integer numberOfPages) throws Exception {

        return BookController.getInstance().saveBook(title, LocalDate.parse(publicationDate),description,unitCost,numberOfPages);}

}
