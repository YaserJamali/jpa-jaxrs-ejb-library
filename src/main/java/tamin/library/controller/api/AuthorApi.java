package tamin.library.controller.api;

import com.google.gson.Gson;
import tamin.library.model.service.AuthorServices;

import javax.ws.rs.*;
import java.time.LocalDate;

@Path("/author")
public class AuthorApi {
    @Path("/find")
    @GET
    @Produces("application/json")
    public String findApi(@QueryParam("token") String token) throws Exception {
        if (token != null && token.equals("123abc")) {
            return AuthorServices.getInstance().findAll();
        }
        return new Gson().toJson("error");

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
            @QueryParam("bio") String bio,
            @QueryParam("books") String books) {

        return AuthorServices.getInstance().update(id, name, family, LocalDate.parse(brithDay), LocalDate.parse(deathDay), bio);

    }

    @Path("saveAuthor")
    @GET
    @Produces("application/json")
    public String save(
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("birthDay")String  birthDay,
            @QueryParam("deathDay") String  deathDay,
            @QueryParam("bio") String bio){
        return AuthorServices.getInstance().saveAuthor(name, family, LocalDate.parse(birthDay), LocalDate.parse(deathDay), bio);
    }

    @Path("/remove")
    @GET
    @Produces("application/json")
    public String removeByID(@QueryParam("id") Long id) {
        return AuthorServices.getInstance().removeByID(id);
    }

}
