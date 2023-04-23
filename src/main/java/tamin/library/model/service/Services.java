package tamin.library.model.service;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class Services<T>  implements AutoCloseable{
    private EntityManager entityManager;

    public   abstract T save(T t);

//    public    abstract T edit(T t);

    public  abstract T remove(Long id);

    public   abstract T findById(Long id);

    public   abstract List<T> findAll();

    @Override
    public void close() throws Exception {
        entityManager.close();

    }
}