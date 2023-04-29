package tamin.library.model.service;


import com.google.gson.Gson;
import tamin.library.model.entity.Musician;
import tamin.library.model.repository.MusicianRepository;

import java.time.LocalDate;

public class MusicianServices extends Services<Musician> {
    private static MusicianServices instance;

    private MusicianServices() {

    }

    public static MusicianServices getInstance() {
        if (instance == null) {
            instance = new MusicianServices();
        }
        return instance;
    }

    @Override
    public String save(Musician musician) {
        return new Gson().toJson(MusicianRepository.getInstance().save(musician));
    }

    @Override
    public Musician update(Musician musician) {
        return MusicianRepository.getInstance().update(musician);
    }

    @Override
    public String findById(Long id) {
        if (id != null && MusicianRepository.getInstance().findById(id) != null) {
            return new Gson().toJson(MusicianRepository.getInstance().findById(id));
        }
        return new Gson().toJson("Error:The Musician Id: " + id + " Is Not Exist ");
    }

    public String findByName(String name) {
        if (name != null) {
            return new Gson().toJson(MusicianRepository.getInstance().findByName(name));
        }
        return new Gson().toJson("ERROR: Please Give Name");
    }

    @Override
    public String findAll() {
        return listWrap(MusicianRepository.getInstance().findAll());
    }

    public String saveInstance(String name, String family, String bio, LocalDate dateOfBirth, LocalDate dateOfDeath, String preferredInstrument) {
        if (name != null && family != null && bio != null && dateOfBirth != null && dateOfDeath != null && preferredInstrument != null) {
            return new Gson().toJson(save(MusicianRepository.getInstance().saveInstance(name, family, bio, dateOfBirth, dateOfDeath, preferredInstrument)));

        }
        return new Gson().toJson("Please Fill All Fields");
    }


    public String updateInstance(Long id, String name, String family, String bio, LocalDate brithDay, LocalDate deathDay, String preferredInstrument) {
        if (id != null && MusicianRepository.getInstance().findById(id) != null) {
            if (name != null && brithDay != null && deathDay != null && bio != null) {
                return new Gson().toJson(update(MusicianRepository.getInstance().updateInstance(id, name, family, bio, brithDay, deathDay, preferredInstrument)));
            }
            return new Gson().toJson("Please Fill All Fields");
        }
        return new Gson().toJson("Error:The Musician Id: " + id + " Is Not Exist");
    }

    public String remove(Long id) {
        if (id != null && MusicianRepository.getInstance().findById(id) != null) {

            return new Gson().toJson(MusicianRepository.getInstance().remove(id) + " Is Removed");
        }
        return new Gson().toJson("Error:The Musician Id: " + id + " Is Not Exist");
    }
}
