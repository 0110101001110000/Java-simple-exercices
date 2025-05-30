
package br.unit.seatwise_project.services;

import br.unit.seatwise_project.models.Reserve;
import br.unit.seatwise_project.repositories.ReserveDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


// Init -------------------------------------------------------------------- //


@Service
public class ReserveServiceImpl implements ReserveService {


    // Attributes

    private final ReserveDAO reserveDAO;


    // Constructors

    @Autowired
    public ReserveServiceImpl(ReserveDAO reserveDAO) {
        this.reserveDAO = reserveDAO;
    }


    // Main methods

    @Override
    public List<Reserve> getAllReserves() throws IOException {
        return this.reserveDAO.getAllReserves();
    }

    @Override
    public String saveReserve(Reserve reserve) throws IOException {
        return this.reserveDAO.saveReserve(reserve);
    }

    @Override
    public String deleteReserve(Long id) throws IOException {
        return this.reserveDAO.deleteReserve(id);
    }
}
