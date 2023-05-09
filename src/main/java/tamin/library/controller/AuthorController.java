package tamin.library.controller;


import tamin.library.service.AuthorServices;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Path("/author")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorController implements BaseController<Response, String, Long> {

    @Inject
    private AuthorServices services;


    @Path("save-author")
    @POST
    @Produces("application/json")
    public Response save(
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("birthDay") String birthDay,
            @QueryParam("bio") String bio) {
        try {
            return Response
                    .ok(services.saveInstance(name, family,
                            LocalDate.parse(birthDay), bio))
                    .build();
        } catch (DateTimeParseException e) {
            return Response.serverError().build();
        }
    }

    @Path("/update-author")
    @PUT
    @Produces("application/json")
    public Response update(
            @QueryParam("id") Long id,
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("birthDay") String brithDay,
            @QueryParam("bio") String bio
    ) {
        return Response
                .ok(services.updateInstance(id, name, family,
                        LocalDate.parse(brithDay), bio))
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("/find/name")
    public Response findByName(
            @QueryParam("name") String name) {
        return Response
                .ok(services.findByName(name))
                .build();
    }


    @Path("/find/id")
    @GET
    @Produces("application/json")
    public Response findById(
            @QueryParam("id") Long id) {

        return Response
                .ok(services.findById(id))
                .build();
    }


    @Path("/find/all")
    @GET
    @Produces("application/json")
    public Response findAll() {
        return Response
                .ok(services.findAll())
                .build();
    }


    @Path("/remove")
    @DELETE
    @Produces("application/json")
    public Response removeById(
            @QueryParam("id") Long id) {
        return Response
                .ok(services.remove(id))
                .build();
    }


    @Path("/add-book-to-author")
    @POST
    @Produces("application/json")
    public Response addRelation(
            @QueryParam("authorId") Long authorId,
            @QueryParam("bookId") Long bookId) {
        return Response
                .ok(services.addAuthorsBooks(authorId, bookId))
                .build();
    }
}
