package tamin.library.model.service;


import com.google.gson.Gson;
import tamin.library.model.entity.CD;
import tamin.library.model.repository.CdRepository;

public class CdServices extends Services<CD> {
    private static CdServices instance;

    private CdServices() {
    }

    public static CdServices getInstance() {
        if (instance == null) {
            instance = new CdServices();
        }
        return instance;
    }

    @Override
    public String save(CD cd) {
        return new Gson().toJson(CdRepository.getInstance().save(cd));
    }

    @Override
    public CD update(CD cd) {
        return CdRepository.getInstance().update(cd);
    }


    @Override
    public String remove(Long id) {
        if (id != null && CdRepository.getInstance().findById(id) != null) {

            return new Gson().toJson(CdRepository.getInstance().remove(id));
        }
        return new Gson().toJson("There Is No CD With This Id: " + id);
    }

    @Override
    public String findById(Long id) {
        if (id != null && CdRepository.getInstance().findById(id) != null) {


            return new Gson().toJson(CdRepository.getInstance().findById(id));
        }
        return new Gson().toJson("There Is No CD With This Id: " + id);
    }

    @Override
    public String findAll() {
        return new Gson().toJson(CdRepository.getInstance().findAll());
    }


    public String saveCd(String title, String description, Double unitCost, Double totalDuration, String genre) {
        if (title != null && description != null && unitCost != null && totalDuration != null && genre != null) {
            return new Gson().toJson(save(CdRepository.getInstance().saveInstance(title, description, unitCost, totalDuration, genre)));
        }

        return new Gson().toJson("Please Fill All Fields");

    }

    public String updateCd(Long id, String title, String description, Double unitCost, Double totalDuration, String genre) {
        if (title != null && CdRepository.getInstance().findById(id) != null) {
            if (description != null && unitCost != null && totalDuration != null && genre != null) {
                return new Gson().toJson(update(CdRepository.getInstance().updateInstance(id, title, description, unitCost, totalDuration, genre)));
            }
            return new Gson().toJson("Please Fill All Fields");
        }
        return new Gson().toJson("There Is No CD With This Id: " + id);
    }


}


