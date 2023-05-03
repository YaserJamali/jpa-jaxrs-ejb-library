package tamin.library.model.entity;

import com.google.gson.Gson;
import org.hibernate.annotations.NamedQuery;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;

@Entity(name = "cdEntity")
@Table(name = "cd_table")
@DiscriminatorValue("CD")
@NamedQuery(name = "FIND_BY_CD_TITLE", query = "select c from cdEntity c where lower(c.title) like lower(:title) order by id ")
@NamedQuery(name = "ALL_CD", query = "select c  from cdEntity c order by id,title")
public class CD extends Item {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Column(name = "total_duration", columnDefinition = "double precision")
    private Double totalDuration;
    @Column
    private String genre;

    @OneToMany(cascade = PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "CD_MUSICIAN_JOIN",
            joinColumns = @JoinColumn(name = "CD_fk"),
            inverseJoinColumns = @JoinColumn(name = "MUSICIAN_fk"))

    private Set<Musician> musicians = new HashSet<>();


    // ======================================
    // =             NAMED-QUERIES          =
    // ======================================
    public final static String FIND_BY_TITLE = "FIND_BY_CD_TITLE";
    public final static String FIND_ALL_CD = "ALL_CD";


    // ======================================
    // =          Getters & Setters         =
    // ======================================


    public Double getTotalDuration() {
        return totalDuration;
    }

    public CD setTotalDuration(Double totalDuration) {
        this.totalDuration = totalDuration;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public CD setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public Set<Musician> getMusicians() {
        return musicians;
    }

    public CD setMusicians(Set<Musician> musicians) {
        this.musicians = musicians;
        return this;
    }


    // ======================================
    // =    hashcode, equals & toString     =
    // ======================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CD)) return false;
        if (!super.equals(o)) return false;
        CD cd = (CD) o;
        return Double.compare(cd.getTotalDuration(), getTotalDuration()) == 0 && Objects.equals(getGenre(), cd.getGenre()) && Objects.equals(getMusicians(), cd.getMusicians());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTotalDuration(), getGenre(), getMusicians());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
