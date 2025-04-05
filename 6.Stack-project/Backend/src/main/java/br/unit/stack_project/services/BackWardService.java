package br.unit.stack_project.services;

import br.unit.stack_project.models.UrlDTO;
import br.unit.stack_project.repositories.BackWardDAO;
import br.unit.stack_project.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


// Init -------------------------------------------------------------------- //


@Service
public class BackWardService {


    // Attributes

    @Autowired
    private BackWardDAO backWardDAO;


    // Main methods

    public ResponseEntity<Object> createElement(UrlDTO url) {
        return backWardDAO.createElement(url);
    }

    public ResponseEntity<Object> getElement() {
        return backWardDAO.getElement();
    }

    public ResponseEntity<Object> deleteElement() {
        return backWardDAO.getElement();
    }
}
