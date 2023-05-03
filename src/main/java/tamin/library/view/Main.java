package tamin.library.view;

import tamin.library.service.CdServices;

public class Main {
    public static void main(String[] args)  {
        System.out.println(CdServices.getInstance().saveInstance("name", "family", 2.5, 3.5, "ddddd"));
    }
}
