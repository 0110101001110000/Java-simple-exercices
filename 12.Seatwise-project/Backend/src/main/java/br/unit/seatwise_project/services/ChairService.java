
package br.unit.seatwise_project.services;

import br.unit.seatwise_project.models.Chair;

import java.io.IOException;
import java.util.List;


// Init -------------------------------------------------------------------- //


public interface ChairService {

    List<Chair> getAllChairs() throws IOException;
    String saveChair(Chair chair) throws IOException;
}
