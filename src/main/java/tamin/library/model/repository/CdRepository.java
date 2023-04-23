package tamin.library.model.repository;


import tamin.library.model.entity.CD;
import tamin.library.model.entity.Musician;
import tamin.library.model.service.AuthorServices;
import tamin.library.model.util.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class CdRepository extends CRUD<CD> {
    private static  CdRepository instance;

    private CdRepository() {
    }
    public static CdRepository getCdRepository() {
        if (instance == null) {
            synchronized (CdRepository.class) {
                if (instance == null) {
                    instance = new CdRepository();
                }
            }
        }
        ;
        return instance;
    }

    @Override
    public CD save(CD cd) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        if (cd.getId()==null){
        manager.persist(cd);}
        manager.merge(cd);
        transaction.commit();
        manager.close();
        return cd;
    }

//    @Override
//    public CD edit(CD cd) {
//        EntityManager manager = JPA.getInstance().getEntityManager();
//        EntityTransaction transaction = manager.getTransaction();
//        transaction.begin();
//        manager.merge(cd);
//        transaction.commit();
//        manager.close();
//        return cd;
//    }

    @Override
    public CD remove(long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        CD cd = manager.find(CD.class, id);
        manager.remove(cd);
        transaction.commit();
        manager.close();
        return cd;
    }

    @Override
    public CD findById(long id) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        CD cd = manager.find(CD.class, id);
        manager.close();
        return cd;
    }

    @Override
    public List<CD> findAll() {
        EntityManager manager = JPA.getInstance().getEntityManager();

        Query query = manager.createQuery("select c from cdEntity c  inner join bookEntity b "  ) ;
//                " ,musicianEntity m ,CD_FK cd where " +
//                "c.id=cd.cdentity_id and m.id=cd.musicians_id_artist" );
        List<CD> cdList = query.getResultList();
        manager.close();
        return cdList;
    }

    public CD addCdAndMusicianOfTheBand(Set<Musician> musicians, CD musicName) {
        EntityManager manager = JPA.getInstance().getEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        // CD cd= save(musicName);
        //   CD cd=new CD();
        musicName.setMusicians(musicians);
        save(musicName);
        transaction.commit();
        manager.close();
        return musicName;

    }


}