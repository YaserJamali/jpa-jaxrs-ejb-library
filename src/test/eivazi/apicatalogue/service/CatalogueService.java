//package ir.tamin.infra.apicatalogue.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import ir.tamin.framework.cdi.util.WebProperties;
//import ir.tamin.framework.core.util.Bundle;
//import ir.tamin.framework.weblogic.oauth.oltu.client.AccessTokenManager;
//import ir.tamin.framework.ws.rest.json.Filter;
//import ir.tamin.framework.ws.rest.json.FilterWrapper;
//import ir.tamin.framework.ws.rest.proxy.EntityProxy;
//import ir.tamin.infra.apicatalogue.model.Catalogue;
//import ir.tamin.infra.apicatalogue.service.general.RestServices;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.utils.URLEncodedUtils;
//import org.apache.http.message.BasicNameValuePair;
//import org.json.JSONObject;
//
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//
//public class CatalogueService {
//
//    @Inject
//    private RestServices rs;
//    @Inject
//    @WebProperties
//    Bundle webProperties;
//    @Inject
//    private CatalogueManager manager;
//
//    public void getSystems() {
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
//        List<NameValuePair> nameValuePairs = new ArrayList<>();
//
//        FilterWrapper fw = new FilterWrapper();
//        Integer start = 0;
//        Integer limit = 1000;
//        if (start != null) {
//            nameValuePairs.save(new BasicNameValuePair("start", start.toString()));
//        }
//        if (limit != null) {
//            nameValuePairs.save(new BasicNameValuePair("limit", limit.toString()));
//        }
//        if (fw != null) {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                nameValuePairs.save(new BasicNameValuePair("filter", mapper.writeValueAsString(fw.getFilters())));
//
//            } catch (Exception ex) {
//                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
//            }
//        }
//        String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
//
//
//        String output = rs.getRequest(webProperties.getProperty("portal.api") + "?" + paramsString, token);
//
//        JSONObject jsonObject = new JSONObject(output);
//        try {
//
//
//            ObjectMapper mapper = new ObjectMapper();
//            List<HashMap<String, Object>> list = mapper.readValue(((JSONObject) jsonObject.get("data")).get("list").toString(), mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
//            List<Map<String, Map<String, Object>>> roleRelations;
////            for (HashMap<String, Object> item : list) {
////                roleRelations=getRoleRelations(item.get("roleCategoryName").toString());
////            }
//
//            for (Map<String, Object> item : list) {
//                roleRelations = getRoleRelations(item.get("roleCategoryName").toString());
//                if (item.get("roleCategoryDesc") != null) {
//                    try {
//                        getServices(item,roleRelations);
//                    } catch (Exception ex) {
//                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
//                    }
//
//                    Catalogue catalogue = new Catalogue();
//                    catalogue.setSystem(item.get("roleCategoryName").toString());
//
//                }
//
//
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
//        }
//
//    }
//
//    private void getServices(Map<String, Object> system, List<Map<String, Map<String, Object>>> relations) throws Exception {
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
//        String output = rs.getRequest(system.get("roleCategoryDesc") + webProperties.getProperty("role.base.url"), token);
//
//        JSONObject jsonObject = new JSONObject(output);
//
//
//        HashMap<String, List<String>> map =
//                new ObjectMapper().readValue(jsonObject.get("data").toString(), HashMap.class);
//
//        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//            for (String item : entry.getValue()) {
//                Catalogue catalogue = new Catalogue();
//                catalogue.setSystem(system.get("roleCategoryName").toString());
//                catalogue.setService(item);
//                catalogue.setRole(entry.getKey());
//                catalogue.setServiceUrl(system.get("roleCategoryDesc").toString() + "/" + item);
//                catalogue.setRoleType("0");
//                manager.save(catalogue);
//                //TODO find from relations
//
//                for (Map<String, Map<String, Object>> relation : relations) {
//                    if (relation.get("childRole").get("roleName").equals(entry.getKey())) {
//                        catalogue.setId(null);
//                        catalogue.setRole(relation.get("parentRole").get("roleDisplayName" ).toString());
//                        catalogue.setRoleType("1");
//                        manager.save(catalogue);
//                    }
//                }
//                // TODO loop and save
//
//            }
//
//        }
//    }
//
//    public List<Map<String, Map<String, Object>>> getRoleRelations(String system) throws Exception {
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
//        List<NameValuePair> nameValuePairs = new ArrayList<>();
//
//        FilterWrapper fw = new FilterWrapper();
//        fw.addFilter("parentRole.roleCategoryObj.roleCategoryName", Filter.Operator.EQUAL, "Default");
//        //fw.addFilter("childRole.roleName", Filter.Operator.EQUAL,"REQUEST SSO CRM");
//        Integer start = 0;
//        Integer limit = 1000;
//        if (start != null) {
//            nameValuePairs.save(new BasicNameValuePair("start", start.toString()));
//        }
//        if (limit != null) {
//            nameValuePairs.save(new BasicNameValuePair("limit", limit.toString()));
//        }
//        if (fw != null) {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                nameValuePairs.save(new BasicNameValuePair("filter", mapper.writeValueAsString(fw.getFilters())));
//
//            } catch (Exception ex) {
//                Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.getMessage());
//            }
//        }
//        String paramsString = URLEncodedUtils.format(nameValuePairs, "UTF-8");
//
//
//        String output = rs.getRequest(webProperties.getProperty("portal.role.relation") + "?" + paramsString, token);
//        JSONObject jsonObject = new JSONObject(output);
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<Map<String, Map<String, Object>>> list = mapper.readValue(((JSONObject) jsonObject.get("data")).get("list").toString(), mapper.getTypeFactory().constructCollectionType(List.class, Object.class));
////    list
////            .stream()
////            .filter(l -> l.entrySet().stream()
////                    .anyMatch(map -> map.getValue().entrySet().stream().anyMatch(t->t.getValue().equals("REQUEST SSO CRM")) )
////            )
////            .collect(Collectors.toList());
//
////    allSubjets
////            .stream()
////            .filter(ele -> ele.contains("Testing"))
////            .collect(Collectors.toList());
//        return list;
//    }
//}
//
