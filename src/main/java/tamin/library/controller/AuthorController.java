package tamin.library.controller;

import com.google.gson.Gson;
import tamin.library.service.AuthorServices;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Path("/author")
@Stateless
public class AuthorController {

    @Inject
    private AuthorServices services;

    public AuthorController() {
        services = AuthorServices.getInstance();
    }
    @Path("saveAuthor")
    @GET
    @Produces("application/json")
    public String save(
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("birthDay") String birthDay,
            @QueryParam("bio") String bio) {
        try {
            return services.saveInstance(name, family, LocalDate.parse(birthDay),  bio);
        } catch (DateTimeParseException e) {
            return new Gson().toJson("Please Give A Valid Date Time " + e.getMessage());
        }
    }

    @Path("/update")
    @GET
    @Produces("application/json")
    public String update(
            @QueryParam("id") Long id,
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("birthDay") String brithDay,
            @QueryParam("bio") String bio
    ) {

        return services.updateInstance(id, name, family, LocalDate.parse(brithDay),  bio);

    }


    @GET
    @Produces("application/json")
    @Path("/search_name")
    public String findByName(@QueryParam("name") String name) {
        return services.findByName(name);
    }


    @Path("/find")
    @GET
    @Produces("application/json")
    public String findById(@QueryParam("id") Long id) throws Exception {

        return services.findById(id);
    }


    @Path("/findAll")
    @GET
    @Produces("application/json")
    public String findApi() {
        return services.findAll();
    }


    @Path("/remove")
    @GET
    @Produces("application/json")
    public String removeByID(@QueryParam("id") Long id) {
        return services.remove(id);
    }



    @Path("/add-book-to-author")
    @GET
    @Produces("application/json")
    public String addBookToAuthor(@QueryParam("authorId") Long authorId ,@QueryParam("bookId") Long bookId)  {

        return services.addAuthorsBooks(authorId,bookId);
    }

}
