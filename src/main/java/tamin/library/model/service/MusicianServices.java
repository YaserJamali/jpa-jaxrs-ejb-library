package tamin.library.model.service;


import com.google.gson.Gson;
import tamin.library.model.entity.Musician;
import tamin.library.model.repository.MusicianRepository;
import tamin.library.model.util.JPA;

import java.util.List;

public class MusicianServices extends Services<Musician>{
    private static  MusicianServices instance;
    private MusicianServices(){

    }

    public static MusicianServices getMusicianServices() {
        if (instance == null) {
            synchronized (MusicianServices.class) {
                if (instance == null) {
                    instance = new MusicianServices();
                }
            }
        }
        ;
        return instance;
    }

    @Override
    public String save(Musician musician) {
        return new Gson().toJson(MusicianRepository.getInstance().save(musician));
    }

    @Override
    public String remove(Long id) {
        return new Gson().toJson(MusicianRepository.getInstance().remove(id));
    }

    @Override
    public String findById(Long id) {
        return new Gson().toJson(MusicianRepository.getInstance().findById(id));
    }

    @Override
    public String  findAll() {
        return listWrap(MusicianRepository.getInstance().findAll());
    }
}
