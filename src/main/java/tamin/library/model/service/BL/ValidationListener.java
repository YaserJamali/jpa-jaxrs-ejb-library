package tamin.library.model.service.BL;


import tamin.library.model.entity.Artist;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class ValidationListener {
    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @PrePersist
    @PreUpdate
    private void validate(Artist artist) {
        System.out.println(".DataValidationListener validate()");
        if (artist.getName() == null || "".equals(artist.getName()))
            throw new IllegalArgumentException("Invalid first name");
        if (artist.getFamily() == null || "".equals(artist.getFamily()))
            throw new IllegalArgumentException("Invalid last name");
    }

}
