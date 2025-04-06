
package br.unit.stack_project.controllers;

import br.unit.stack_project.models.PageDTO;
import br.unit.stack_project.models.UrlDTO;
import br.unit.stack_project.services.PageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


/**
 * @author 0110101001110000
 */
@RestController
@RequestMapping("/api/page")
public class PageController {


    // Attributes

    private final PageService pageService = new PageService();


    // Main methods

    @PostMapping
    private ResponseEntity<String> createPage(@RequestBody @Valid PageDTO page) {
        try {
            String response = pageService.createPage(page);
            Logger.getLogger(PageController.class.getName()).info("Foi adicionada uma nova página");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, "Erro ao criar página", e);
            return new ResponseEntity<>("Erro ao criar página", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    private ResponseEntity<PageDTO> getPage(@RequestBody @Valid UrlDTO url) {
        try {
            PageDTO response = pageService.getPage(url);
            if (response != null) {
                Logger.getLogger(PageController.class.getName()).info("A página foi listada");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            PageDTO nullPage = new PageDTO(null, null);
            Logger.getLogger(PageController.class.getName()).info("A página foi listada, pois, ela não existe");
            return new ResponseEntity<>(nullPage, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, "Erro ao listar página", e);
            PageDTO nullPage = new PageDTO(null, null);
            return new ResponseEntity<>(nullPage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*    @PutMapping
    private ResponseEntity<String> updatePage(@RequestBody @Valid UrlDTO url, @RequestBody @Valid PageDTO page) {
        try {
            String response = pageService.updatePage(url, page);
            if (response != null) {
                Logger.getLogger(PageController.class.getName()).info("Foi adicionada uma nova página");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            Logger.getLogger(PageController.class.getName()).info("A página solicitada não existe");
            return new ResponseEntity<>("Erro ao atualizar página.", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, "Erro ao atualizar página", e);
            return new ResponseEntity<>("Erro ao atualizar página", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @DeleteMapping
    private ResponseEntity<String> deletePage(@RequestBody @Valid UrlDTO url) {
        try {
            String response = pageService.deletePage(url);
            if (response != null) {
                Logger.getLogger(PageController.class.getName()).info("A página foi removida");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Logger.getLogger(PageController.class.getName()).info("A página não foi removida, pois, ela não existe");
            return new ResponseEntity<>("Erro ao remover página", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, "Erro ao remover página", e);
            return new ResponseEntity<>("Erro ao remover página", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
