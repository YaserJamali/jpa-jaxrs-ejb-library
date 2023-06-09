package tamin.library.model.entity;

import com.google.gson.Gson;
import tamin.library.utiles.AgeCalculationListener;
import tamin.library.utiles.LifecycleListener;
import tamin.library.utiles.Loggable;
import tamin.library.utiles.ValidationListener;

import javax.ejb.Stateful;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

//@MappedSuperclass
@Entity(name = "artistEntity")
@Table(name = "ARTIST_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EntityListeners({AgeCalculationListener.class, ValidationListener.class, LifecycleListener.class})
@DiscriminatorColumn(name = "ARTIST", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PERSON")
@Loggable
public abstract class Artist implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Id()
    @Column(name = "ID_ARTIST")
    @SequenceGenerator(name = "artistSeq", sequenceName = "artist_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artistSeq")
    private Long id;


    @Version
    private Integer version;

    @NotNull
    @Size(min = 4, max = 20)
    @Column(columnDefinition = "nvarchar2(35)", nullable = false)
    private String name;

    @Column(columnDefinition = "nvarchar2(35)", nullable = false)
    private String family;

    @Column(columnDefinition = "varchar2(500)")
    private String bio;


    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;



    @Transient
    @Column(name = "AGE")
    private Integer age;


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

    public Integer getVersion() {
        return version;
    }

    public Artist setVersion(Integer version) {
        this.version = version;
        return this;
    }


// ======================================
    // =    hashcode, equals & toString     =
    // ======================================


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Artist)) return false;
//        Artist artist = (Artist) o;
//        return Objects.equals(getId(), artist.getId()) && Objects.equals(getName(), artist.getName()) &&
//                Objects.equals(getFamily(), artist.getFamily()) &&
//                Objects.equals(getBio(), artist.getBio()) &&
//                Objects.equals(getDateOfBirth(), artist.getDateOfBirth()) &&
//                Objects.equals(getAge(), artist.getAge());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), getName(), getFamily(), getBio(), getDateOfBirth(), getAge());
//    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
