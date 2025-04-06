
package br.unit.stack_project.services;

import br.unit.stack_project.models.PageDTO;
import br.unit.stack_project.models.UrlDTO;
import br.unit.stack_project.repositories.PageDAO;
import org.springframework.stereotype.Service;


// Init -------------------------------------------------------------------- //


/**
 * @author 0110101001110000
 */
@Service
public class PageService {


    // Attributes

    private final PageDAO pageDAO = new PageDAO();


    // Main methods

    public String createPage(PageDTO page) {
        return pageDAO.createPage(page);
    }

    public PageDTO getPage(UrlDTO url) {
        return pageDAO.getPage(url);
    }

    public String updatePage(UrlDTO url, PageDTO page) {
        return pageDAO.updatePage(url, page);
    }

    public String deletePage(UrlDTO url) {
        return pageDAO.deletePage(url);
    }
}
