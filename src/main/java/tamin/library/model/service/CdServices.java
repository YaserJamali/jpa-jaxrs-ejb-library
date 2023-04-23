package tamin.library.model.service;


import tamin.library.model.entity.CD;
import tamin.library.model.entity.Musician;
import tamin.library.model.repository.CdRepository;
import tamin.library.model.util.JPA;

import java.util.List;
import java.util.Set;

public class CdServices extends Services<CD>{
    private static  CdServices instance;
    private CdServices(){

    }
    public static CdServices getCdServices(){
        if (instance == null) {
            synchronized (CdServices.class) {
                if (instance == null) {
                    instance = new CdServices();
                }
            }
        }
        ;
        return instance;
    }

    @Override
    public CD save(CD cd) {
        return CdRepository.getCdRepository().save(cd);
    }

//    @Override
//    public CD edit(CD cd) {
//        return CdRepository.getCdRepository().edit(cd);
//    }

    @Override
    public CD remove(Long id) {
        return CdRepository.getCdRepository().remove(id);
    }

    @Override
    public CD findById(Long id) {
        return CdRepository.getCdRepository().findById(id);
    }

    @Override
    public List<CD> findAll() {
        return CdRepository.getCdRepository().findAll();
    }
    public CD addCdAndMusicianOfTheBand(Set<Musician> musicians, CD musicName){
        return CdRepository.getCdRepository().addCdAndMusicianOfTheBand(musicians,musicName);
    }
}
