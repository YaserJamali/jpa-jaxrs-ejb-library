package tamin.library.model.service.BL;

import tamin.library.model.service.BookServices;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Random;


public class BookBeanNumberGenerator implements Serializable {
    private static BookBeanNumberGenerator instance;
    private BookBeanNumberGenerator(){}
    public static BookBeanNumberGenerator getInstance( ) {

        if (instance == null) {
            synchronized (BookServices.class) {
                if (instance == null) {
                    instance = new BookBeanNumberGenerator();
                }
            }
        }
        ;
        return instance;
    }

    private Integer nextNumber=1;
    public String test(){
        return "13-15489-"+nextNumber++;
    }


}
