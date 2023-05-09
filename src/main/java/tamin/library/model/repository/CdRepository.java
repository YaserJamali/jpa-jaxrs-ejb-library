package tamin.library.model.repository;


import tamin.library.model.entity.Cd;
import tamin.library.utiles.Loggable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;

@Singleton
@RequestScoped
public class CdRepository implements CRUD<Cd, String, Long> {
    private static CdRepository instance;

    @PersistenceContext(unitName = "JPA")
    @Produces
    private EntityManager manager;


    private CdRepository() {

    }

    public static CdRepository getInstance() {
        if (instance == null) {
            instance = new CdRepository();
        }
        return instance;
    }

    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class, IllegalArgumentException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Cd save(Cd cd) {
        manager.persist(cd);
        return cd;
    }

    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class, IllegalArgumentException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Cd update(Cd cd) {
        manager.merge(cd);
        return cd;
    }

    @Override
    public List<Cd> findByName(String title) {
        TypedQuery<Cd> query = manager.createNamedQuery(Cd.FIND_BY_TITLE, Cd.class);
        query.setParameter("title", "%" + title + "%");
        List<Cd> list = query.getResultList();
        return list;
    }

    @Override
    public Cd findById(Long id) {
        Cd cd = manager.find(Cd.class, id);
        return cd;
    }

    @Override
    public List<Cd> findAll() {
        TypedQuery<Cd> query = manager.createNamedQuery(Cd.FIND_ALL_CD, Cd.class);
        List<Cd> list = query.getResultList();
        return list;
    }


    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Cd remove(Long id) {
        Cd cd = manager.find(Cd.class, id);
        manager.remove(cd);
        return cd;
    }

    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Cd raiseUnitCost(Long id, Double raise) {
        Cd cd = manager.find(Cd.class, id);
        cd.setUnitCost(cd.getUnitCost() + raise);
        return cd;

    }
}
