package tamin.library.controller;

import tamin.library.service.BookServices;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("book")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController implements BaseController<Response, String, Long> {

    @Inject
    private BookServices services;

    @POST
    @Produces("application/json")
    @Path("/save-book")
    public Response saveBook(
            @QueryParam("title") String title,
            @QueryParam("publicationDate") String publicationDate,
            @QueryParam("description") String description,
            @QueryParam("unitCost") double unitCost,
            @QueryParam("numberOfPages") Integer numberOfPages) {
        return Response
                .ok(services.saveInstance(title, LocalDate.parse(publicationDate), description, unitCost, numberOfPages))
                .build();
    }

    @PUT
    @Produces("application/json")
    @Path("/update-book")
    public Response updateBook(
            @QueryParam("id") Long id,
            @QueryParam("title") String title,
            @QueryParam("publicationDate") String publicationDate,
            @QueryParam("description") String description,
            @QueryParam("unitCost") double unitCost,
            @QueryParam("numberOfPages") Integer numberOfPages) {
        return Response
                .ok(services.updateInstance(id, title, LocalDate.parse(publicationDate)
                        , description, unitCost, numberOfPages))
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("/find/title")
    public Response findByName(
            @QueryParam("title") String title) {
        return Response
                .ok(services.findByName(title))
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("/find/id")
    public Response findById(
            @QueryParam("id") Long id) {

        return Response
                .ok(services.findById(id))
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("/find/all")
    public Response findAll() {
        return Response
                .ok(services.findAll())
                .build();
    }

    @DELETE
    @Produces("application/json")
    @Path("/remove/id")
    public Response removeById(
            @QueryParam("id") Long id) {
        return Response
                .ok(services.remove(id))
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("/find/date")
    public Response findByDate(
            @QueryParam("date") String date) {
        return Response
                .ok(services.findByDate(LocalDate.parse(date)))
                .build();
    }

    @PUT
    @Produces("application/json")
    @Path("/raiseUntCost")
    public Response raiseUntCost(
            @QueryParam("id") Long id,
            @QueryParam("raise") double raise) {
        return Response
                .ok(services.raiseUnitCost(id, raise))
                .build();
    }

    @Override
    public Response addRelation(Long u1, Long u2) {
        return null;
    }
}
