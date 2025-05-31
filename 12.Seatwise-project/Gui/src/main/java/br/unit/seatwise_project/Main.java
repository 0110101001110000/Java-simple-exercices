
package br.unit.seatwise_project;

import br.unit.seatwise_project.model.Chair;
import br.unit.seatwise_project.service.ChairService;
import br.unit.seatwise_project.utility.LoggerUtils;

import java.util.List;
import java.util.logging.Level;


// Init -------------------------------------------------------------------- //


public class Main {

    public static void main(String[] args) {

        LoggerUtils.getLogger(Main.class).info("Hello, World!");


        // TEMPORARY ------------------------------------------------------- //

        try {
            List<Chair> result = ChairService.getAllChairs().get();
            System.out.println("Resposta da API: ");
            for(Chair chair : result) {
                System.out.println(chair.getId());
            }
        } catch (Exception e) {
            LoggerUtils.getLogger(Main.class).log(Level.SEVERE,"Erro na requisição ao obter cadeiras", e);
        }

        // TEMPORARY ------------------------------------------------------- //


    }
}
