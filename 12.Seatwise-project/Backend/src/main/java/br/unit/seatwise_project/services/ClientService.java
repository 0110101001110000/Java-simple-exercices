
package br.unit.seatwise_project.services;

import br.unit.seatwise_project.models.Client;

import java.io.IOException;


// Init -------------------------------------------------------------------- //


public interface ClientService {

    Client getClientByEmail(String email) throws IOException;
    String saveClient(Client client)      throws IOException;
}
