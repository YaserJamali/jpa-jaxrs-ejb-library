package tamin.library.model.repository;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class CRUD<T> implements AutoCloseable {
    private EntityManager entityManager;

    public   abstract T save(T t);

// public    abstract T edit(T t);

    public  abstract T remove(long id);

    public   abstract T findById(long id);

    public   abstract List<T> findAll();

    @Override
      public void close() throws Exception {
        entityManager.close();

    }
}
