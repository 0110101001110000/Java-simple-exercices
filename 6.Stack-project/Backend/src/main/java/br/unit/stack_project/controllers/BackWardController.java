package br.unit.stack_project.controllers;

import br.unit.stack_project.models.UrlDTO;
import br.unit.stack_project.services.BackWardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


@RestController
@RequestMapping("/api/backward")
public class BackWardController {


    // Attributes

    private final BackWardService backWardService = new BackWardService();


    // Main methods

    @PostMapping
    private ResponseEntity<String> createElement(@RequestBody @Valid UrlDTO url) {
        try {
            String response = backWardService.createElement(url);
            Logger.getLogger(BackWardController.class.getName()).info("Foi adicionado um novo histórico de backward");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            Logger.getLogger(BackWardController.class.getName()).log(Level.SEVERE, "Erro ao adicionar histórico de backward", e);
            return new ResponseEntity<>("Erro ao adicionar histórico de backward", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    private ResponseEntity<UrlDTO> getElement() {
        try {
            UrlDTO response = backWardService.getElement();
            UrlDTO url = (response != null) ? response : new UrlDTO(null);
            Logger.getLogger(BackWardController.class.getName()).info("O Histórico de backward foi listado");
            return new ResponseEntity<>(url, HttpStatus.OK);
        } catch (RuntimeException e) {
            Logger.getLogger(BackWardController.class.getName()).log(Level.SEVERE, "Erro ao listar histórico de backward", e);
            UrlDTO url = new UrlDTO(null);
            return new ResponseEntity<UrlDTO>(url, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    private ResponseEntity<String> deleteElement() {
        try {
            String response = backWardService.deleteElement();
            Logger.getLogger(BackWardController.class.getName()).info("O Histórico de backward foi removido");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Logger.getLogger(BackWardController.class.getName()).log(Level.SEVERE, "Erro ao remover histórico de backward", e);
            return new ResponseEntity<>("Erro ao remover histórico de backward", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
