
package br.unit.stack_project.repositories;

import br.unit.stack_project.models.PageDTO;
import br.unit.stack_project.models.UrlDTO;

import java.util.ArrayList;


// Init -------------------------------------------------------------------- //


/**
 * @author 0110101001110000
 */
public class PageDAO {


    // Attributes

    final private ArrayList<PageDTO> database = new ArrayList<>();


    // Main methods

    public String createPage(PageDTO page) {
        database.add(page);
        return "A página foi adicionada";
    }

    public PageDTO getPage(UrlDTO url) {
        for (PageDTO page : database) {
            if (page.getUrl().equals(url.getUrl())) {
                return page;
            }
        }
        return null;
    }

    public String updatePage(UrlDTO url, PageDTO page) {
        PageDTO interationPage;
        for (int i = 0; i < database.size(); i++) {
            interationPage = database.get(i);
            if (interationPage.getUrl().equals(url.getUrl())) {
                database.set(i, page);
                return "A página foi atualizada";
            }
        }
        // return "Erro ao atualizar página. Erro: página não encontrada";
        return null;
    }

    public String deletePage(UrlDTO url) {
        PageDTO interationPage;
        for (int i = 0; i < database.size(); i++) {
            interationPage = database.get(i);
            if (interationPage.getUrl().equals(url.getUrl())) {
                database.remove(i);
                return "A página foi removida";
            }
        }
        // return "Erro ao remover página. Erro: página não encontrada";
        return null;
    }
}
