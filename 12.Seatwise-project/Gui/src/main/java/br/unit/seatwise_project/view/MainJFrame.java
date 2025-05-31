
package br.unit.seatwise_project.view;

import br.unit.seatwise_project.config.ViewConfig;
import br.unit.seatwise_project.controller.MainController;
import br.unit.seatwise_project.utility.LoggerUtils;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


public class MainJFrame extends JFrame {


    // Attributes

    private final Logger logger = LoggerUtils.getLogger(MainJFrame.class);


    // Constructors

    public MainJFrame() throws HeadlessException {
        logger.info("Inicializando frame principal");

        this.setSize(ViewConfig.SIZE);
        this.setTitle(ViewConfig.TITLE);
        this.setLayout(new BorderLayout());
        this.setResizable(ViewConfig.RESIZABLE);
        this.setMinimumSize(ViewConfig.MIN_SIZE);
        this.setDefaultCloseOperation(ViewConfig.CLOSE_OPERATION);
        this.getContentPane().setBackground(ViewConfig.BACKGROUND);
        this.setIconImage(new ImageIcon(ViewConfig.WINDOW_ICON).getImage());


        // Spaces

        JPanel topSpace    = new JPanel();
        JPanel bottomSpace = new JPanel();
        JPanel leftSpace   = new JPanel();
        JPanel rightSpace  = new JPanel();

        topSpace.setPreferredSize(new Dimension(100, 64));
        bottomSpace.setPreferredSize(new Dimension(100, 64));
        leftSpace.setPreferredSize(new Dimension(256, 100));
        rightSpace.setPreferredSize(new Dimension(256, 100));

        topSpace.setBackground(ViewConfig.BACKGROUND);
        bottomSpace.setBackground(ViewConfig.BACKGROUND);
        leftSpace.setBackground(ViewConfig.BACKGROUND);
        rightSpace.setBackground(ViewConfig.BACKGROUND);


        // Main panel

        JPanel mainPanel = new JPanel();

        mainPanel.setPreferredSize(new Dimension(100, 100));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        // Logo section panel

        JPanel logoSectionPanel = new JPanel();

        logoSectionPanel.setBackground(Color.WHITE);
        logoSectionPanel.setLayout(new BorderLayout());


        // Logo section panel

        JPanel contentSectionPanel = new JPanel();

        contentSectionPanel.setBackground(Color.WHITE);
        contentSectionPanel.setLayout(new FlowLayout());


        // Label

        JLabel logo = new JLabel();

        ImageIcon logoImage = new ImageIcon("src/main/resources/images/transparent-icon.png");
        logo.setIcon(logoImage);
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.setVerticalAlignment(JLabel.CENTER);


        // Chairs

        // ...


        // Resize components

        mainPanel.addComponentListener(new MainController.SizeTracker(logoSectionPanel, contentSectionPanel, logo, logoImage.getImage()));


        // Add components

        logger.info("Adicionando componentes no frame principal");

        logoSectionPanel.add(logo, BorderLayout.CENTER);

        mainPanel.add(logoSectionPanel);
        mainPanel.add(contentSectionPanel);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(topSpace, BorderLayout.NORTH);
        this.add(bottomSpace, BorderLayout.SOUTH);
        this.add(leftSpace, BorderLayout.WEST);
        this.add(rightSpace, BorderLayout.EAST);
    }


    // Main methods

    // ...
}
