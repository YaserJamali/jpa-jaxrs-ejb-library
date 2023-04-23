//package ir.tamin.infra.apicatalogue.ws.rest;
//
//import ir.tamin.framework.ws.rest.ResponseHelper;
//import ir.tamin.framework.ws.rest.json.FilterWrapper;
//import ir.tamin.framework.ws.rest.json.SortWrapper;
//import ir.tamin.infra.apicatalogue.service.CatalogueManager;
//
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.SecurityContext;
//
//@Path("/catalogue")
//@RequestScoped
//@Produces(MediaType.APPLICATION_JSON)
//public class CatalogueRESTService {
//
//    @Inject private CatalogueManager manager;
//
//    @GET
//    public Response getAll(@QueryParam("filter") FilterWrapper fw, @QueryParam("start") Integer start, @QueryParam("limit") Integer limit, @QueryParam("sort") SortWrapper sw, @Context SecurityContext sc) throws Exception {
//
//        return ResponseHelper.ok(manager.getAll(fw,start,limit));
//    }
//}
