package tamin.library.model.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class JPA implements AutoCloseable {
    private static JPA instance;


    @PersistenceContext
    @Produces
    private final EntityManagerFactory factory;


    private JPA() {
        factory = Persistence.createEntityManagerFactory("JPA");
    }


    public static JPA getInstance() {
        if (instance == null) {
            synchronized (JPA.class) {
                if (instance == null) {
                    instance = new JPA();
                }
            }
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return factory.createEntityManager();

    }

    @Override
    public void close()  {
        factory.close();
    }
}

