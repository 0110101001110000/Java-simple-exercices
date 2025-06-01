
package br.unit.seatwise_project.controller;

import br.unit.seatwise_project.model.Chair;
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


        // Constructors

        public ClickActionHandler(MainJFrame.ReserveButton reserveButton) {
            this.reserveButton = reserveButton;
        }


        // Main methods

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(this.reserveButton)) {
                logger.info("Cadeira de id " + this.reserveButton.getChair().getId() + " foi clicada!");
            }
        }
    }

    public static class SizeTracker extends ComponentAdapter {


        // Attributes

        JPanel logoSectionPanel;
        JPanel contentSectionPanel;
        JLabel logo;
        Image logoImage;


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
            if (newImageSize < 256) {
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
