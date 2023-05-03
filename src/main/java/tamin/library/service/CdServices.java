package tamin.library.service;


import com.google.gson.Gson;
import tamin.library.model.entity.CD;
import tamin.library.model.entity.Musician;
import tamin.library.model.repository.CdRepository;
import tamin.library.model.repository.MusicianRepository;

import javax.inject.Inject;

public class CdServices extends Services<CD,String,Long> {
    private static CdServices instance;

    @Inject
    private CdRepository repository;

    private CdServices() {
        repository = CdRepository.getInstance();
    }

    public static CdServices getInstance() {
        if (instance == null) {
            instance = new CdServices();
        }
        return instance;
    }

    @Override
    public String save(CD cd) {
        return new Gson().toJson(repository.save(cd));
    }

    @Override
    public CD update(CD cd) {
        return repository.update(cd);
    }

    @Override
    public String findByName(String title) {
        if (title != null) {
            String temp = title.toLowerCase();
            return new Gson().toJson(repository.findByName(temp));
        }
        return new Gson().toJson("Please Enter A Valid Title ");
    }



    @Override
    public String findById(Long id) {
        if (id != null && repository.findById(id) != null) {


            return new Gson().toJson(repository.findById(id));
        }
        return new Gson().toJson("There Is No CD With This Id: " + id);
    }

    @Override
    public String findAll() {
        return new Gson().toJson(repository.findAll());
    }


    @Override
    public String remove(Long id) {
        if (id != null && repository.findById(id) != null) {

            return new Gson().toJson(repository.remove(id));
        }
        return new Gson().toJson("There Is No CD With This Id: " + id);
    }

    public String saveInstance(String title, String description, Double unitCost, Double totalDuration, String genre) {
        if (title != null && description != null && unitCost != null && totalDuration != null && genre != null) {
            CD cd = new CD();
            cd.setTotalDuration(totalDuration);
            cd.setGenre(genre);
            cd.setTitle(title);
            cd.setDescription(description);
            cd.setUnitCost(unitCost);
            repository.save(cd);
            return new Gson().toJson(cd);
        }
        return new Gson().toJson("Please Fill All Fields");
    }

    public String updateInstance(Long id, String title, String description, Double unitCost, Double totalDuration, String genre) {
        CD cd = repository.findById(id);
        if (title != null && cd != null) {
            if (description != null && unitCost != null && totalDuration != null && genre != null) {
                cd.setTotalDuration(totalDuration);
                cd.setGenre(genre);
                cd.setTitle(title);
                cd.setDescription(description);
                cd.setUnitCost(unitCost);
                repository.update(cd);
                return new Gson().toJson(cd);
            }
            return new Gson().toJson("Please Fill All Fields");
        }
        return new Gson().toJson("There Is No CD With This Id: " + id);
    }


    public String addMusicianToCd(Long cdId, Long musicianId) {
        if (cdId != null && musicianId != null) {
            CD cd = repository.findById(cdId);
            if (cd != null) {
               Musician musician = MusicianRepository.getInstance().findById(musicianId);
                if (musician != null) {
                    cd.getMusicians().add(musician);
                    update(cd);
                    return new Gson().toJson(cd);
                }
                return new Gson().toJson("ERROR There Is No Musician With Id: " + musicianId);
            }
            return new Gson().toJson("ERROR There Is No CD With Id: " + cdId);
        }
        return new Gson().toJson("ERROR:Please Give IDs");
    }

    public String raiseUnitCost(Long id, Double raise) {
        if (id != null && raise != null) {
            if (repository.findById(id) != null) {
                return new Gson().toJson(repository.raiseUnitCost(id, raise));
            }
            return new Gson().toJson("This ID: " + id + " IS NOT EXIST ANYMORE");
        }
        return new Gson().toJson("Please Fil All Expectations");
    }

}


