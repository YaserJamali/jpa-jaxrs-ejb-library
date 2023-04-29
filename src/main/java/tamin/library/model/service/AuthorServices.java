package tamin.library.model.service;


import com.google.gson.Gson;
import tamin.library.model.entity.Author;
import tamin.library.model.repository.AuthorRepository;

import java.time.LocalDate;

public class AuthorServices extends Services<Author> {
    private static AuthorServices instance;

    private AuthorServices() {

    }

    public static AuthorServices getInstance() {
        if (instance == null) {
            instance = new AuthorServices();
        }
        return instance;
    }

    @Override
    public String save(Author author) {
        return new Gson().toJson(AuthorRepository.getInstance().save(author));
    }

    @Override
    public Author update(Author author) {
        return AuthorRepository.getInstance().update(author);
    }


    @Override
    public String findById(Long id) {
        if (AuthorRepository.getInstance().findById(id) == null) {
            return new Gson().toJson("Error:The Author Id Is Not Exist");
        }
        return new Gson().toJson(AuthorRepository.getInstance().findById(id));

    }

    @Override
    public String findAll() {
        return listWrap(AuthorRepository.getInstance().findAll());
    }

    public String updateInstance(Long id, String name, String family, LocalDate brithDay, LocalDate deathDay, String bio) {
        if (id != null && AuthorRepository.getInstance().findById(id) != null) {

            if (name != null && brithDay != null && deathDay != null && bio != null) {
                return new Gson().toJson(update(AuthorRepository.getInstance().updateInstance(id, name, family, brithDay, deathDay, bio)));
            }
            return new Gson().toJson("Please Fill All Fields");
        }
        return new Gson().toJson("Error:The Author Id Is Not Exist");
    }

    public String saveInstance(String name, String family, LocalDate brithDay, LocalDate deathDay, String bio) {
        if (name != null && family != null && bio != null && brithDay != null && deathDay != null) {

            return new Gson().toJson(save(AuthorRepository.getInstance().saveInstance(name, family, brithDay, deathDay, bio)));

        }
        return new Gson().toJson("The Information are Invalid or incomplete");
    }

    public String remove(Long id) {
        if (id != null && AuthorRepository.getInstance().findById(id) != null) {

            return new Gson().toJson(AuthorRepository.getInstance().remove(id) + " Is Removed");
        }
        return new Gson().toJson("This ID: " + id + " IS Not Exist");
    }
}
