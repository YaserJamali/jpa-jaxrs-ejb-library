package tamin.library.service;


import com.google.gson.Gson;
import tamin.library.model.entity.Author;
import tamin.library.model.entity.Book;
import tamin.library.model.repository.AuthorRepository;
import tamin.library.model.repository.BookRepository;

import javax.inject.Inject;
import java.time.LocalDate;

public class AuthorServices extends Services<Author, String, Long> {
    private static AuthorServices instance;

    @Inject
    private AuthorRepository repository;

    private AuthorServices() {
        repository = AuthorRepository.getInstance();
    }

    public static AuthorServices getInstance() {
        if (instance == null) {
            instance = new AuthorServices();
        }
        return instance;
    }

    @Override
    public String save(Author author) {
        return new Gson().toJson(repository.save(author));
    }

    @Override
    public Author update(Author author) {
        return repository.update(author);
    }

    @Override
    public String findByName(String name) {
        if (name != null) {
            String temp = name.toLowerCase();
            return new Gson().toJson(repository.findByName(temp));
        }
        return new Gson().toJson("Please Enter A Valid Title ");
    }


    @Override
    public String findById(Long id) {
        if (repository.findById(id) == null) {
            return new Gson().toJson("Error:The Author Id Is Not Exist");
        }
        return new Gson().toJson(repository.findById(id));
    }

    @Override
    public String findAll() {
        return new Gson().toJson(repository.findAll());
    }


    @Override
    public String remove(Long id) {
        if (id != null && repository.findById(id) != null) {
            return new Gson().toJson(repository.remove(id) + " Is Removed");
        }
        return new Gson().toJson("This ID: " + id + " IS Not Exist");
    }

    public String updateInstance(Long id, String name, String family, LocalDate brithDay, String bio) {
        Author author = repository.findById(id);
        if (id != null && author != null) {
            if (name != null && brithDay != null && bio != null) {
                author.setName(name)
                        .setFamily(family)
                        .setDateOfBirth(brithDay)
                        .setBio(bio);
                repository.update(author);
                return new Gson().toJson(author);
            }
            return new Gson().toJson("Please Fill All Fields");
        }
        return new Gson().toJson("Error:The Author Id Is Not Exist");
    }

    public String saveInstance(String name, String family, LocalDate brithDay, String bio) {
        if (name != null && family != null && bio != null && brithDay != null) {
            Author author = new Author();
            author.setName(name)
                    .setFamily(family)
                    .setDateOfBirth(brithDay)
                    .setBio(bio);
            save(author);
            return new Gson().toJson(author);
        }
        return new Gson().toJson("The Information are Invalid or incomplete");
    }


    public String addAuthorsBooks(Long authorId, Long bookId) {
        if (authorId != null && bookId != null) {
            Author author = repository.findById(authorId);
            if (author != null) {
                Book book = BookRepository.getInstance().findById(bookId);
                if (book != null) {
                    author.getBookList().add(book);
                    update(author);
                    return new Gson().toJson(author);
                }
                return new Gson().toJson("ERROR There Is No Book With Id: " + bookId);
            }
            return new Gson().toJson("ERROR There Is No Author With Id: " + authorId);
        }
        return new Gson().toJson("ERROR:Please Give IDs");
    }
}
