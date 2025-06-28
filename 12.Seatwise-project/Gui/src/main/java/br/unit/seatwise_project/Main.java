
package br.unit.seatwise_project;

import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.view.LoginJFrame;
import br.unit.seatwise_project.view.MainJFrame;
import br.unit.seatwise_project.view.SignJFrame;


// Init -------------------------------------------------------------------- //


public class Main {

    public static void main(String[] args) {

        openLoginFrame();
    }

    public static void openLoginFrame() {
        new LoginJFrame().setVisible(true);
    }

    public static void openSignFrame() {
        new SignJFrame().setVisible(true);
    }

    public static void openMainFrame(Client client) {
        new MainJFrame(client).setVisible(true);
    }
}
