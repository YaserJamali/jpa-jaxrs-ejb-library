package tamin.library.controller.api;

import com.google.gson.Gson;
import tamin.library.model.service.AuthorServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Path("/author")
public class AuthorApi {
    @Path("/findAll")
    @GET
    @Produces("application/json")
    public String findApi(@QueryParam("token") String token) {
        if (token != null && token.equals("123abc")) {
            return AuthorServices.getInstance().findAll();
        }
        return new Gson().toJson("error");

    }

    @Path("/find")
    @GET
    @Produces("application/json")
    public String findById(@QueryParam("id") Long id) throws Exception {

        return AuthorServices.getInstance().findById(id);
    }

    @Path("/update")
    @GET
    @Produces("application/json")
    public String update(
            @QueryParam("id") Long id,
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("brithDay") String brithDay,
            @QueryParam("deathDay") String deathDay,
            @QueryParam("bio") String bio
    ) {

        return AuthorServices.getInstance().updateInstance(id, name, family, LocalDate.parse(brithDay), LocalDate.parse(deathDay), bio);

    }

    @Path("saveAuthor")
    @GET
    @Produces("application/json")
    public String save(
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("birthDay") String birthDay,
            @QueryParam("deathDay") String deathDay,
            @QueryParam("bio") String bio) {
        try {
            return AuthorServices.getInstance().saveInstance(name, family, LocalDate.parse(birthDay), LocalDate.parse(deathDay), bio);
        } catch (DateTimeParseException e) {
            return new Gson().toJson("Please Give A Valid Date Time "+e.getMessage());
        }
    }

    @Path("/remove")
    @GET
    @Produces("application/json")
    public String removeByID(@QueryParam("id") Long id) {
        return AuthorServices.getInstance().remove(id);
    }

}
