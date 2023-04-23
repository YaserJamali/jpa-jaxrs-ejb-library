package tamin.library.model.service;


import tamin.library.model.entity.Book;
import tamin.library.model.repository.AuthorRepository;
import tamin.library.model.repository.BookRepository;
import tamin.library.model.service.BL.BookBeanNumberGenerator;
import tamin.library.model.util.JPA;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
@SessionScoped
@Named
//@CDIUI("")
//@Push

public class BookServices extends Services<Book> implements Serializable {
    private static  BookServices instance ;

    private BookServices() {

    }

    public static BookServices getBookServices() {

            if (instance == null) {
                synchronized (BookServices.class) {
                    if (instance == null) {
                        instance = new BookServices();
                    }
                }
            }
            ;
            return instance;
        }


    @Override
    public Book save(Book book) {
        return BookRepository.getBookRepository().save(book);
    }

//    @Override
//    public Book edit(Book book) {
//        return BookRepository.getBookRepository().edit(book);
//    }

    @Override
    public Book remove(Long id) {
        return BookRepository.getBookRepository().remove(id);
    }

    @Override
    public Book findById(Long id) {
        return BookRepository.getBookRepository().findById(id);
    }

    @Override
    public List<Book> findAll() {
        return BookRepository.getBookRepository().findAll();
    }

   @Inject
   private BookBeanNumberGenerator generator;
    @Inject
    public int Test1(){
        return this.generator.test();
   }
}
