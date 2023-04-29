package tamin.library.model.repository;


import tamin.library.model.entity.Musician;
import tamin.library.model.util.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class MusicianRepository extends CRUD<Musician> {
    private static MusicianRepository instance;

    private MusicianRepository() {

    }

    public static MusicianRepository getInstance() {

        if (instance == null) {
            instance = new MusicianRepository();
        }

        return instance;
    }


    @Override
    public Musician save(Musician musician) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(musician);
        transaction.commit();
        manager.close();
        return musician;
    }

    @Override
    public Musician update(Musician musician) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(musician);
        transaction.commit();
        manager.close();
        return musician;
    }


    @Override
    public Musician remove(Long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Musician musician = manager.find(Musician.class, id);
        if (musician != null) {
            manager.remove(musician);
            transaction.commit();
            manager.close();
            return musician;
        } else return null;
    }

    @Override
    public Musician findById(Long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        Musician musician = manager.find(Musician.class, id);
        manager.close();
        return musician;
    }

    @Override
    public List<Musician> findAll() {
        EntityManager manager = JPA.getInstance().getEntityManager();
        TypedQuery<Musician> query = manager.createQuery("select musicians from musicianEntity musicians order by id", Musician.class);
        List<Musician> musicianList = query.getResultList();
        manager.close();
        return musicianList;
    }


    public List<Musician> findByName(String name) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        TypedQuery<Musician> query = manager.createNamedQuery(Musician.FIND_BY_NAME, Musician.class);
        query.setParameter("musicanName", "%" + name + "%");
        List<Musician> list = query.getResultList();
        manager.close();
        return list;
    }

    public Musician saveInstance(String name, String family, String bio, LocalDate dateOfBirth, LocalDate dateOfDeath, String preferredInstrument) {
        Musician musician = new Musician();
        musician.setPreferredInstrument(preferredInstrument)
                .setDateOfDeath(dateOfDeath)
                .setName(name)
                .setFamily(family)
                .setBio(bio)
                .setDateOfBirth(dateOfBirth);
        return musician;
    }

    public Musician updateInstance(Long id, String name, String family, String bio, LocalDate brithDay, LocalDate deathDay, String preferredInstrument) {
        Musician musician = findById(id);
        musician.setPreferredInstrument(preferredInstrument)
                .setDateOfDeath(deathDay)
                .setName(name)
                .setFamily(family)
                .setBio(bio)
                .setDateOfBirth(brithDay)
                .setAge(Period.between(brithDay, deathDay).getYears());
        return musician;
    }


}
