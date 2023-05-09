package tamin.library.model.repository;


import java.io.Serializable;
import java.util.List;

public interface CRUD<T, S extends String, U extends Number> extends Serializable {

    T save(T t);

    T update(T t);

    T remove(U u);

    T findById(U u);

    List<T> findAll();

    List<T> findByName(S s);

}
