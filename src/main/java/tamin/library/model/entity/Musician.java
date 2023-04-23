package tamin.library.model.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

import com.google.gson.Gson;
import org.hibernate.annotations.NamedQuery;


@Entity(name = "musicianEntity")
//@EntityListeners({
//        ValidationListener.class
//
//})
@NamedQuery(name = "OLD_MUSICIAN", query = "select m.name,m.family,m.dateOfBirth   from musicianEntity m order by id,name,family")
@NamedQuery(name = "ALL", query = "select m  from musicianEntity m order by id,name,family")
@NamedQuery(name = "findByName", query = "select m from musicianEntity m where m.name like :musicanName order by id,name,family")
@NamedQuery(name = "findByInstrument", query = "select  m.id,m.name,m.family,m.bio" +
        " from  musicianEntity m  " +
        "where lower(m.preferredInstrument)" +
        " like '%?1%' order by m.id,m.name")
@Table(name = "musician_table")
@DiscriminatorValue("MUSICIAN")
public class Musician extends Artist implements Serializable {


    // ======================================
    // =             NAMED-QUERIES             =
    // ======================================
    public final static String FIND_BY_NAME = "findByName";


    // ======================================
    // =             Attributes             =
    // ======================================

    @Column(name = "preferred_instrument", columnDefinition = "nvarchar2(30)")
    private String preferredInstrument;


    // ======================================
    // =            Constructors            =
    // ======================================

//    public Musician() {
//    }
//
//    public Musician(String name, String family, String bio, LocalDate dateOfBirth, String preferredInstrument) {
//        super(name, family, bio, dateOfBirth);
//        this.preferredInstrument = preferredInstrument;
//    }
//
//    public Musician(Long id, String name, String family, String bio, LocalDate dateOfBirth, Integer age, String preferredInstrument) {
//        super(id, name, family, bio, dateOfBirth, age);
//        this.preferredInstrument = preferredInstrument;
//    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getPreferredInstrument() {
        return preferredInstrument;
    }

    public Musician setPreferredInstrument(String preferredInstrument) {
        this.preferredInstrument = preferredInstrument;
        return this;
    }

    // ======================================
    // =    hashcode, equals & toString     =
    // ======================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musician)) return false;
        if (!super.equals(o)) return false;

        Musician musician = (Musician) o;

        return getPreferredInstrument().equals(musician.getPreferredInstrument());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
