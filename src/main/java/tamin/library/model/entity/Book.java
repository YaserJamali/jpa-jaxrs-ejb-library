package tamin.library.model.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "bookEntity")
@Table(name = "book_table")
@DiscriminatorValue("BOOK") //its work with @Entity Annotations
//@JoinTable(name = Book_artist,)
public class Book extends Item {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Column(length = 15)
    private String isbn;


    @Column(name = "nb_of_pages")
    private Integer nbOfPage;
    @Past
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    // ======================================
    // =            Constructors            =
    // ======================================


    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public Book setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
        return this;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Book setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }


    // ======================================
    // =    hashcode, equals & toString     =
    // ======================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(getIsbn(), book.getIsbn()) && Objects.equals(getNbOfPage(), book.getNbOfPage()) && Objects.equals(getPublicationDate(), book.getPublicationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIsbn(), getNbOfPage(), getPublicationDate());
    }

}
