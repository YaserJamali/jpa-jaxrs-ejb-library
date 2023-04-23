//package ir.tamin.infra.apicatalogue.model;
//
//import ir.tamin.framework.domain.AbstractEntity;
//import ir.tamin.framework.domain.BaseEntity;
//
//import javax.persistence.*;
//
//@Table(name = "Catalogue")
//@Entity
//
//public class Catalogue extends BaseEntity {
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
//    @Id
//    private Long id;
//    private String system;
//    private String service;
//    private String role;
//    private String roleType;
//    private String serviceUrl;
//    private String producer;
//    private String unit;
//    private String description;
//
//    public Catalogue(String system, String service, String serviceUrl, String producer, String unit, String description) {
//        this.system = system;
//        this.service = service;
//        this.serviceUrl = serviceUrl;
//        this.producer = producer;
//        this.unit = unit;
//        this.description = description;
//    }
//
//    public Catalogue() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
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
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getRoleType() {
//        return roleType;
//    }
//
//    public void setRoleType(String roleType) {
//        this.roleType = roleType;
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
//    @Override
//    public Object getIdentifierInstance() {
//        return id;
//    }
//}
