package tamin.library.model.entity;


import com.google.gson.Gson;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


import static javax.persistence.CascadeType.PERSIST;

@Entity(name = "authorEntity")
@Table(name = "author_table")
@DiscriminatorValue("AUTHOR")
public class Author extends Artist {


    // ======================================
    // =             Attributes             =
    // ======================================
//    @Column(columnDefinition = "number(2)")
//    @Enumerated(EnumType.ORDINAL)
//    private Language language;
    @OneToMany(cascade = PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "books_fk",
            joinColumns = @JoinColumn(name = "a_fk"),
            inverseJoinColumns = @JoinColumn(name = "b_fk"))
    private Set<Book> bookList = new HashSet<>();

    // ======================================
    // =     Lifecycle Callback Methods     =
    // ======================================





    // ======================================
    // =          Getters & Setters         =
    // ======================================

//    public Language getLanguage() {
//        return language;
//    }
//
//    public Author setLanguage(Language language) {
//        this.language = language;
//        return this;
//    }

    public Set<Book> getBookList() {
        return bookList;
    }

    public Author setBookList(Set<Book> bookList) {
        this.bookList = bookList;
        return this;
    }

    // ======================================
    // =    hashcode, equals & toString     =
    // ======================================


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        Author author = (Author) o;
//        return Objects.equals(bookList, author.bookList);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), bookList);
//    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
