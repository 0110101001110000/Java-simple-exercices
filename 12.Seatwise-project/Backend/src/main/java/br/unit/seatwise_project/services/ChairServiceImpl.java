
package br.unit.seatwise_project.services;

import br.unit.seatwise_project.models.Chair;
import br.unit.seatwise_project.repositories.ChairDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


// Init -------------------------------------------------------------------- //


@Service
public class ChairServiceImpl implements ChairService {


    // Attributes

    private final ChairDAO chairDAO;


    // Constructors

    @Autowired
    public ChairServiceImpl(ChairDAO chairDAO) {
        this.chairDAO = chairDAO;
    }


    // Main methods

    @Override
    public List<Chair> getAllChairs() throws IOException {
        return this.chairDAO.getAllChairs();
    }

    @Override
    public String saveChair(Chair chair) throws IOException {
        return this.chairDAO.saveChair(chair);
    }
}
