
package br.unit.seatwise_project.repositories;

import br.unit.seatwise_project.db.Database;
import br.unit.seatwise_project.models.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Init -------------------------------------------------------------------- //


@Repository
public class ReserveDAOImpl implements ReserveDAO {


    // Attributes

    private final Database database;


    // Constructors

    @Autowired
    public ReserveDAOImpl() {
        this.database = new Database("src/main/java/br/unit/seatwise_project/db/reserve.txt");
    }


    // Main methods

    @Override
    public List<Reserve> getAllReserves() throws IOException {
        List<Reserve> list = new ArrayList<>();

        for (String row : this.database.readRecords()) {
            String[] cols     = row.split(",");
            Long     id       = Long.parseLong(cols[0]);
            Long     clientId = Long.parseLong(cols[1]);
            Long     chairId  = Long.parseLong(cols[2]);
            list.add(new Reserve(id, clientId, chairId));
        }

        return list;
    }

    @Override
    public String saveReserve(Reserve reserve) throws IOException {
        reserve.setId(this.database.generateNextId());

        this.database.createRecord(
                String.format("%d,%d,%d", reserve.getId(), reserve.getClientId(), reserve.getChairId())
        );

        return "Reserva criada com sucesso";
    }

    @Override
    public String deleteReserve(Long id) throws IOException {
        return (this.database.deleteRecord(id)) ? "Reserva removida com sucesso" : null;
    }
}
