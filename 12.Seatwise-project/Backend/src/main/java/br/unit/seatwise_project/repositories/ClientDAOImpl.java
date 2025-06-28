
package br.unit.seatwise_project.repositories;

import br.unit.seatwise_project.db.Database;
import br.unit.seatwise_project.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;


// Init -------------------------------------------------------------------- //


@Repository
public class ClientDAOImpl implements ClientDAO {


    // Attributes

    private final Database database;


    // Constructors

    @Autowired
    public ClientDAOImpl() {
        this.database = new Database("src/main/java/br/unit/seatwise_project/db/client.txt");
    }


    // Main methods

    @Override
    public Client getClientByEmail(String email) throws IOException {
        for (String row : this.database.readRecords()) {
            String[] cols         = row.split(",");
            Long     userId       = Long.parseLong(cols[0]);
            String   userEmail    = cols[1];
            String   userPassword = cols[2];
            String   userPhone    = cols[3];

            if (email.equals(userEmail)) {
                return new Client(userId, userEmail, userPassword, userPhone);
            }
        }

        return null;
    }

    @Override
    public String saveClient(Client client) throws IOException {
        client.setId(this.database.generateNextId());
        this.database.createRecord(String.format("%d,%s,%s,%s", client.getId(), client.getEmail(), client.getPassword(), client.getPhone()));

        return "Usuário criado com sucesso";
    }
}
