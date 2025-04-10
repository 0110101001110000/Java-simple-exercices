
package br.unit.stack_project.controllers;

import br.unit.stack_project.models.UrlDTO;
import br.unit.stack_project.services.ForWardService;
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
@RequestMapping("/api/forward")
public class ForWardController {


    // Attributes

    private final ForWardService forWardService= new ForWardService();


    // Main methods

    @PostMapping
    private ResponseEntity<String> createElement(@RequestBody @Valid UrlDTO url) {
        try {
            String response = forWardService.createElement(url);
            Logger.getLogger(ForWardController.class.getName()).info("Foi adicionado um novo histórico de forward");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            Logger.getLogger(ForWardController.class.getName()).log(Level.SEVERE, "Erro ao adicionar histórico de forward", e);
            return new ResponseEntity<>("Erro ao adicionar histórico de forward", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    private ResponseEntity<UrlDTO> getElement() {
        try {
            UrlDTO response = forWardService.getElement();
            if (response != null) {
                Logger.getLogger(ForWardController.class.getName()).info("O Histórico de forward foi listado");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            Logger.getLogger(ForWardController.class.getName()).info("O Histórico de forward não foi listado, pois ele não existe");
            UrlDTO nullUrl = new UrlDTO(null);
            return new ResponseEntity<>(nullUrl, HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            Logger.getLogger(ForWardController.class.getName()).log(Level.SEVERE, "Erro ao listar histórico de forward", e);
            UrlDTO url = new UrlDTO(null);
            return new ResponseEntity<>(url, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    private ResponseEntity<String> deleteElement() {
        try {
            String response = forWardService.deleteElement();
            Logger.getLogger(ForWardController.class.getName()).info("O Histórico de forward foi removido");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Logger.getLogger(ForWardController.class.getName()).log(Level.SEVERE, "Erro ao remover histórico de forward", e);
            return new ResponseEntity<>("Erro ao remover histórico de forward", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
