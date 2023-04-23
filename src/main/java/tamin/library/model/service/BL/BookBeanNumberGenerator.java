package tamin.library.model.service.BL;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Random;


public class BookBeanNumberGenerator implements Serializable {
    public int test(){
        return new Random().nextInt();
    }


}
