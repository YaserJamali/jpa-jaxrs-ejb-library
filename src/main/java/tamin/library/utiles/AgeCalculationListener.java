package tamin.library.utiles;




import tamin.library.model.entity.Artist;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.time.LocalDate;
import java.time.Period;


public class AgeCalculationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public  void calculateAge(Artist artist) {
        System.out.println(".AgeCalculationListener calculateAge()");
        if (artist.getDateOfBirth() == null) {
            artist.setAge(null);
            return;
        }
        artist.setAge(Period.between(artist.getDateOfBirth(), LocalDate.now()).getYears());
    }
}