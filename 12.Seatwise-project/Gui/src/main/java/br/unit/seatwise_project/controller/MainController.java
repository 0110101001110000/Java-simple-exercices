
package br.unit.seatwise_project.controller;

import br.unit.seatwise_project.model.Chair;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.model.Reserve;
import br.unit.seatwise_project.service.ChairService;
import br.unit.seatwise_project.service.ReserveService;
import br.unit.seatwise_project.utility.LoggerUtils;
import br.unit.seatwise_project.view.ui.ChairButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.concurrent.CompletionException;
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
        }
        catch (CompletionException completionException) {
            String message = "Erro de conexão ao comunicar-se com a Api";
            logger.log(Level.SEVERE,message, completionException);
            JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        catch (Exception exception) {
            logger.log(Level.SEVERE,"Erro na requisição ao obter cadeiras", exception);
            return null;
        }
    }

    public static List<Reserve> getAllReserves() {
        try {
            return ReserveService.getAllReserves().get();
        }
        catch (CompletionException completionException) {
            String message = "Erro de conexão ao comunicar-se com a Api";
            logger.log(Level.SEVERE,message, completionException);
            JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        catch (Exception exception) {
            logger.log(Level.SEVERE,"Erro na requisição ao obter reservas", exception);
            return null;
        }
    }

    public static void addReserve(Reserve reserve) {
        try {
            String response = ReserveService.addReserve(reserve).get();
            logger.info("Mensagem da requisição: " + response);
        }
        catch (CompletionException completionException) {
            String message = "Erro de conexão ao comunicar-se com a Api";
            logger.log(Level.SEVERE,message, completionException);
            JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception exception) {
            logger.log(Level.SEVERE,"Erro na requisição ao criar reserva", exception);
        }
    }

    public static void deleteReserve(Long id) {
        try {
            String response = ReserveService.deleteReserve(id).get();
            logger.info("Mensagem da requisição: " + response);
        }
        catch (CompletionException completionException) {
            String message = "Erro de conexão ao comunicar-se com a Api";
            logger.log(Level.SEVERE,message, completionException);
            JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception exception) {
            logger.log(Level.SEVERE,"Erro na requisição ao remover reserva", exception);
        }
    }


    // Private classes

    public static class ClickActionHandler implements ActionListener {


        // Attributes

        private final Logger logger = LoggerUtils.getLogger(ClickActionHandler.class);

        private final ChairButton chairButton;
        private final Client      client;
        private final Chair       chair;

        private Reserve reserve;


        // Constructors

        public ClickActionHandler(ChairButton chairButton) {
            this.chairButton = chairButton;
            this.client      = chairButton.getClient();
            this.chair       = chairButton.getChair();
            this.reserve     = chairButton.getReserve();
        }


        // Main methods

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(this.chairButton)) {
                if (this.reserve == null) {
                    int confirmation = JOptionPane.showConfirmDialog(
                            null,
                            String.format("Reservar a cadeira de id %d?", this.chairButton.getChair().getId()),
                            "Reservar Cadeira",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );
                    if (confirmation == JOptionPane.YES_OPTION) {
                        logger.info("Tentando reservar cadeira de id " + this.chair.getId());

                        Reserve newReserve = new Reserve(null, this.client.getId(), this.chair.getId());

                        addReserve(newReserve);

                        List<Reserve> reserves = getAllReserves();
                        if (reserves != null) {
                            for (Reserve reserve : reserves) {
                                if (newReserve.getClientId().equals(reserve.getClientId()) && newReserve.getChairId().equals(reserve.getChairId())) {
                                    newReserve.setId(reserve.getId());
                                }
                            }
                        }

                        // Fetch chair button
                        if (newReserve.getId() != null) {
                            this.chairButton.setReserve(newReserve);
                            this.reserve = newReserve;
                            this.chairButton.fetch();
                        }

                        logger.info("Reserva concluída com sucesso");
                    }
                }
                else if (this.client.getId().equals(this.reserve.getClientId())) {
                    int confirmation = JOptionPane.showConfirmDialog(
                            null,
                            String.format("Cancelar sua reserva da cadeira de id %d?", this.chair.getId()),
                            "Cancelar Reserva",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (confirmation == JOptionPane.YES_OPTION) {
                        logger.info("Tentando cancelar reserva da cadeira de id " + this.chair.getId());

                        deleteReserve(this.reserve.getId());

                        // Fetch chair button
                        this.chairButton.setReserve(null);
                        this.reserve = null;
                        this.chairButton.fetch();

                        logger.info("Cancelamento de reserva concluído com sucesso");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Essa cadeira já foi reservada",
                            "Reservar Cadeira",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
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
