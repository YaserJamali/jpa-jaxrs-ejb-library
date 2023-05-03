package tamin.library.service;


import com.google.gson.Gson;
import tamin.library.model.entity.Musician;
import tamin.library.model.repository.MusicianRepository;

import javax.inject.Inject;
import java.time.LocalDate;


public class MusicianServices extends Services<Musician,String,Long> {


    @Inject
    private MusicianRepository repository;

    private static MusicianServices instance;

    private MusicianServices() {
        repository = MusicianRepository.getInstance();

    }

    public static MusicianServices getInstance() {
        if (instance == null) {
            instance = new MusicianServices();
        }
        return instance;
    }

    @Override
    public String save(Musician musician) {
        return new Gson().toJson(repository.save(musician));
    }

    @Override
    public Musician update(Musician musician) {
        return repository.update(musician);
    }
    @Override
    public String findByName(String name) {
        if (name != null) {
            String temp = name.toLowerCase();
            return new Gson().toJson(repository.findByName(temp));
        }
        return new Gson().toJson("Please Enter A Valid Title ");
    }
    @Override
    public String findById(Long id) {
        if (id != null && repository.findById(id) != null) {
            return new Gson().toJson(repository.findById(id));
        }
        return new Gson().toJson("Error:The Musician Id: " + id + " Is Not Exist ");
    }


    @Override
    public String findAll() {
        return new Gson().toJson(repository.findAll());
    }

    @Override
    public String remove(Long id) {
        if (id != null && repository.findById(id) != null) {

            return new Gson().toJson(repository.remove(id) + " Is Removed");
        }
        return new Gson().toJson("Error:The Musician Id: " + id + " Is Not Exist");
    }

    public String saveInstance(String name, String family, String bio, LocalDate dateOfBirth,  String preferredInstrument) {
        if (name != null && family != null && bio != null && dateOfBirth != null  && preferredInstrument != null) {
            Musician musician = new Musician();
            musician.setPreferredInstrument(preferredInstrument)
                    .setName(name)
                    .setFamily(family)
                    .setBio(bio)
                    .setDateOfBirth(dateOfBirth);
            repository.save(musician);
            return new Gson().toJson(musician);

        }
        return new Gson().toJson("Please Fill All Fields");
    }

    public String updateInstance(Long id, String name, String family, String bio, LocalDate brithDay, String preferredInstrument) {
        Musician musician = repository.findById(id);
        if (id != null && musician != null) {
            if (name != null && brithDay != null && bio != null) {
                musician.setPreferredInstrument(preferredInstrument)
                        .setName(name)
                        .setFamily(family)
                        .setBio(bio)
                        .setDateOfBirth(brithDay);
                return new Gson().toJson(repository.update(musician));
            }
            return new Gson().toJson("Please Fill All Fields");
        }
        return new Gson().toJson("Error:The Musician Id: " + id + " Is Not Exist");
    }

}
