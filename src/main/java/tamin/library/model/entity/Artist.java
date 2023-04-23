package tamin.library.model.entity;

import com.google.gson.Gson;
import tamin.library.model.service.BL.AgeCalculationListener;
import tamin.library.model.service.BL.ValidationListener;

import javax.persistence.*;
import java.time.LocalDate;

//@MappedSuperclass
@Entity(name = "artistEntity")
@Table(name = "ARTIST_TABLE")
@EntityListeners({AgeCalculationListener.class, ValidationListener.class})
@DiscriminatorColumn(name = "ARTIST", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PERSON")


public abstract class Artist {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id()
    @Column(name = "ID_ARTIST")
    @SequenceGenerator(name = "artistSeq", sequenceName = "artist_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artistSeq")
    protected Long id;

    @Column(columnDefinition = "nvarchar2(35)", nullable = false)
    protected String name;

    @Column(columnDefinition = "nvarchar2(35)", nullable = false)
    protected String family;

    @Column(columnDefinition = "varchar2(500)")
    protected String bio;

    @Column(name = "date_of_birth")
    protected LocalDate dateOfBirth;

    @Transient
    @Column(name = "AGE")
    protected Integer age;


    // ======================================
    // =            Constructors            =
    // ======================================
//    public Artist() {
//    }
//
//    public Artist(String name, String family, LocalDate dateOfBirth) {
//        this.name = name;
//        this.family = family;
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public Artist(String name, String family, String bio, LocalDate dateOfBirth) {
//        this.name = name;
//        this.family = family;
//        this.bio = bio;
//        this.dateOfBirth = dateOfBirth;
//        this.age = age;
//    }
//
//    public Artist(Long id, String name, String family, String bio, LocalDate dateOfBirth, Integer age) {
//        this.id = id;
//        this.name = name;
//        this.family = family;
//        this.bio = bio;
//        this.dateOfBirth = dateOfBirth;
//        this.age = age;
//    }


    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public Artist setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Artist setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Artist setFamily(String family) {
        this.family = family;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public Artist setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Artist setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Artist setAge(Integer age) {
        this.age = age;
        return this;
    }


    // ======================================
    // =    hashcode, equals & toString     =
    // ======================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artist)) return false;

        Artist artist = (Artist) o;

        if (getId() != artist.getId()) return false;
        if (!getName().equals(artist.getName())) return false;
        if (!getFamily().equals(artist.getFamily())) return false;
        if (!getBio().equals(artist.getBio())) return false;
        if (!getDateOfBirth().equals(artist.getDateOfBirth())) return false;
        return getAge().equals(artist.getAge());
    }



    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
