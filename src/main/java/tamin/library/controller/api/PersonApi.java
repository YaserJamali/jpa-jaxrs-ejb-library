package tamin.library.controller.api;


import com.google.gson.Gson;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;
import tamin.library.controller.PersonController;
import tamin.library.controller.PersonControllerTest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


@Path("/person")
public class PersonApi  {

    @GET
    @Produces("application/json")
    @Path("/find")
    public String findApi(@QueryParam("token")String token) throws Exception {
      if (token!=null&&token.equals("123abc")){
       return PersonController.getInstance().findAll();}
      return new Gson().toJson("error");

    }
    @GET
    @Produces("application/json")
    @Path("/save")
    public String save(@QueryParam("title")String title,@QueryParam("description") String description,@QueryParam("cost") double unitCost,@QueryParam("duration") double totalDuration,@QueryParam("genre") String genre) throws Exception {

            return PersonController.saveCd(title,description,unitCost,totalDuration,genre);}



}

