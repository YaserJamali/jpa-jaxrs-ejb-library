package tamin.library.controller;

public class CdController {
    private static CdController instance ;

    private CdController() {

    }

    public static CdController getInstance() {
        if (instance == null) {
            synchronized (CdController.class) {
                if (instance == null) {
                    instance = new CdController();
                }
            }
        }
        return instance;
    }
}
