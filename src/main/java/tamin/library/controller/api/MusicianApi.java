package tamin.library.controller.api;

import com.google.gson.Gson;
import tamin.library.model.service.MusicianServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Path("/musician")
public class MusicianApi {

    @GET
    @Path("saveMusician")
    @Produces("application/json")
    public String save(
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("bio") String bio,
            @QueryParam("birthDay") String birthDay,
            @QueryParam("deathDay") String deathDay,
            @QueryParam("preferredInstrument") String preferredInstrument
    ) {
        try {
            return MusicianServices.getInstance().
                    saveInstance(name, family, bio, LocalDate.parse(birthDay), LocalDate.parse(deathDay), preferredInstrument);
        } catch (DateTimeParseException e) {
            return new Gson().toJson("Please Give A Valid Date Time " + e.getMessage());
        }
    }

    @GET
    @Path("/updateMusician")
    @Produces("application/json")
    public String update(
            @QueryParam("id") Long id,
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("bio") String bio,
            @QueryParam("birthDay") String birthDay,
            @QueryParam("deathDay") String deathDay,
            @QueryParam("preferredInstrument") String preferredInstrument
    ) {
        try {
            return MusicianServices.getInstance().
                    updateInstance(id, name, family, bio, LocalDate.parse(birthDay), LocalDate.parse(deathDay), preferredInstrument);
        } catch (DateTimeParseException e) {
            return new Gson().toJson("Please Give A Valid Date Time " + e.getMessage());
        }
    }

    @GET
    @Produces("application/json")
    @Path("/searchById")
    public String findById(@QueryParam("id") Long id) {

        return MusicianServices.getInstance().findById(id);
    }

    @GET
    @Produces("application/json")
    @Path("/removeById")
    public String removeById(@QueryParam("id") Long id) {

        return MusicianServices.getInstance().remove(id);
    }

    @GET
    @Produces("application/json")
    @Path("/searchByName")
    public String findByName(@QueryParam("name") String name) {
        return MusicianServices.getInstance().findByName(name);
    }

    @GET
    @Produces("application/json")
    @Path("/findAll")
    public String findAll() {
        return MusicianServices.getInstance().findAll();
    }


}
