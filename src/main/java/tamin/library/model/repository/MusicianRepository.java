package tamin.library.model.repository;


import tamin.library.model.entity.Musician;
import tamin.library.model.util.JPA;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Singleton
public class MusicianRepository extends CRUD<Musician, String, Long> {

    private static MusicianRepository instance;

    @Inject
    private EntityManager manager;

    @PersistenceContext
    @Produces
    private EntityTransaction transaction;

    @Inject
    private JPA jpa;

    private MusicianRepository() {
        jpa = JPA.getInstance();
    }

    public static MusicianRepository getInstance() {
        if (instance == null) {
            instance = new MusicianRepository();
        }
        return instance;
    }

    @Override
    @Transactional
    public Musician save(Musician musician) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(musician);
        transaction.commit();
        manager.close();
        return musician;
    }

    @Override
    @Transactional
    public Musician update(Musician musician) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(musician);
        transaction.commit();
        manager.close();
        return musician;
    }

    @Override
    public List<Musician> findByName(String name1) {
        manager = jpa.getEntityManager();
        TypedQuery<Musician> query = manager.createNamedQuery(Musician.FIND_MUSICIAN_NAME, Musician.class);
        query.setParameter("name", "%" + name1 + "%");
        List<Musician> list = query.getResultList();
        manager.close();
        return list;
    }




    @Override
    public Musician findById(Long id) {
        manager = JPA.getInstance().getEntityManager();
        Musician musician = manager.find(Musician.class, id);
        manager.close();
        return musician;
    }

    @Override
    public List<Musician> findAll() {
        manager = jpa.getEntityManager();
        TypedQuery<Musician> query = manager.createNamedQuery(Musician.FIND_ALL_MUSICIAN, Musician.class);
        List<Musician> musicianList = query.getResultList();
        manager.close();
        return musicianList;
    }

    @Override
    @Transactional
    public Musician remove(Long id) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        Musician musician = manager.find(Musician.class, id);
        manager.remove(musician);
        transaction.commit();
        manager.close();
        return musician;
    }


}
