
package br.unit.seatwise_project.view;

import br.unit.seatwise_project.Main;
import br.unit.seatwise_project.config.ViewConfig;
import br.unit.seatwise_project.controller.LoginController;
import br.unit.seatwise_project.controller.SignController;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.utility.LoggerUtils;
import br.unit.seatwise_project.view.ui.RoundedBorder;
import br.unit.seatwise_project.view.ui.RoundedButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


public class SignJFrame extends JFrame {


    // Attributes

    private final Logger logger = LoggerUtils.getLogger(SignJFrame.class);


    // Constructors

    public SignJFrame() throws HeadlessException {
        logger.info("Inicializando frame de sign");

        this.setSize(ViewConfig.SIZE);
        this.setTitle(ViewConfig.TITLE);
        this.setLayout(new BorderLayout());
        this.setResizable(ViewConfig.RESIZABLE);
        this.setMinimumSize(ViewConfig.MIN_SIZE);
        this.setDefaultCloseOperation(ViewConfig.CLOSE_OPERATION);
        this.getContentPane().setBackground(ViewConfig.BACKGROUND);
        this.setIconImage(new ImageIcon(ViewConfig.WINDOW_ICON).getImage());


        // Spaces

        JPanel topSpacePanel    = new JPanel();
        JPanel bottomSpacePanel = new JPanel();
        JPanel leftSpacePanel   = new JPanel();
        JPanel rightSpacePanel  = new JPanel();

        int verticalSpace   = 64;
        int horizontalSpace = 186;

        topSpacePanel.setPreferredSize(new Dimension(100, verticalSpace));
        bottomSpacePanel.setPreferredSize(new Dimension(100, verticalSpace));
        leftSpacePanel.setPreferredSize(new Dimension(horizontalSpace, 100));
        rightSpacePanel.setPreferredSize(new Dimension(horizontalSpace, 100));

        topSpacePanel.setBackground(ViewConfig.BACKGROUND);
        bottomSpacePanel.setBackground(ViewConfig.BACKGROUND);
        leftSpacePanel.setBackground(ViewConfig.BACKGROUND);
        rightSpacePanel.setBackground(ViewConfig.BACKGROUND);


        // Main panel

        JPanel mainPanel = new JPanel();

        mainPanel.setPreferredSize(new Dimension(100, 100));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        // Logo section panel

        JPanel logoSectionPanel = new JPanel();

        int logoSectionPanelWidth  = (ViewConfig.SIZE.width - horizontalSpace * 2);
        int logoSectionPanelHeight = (ViewConfig.SIZE.height - verticalSpace * 2) / 3;

        logoSectionPanel.setPreferredSize(new Dimension(logoSectionPanelWidth, logoSectionPanelHeight));
        logoSectionPanel.setBackground(Color.WHITE);
        logoSectionPanel.setLayout(new BorderLayout());


        // Content section panel

        JPanel contentSectionPanel = new JPanel();

        int contentSectionPanelWidth  = ViewConfig.SIZE.width - (horizontalSpace * 2);
        int contentSectionPanelHeight = ViewConfig.SIZE.height - (verticalSpace * 2) / 3 * 2;

        contentSectionPanel.setPreferredSize(new Dimension(contentSectionPanelWidth, contentSectionPanelHeight));
        contentSectionPanel.setBackground(Color.WHITE);
        contentSectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 32));


        // Logo

        JLabel logo = new JLabel();

        ImageIcon logoIcon = new ImageIcon(ViewConfig.LOGO);
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.setVerticalAlignment(JLabel.CENTER);
        int newImageSize = contentSectionPanelHeight / 3 - 12;
        Image resizedLogo = logoIcon.getImage().getScaledInstance(
                newImageSize,
                newImageSize,
                Image.SCALE_DEFAULT
        );
        logo.setIcon(new ImageIcon(resizedLogo));


        // Entries

        RoundedBorder roundedBorder = new RoundedBorder();

        roundedBorder.setRadius(8);
        roundedBorder.setWeight(-5);
        roundedBorder.setColor(Color.LIGHT_GRAY);

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField phoneField = new JTextField();

        int componentsWidth = contentSectionPanelWidth - 100;

        emailField.setPreferredSize(new Dimension(componentsWidth, 36));
        passwordField.setPreferredSize(new Dimension(componentsWidth, 36));
        phoneField.setPreferredSize(new Dimension(componentsWidth, 36));

        emailField.setText("Digite seu E-mail...");
        passwordField.setText("Digite sua Senha...");
        phoneField.setText("Digite seu Telefone... EX: +557999000000");

        emailField.setBorder(roundedBorder);
        passwordField.setBorder(roundedBorder);
        phoneField.setBorder(roundedBorder);

        emailField.setForeground(Color.GRAY);
        passwordField.setForeground(Color.GRAY);
        phoneField.setForeground(Color.GRAY);

        emailField.setFont(new Font(null, Font.BOLD, 12));
        passwordField.setFont(new Font(null, Font.BOLD, 12));
        phoneField.setFont(new Font(null, Font.BOLD, 12));

        RoundedButton submitButton = new RoundedButton();

        submitButton.setPreferredSize(new Dimension(componentsWidth, 36));
        submitButton.setText("Cadastrar-se");
        submitButton.setBackground(new Color(0, 156, 231));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font(null, Font.BOLD, 16));
        submitButton.setOnPressBackground(new Color(0, 135, 217));

        JLabel loginButton = new JLabel();

        loginButton.setPreferredSize(new Dimension(156, 18));
        loginButton.setText("Tenho Conta");
        loginButton.setForeground(Color.LIGHT_GRAY);
        loginButton.setHorizontalAlignment(JLabel.CENTER);
        loginButton.setFont(new Font(null, Font.BOLD, 14));


        // Resize components

        mainPanel.addComponentListener(new LoginController.SizeTracker(logoSectionPanel, contentSectionPanel, logo, logoIcon.getImage()));


        // Action Listener

        submitButton.addActionListener(new SignController.ClickActionHandler(this, emailField, passwordField, phoneField, submitButton));
        loginButton.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SignJFrame.super.dispose();

                    Main.openLoginFrame();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    loginButton.setForeground(new Color(0, 135, 217));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    loginButton.setForeground(Color.LIGHT_GRAY);
                }
            }
        );


        // Add components

        logger.info("Adicionando componentes no frame de sign");

        logoSectionPanel.add(logo, BorderLayout.CENTER);

        contentSectionPanel.add(emailField);
        contentSectionPanel.add(passwordField);
        contentSectionPanel.add(phoneField);
        contentSectionPanel.add(submitButton);
        contentSectionPanel.add(loginButton);

        mainPanel.add(logoSectionPanel);
        mainPanel.add(contentSectionPanel);

        this.add(mainPanel, BorderLayout.CENTER);

        this.add(topSpacePanel, BorderLayout.NORTH);
        this.add(bottomSpacePanel, BorderLayout.SOUTH);
        this.add(leftSpacePanel, BorderLayout.WEST);
        this.add(rightSpacePanel, BorderLayout.EAST);
    }
}
