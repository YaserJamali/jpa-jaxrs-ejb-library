package tamin.library.controller;

import tamin.library.service.MusicianServices;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Path("/musician")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MusicianController implements BaseController<Response, String, Long> {

    @Inject
    private MusicianServices services;

    @POST
    @Path("/save-musician")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("bio") String bio,
            @QueryParam("birthDay") String birthDay,
            @QueryParam("preferredInstrument") String preferredInstrument
    ) {
        try {
            return Response.ok(services.
                            saveInstance(name, family, bio, LocalDate.parse(birthDay)
                                    , preferredInstrument))
                    .build();
        } catch (DateTimeParseException e) {
            return Response
                    .ok("Please Give A Valid Date Time " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/update-musician")
    @Produces("application/json")
    public Response update(
            @QueryParam("id") Long id,
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("bio") String bio,
            @QueryParam("birthDay") String birthDay,
            @QueryParam("preferredInstrument") String preferredInstrument
    ) {
        try {
            return Response
                    .ok(services.
                            updateInstance(id, name, family, bio
                                    , LocalDate.parse(birthDay), preferredInstrument))
                    .build();
        } catch (DateTimeParseException e) {
            return Response
                    .ok("Please Give A Valid Date Time " + e.getMessage())
                    .build();
        }
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

    @GET
    @Produces("application/json")
    @Path("/find/id")
    @Override
    public Response findById(
            @QueryParam("id") Long id) {
        return Response
                .ok(services.findById(id))
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("/find/all")
    @Override
    public Response findAll() {
        return Response
                .ok(services.findAll())
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("/remove/id")
    @Override
    public Response removeById(
            @QueryParam("id") Long id) {

        return Response
                .ok(services.remove(id))
                .build();
    }

    @Path("/add-musician-to-cd")
    @POST
    @Produces("application/json")
    @Override
    public Response addRelation(
            @QueryParam("musicianId") Long musicianId,
            @QueryParam("cdId") Long cdId) {
        return Response
                .ok(services.addCDtoMusician(musicianId, cdId))
                .build();
    }
}
