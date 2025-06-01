
package br.unit.seatwise_project;

import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.view.MainJFrame;


// Init -------------------------------------------------------------------- //


public class Main {

    public static void main(String[] args) {

        new MainJFrame(new Client(1L, "test@test.test", "test", "+00 (00) 00000-0000")).setVisible(true);
    }
}
