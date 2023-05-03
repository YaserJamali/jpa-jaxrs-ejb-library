package tamin.library.model.repository;

import tamin.library.model.util.JPA;

import java.util.List;

public abstract class CRUD<T,S,U extends Number> implements AutoCloseable {

    public abstract T save(T t);
    public abstract T update(T t);

    public abstract T remove(U u);

    public abstract T findById(U u);

    public abstract List<T> findAll();
    public abstract List<T> findByName(S s);

    @Override
    public void close()  {
        JPA.getInstance().getEntityManager().close();
    }

}
