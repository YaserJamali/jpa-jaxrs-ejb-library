package tamin.library.service;

import java.io.Serializable;

public interface Services<T, S extends String, U extends Number> extends Serializable {

    void save(T t);

    void update(T t);

    S remove(U u);

    S findById(U u);

    S findAll();

    S findByName(S s);


}