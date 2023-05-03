package tamin.library.controller;

import com.google.gson.Gson;
import tamin.library.service.MusicianServices;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Path("/musician")
@Stateless
public class MusicianController {


    @Inject
    private MusicianServices services;


    public MusicianController() {
        services = MusicianServices.getInstance();
    }

    @GET
    @Path("saveMusician")
    @Produces("application/json")
    public String save(
            @QueryParam("name") String name,
            @QueryParam("family") String family,
            @QueryParam("bio") String bio,
            @QueryParam("birthDay") String birthDay,
            @QueryParam("preferredInstrument") String preferredInstrument
    ) {
        try {
            return services.
                    saveInstance(name, family, bio, LocalDate.parse(birthDay), preferredInstrument);
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
            @QueryParam("preferredInstrument") String preferredInstrument
    ) {
        try {
            return services.
                    updateInstance(id, name, family, bio, LocalDate.parse(birthDay), preferredInstrument);
        } catch (DateTimeParseException e) {
            return new Gson().toJson("Please Give A Valid Date Time " + e.getMessage());
        }
    }


    @GET
    @Produces("application/json")
    @Path("/search_name")
    public String findByName(@QueryParam("name") String name) {
        return services.findByName(name);
    }

    @GET
    @Produces("application/json")
    @Path("/searchById")
    public String findById(@QueryParam("id") Long id) {

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
    @Path("/removeById")
    public String removeById(@QueryParam("id") Long id) {

        return services.remove(id);
    }

}
