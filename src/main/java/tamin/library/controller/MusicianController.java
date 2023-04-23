package tamin.library.controller;

public class MusicianController {
    private static MusicianController instance ;

    private MusicianController() {

    }

    public static MusicianController getInstance() {
        if (instance == null) {
            synchronized (MusicianController.class) {
                if (instance == null) {
                    instance = new MusicianController();
                }
            }
        }
        return instance;
    }
}
