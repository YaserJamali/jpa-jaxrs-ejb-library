package tamin.library.model.service;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class Services<T> implements AutoCloseable {
    @Inject
    private EntityManager entityManager;

    public abstract String save(T t);


    public abstract String remove(Long id);

    public abstract String findById(Long id);

    public   abstract String findAll();


    public String listWrap(List<T> list) {
        StringBuilder builder = new StringBuilder();
        for (T t : list) {
            builder.append(t).append("\n");
        }
        return new Gson().toJson(builder);
    }


    @Override
    public void close() throws Exception {
        entityManager.close();

    }
}