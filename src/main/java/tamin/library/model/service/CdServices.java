package tamin.library.model.service;


import com.google.gson.Gson;
import tamin.library.model.entity.CD;
import tamin.library.model.repository.CdRepository;
import tamin.library.model.repository.MusicianRepository;
import tamin.library.model.service.BL.Utils;

public class CdServices extends Services<CD>{
    private static  CdServices instance;

    private CdServices(){

    }
    public static CdServices getInstance(){
        if (instance == null) {
            synchronized (CdServices.class) {
                if (instance == null) {
                    instance = new CdServices();
                }
            }
        }
        return instance;
    }

    @Override
    public String save(CD cd) {
        return new Gson().toJson(CdRepository.getInstance().save(cd));
    }


    @Override
    public String remove(Long id) {
        return new Gson().toJson(CdRepository.getInstance().remove(id));
    }

    @Override
    public String findById(Long id) {
        return new Gson().toJson(CdRepository.getInstance().findById(id));
    }

    @Override
    public String findAll() {
        return listWrap(CdRepository.getInstance().findAll());
    }



}
