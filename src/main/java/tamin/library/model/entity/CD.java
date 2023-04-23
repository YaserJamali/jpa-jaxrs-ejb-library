package tamin.library.model.entity;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import static javax.persistence.CascadeType.PERSIST;

@Entity(name = "cdEntity")
@Table(name = "cd_table")
@DiscriminatorValue("CD") //its work with @Entity Annotations
public class CD extends Item {

    // ======================================
    // =             Attributes             =
    // ======================================
//  @Column(name = "total_duration")
    private double totalDuration;

    private String genre;

    @OneToMany(cascade = PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "CD_FK")

    private Set<Musician> musicians = new HashSet<>();


    // ======================================
    // =            Constructors            =
    // ======================================


//    public CD() {
//    }
//
//
//    public CD(String title, String description, double unitCost, double totalDuration, String genre) {
//        super(title, description, unitCost);
//        this.totalDuration = totalDuration;
//        this.genre = genre;
//    }
//
//    public CD(Long id, String title, String description, double unitCost, double totalDuration, String genre) {
//        super(id, title, description, unitCost);
//        this.totalDuration = totalDuration;
//        this.genre = genre;
//    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getTitle() {
        return title;
    }

    public CD setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CD setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public CD setUnitCost(double unitCost) {
        this.unitCost = unitCost;
        return this;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public CD setTotalDuration(double totalDuration) {
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


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CD)) return false;
//        if (!super.equals(o)) return false;
//
//        CD cd = (CD) o;
//
//        if (!getTotalDuration().equals(cd.getTotalDuration())) return false;
//        if (!getGenre().equals(cd.getGenre())) return false;
//        return getMusicians().equals(cd.getMusicians());
//    }

//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + getTotalDuration().hashCode();
//        result = 31 * result + getGenre().hashCode();
//        result = 31 * result + getMusicians().hashCode();
//        return result;
//    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
