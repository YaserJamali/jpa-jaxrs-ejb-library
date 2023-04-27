package tamin.library.controller;

import com.google.gson.Gson;
import tamin.library.model.entity.CD;
import tamin.library.model.service.CdServices;

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



    public  String saveCd(String title, String description, Double unitCost, Double totalDuration, String genre) {
        CD cd = new CD();
        cd.setTotalDuration(totalDuration);
        cd.setGenre(genre);
        cd.setTitle(title);
        cd.setDescription(description);
        cd.setUnitCost(unitCost);

        CdServices.getInstance().save(cd);
        return new Gson().toJson(cd);
    }


}
