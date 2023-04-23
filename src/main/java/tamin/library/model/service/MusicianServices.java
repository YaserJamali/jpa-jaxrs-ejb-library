package tamin.library.model.service;


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
    public Musician save(Musician musician) {
        return MusicianRepository.getInstance().save(musician);
    }

//    @Override
//    public Musician edit(Musician musician) {
//        return MusicianRepository.getInstance().edit(musician);
//    }

    @Override
    public Musician remove(Long id) {
        return MusicianRepository.getInstance().remove(id);
    }

    @Override
    public Musician findById(Long id) {
        return MusicianRepository.getInstance().findById(id);
    }

    @Override
    public List<Musician> findAll() {
        return MusicianRepository.getInstance().findAll();
    }
}
