
package br.unit.seatwise_project.services;

import br.unit.seatwise_project.models.Client;
import br.unit.seatwise_project.repositories.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


// Init -------------------------------------------------------------------- //


@Service
public class ClientServiceImpl implements ClientService {


    // Attributes

    private final ClientDAO clientDAO;


    // Constructors

    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }


    // Main methods

    @Override
    public Client getClientByEmail(String email) throws IOException {
        return this.clientDAO.getClientByEmail(email);
    }

    @Override
    public String saveClient(Client client) throws IOException {
        return this.clientDAO.saveClient(client);
    }
}
