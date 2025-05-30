
package br.unit.seatwise_project.repositories;

import br.unit.seatwise_project.models.Client;

import java.io.IOException;


// Init -------------------------------------------------------------------- //


public interface ClientDAO {

    Client getClientByEmail(String email) throws IOException;
    String saveClient(Client client)      throws IOException;
}
