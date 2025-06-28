
package br.unit.seatwise_project.controller;

import br.unit.seatwise_project.Main;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.service.ClientService;
import br.unit.seatwise_project.utility.JsonUtils;
import br.unit.seatwise_project.utility.LoggerUtils;
import br.unit.seatwise_project.view.ui.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;
import java.util.concurrent.CompletionException;
import java.util.logging.Level;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


public class SignController {


    // Attributes

    private static final Logger logger = LoggerUtils.getLogger(SignController.class);


    // Main methods

    public static void addClient(Client client) {
        try {
            String response = ClientService.addClient(client).get();
            logger.info("Mensagem da requisição: " + response);
        }
        catch (CompletionException completionException) {
            String message = "Erro de conexão ao comunicar-se com a Api";
            logger.log(Level.SEVERE,message, completionException);
            JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception exception) {
            logger.log(Level.SEVERE,"Erro na requisição ao registrar cliente", exception);
        }
    }


    // Private classes

    public static class ClickActionHandler implements ActionListener {


        // Attributes

        private final Logger logger = LoggerUtils.getLogger(ClickActionHandler.class);

        private final JFrame         signFrame;
        private final JTextField     emailField;
        private final JPasswordField passwordField;
        private final JTextField     phoneField;
        private final RoundedButton  submitButton;


        // Constructors

        public ClickActionHandler(JFrame signFrame, JTextField emailField, JPasswordField passwordField,  JTextField phoneField, RoundedButton submitButton) {
            this.signFrame     = signFrame;
            this.emailField    = emailField;
            this.passwordField = passwordField;
            this.phoneField    = phoneField;
            this.submitButton  = submitButton;
        }


        // Main methods

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(this.submitButton)) {
                Client client = new Client(
                        null,
                        this.emailField.getText(),
                        String.join("", Arrays.toString(this.passwordField.getPassword()).replaceAll("\\[|\\]", "").split(", ")),
                        this.phoneField.getText()
                );

                System.out.println(JsonUtils.parseString(client));

                addClient(client);

                JOptionPane.showMessageDialog(
                        null,
                        "O cadastro foi concluído com sucesso.",
                        "Cadastro concluído",
                        JOptionPane.INFORMATION_MESSAGE
                );

                this.signFrame.dispose();

                Main.openLoginFrame();
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
