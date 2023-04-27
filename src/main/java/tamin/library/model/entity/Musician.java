package tamin.library.model.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

import com.google.gson.Gson;
import org.hibernate.annotations.NamedQuery;
import tamin.library.model.service.BL.LifecycleListener;
import tamin.library.model.service.BL.ValidationListener;


@Entity(name = "musicianEntity")
@EntityListeners({
        ValidationListener.class, LifecycleListener.class

})
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

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
