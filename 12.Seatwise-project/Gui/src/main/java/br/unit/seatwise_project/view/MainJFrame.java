
package br.unit.seatwise_project.view;

import br.unit.seatwise_project.config.ViewConfig;
import br.unit.seatwise_project.controller.MainController;
import br.unit.seatwise_project.model.Chair;
import br.unit.seatwise_project.utility.LoggerUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
        contentSectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


        // Label

        JLabel logo = new JLabel();

        ImageIcon logoImage = new ImageIcon(ViewConfig.LOGO);
        logo.setIcon(logoImage);
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.setVerticalAlignment(JLabel.CENTER);


        // Chairs

        List<ChairButton> buttons = new ArrayList<>();
        List<Chair> getAllChairsResponse = MainController.getAllChairs();
        if (getAllChairsResponse != null) {
            for (Chair chair : getAllChairsResponse) {
                ChairButton button = new ChairButton(chair.getId());

                button.setBackground(Color.WHITE);
                button.setPreferredSize(new Dimension(32, 32));
                button.setIcon(new ImageIcon("src/main/resources/images/transparent-icon-32x32.png"));
                button.addActionListener(new MainController.ClickActionHandler(button));

                buttons.add(button);
            }
        } else {
            logger.warning("Cadeiras não encontradas");
        }


        // Resize components

        mainPanel.addComponentListener(new MainController.SizeTracker(logoSectionPanel, contentSectionPanel, logo, logoImage.getImage()));


        // Add components

        logger.info("Adicionando componentes no frame principal");

        for (ChairButton button : buttons) {contentSectionPanel.add(button);}

        logoSectionPanel.add(logo, BorderLayout.CENTER);

        mainPanel.add(logoSectionPanel);
        mainPanel.add(contentSectionPanel);

        this.add(mainPanel, BorderLayout.CENTER);

        this.add(topSpace, BorderLayout.NORTH);
        this.add(bottomSpace, BorderLayout.SOUTH);
        this.add(leftSpace, BorderLayout.WEST);
        this.add(rightSpace, BorderLayout.EAST);
    }


    // Other classes

    public static class ChairButton extends JButton {


        // Attributes

        private Long id;
        private int  radius;


        // Constructors

        public ChairButton(Long id) {
            super();

            this.id     = id;
            this.radius = 20;

            setContentAreaFilled(false);
        }


        // Getters and Setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }


        // Main methods

        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.LIGHT_GRAY);
            } else {
                g.setColor(getBackground());
            }
            g.fillRoundRect(0, 0, getWidth(), getHeight(), this.radius, this.radius);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            g.setColor(getBackground());
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, this.radius, this.radius);
        }
    }
}
