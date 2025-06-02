
package br.unit.seatwise_project.view;

import br.unit.seatwise_project.config.ViewConfig;
import br.unit.seatwise_project.controller.MainController;
import br.unit.seatwise_project.model.Chair;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.model.Reserve;
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
    private final Client client;


    // Constructors

    public MainJFrame(Client client) throws HeadlessException {
        this.client = client;

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
        leftSpace.setPreferredSize(new Dimension(186, 100));
        rightSpace.setPreferredSize(new Dimension(186, 100));

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


        // Logo

        JLabel logo = new JLabel();

        ImageIcon logoImage = new ImageIcon(ViewConfig.LOGO);
        logo.setIcon(logoImage);
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.setVerticalAlignment(JLabel.CENTER);


        // Fetch chairs and reserves

        List<ReserveButton> buttons                = new ArrayList<>();
        List<Chair>         getAllChairsResponse   = MainController.getAllChairs();
        List<Reserve>       getAllReservesResponse = MainController.getAllReserves();

        if (getAllChairsResponse != null) {
            for (Chair chair : getAllChairsResponse) {
                ReserveButton button = new ReserveButton(chair, null);

                button.setBackground(Color.WHITE);
                button.setPreferredSize(new Dimension(32, 32));
                button.addActionListener(new MainController.ClickActionHandler(button, client));
                button.setOnPressBackground(new Color(240, 244, 249));
                button.setIcon(new ImageIcon("src/main/resources/images/transparent-icon-free-32x32.png"));

                this.checkReserve(getAllReservesResponse, button);

                button.setToolTipText(String.format("Id: %d | Status: %s", button.chair.getId(), (button.reserve == null)? "Livre" : "Ocupada"));

                buttons.add(button);
            }
        } else {
            logger.warning("Cadeiras não encontradas");
        }


        // Resize components

        mainPanel.addComponentListener(new MainController.SizeTracker(logoSectionPanel, contentSectionPanel, logo, logoImage.getImage()));


        // Add components

        logger.info("Adicionando componentes no frame principal");

        for (ReserveButton button : buttons) {contentSectionPanel.add(button);}

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

    private void checkReserve(List<Reserve> getAllReservesResponse, ReserveButton button) {
        if (getAllReservesResponse != null) {
            for (Reserve reserve : getAllReservesResponse) {
                if (reserve.getChairId().equals(button.getChair().getId())) {

                    button.setIcon(new ImageIcon("src/main/resources/images/transparent-icon-block-32x32.png"));
                    button.setReserve(reserve);

                    if (reserve.getClientId().equals(this.client.getId())) {
                        button.setBorderColor(new Color(147, 198, 161));
                    }

                    break;
                }
            }
        }
    }


    // Other classes

    public static class ReserveButton extends JButton {


        // Attributes

        private Chair   chair;
        private Reserve reserve;

        private int   radius;
        private Color borderColor;
        private Color onPressBackground;


        // Constructors

        public ReserveButton(Chair chair, Reserve reserve) {
            super();

            this.chair             = chair;
            this.reserve           = reserve;
            this.radius            = 15;
            this.onPressBackground = Color.LIGHT_GRAY;

            setContentAreaFilled(false);
        }


        // Getter methods

        public Chair getChair() {
            return chair;
        }

        public Reserve getReserve() {
            return reserve;
        }

        public int getRadius() {
            return radius;
        }

        public Color getBorderColor() {
            return borderColor;
        }

        public Color getOnPressBackground() {
            return onPressBackground;
        }


        // Setter methods

        public void setChair(Chair chair) {
            this.chair = chair;
        }

        public void setReserve(Reserve reserve) {
            this.reserve = reserve;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public void setBorderColor(Color borderColor) {
            this.borderColor = borderColor;
        }

        public void setOnPressBackground(Color onPressBackground) {
            this.onPressBackground = onPressBackground;
        }


        // Main methods

        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(this.getOnPressBackground());
            } else {
                g.setColor(getBackground());
            }
            g.fillRoundRect(0, 0, getWidth(), getHeight(), this.getRadius(), this.getRadius());
            super.paintComponent(g);
        }

        protected void paintBorder(Graphics g) {
            g.setColor(this.getBorderColor());
            g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, this.getRadius(), this.getRadius());
        }
    }
}
