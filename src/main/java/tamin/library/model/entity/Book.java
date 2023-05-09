package tamin.library.model.entity;
import org.hibernate.annotations.NamedQuery;
import tamin.library.utiles.Loggable;

import javax.ejb.Stateful;
import javax.persistence.*;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "bookEntity")
@Table(name = "book_table")
@DiscriminatorValue("BOOK")
@NamedQuery(name = "FIND_BY_BOOK_TITLE", query = "select b from bookEntity b where lower(b.title) like lower(:bookTitle) order by id")
@NamedQuery(name = "ALL_BOOKS", query = "select b  from bookEntity b order by id,title")
@Stateful
@Loggable
public class Book extends Item implements Serializable {

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
    // =             NAMED-QUERIES          =
    // ======================================
    public final static String FIND_BY_TITLE = "FIND_BY_BOOK_TITLE";
    public final static String FIND_ALL_BOOKS = "ALL_BOOKS";


    // ======================================
    // =            Constructors            =
    // ======================================
@PrePersist
@PreUpdate
public void validate(){
    System.out.println(".DataValidationListener validate()");
    if (getTitle() == null || "".equals(getTitle()))
        throw new IllegalArgumentException("Invalid Title name");
}

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
