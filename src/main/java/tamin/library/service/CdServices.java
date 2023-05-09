package tamin.library.service;


import com.google.gson.Gson;
import tamin.library.model.entity.Cd;
import tamin.library.model.entity.Musician;
import tamin.library.model.repository.CdRepository;
import tamin.library.model.repository.MusicianRepository;

import javax.inject.Inject;

public class CdServices implements Services<Cd, String, Long> {
    private static CdServices instance;

    @Inject
    private CdRepository repository;

    private CdServices() {
    }

    public static CdServices getInstance() {
        if (instance == null) {
            instance = new CdServices();
        }
        return instance;
    }

    @Override
    public void save(Cd cd) {
        repository.save(cd);
    }

    @Override
    public void update(Cd cd) {
        repository.update(cd);
    }

    @Override
    public String findByName(String title) {
        if (title != null) {
            return new Gson().toJson(repository.findByName(title));
        }
        return new Gson().toJson("Please Enter A Valid Title ");
    }



    @Override
    public String findById(Long id) {
        if (id != null) {
            Cd cd = repository.findById(id);
            if (cd != null) {
                return new Gson().toJson(cd);
            }
            return new Gson().toJson("There Is No Cd With This Id: " + id);
        }
        return new Gson().toJson("Please Enter A Valid Number ");
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
        return new Gson().toJson("There Is No Cd With This Id: " + id);
    }

    public String saveInstance(String title, String description, Double unitCost, Double totalDuration, String genre) {
        if (title != null && description != null && unitCost != null && totalDuration != null && genre != null) {
            Cd cd = new Cd();
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
        Cd cd = repository.findById(id);
        if (id != null && cd != null) {
            if (title != null && description != null && unitCost != null && totalDuration != null && genre != null) {
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
        return new Gson().toJson("There Is No Cd With This Id: " + id);
    }


    public String addMusicianToCd(Long cdId, Long musicianId) {
        if (cdId != null && musicianId != null) {
            Cd cd = repository.findById(cdId);
            if (cd != null) {
               Musician musician = MusicianRepository.getInstance().findById(musicianId);
                if (musician != null) {
                    cd.getMusicians().add(musician);
                    update(cd);
                    return new Gson().toJson(cd);
                }
                return new Gson().toJson("ERROR There Is No Musician With Id: " + musicianId);
            }
            return new Gson().toJson("ERROR There Is No Cd With Id: " + cdId);
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


