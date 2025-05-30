
package br.unit.seatwise_project.repositories;

import br.unit.seatwise_project.models.Reserve;

import java.io.IOException;
import java.util.List;


// Init -------------------------------------------------------------------- //


public interface ReserveDAO {

    List<Reserve> getAllReserves()             throws IOException;
    String        saveReserve(Reserve reserve) throws IOException;
    String        deleteReserve(Long id)       throws IOException;
}
