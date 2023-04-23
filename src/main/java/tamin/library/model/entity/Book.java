package tamin.library.model.entity;

import com.google.gson.Gson;

import javax.persistence.*;
import java.time.LocalDate;

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

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Column(name = "nb_of_pages")
    private Integer nbOfPage;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    // ======================================
    // =            Constructors            =
    // ======================================

//    public Book() {
//    }
//
//
//    public Book(String title, String description, double unitCost, String isbn, Integer nbOfPage, LocalDate publicationDate) {
//        super(title, description, unitCost);
//        this.isbn = isbn;
//        this.nbOfPage = nbOfPage;
//        this.publicationDate = publicationDate;
//    }
//
//    public Book(Long id, String title, String description, double unitCost, String isbn, Integer nbOfPage, LocalDate publicationDate) {
//        super(id, title, description, unitCost);
//        this.isbn = isbn;
//        this.nbOfPage = nbOfPage;
//        this.publicationDate = publicationDate;
//    }


    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public Book setUnitCost(double unitCost) {
        this.unitCost = unitCost;
        return this;
    }

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
    // ======================================
    // =    hashcode, equals & toString     =
    // ======================================


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (!getIsbn().equals(book.getIsbn())) return false;
        if (!getNbOfPage().equals(book.getNbOfPage())) return false;
        return publicationDate.equals(book.publicationDate);
    }

    @Override
    public int hashCode() {
        int result = getIsbn().hashCode();
        result = 31 * result + getNbOfPage().hashCode();
        result = 31 * result + publicationDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
