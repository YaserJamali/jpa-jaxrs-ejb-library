package tamin.library.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {
    // private static   JPA instance=new JPA();
//    private static EntityManagerFactory factory =
//            Persistence.createEntityManagerFactory("JPA-LIBRARY");
//    private JPA(){
//
//    }
//
//    public static JPA getJpa() {
//        return instance;
//    }
//    public  EntityManager getConnection(){
//      return factory.createEntityManager();
//
//    }
    private static JPA instance;

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("JPA");

    private JPA() {
    }


    public static JPA getInstance() {
        if (instance == null) {
            synchronized (JPA.class) {
                if (instance == null) {
                    instance = new JPA();
                }
            }
        }
        ;
        return instance;
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();

    }

//  @Override
//  public void close() throws Exception {
//    factory.close();
//  }
}

