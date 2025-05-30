
package br.unit.seatwise_project.repositories;

import br.unit.seatwise_project.db.Database;
import br.unit.seatwise_project.models.Chair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Init -------------------------------------------------------------------- //


@Repository
public class ChairDAOImpl implements ChairDAO {


    // Attributes

    private final Database database;


    // Constructors

    @Autowired
    public ChairDAOImpl() {
        this.database = new Database("src/main/java/br/unit/seatwise_project/db/chair.txt");
    }


    // Main methods

    @Override
    public List<Chair> getAllChairs() throws IOException {
        List<Chair> list = new ArrayList<>();
        for (String id : this.database.readRecords()) {
            list.add(new Chair(Long.parseLong(id)));
        }
        return list;
    }

    @Override
    public String saveChair(Chair chair) throws IOException {
        chair.setId(this.database.generateNextId());
        this.database.createRecord(String.valueOf(chair.getId()));
        return "Cadeira criada com sucesso";
    }
}
