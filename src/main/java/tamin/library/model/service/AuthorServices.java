package tamin.library.model.service;


import tamin.library.model.entity.Author;
import tamin.library.model.entity.Book;
import tamin.library.model.repository.AuthorRepository;
import tamin.library.model.util.JPA;

import java.util.List;
import java.util.Set;

public class AuthorServices extends Services<Author> {
    private static  AuthorServices instance;

    private AuthorServices() {

    }

    public static AuthorServices getAuthorServices() {
        if (instance == null) {
            synchronized (AuthorServices.class) {
                if (instance == null) {
                    instance = new AuthorServices();
                }
            }
        }
        ;
        return instance;
    }

    @Override
    public Author save(Author author) {
        return AuthorRepository.getAuthorRepository().save(author);
    }

//    @Override
//    public Author edit(Author author) {
//        return AuthorRepository.getAuthorRepository().edit(author);
//    }

    @Override
    public Author remove(Long id) {
        return AuthorRepository.getAuthorRepository().remove(id);
    }

    @Override
    public Author findById(Long id) {
        return AuthorRepository.getAuthorRepository().findById(id);
    }

    @Override
    public List<Author> findAll() {
        return AuthorRepository.getAuthorRepository().findAll();
    }

    public Author addAuthorAndBooks(Set<Book> booksList, Author author) {
        return AuthorRepository.getAuthorRepository().addAuthorAndBooks(booksList, author);
    }
    private void validate(){



    }
}
