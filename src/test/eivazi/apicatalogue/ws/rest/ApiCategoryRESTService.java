//package ir.tamin.infra.apicatalogue.ws.rest;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import ir.tamin.framework.cdi.util.WebProperties;
//import ir.tamin.framework.core.util.Bundle;
//import ir.tamin.framework.weblogic.oauth.oltu.client.AccessTokenManager;
//import ir.tamin.framework.ws.rest.ResponseHelper;
//import ir.tamin.infra.apicatalogue.model.RoleDtoModel;
//import ir.tamin.infra.apicatalogue.service.general.RestServices;
//import org.json.JSONObject;
//
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.SecurityContext;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//@Path("/api-list")
//@RequestScoped
//@Produces(MediaType.APPLICATION_JSON)
//public class ApiCategoryRESTService {
//    @Inject
//    private RestServices rs;
//    @Inject
//    @WebProperties
//    Bundle webProperties;
//
//    @GET
//    public Response get(@QueryParam("base-url") String baseUrl, @Context SecurityContext sc) throws Exception {
//        AccessTokenManager accessTokenManager = new AccessTokenManager();
//        accessTokenManager.setWebProperties(webProperties);
//        JsonNode js = null;
//        try {
//            js = accessTokenManager.requestTokenWithClientCredentials(webProperties.getProperty("oauth.client_id"), webProperties.getProperty("oauth.client_secret"));
//
//        } catch (Exception ex) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
//        }
//
//        String token = js.get("access_token").textValue();
//
//        String output = rs.getRequest(baseUrl + webProperties.getProperty("api.base.url") , token);
//
//        JSONObject jsonObject = new JSONObject(output);
//
//        Map<String, Object> result = new HashMap();
//        ObjectMapper mapper = new ObjectMapper();
//        List<Object> model = mapper.readValue(jsonObject.get("data").toString(), mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
//        result.put("list",  model);
//        result.put("total",  model.size());
//        return ResponseHelper.ok(result);
//    }
//
//    @POST
//    @Path("/roles")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getRole(RoleDtoModel model, @Context SecurityContext sc) throws Exception {
//
//        AccessTokenManager accessTokenManager = new AccessTokenManager();
//        accessTokenManager.setWebProperties(webProperties);
//        JsonNode js = null;
//        try {
//            js = accessTokenManager.requestTokenWithClientCredentials(webProperties.getProperty("oauth.client_id"), webProperties.getProperty("oauth.client_secret"));
//
//        } catch (Exception ex) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
//        }
//
//        String token = js.get("access_token").textValue();
//
//        String output = rs.getRequest(model.getBaseUrl()+ webProperties.getProperty("role.base.url") , token);
//
//        JSONObject jsonObject = new JSONObject(output);
//
//
//        HashMap<String,List<String>> result =
//                new ObjectMapper().readValue(jsonObject.get("data").toString(), HashMap.class);
//
//        Map<String, Object> filteredMap = result.entrySet()
//                .stream()
//                .filter(map -> map
//                        .getValue()
//                        .stream()
//                        .anyMatch(list -> list.contains(model.getApiPath()))
//                )
//        .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue() ));
//
//        return ResponseHelper.ok(filteredMap);
//    }
//}
