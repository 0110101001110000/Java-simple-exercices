
package br.unit.seatwise_project.controller;

import br.unit.seatwise_project.Main;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.service.ClientService;
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


public class LoginController {


    // Attributes

    private static final Logger logger = LoggerUtils.getLogger(LoginController.class);


    // Main methods

    public static Client getClientByEmail(String email) {
        try {
            return ClientService.getClientByEmail(email).get();
        }
        catch (CompletionException completionException) {
            String message = "Erro de conexão ao comunicar-se com a Api";
            logger.log(Level.SEVERE,message, completionException);
            JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        catch (Exception exception) {
            logger.log(Level.SEVERE,"Erro na requisição ao obter cliente", exception);
            return null;
        }
    }


    // Private classes

    public static class ClickActionHandler implements ActionListener {


        // Attributes

        private final Logger logger = LoggerUtils.getLogger(ClickActionHandler.class);

        private final JFrame         loginFrame;
        private final JTextField     emailField;
        private final JPasswordField passwordField;
        private final RoundedButton  submitButton;


        // Constructors

        public ClickActionHandler(JFrame loginFrame,  JTextField emailField, JPasswordField passwordField, RoundedButton submitButton) {
            this.loginFrame    = loginFrame;
            this.emailField    = emailField;
            this.passwordField = passwordField;
            this.submitButton  = submitButton;
        }


        // Main methods

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(this.submitButton)) {
                Client client = getClientByEmail(emailField.getText());
                if (client != null) {
                    if (client.getPassword().equals(String.join("", Arrays.toString(this.passwordField.getPassword()).replaceAll("\\[|\\]", "").split(", ")))) {
                        logger.info("E-mail e senha corretos");

                        this.loginFrame.dispose();

                        Main.openMainFrame(client);
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Senha incorreta, tente novamente.",
                                "Senha incorreta",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Usuário não encontrado, o E-mail pode está incorreto.",
                            "Usuário não encontrado",
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
