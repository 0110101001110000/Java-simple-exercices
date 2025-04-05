package br.unit.stack_project.services;

import br.unit.stack_project.models.UrlDTO;
import br.unit.stack_project.repositories.BackWardDAO;
import org.springframework.stereotype.Service;


// Init -------------------------------------------------------------------- //


/**
 * @author 0110101001110000
 */
@Service
public class BackWardService {


    // Attributes

    private final BackWardDAO backWardDAO = new BackWardDAO();


    // Main methods

    public String createElement(UrlDTO url) {
        return backWardDAO.createElement(url);
    }

    public UrlDTO getElement() {
        return backWardDAO.getElement();
    }

    public String deleteElement() {
        return backWardDAO.deleteElement();
    }
}
