
package br.unit.seatwise_project.controllers;

import br.unit.seatwise_project.models.Reserve;
import br.unit.seatwise_project.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


@RestController
@RequestMapping("/api/reserve")
public class ReserveController {


    // Attributes

    private final Logger logger = Logger.getLogger(ReserveController.class.getName());

    private final ReserveService reserveService;


    // Constructors

    @Autowired
    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }


    // Main methods

    @GetMapping
    public ResponseEntity<List<Reserve>> getAllReserves() {
        try {
            List<Reserve> response = reserveService.getAllReserves();

            if ((response != null) && (!response.isEmpty())) {
                logger.info("Reservas obtidas com sucesso");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            logger.info("Reservas não encontradas");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Erro interno ao obter reservas", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> addReserve(@RequestBody Reserve reserve) {
        try {
            String response = reserveService.saveReserve(reserve);
            logger.info(response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String message = "Erro interno ao adicionar reserva";
            logger.log(Level.SEVERE,message, e);
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReserve(@PathVariable Long id) {
        try {
            String response = reserveService.deleteReserve(id);

            if (response != null) {
                logger.info(response);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            String message = "Reserva não encontrada";
            logger.info(message);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            String message = "Erro interno ao remover reserva";
            logger.log(Level.SEVERE, message, e);
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
