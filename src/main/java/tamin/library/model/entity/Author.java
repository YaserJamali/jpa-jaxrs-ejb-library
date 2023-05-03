package tamin.library.model.entity;


import com.google.gson.Gson;
import org.hibernate.annotations.NamedQuery;
import tamin.library.service.utiles.LifecycleListener;
import tamin.library.service.utiles.ValidationListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;

@Entity(name = "authorEntity")
@Table(name = "author_table")
@DiscriminatorValue("AUTHOR")
@EntityListeners({
        ValidationListener.class, LifecycleListener.class
})
@NamedQuery(name = "FIND_BY_AUTHOR_NAME", query = "select a from authorEntity a where lower(a.name) like lower(:name) order by id,name,family")
@NamedQuery(name = "ALL_AUTHORS", query = "select a  from authorEntity a order by id,name,family")
public class Author extends Artist {

    // ======================================
    // =             Attributes             =
    // ======================================
    @Column(columnDefinition = "number(2)")
    @Enumerated(EnumType.ORDINAL)
    private Language language;
    @OneToMany(cascade = PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "author_books_JOIN",
            joinColumns = @JoinColumn(name = "AUTHOR_fk"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_fk"))
    private Set<Book> bookList = new HashSet<>();

    // ======================================
    // =             NAMED-QUERIES          =
    // ======================================
    public final static String FIND_AUTHOR_NAME = "FIND_BY_AUTHOR_NAME";
    public final static String FIND_ALL_AUTHORS = "ALL_AUTHORS";


    // ======================================
    // =     Lifecycle Callback Methods     =
    // ======================================


    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Language getLanguage() {
        return language;
    }

    public Author setLanguage(Language language) {
        this.language = language;
        return this;
    }

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
