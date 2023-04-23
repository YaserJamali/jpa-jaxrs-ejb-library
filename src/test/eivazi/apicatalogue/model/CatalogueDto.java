//package ir.tamin.infra.apicatalogue.model;
//
//import ir.tamin.framework.domain.BaseEntity;
//
//import javax.persistence.*;
//import java.util.List;
//
//
//public class CatalogueDto  {
//
//    private String system;
//    private String service;
//    private List<Role> roles;
//    private String serviceUrl;
//    private String producer;
//    private String unit;
//    private String description;
//
//    public CatalogueDto(String system, String service, String serviceUrl, String producer, String unit, String description) {
//        this.system = system;
//        this.service = service;
//        this.serviceUrl = serviceUrl;
//        this.producer = producer;
//        this.unit = unit;
//        this.description = description;
//    }
//
//    public CatalogueDto() {
//    }
//
//
//    public String getSystem() {
//        return system;
//    }
//
//    public void setSystem(String system) {
//        this.system = system;
//    }
//
//    public String getService() {
//        return service;
//    }
//
//    public void setService(String service) {
//        this.service = service;
//    }
//
//
//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
//
//    public String getServiceUrl() {
//        return serviceUrl;
//    }
//
//    public void setServiceUrl(String serviceUrl) {
//        this.serviceUrl = serviceUrl;
//    }
//
//    public String getProducer() {
//        return producer;
//    }
//
//    public void setProducer(String producer) {
//        this.producer = producer;
//    }
//
//    public String getUnit() {
//        return unit;
//    }
//
//    public void setUnit(String unit) {
//        this.unit = unit;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//}
