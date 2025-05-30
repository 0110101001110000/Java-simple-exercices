
package br.unit.seatwise_project.controllers;

import br.unit.seatwise_project.models.Chair;
import br.unit.seatwise_project.services.ChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


@RestController
@RequestMapping("/api/chair")
public class ChairController {


    // Attributes

    private final Logger logger = Logger.getLogger(ChairController.class.getName());

    private final ChairService chairService;


    // Constructors

    @Autowired
    public ChairController(ChairService chairService) {
        this.chairService = chairService;
    }

    @GetMapping
    public ResponseEntity<List<Chair>> getAllChairs() {
        final List<Chair> nullChairs = new ArrayList<>(1);
        nullChairs.add(new Chair(null));
        try {
            List<Chair> response = chairService.getAllChairs();

            if ((response != null) && (!response.isEmpty())) {
                logger.info("Cadeiras obtidas com sucesso");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            logger.info("Cadeiras não encontradas");
            return new ResponseEntity<>(nullChairs, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Erro interno ao obter cadeiras", e);
            return new ResponseEntity<>(nullChairs, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> addChair(@RequestBody Chair chair) {
        try {
            String response = chairService.saveChair(chair);
            logger.info(response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String message = "Erro interno ao adicionar cadeira";
            logger.log(Level.SEVERE, message, e);
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
