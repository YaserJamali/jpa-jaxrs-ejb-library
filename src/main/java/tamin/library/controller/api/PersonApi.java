package tamin.library.controller.api;


import com.google.gson.Gson;
import tamin.library.controller.PersonController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;


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
    @Path("/saveCd")
    public String save(@QueryParam("title")String title,@QueryParam("description") String description,@QueryParam("cost") double unitCost,@QueryParam("duration") double totalDuration,@QueryParam("genre") String genre) throws Exception {

            return PersonController.saveCd(title,description,unitCost,totalDuration,genre);}
  @GET
    @Produces("application/json")
    @Path("/saveBook")//String title, LocalDate localDate, String description, double totalDuration,Integer numberOfPages
    public String saveBook(@QueryParam("title")String title,
                       @QueryParam("publicationDate") String publicationDate,
                       @QueryParam("description") String description,
                       @QueryParam("cost") double unitCost,
                       @QueryParam("numberOfPages") Integer numberOfPages) throws Exception {


      return PersonController.saveBook(title, LocalDate.parse(publicationDate),description,unitCost,numberOfPages);}



}

