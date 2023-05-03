package tamin.library.model.repository;


import tamin.library.model.entity.CD;
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
public class CdRepository extends CRUD<CD, String, Long> {
    private static CdRepository instance;
    @Inject
    private EntityManager manager;

    @PersistenceContext
    @Produces
    private EntityTransaction transaction;

    @Inject
    private JPA jpa;

    private CdRepository() {
        jpa = JPA.getInstance();
    }

    public static CdRepository getInstance() {
        if (instance == null) {
            instance = new CdRepository();
        }
        return instance;
    }

    @Override
    @Transactional
    public CD save(CD cd) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(cd);
        transaction.commit();
        manager.close();
        return cd;
    }

    @Override
    @Transactional
    public CD update(CD cd) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(cd);
        transaction.commit();
        manager.close();
        return cd;
    }

    @Override
    public List<CD> findByName(String title) {
        manager = jpa.getEntityManager();
        TypedQuery<CD> query = manager.createNamedQuery(CD.FIND_BY_TITLE, CD.class);
        query.setParameter("title", "%" + title + "%");
        List<CD> list = query.getResultList();
        manager.close();
        return list;
    }

    @Override
    public CD findById(Long id) {
        manager = JPA.getInstance().getEntityManager();
        CD cd = manager.find(CD.class, id);
        manager.close();
        return cd;
    }

    @Override
    public List<CD> findAll() {
        manager = jpa.getEntityManager();
        TypedQuery<CD> query = manager.createNamedQuery(CD.FIND_ALL_CD, CD.class);
        List<CD> list = query.getResultList();
        manager.close();
        return list;
    }


    @Override
    @Transactional
    public CD remove(Long id) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        CD cd = manager.find(CD.class, id);
        manager.remove(cd);
        transaction.commit();
        manager.close();
        return cd;
    }

    public CD raiseUnitCost(Long id, Double raise) {
        manager = jpa.getEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();
        CD cd = manager.find(CD.class, id);
        cd.setUnitCost(cd.getUnitCost() + raise);
        transaction.commit();
        manager.close();
        return cd;

    }


}
