package tamin.library.model.repository;


import tamin.library.model.entity.Musician;
import tamin.library.model.service.AuthorServices;
import tamin.library.model.util.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class MusicianRepository extends CRUD<Musician> {
    private static  MusicianRepository instance ;

    private MusicianRepository() {

    }

    public static MusicianRepository getInstance() {
        if (instance == null) {
            synchronized (MusicianRepository.class) {
                if (instance == null) {
                    instance = new MusicianRepository();
                }
            }
        }
        ;
        return instance;
    }


    @Override
    public Musician save(Musician musician) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        if(musician.getId()==null){
        manager.persist(musician);}
        manager.merge(musician);
        transaction.commit();
        manager.close();
        return musician;
    }

//    @Override
//    public Musician edit(Musician musician) {
//        EntityManager manager = JPA.getInstance().getEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();
//        manager.merge(musician);
//        transaction.commit();
//        manager.close();
//        return musician;
//    }

    @Override
    public Musician remove(long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Musician musician = manager.find(Musician.class, id);
        manager.persist(musician);
        transaction.commit();
        manager.close();
        return musician;
    }

    @Override
    public Musician findById(long id) {
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
        query.setParameter("musicanName","%"+ name+"%");
        List<Musician> list = query.getResultList();
        manager.close();
        return list;
    }

}
