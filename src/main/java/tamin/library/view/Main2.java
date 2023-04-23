package tamin.library.view;

import tamin.library.model.entity.*;
import tamin.library.model.repository.MusicianRepository;
import tamin.library.model.service.AuthorServices;
import tamin.library.model.service.CdServices;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;


public class Main2 {
    public static void main(String[] args) {

        Set<Book> dostoyevskyBooks = new HashSet<>();


        Book book1 = new Book();
        book1.setNbOfPage(65).setIsbn(book1.getIsbn())
                .setPublicationDate(LocalDate.of(1882, 5, 16))
                .setTitle("Fool")
                .setDescription("The Complex Novel Of Fyodor Dostoyevsky")
                .setUnitCost(12.0F);

        Book book2 = new Book();
        book2.setNbOfPage(602).setIsbn(book1.getIsbn()).
                setPublicationDate(LocalDate.of(1887, 9, 8)).
                setTitle("The Consequence Of Crimes").
                setUnitCost(70.9F).
                setDescription("The Most Famous Fyodor Dostoyevsky's Novel");

        Book book3 = new Book();
        book3.setNbOfPage(95).setIsbn(book1.getIsbn()).
                setPublicationDate(LocalDate.of(1886, 6, 12)).
                setTitle("The Bright Nights").
                setDescription("The Least  Novel Of Fyodor Dostoyevsky").
                setUnitCost(102.99F);


        Author fyodorDostoyevsky = new Author();
        dostoyevskyBooks.add(book1);
        dostoyevskyBooks.add(book2);
        dostoyevskyBooks.add(book3);

        fyodorDostoyevsky
                .setLanguage(Language.RUSSIAN).setBookList(dostoyevskyBooks)
                .setName("Fyodor").
                setFamily("Dostoyevsky").
                setBio("One Of The most Famous Russian's Authors").
                setDateOfBirth(LocalDate.of(1821, 10, 11));


        fyodorDostoyevsky.setBookList(dostoyevskyBooks);
//        AuthorServices.getAuthorServices().addAuthorAndBooks(dostoyevskyBooks, fyodorDostoyevsky);


        Set<Book> hemingwayBooks = new HashSet<>();

        Book book4 = new Book();
        book4.setNbOfPage(254).
                setPublicationDate(LocalDate.of(1926, 1, 22)).
                setTitle("The Sun Also Rises").
                setDescription("The Sun Also Rises is a 1926 novel by American writer Ernest Hemingway, his first, that portrays American and British expatriates who travel from Paris to the Festival of San Fermín in Pamplona to watch the running of the bulls and the bullfights.").
                setUnitCost(352.2F);

        Book book5 = new Book();
        book5.setNbOfPage(202).
                setPublicationDate(LocalDate.of(1926, 7, 2)).
                setTitle("The Old Man And Sea").
                setDescription("July 2nd, 1961 was the highly influential American author of such classic novels as The Sun Also Rises, For Whom the Bell Tolls")
                .setUnitCost(702.F);

        hemingwayBooks.add(book4);
        hemingwayBooks.add(book5);

        Author ernestHemingway = new Author();
        ernestHemingway.setLanguage(Language.ENGLISH).setBookList(hemingwayBooks).
                setName("Ernest").
                setFamily("Hemingway").
                setBio("Ernest Miller Hemingway (July 21, 1899 – July 2, 1961) was an American novelist, short-story writer, and journalist").
                setDateOfBirth(LocalDate.of(1899, 7, 21));


//        AuthorServices.getAuthorServices().addAuthorAndBooks(hemingwayBooks, ernestHemingway);


        Set<Musician> metalica = new HashSet<>();
        Musician musician1 = new Musician();
        musician1.setPreferredInstrument("Piano").setDateOfBirth(LocalDate.of(1978, 8, 23))
                .setBio("Pianist of the band")
                .setName("Sara")
                .setFamily("Ride");

        Musician musician2 = new Musician();
        musician2.setPreferredInstrument("Singer")
                .setDateOfBirth(LocalDate.of(1980, 5, 7)).
                setName("James")
                .setFamily("Jamin")
                .setBio("Singer of the band");


        Musician musician3 = new Musician();
        musician3.setPreferredInstrument("Guitar")
                .setName("Horna")
                .setFamily("Michel")
                .setBio("Guitarist of the band")
                .setDateOfBirth(LocalDate.of(1987, 1, 10));

        Musician musician4 = new Musician();
        musician4.setPreferredInstrument("Dramer").setName("Kara")
                .setFamily("Davis")
                .setBio("Dramer of the band")
                .setDateOfBirth(LocalDate.of(1992, 9, 18));

        metalica.add(musician1);
        metalica.add(musician2);
        metalica.add(musician3);
        metalica.add(musician4);


        CD cd1 = new CD();
        cd1.setMusicians(metalica).setTitle("Nothing Else Matter")
                .setDescription("Wonderful and the best music of this band")
                .setGenre("Metal").
                setUnitCost(150F).
                setTotalDuration(5.5F);
//        CdServices.getCdServices().addCdAndMusicianOfTheBand(metalica, cd1);


        Set<Musician> beatles = new HashSet<>();

        Musician musician5 = new Musician();
        musician5.setName("Jhon")
                .setFamily("Lenon")
                .setBio("Singer of the band")
                .setDateOfBirth(LocalDate.of(1956, 8, 15));

        Musician musician6 = new Musician();
        musician6.setName("Paul")
                .setFamily("McCartney")
                .setBio("Dramer of the band")
                .setDateOfBirth(LocalDate.of(1962, 3, 25));

        Musician musician7 = new Musician();
        musician7.setName("Ringo")
                .setFamily("Starr")
                .setBio("Dancer of the band")
                .setDateOfBirth(LocalDate.of(1970, 11, 14));

        Musician musician8 = new Musician();
        musician8.setName("George")
                .setFamily("Harrison")
                .setBio("Vialonis of the band")
                .setDateOfBirth(LocalDate.of(1964, 4, 20));

        beatles.add(musician5);
        beatles.add(musician6);
        beatles.add(musician7);
        beatles.add(musician8);

        CD cd2 = new CD();
        cd2.setMusicians(beatles).setTitle("Sergent Pepper")
                .setDescription("Best song of their band")
                .setGenre("Rock").
                setUnitCost(15.5F).
                setTotalDuration(8.5F);

//        CdServices.getCdServices().addCdAndMusicianOfTheBand(beatles, cd2);

        CdServices.getCdServices().save(cd1);
        CdServices.getCdServices().save(cd2);
        AuthorServices.getAuthorServices().save(ernestHemingway);
        AuthorServices.getAuthorServices().save(fyodorDostoyevsky);
        System.out.println(MusicianRepository.getInstance().findByName("ra"));
        int age = Period.between(LocalDate.of(1986, 6, 30), LocalDate.now()).getYears();
        System.out.println(age);


        Author yaser = new Author();
        yaser.setName("Yaser")
                .setFamily("Jamlai")
                .setDateOfBirth(LocalDate.of(1986, 6, 30));

        AuthorServices.getAuthorServices().save(yaser);
        System.out.println(yaser);


    }
}
