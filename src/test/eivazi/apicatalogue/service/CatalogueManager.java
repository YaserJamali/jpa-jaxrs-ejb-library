//package ir.tamin.infra.apicatalogue.service;
//
//import ir.tamin.framework.ws.rest.json.FilterWrapper;
//import ir.tamin.infra.apicatalogue.model.Catalogue;
//import ir.tamin.infra.apicatalogue.model.CatalogueDto;
//import ir.tamin.infra.apicatalogue.model.Role;
//
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import javax.persistence.metamodel.EntityType;
//import javax.persistence.metamodel.Metamodel;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Stateless
//public class CatalogueManager {
//    @Inject
//    private EntityManager em;
//
//    public void save(Catalogue entity) {
//        em.persist(entity);
//    }
//
//    public Map<String,Object> getAll(FilterWrapper filter, Integer start, Integer limit) {
//
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<CatalogueDto>  query = builder.createQuery(CatalogueDto.class);
//        Root<Catalogue> c = query.from(Catalogue.class);
//        Metamodel m = em.getMetamodel();
//
//        query.select(builder.construct(CatalogueDto.class,c.get("system"),c.get("service"),c.get("serviceUrl"),c.get("producer"),c.get("description"),c.get("unit"))).distinct(true);
//        TypedQuery createQuery = em.createQuery(query);
//
//        List<CatalogueDto> services = createQuery.setFirstResult(start).setMaxResults(limit)
//                .getResultList();
//        for(CatalogueDto item:services) {
//            List<Role> roles = getRoles(item.getSystem(),item.getService());
//            item.setRoles(roles);
//        }
//        Map<String,Object> result= new HashMap<>();
//        result.put("list",services);
//        result.put("total",1000);
//        return result;
//    }
//    public Map<String,Object> getAll2(FilterWrapper filter, Integer start, Integer limit) {
//        List<CatalogueDto> services =getAllService(filter,start,limit);
//        for(CatalogueDto item:services) {
//            List<Role> roles = getRoles(item.getSystem(),item.getService());
//            item.setRoles(roles);
//        }
//        Map<String,Object> result= new HashMap<>();
//        result.put("list",services);
//        result.put("total",1000);
//        return result;
//    }
//
//    private List<CatalogueDto> getAllService(FilterWrapper filter, Integer start, Integer limit) {
//
//
//       return em.createQuery("select distinct  new ir.tamin.infra.apicatalogue.model.Catalogue( t.system,t.service,t.serviceUrl,t.producer,t.description,t.unit) from Catalogue t ")
//               .setFirstResult(start)
//               .setMaxResults(limit)
//               .getResultList();
//    }
//
//
//    private List<Role> getRoles(String system, String service){
//        return  em.createQuery("select new ir.tamin.infra.apicatalogue.model.Role(t.role,t.roleType) from Catalogue t where t.system=:system and t.service=:service")
//                .setParameter("system",system)
//                .setParameter("service",service)
//                .getResultList();
//
//    }
//}
