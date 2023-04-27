package tamin.library.controller.api;

import tamin.library.controller.CdController;

import javax.ws.rs.*;

@Path("/cd")
public class CdApi {

    @POST
//    @Produces("application/json")
    @Path("/saveCd")
    public String save(@QueryParam("title")String title,
                       @QueryParam("description") String description,
                       @QueryParam("cost") double unitCost,
                       @QueryParam("duration") double totalDuration,
                       @QueryParam("genre") String genre

                       ) throws Exception {

        return CdController.getInstance().saveCd(title,description,unitCost,totalDuration,genre);}

}
