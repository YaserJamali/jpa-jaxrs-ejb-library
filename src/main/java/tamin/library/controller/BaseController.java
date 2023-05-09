package tamin.library.controller;

import javax.ws.rs.core.Response;
import java.io.Serializable;

public interface BaseController<R extends Response, S extends String, U extends Number> extends Serializable {
    R findById(U u);

    R findByName(S s);

    R findAll();

    R removeById(U u);

    R addRelation(U u1, U u2);
}
