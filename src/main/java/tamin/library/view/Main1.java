//package tamin.library.view;
//
//import tamin.library.model.entity.*;
//import tamin.library.model.repository.BookRepository;
//import tamin.library.model.repository.MusicianRepository;
//import tamin.library.model.service.AuthorServices;
//import tamin.library.model.service.CdServices;
//import tamin.library.model.service.MusicianServices;
//
//import java.time.LocalDate;
//import java.time.Period;
//import java.util.HashSet;
//import java.util.Set;
//
//
//public class Main1 {
//    public static void main(String[] args) throws Exception {
//        Set<Book> dostoyevskyBooks = new HashSet<>();
//        dostoyevskyBooks.add(new Book("Fool", "The Complex Novel Of Fyodor Dostoyevsky", 178.5F, "123-456-789", 348, LocalDate.of(1882, 5, 16)));
//        dostoyevskyBooks.add(new Book("The Consequence Of Crimes", "The Most Famous Fyodor Dostoyevsky's Novel", 700.9F, "200-321-159", 602, LocalDate.of(1887, 9, 8)));
//        dostoyevskyBooks.add(new Book("The Bright Nights", "The Least  Novel Of Fyodor Dostoyevsky", 102.99F, "221-761-234", 95, LocalDate.of(1886, 6, 12)));
//        Author fyodorDostoyevsky = new Author();
//        fyodorDostoyevsky.setName("Fyodor").
//                setFamily("Dostoyevsky").
//                setBio( "One Of The most Famous Russian's Authors").
//                setDateOfBirth(LocalDate.of(1821, 10, 11));
//
////                ("Fyodor", "Dostoyevsky", "One Of The most Famous Russian's Authors",
////                LocalDate.of(1821, 10, 11), RUSSIAN);
//        fyodorDostoyevsky.setBookList(dostoyevskyBooks);
//        AuthorServices.getAuthorServices().addAuthorAndBooks(dostoyevskyBooks, fyodorDostoyevsky);
//
//
////        BookServices.getBookServices().save(new Book("Fool", "The Complex Novel Of Fidor Dastayoski", 178.5F, "123-456-789", 348, LocalDate.of(1882, 5, 16)));
//
//        Set<Book> hemingwayBooks = new HashSet<>();
//        hemingwayBooks.add(new Book("The Sun Also Rises", "The Sun Also Rises is a 1926 novel by American writer Ernest Hemingway, his first, that portrays American and British expatriates who travel from Paris to the Festival of San Fermín in Pamplona to watch the running of the bulls and the bullfights.",
//                352.2F, "123-456-789", 254, LocalDate.of(1926, 1, 22)));
//        hemingwayBooks.add(new Book("The Old Man And Sea", "July 2nd, 1961 was the highly influential American author of such classic novels as The Sun Also Rises, For Whom the Bell Tolls",
//                702.F, "123-875-623", 202, LocalDate.of(1926, 7, 2)));
//
//        Author ernestHemingway=new Author();
//        ernestHemingway.setName("Ernest").
//                setFamily("Hemingway").
//                setBio("Ernest Miller Hemingway (July 21, 1899 – July 2, 1961) was an American novelist, short-story writer, and journalist").
//                setDateOfBirth(LocalDate.of(1899, 7, 21));
//
//        AuthorServices.getAuthorServices().addAuthorAndBooks(hemingwayBooks, ernestHemingway);
//
//
////        BookServices.getBookServices().save(new Book("The Old Man And Sea", "This Is About The Man Who Just Loves Sea and He wen to Sea Every Day", 162.78F, "782-264-326", 199, LocalDate.of(1925, 12, 19)));
////
////
////
////
////
////
////
////
//        BookServices.getBookServices().save(new Book("Confidence", "The Phsycolgical book About Confidence", 405.99F, "100-289-888", 520, LocalDate.of(1906, 6, 13)));
//
//        AuthorServices.getAuthorServices().save(new Author("Ali", "Janadaghi", "best man in java",
//                LocalDate.of(1356, 7, 18), 45, JAPANESE));
//        AuthorServices.getAuthorServices().save(new Author("Robin", "Williams", "Romance Writer",
//                LocalDate.of(1340, 3, 5), 38, ENGLISH));
//        AuthorServices.getAuthorServices().save(new Author("Marc", "Marques", "Winner Of The Nobel Prize",
//                LocalDate.of(1352, 9, 27), 45, PORTUGUESE));
//        System.out.println(AuthorServices.getAuthorServices().findAll());
////
//
//        Set<Musician> metalica = new HashSet<>();
//        Musician sara=new Musician();
//        sara.setName("Sara").setFamily("Ride").setBio("Pianist of the band").setDateOfBirth(LocalDate.of(1978, 8, 23));
////        metalica.add(new Musician("Sara", "Ride", "Pianist of the band", LocalDate.of(1978, 8, 23), "Piano"));
////        metalica.add(new Musician("James", "Jamin", "Singer of the band", LocalDate.of(1980, 5, 7), "Guitar"));
////        metalica.add(new Musician("Horna", "Michel", "Guitarist of the band", LocalDate.of(1987, 1, 10), "Guitar"));
////        metalica.add(new Musician("Kara", "Davis", "Dramer of the band", LocalDate.of(1992, 9, 18), "Dram"));
//        CD nothingElseMatter = new CD();
//        nothingElseMatter.setTitle("Nothing Else Matter").setDescription("Wonderful and the best music of this band").
//        setGenre("Metal").setUnitCost(150F).setTotalDuration(5.5F);
//
////                ("Nothing Else Matter", "Wonderful and the best music of this band", 150F, 5.5F, "Metal");
////        CdServices.getCdServices().addCdAndMusicianOfTheBand(metalica, nothingElseMatter);
//
//
////        Set<Musician> beatles = new HashSet<>();
////        beatles.add(new Musician("Jhon", "Lenon", "Singer of the band", LocalDate.of(1956, 8, 15), "Singer"));
////        beatles.add(new Musician("Paul", "McCartney", "Dramer of the band", LocalDate.of(1962, 3, 25), "Dramer"));
////        beatles.add(new Musician("Ringo", "Starr", "Dancer of the band", LocalDate.of(1970, 11, 14), "Dancing"));
////        beatles.add(new Musician("George", "Harrison", "Vialonis of the band", LocalDate.of(1964, 4, 20), "Violon"));
////        CD sergentPepper = new CD("Sergent Pepper", "Best song of their band", 15.5F, 17.3F, "Rock");
////
////        CdServices.getCdServices().addCdAndMusicianOfTheBand(beatles, sergentPepper);
////        System.out.println(MusicianServices.getMusicianServices().findById(2L));
////        System.out.println(MusicianServices.getMusicianServices().findAll());
////        System.out.println(CdServices.getCdServices().findById(2L));
////        System.out.println(CdServices.getCdServices().findAll());
//
////        Book book = BookServices.getBookServices().raiseUnitCost(1L, 14.2F);
////        System.out.println(book);
//        System.out.println(BookRepository.getBookRepository().findBookByTitle("ler"));
//        System.out.println(BookRepository.getBookRepository().findAll());
//        System.out.println(BookRepository.getBookRepository().findByDate(LocalDate.of(2000, 1, 1)));
//
//        System.out.println(MusicianRepository.getInstance().findByName("ra"));
//       // JPA.getJpa().close();
//int age= Period.between(LocalDate.of(1986,6,30),LocalDate.now()).getYears();
//        System.out.println(age);
//
////        AuthorServices.getAuthorServices().save(new Author("", null, "One Of The most Famous Russian's Authors",
////             null, 45, RUSSIAN));
//        Author yaser =new Author();
//        yaser.setName("Yaser").setFamily("Jamlai").setDateOfBirth(LocalDate.of(1986, 6, 30));
////                ((11L,"Yaser", "Jamlai","good",
////               LocalDate.of(1986, 6, 30),age, Language.CHINESE);
//        AuthorServices.getAuthorServices().save(yaser);
//        System.out.println(yaser);
////        AuthorServices.getAuthorServices().save();
////        AuthorServices.getAuthorServices().remove(2L);
//
//
//
//
//
//
//
//
//
//    }
//}
