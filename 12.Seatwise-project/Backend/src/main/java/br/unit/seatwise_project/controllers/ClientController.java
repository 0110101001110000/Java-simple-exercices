
package br.unit.seatwise_project.controllers;

import br.unit.seatwise_project.models.Client;
import br.unit.seatwise_project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


@RestController
@RequestMapping("/api/client")
public class ClientController {


    // Attributes

    private final Logger logger = Logger.getLogger(ClientController.class.getName());

    private final ClientService clientService;


    // Constructors

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email) {
        try {
            Client response = clientService.getClientByEmail(email);

            if (response != null) {
                logger.info("Cliente obtido com sucesso");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

            logger.info("Cliente não encontrado");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Erro interno ao obter cliente", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<String> addClient(@RequestBody Client client) {
        try {
            String response = clientService.saveClient(client);
            logger.info(response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String message = "Erro interno ao adicionar cliente";
            logger.log(Level.SEVERE, message, e);
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
