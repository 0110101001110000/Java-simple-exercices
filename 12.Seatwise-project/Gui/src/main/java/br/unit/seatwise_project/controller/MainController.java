
package br.unit.seatwise_project.controller;

import br.unit.seatwise_project.model.Chair;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.model.Reserve;
import br.unit.seatwise_project.service.ChairService;
import br.unit.seatwise_project.service.ReserveService;
import br.unit.seatwise_project.utility.LoggerUtils;
import br.unit.seatwise_project.view.MainJFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;


// Init -------------------------------------------------------------------- //


public class MainController {


    // Attributes

    private static final Logger logger = LoggerUtils.getLogger(MainController.class);


    // Main methods

    public static List<Chair> getAllChairs() {
        try {
            return ChairService.getAllChairs().get();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Erro na requisição ao obter cadeiras", e);
            return null;
        }
    }

    public static List<Reserve> getAllReserves() {
        try {
            return ReserveService.getAllReserves().get();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Erro na requisição ao obter reservas", e);
            return null;
        }
    }


    // Private classes

    public static class ClickActionHandler implements ActionListener {


        // Attributes

        private final Logger logger = LoggerUtils.getLogger(ClickActionHandler.class);

        private final MainJFrame.ReserveButton reserveButton;
        private final Client                   client;


        // Constructors

        public ClickActionHandler(MainJFrame.ReserveButton reserveButton, Client client) {
            this.reserveButton = reserveButton;
            this.client        = client;
        }


        // Main methods

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(this.reserveButton)) {
                logger.info("Cadeira de id " + this.reserveButton.getChair().getId() + " foi clicada!");

                if (this.reserveButton.getReserve() == null) {
                    int confirmation = JOptionPane.showConfirmDialog(
                            null,
                            String.format("Realmente deseja reservar a cadeira de id %d?", this.reserveButton.getChair().getId()),
                            "Reservar Cadeira",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (confirmation == JOptionPane.YES_OPTION) {
                        this.addReserve();
                    }
                }

                else {
                    JOptionPane.showMessageDialog(
                            null,
                            (this.client.getId().equals(this.reserveButton.getReserve().getClientId()) ? "Você já reservou essa cadeira" : "Essa cadeira já foi reservada"),
                            "Reservar Cadeira",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        }

        private void addReserve() {
            try {
                String response = ReserveService.addReserve(new Reserve(null, this.client.getId(), this.reserveButton.getChair().getId())).get();
                logger.info(response);
            } catch (Exception exception) {
                logger.log(Level.SEVERE,"Erro na requisição ao obter criar reserva", exception);
            }
        }
    }

    public static class SizeTracker extends ComponentAdapter {


        // Attributes

        JPanel logoSectionPanel;
        JPanel contentSectionPanel;
        JLabel logo;
        Image  logoImage;


        // Constructors

        public SizeTracker(JPanel logoSectionPanel, JPanel contentSectionPanel, JLabel logo, Image logoImage) {
            this.logoSectionPanel    = logoSectionPanel;
            this.contentSectionPanel = contentSectionPanel;
            this.logo                = logo;
            this.logoImage           = logoImage;
        }


        // Main methods

        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);

            int width = ((JComponent) e.getSource()).getWidth();
            int height = ((JComponent) e.getSource()).getHeight();

            this.logoSectionPanel.setPreferredSize(new Dimension(width, height / 3));
            this.contentSectionPanel.setPreferredSize(new Dimension(width, height / 3 * 2));

            // Resize logo
            int newImageSize = Math.min(logoSectionPanel.getWidth(), logoSectionPanel.getHeight());
            if (newImageSize < 320) {
                Image newImage = logoImage.getScaledInstance(
                        newImageSize,
                        newImageSize,
                        Image.SCALE_DEFAULT
                );
                this.logo.setIcon(new ImageIcon(newImage));
            }
        }
    }
}
