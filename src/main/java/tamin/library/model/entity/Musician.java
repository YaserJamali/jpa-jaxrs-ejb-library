package tamin.library.model.entity;

import com.google.gson.Gson;
import org.hibernate.annotations.NamedQuery;
import tamin.library.utiles.LifecycleListener;
import tamin.library.utiles.Loggable;
import tamin.library.utiles.ValidationListener;

import javax.ejb.Stateful;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;


@Entity(name = "musicianEntity")
@EntityListeners({
        ValidationListener.class, LifecycleListener.class

})
@NamedQuery(name = "OLD_MUSICIAN", query = "select m.name,m.family,m.dateOfBirth   from musicianEntity m order by id,name,family")
@NamedQuery(name = "ALL_MUSICIAN", query = "select m  from musicianEntity m order by id,name,family")
@NamedQuery(name = "FIND_BY_MUSICIAN_NAME", query = "select m from musicianEntity m where lower(m.name) like lower(:name) order by id")
@NamedQuery(name = "findByInstrument", query = "select  m.id,m.name,m.family,m.bio" +
        " from  musicianEntity m  " +
        "where lower(m.preferredInstrument)" +
        " like '%?1%' order by m.id,m.name")
@Table(name = "musician_table")
@DiscriminatorValue("MUSICIAN")
@Stateful
@Loggable
public class Musician extends Artist {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Column(name = "preferred_instrument", columnDefinition = "nvarchar2(30)")
    private String preferredInstrument;

    @ManyToMany(cascade = PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "CD_MUSICIAN_JOIN")

    private Set<Cd> cds = new HashSet<>();


    // ======================================
    // =             NAMED-QUERIES          =
    // ======================================
    public final static String FIND_MUSICIAN_NAME = "FIND_BY_MUSICIAN_NAME";
    public final static String FIND_ALL_MUSICIAN = "ALL_MUSICIAN";


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

    public Set<Cd> getCds() {
        return cds;
    }

    public Musician setCds(Set<Cd> cds) {
        this.cds = cds;
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
