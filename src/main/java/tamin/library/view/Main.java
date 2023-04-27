package tamin.library.view;

import tamin.library.controller.CdController;
import tamin.library.controller.api.CdApi;
import tamin.library.model.service.CdServices;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(CdController.getInstance().saveCd("name", "family", 2.5, 3.5, "ddddd"));
    }
}
