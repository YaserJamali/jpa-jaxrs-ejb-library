package tamin.library.controller.api;

import tamin.library.model.service.CdServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/cd")
public class CdApi {

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

        return CdServices.getInstance().saveCd(title, description, unitCost, totalDuration, genre);
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

        return CdServices.getInstance().updateCd(id, title, description, unitCost, totalDuration, genre);
    }

    @GET
    @Produces("application/json")
    @Path("/findCdById")
    public String findCdById(
            @QueryParam("id") Long id) {
        return CdServices.getInstance().findById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/removeCdById")
    public String removeCdById(
            @QueryParam("id") Long id) {
        return CdServices.getInstance().remove(id);
    }


    @GET
    @Produces("application/json")
    @Path("/findAll")
    public String findAll() {
        return CdServices.getInstance().findAll();
    }

}
