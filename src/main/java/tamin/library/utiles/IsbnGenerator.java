package tamin.library.utiles;

import javax.inject.Singleton;
import javax.persistence.*;
import java.util.Random;

@EightDigits @Singleton
public class IsbnGenerator implements NumberGenerator {
    private static IsbnGenerator instance;

    public IsbnGenerator() {
    }



    public static IsbnGenerator getInstance() {
        if (instance == null)
            instance = new IsbnGenerator();
        return instance;
    }


    @EightDigits
    public String numberGenerator() {
        return "8-453"+new Random().nextInt(9999)+"-" +new Random().nextInt(9999);
    }

    @PrePersist
    public void prePersist(Object o) {

    }

    @PreUpdate
    public void preUpdate(Object o) {

    }

    @PreRemove
    public void preRemove(Object o) {

    }

    @PostLoad
    public void postLoad(Object o) {

    }

    @PostRemove
    public void postRemove(Object o) {

    }

    @PostUpdate
    public void postUpdate(Object o) {

    }

    @PostPersist
    public void postPersist(Object o) {

    }
}
