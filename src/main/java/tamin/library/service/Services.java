package tamin.library.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public abstract class Services<T, S extends String, U extends Number> implements AutoCloseable {
    @Inject
    private EntityManager entityManager;

    public abstract S save(T t);

    public abstract T update(T t);

    public abstract S remove(U u);

    public abstract S findById(U u);

    public abstract S findAll();

    public abstract S findByName(S s);


    @Override
    public void close() {
        entityManager.close();

    }
}