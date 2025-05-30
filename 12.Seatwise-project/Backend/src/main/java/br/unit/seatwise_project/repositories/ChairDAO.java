
package br.unit.seatwise_project.repositories;

import br.unit.seatwise_project.models.Chair;
import br.unit.seatwise_project.models.Client;

import java.io.IOException;
import java.util.List;


// Init -------------------------------------------------------------------- //


public interface ChairDAO {

    List<Chair> getAllChairs()         throws IOException;
    String      saveChair(Chair chair) throws IOException;
}

