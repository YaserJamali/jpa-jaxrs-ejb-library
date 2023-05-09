package tamin.library.controller;

import tamin.library.service.CdServices;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cd")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CdController implements BaseController<Response, String, Long> {
    @Inject
    private CdServices services;


    @POST
    @Produces("application/json")
    @Path("/save-cd")
    public Response save(
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("cost") double unitCost,
            @QueryParam("duration") double totalDuration,
            @QueryParam("genre") String genre) throws Exception {

        return Response
                .ok(services.saveInstance(title, description, unitCost, totalDuration, genre))
                .build();
    }

    @PUT
    @Produces("application/json")
    @Path("/update-cd")
    public Response update(
            @QueryParam("id") Long id,
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("cost") double unitCost,
            @QueryParam("duration") double totalDuration,
            @QueryParam("genre") String genre) {
        return Response.ok(services.updateInstance(id, title
                , description, unitCost
                , totalDuration, genre)).build();
    }

    @GET
    @Produces("application/json")
    @Path("/search_title")
    public Response findByName(
            @QueryParam("title") String title) {

        return Response
                .ok()
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("/findCdById")
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


    @GET
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
    @Path("/raise-untCost")
    public Response raiseUntCost(
            @QueryParam("id") Long id,
            @QueryParam("raise") double raise) {
        return Response
                .ok(services.raiseUnitCost(id, raise))
                .build();
    }


    @Path("/add-musician-to-cd")
    @PUT
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addRelation(
            @QueryParam("cdId") Long cdId,
            @QueryParam("musicianId") Long musicianId) {

        return Response
                .ok(services.addMusicianToCd(cdId, musicianId))
                .build();
    }


}
