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
            synchronized (AuthorServices.class) {
                if (instance == null) {
                    instance = new AuthorServices();
                }
            }
        }
        return instance;
    }

    @Override
    public String save(Author author) {
        return new Gson().toJson(AuthorRepository.getInstance().save(author));
    }

//    @Override
//    public Author edit(Author author) {
//        return AuthorRepositor.getAuthorRepository().edit(author);
//    }

    @Override
    public String remove(Long id) {
        return new Gson().toJson(AuthorRepository.getInstance().remove(id));
    }

    @Override
    public String findById(Long id) {
        return new Gson().toJson(AuthorRepository.getInstance().findById(id));
    }

    @Override
    public String findAll() {
        return listWrap(AuthorRepository.getInstance().findAll());
    }

    public String update(Long id, String name, String family, LocalDate brithDay, LocalDate deathDay, String bio) {
        return AuthorRepository.getInstance().updateInstance(id, name, family, brithDay, deathDay, bio);
    }

    public String saveAuthor(String name, String family, LocalDate brithDay, LocalDate deathDay, String bio) {
        return AuthorRepository.getInstance().saveInstance(name, family, brithDay, deathDay, bio);
    }

    public String removeByID(Long id) {
        if (AuthorRepository.getInstance().findById(id) != null) {

            return new Gson().toJson(AuthorRepository.getInstance().remove(id)+" Is Removed");
        }
        return new Gson().toJson("This ID: " + id + " IS Not Exist");
    }


}
