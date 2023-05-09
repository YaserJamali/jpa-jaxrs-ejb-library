package tamin.library.model.repository;


import tamin.library.model.entity.Musician;
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
@Transactional
public class MusicianRepository implements CRUD<Musician, String, Long> {
    private static MusicianRepository instance;

    @PersistenceContext(unitName = "JPA")
    @Produces
    private EntityManager manager;

    private MusicianRepository() {

    }

    public static MusicianRepository getInstance() {
        if (instance == null) {
            instance = new MusicianRepository();
        }
        return instance;
    }

    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class, IllegalArgumentException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Musician save(Musician musician) {
        manager.persist(musician);
        return musician;
    }

    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class, IllegalArgumentException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Musician update(Musician musician) {
        manager.merge(musician);
        return musician;
    }

    @Override
    public List<Musician> findByName(String name1) {
        TypedQuery<Musician> query = manager.createNamedQuery(Musician.FIND_MUSICIAN_NAME, Musician.class);
        query.setParameter("name", "%" + name1 + "%");
        List<Musician> list = query.getResultList();
        manager.close();
        return list;
    }




    @Override
    public Musician findById(Long id) {
        Musician musician = manager.find(Musician.class, id);
        return musician;
    }

    @Override
    public List<Musician> findAll() {
        TypedQuery<Musician> query = manager.createNamedQuery(Musician.FIND_ALL_MUSICIAN, Musician.class);
        List<Musician> musicianList = query.getResultList();
        manager.close();
        return musicianList;
    }

    @Override
    @Loggable
    @Transactional(rollbackOn = {ArrayIndexOutOfBoundsException.class},
            dontRollbackOn = {SQLWarning.class, SQLException.class})
    public Musician remove(Long id) {
        Musician musician = manager.find(Musician.class, id);
        manager.remove(musician);
        return musician;
    }


}
