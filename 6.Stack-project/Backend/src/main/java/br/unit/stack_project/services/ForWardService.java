
package br.unit.stack_project.services;

import br.unit.stack_project.models.UrlDTO;
import br.unit.stack_project.repositories.ForWardDAO;
import org.springframework.stereotype.Service;


// Init -------------------------------------------------------------------- //


/**
 * @author 0110101001110000
 */
@Service
public class ForWardService {


    // Attributes

    private final ForWardDAO forWardDAO = new ForWardDAO();


    // Main methods

    public String createElement(UrlDTO url) {
        return forWardDAO.createElement(url);
    }

    public UrlDTO getElement() {
        return forWardDAO.getElement();
    }

    public String deleteElement() {
        return forWardDAO.deleteElement();
    }
}
