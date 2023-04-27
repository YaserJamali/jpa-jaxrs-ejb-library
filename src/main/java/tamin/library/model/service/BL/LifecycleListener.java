package tamin.library.model.service.BL;



import javax.persistence.*;


public class LifecycleListener {

  // ======================================
  // =          Lifecycle Methods         =
  // ======================================

  @PrePersist
  void prePersist(Object object) {
    System.out.println(".LifecycleListener       prePersist()");}

  @PostPersist
  void postPersist(Object object) {
    System.out.println(".LifecycleListener       postPersist()");
  }

  @PreUpdate
  void preUpdate(Object object) {
    System.out.println(".LifecycleListener       preUpdate()");
  }

  @PostUpdate
  void postUpdate(Object object) {
    System.out.println(".LifecycleListener       postUpdate()");
  }

  @PreRemove
  void preRemove(Object object) {
    System.out.println(".LifecycleListener       preRemove()");
  }

  @PostRemove
  void postRemove(Object object) {
    System.out.println(".LifecycleListener       postRemove()");
  }

  @PostLoad
  void postLoad(Object object) {
    System.out.println(".LifecycleListener       postLoad()");
  }



}