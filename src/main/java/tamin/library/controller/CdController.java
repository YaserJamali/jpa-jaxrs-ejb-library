package tamin.library.controller;

import tamin.library.service.CdServices;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/cd")
@Stateless
public class CdController {
    @Inject
    private CdServices services;

    public CdController() {
        services = CdServices.getInstance();
    }

    //    @POST
    @GET
    @Produces("application/json")
    @Path("/saveCd")
    public String save(
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("cost") double unitCost,
            @QueryParam("duration") double totalDuration,
            @QueryParam("genre") String genre) throws Exception {

        return services.saveInstance(title, description, unitCost, totalDuration, genre);
    }

    @GET
    @Produces("application/json")
    @Path("/updateCd")
    public String updateCd(
            @QueryParam("id") Long id,
            @QueryParam("title") String title,
            @QueryParam("description") String description,
            @QueryParam("cost") double unitCost,
            @QueryParam("duration") double totalDuration,
            @QueryParam("genre") String genre) {

        return services.updateInstance(id, title, description, unitCost, totalDuration, genre);
    }

    @GET
    @Produces("application/json")
    @Path("/search_title")
    public String findByName(@QueryParam("title") String title) {
        return services.findByName(title);
    }

    @GET
    @Produces("application/json")
    @Path("/findCdById")
    public String findCdById(
            @QueryParam("id") Long id) {
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
    @Path("/removeCdById")
    public String removeCdById(
            @QueryParam("id") Long id) {
        return services.remove(id);
    }
    @GET
    @Produces("application/json")
    @Path("/raiseUntCost")
    public String raiseUntCost(
            @QueryParam("id") Long id,
            @QueryParam("raise") double raise) {

        return services.raiseUnitCost(id, raise);
    }


    @Path("/add-musician-to-cd")
    @GET
    @Produces("application/json")
    public String addBookToAuthor(@QueryParam("cdId") Long cdId,
                                  @QueryParam("musicianId") Long musicianId) {

        return services.addMusicianToCd(cdId, musicianId);
    }


}
