package tamin.library.model.repository;

import com.google.gson.Gson;
import tamin.library.model.util.JPA;

import java.util.List;

public abstract class CRUD<T> implements AutoCloseable {

    public abstract T save(T t);
    public abstract T update(T t);

    public abstract T remove(Long id);

    public abstract T findById(Long id);

    public abstract List<T> findAll();

    public String listWrap(List<T> list) {
        StringBuilder builder = new StringBuilder();
        for (T t : list) {
            builder.append(t).append("\n");
        }
        return new Gson().toJson(builder);
    }


    @Override
    public void close() throws Exception {
        JPA.getInstance().getEntityManager().close();
    }

}
