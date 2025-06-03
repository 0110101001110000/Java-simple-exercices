
package br.unit.seatwise_project.view;

import br.unit.seatwise_project.config.ViewConfig;
import br.unit.seatwise_project.controller.MainController;
import br.unit.seatwise_project.model.Chair;
import br.unit.seatwise_project.model.Client;
import br.unit.seatwise_project.model.Reserve;
import br.unit.seatwise_project.utility.LoggerUtils;
import br.unit.seatwise_project.view.ui.ChairButton;

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

    private final List<ChairButton> chairButtons;


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
        contentSectionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


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


        // Fetch reserve buttons and reserves

        chairButtons = this.fetchReserveButtons();
        this.fetchReserves(chairButtons);


        // Resize components

        mainPanel.addComponentListener(new MainController.SizeTracker(logoSectionPanel, contentSectionPanel, logo, logoIcon.getImage()));


        // Add components

        logger.info("Adicionando componentes no frame principal");

        for (ChairButton button : chairButtons) {contentSectionPanel.add(button);}

        logoSectionPanel.add(logo, BorderLayout.CENTER);

        mainPanel.add(logoSectionPanel);
        mainPanel.add(contentSectionPanel);

        this.add(mainPanel, BorderLayout.CENTER);

        this.add(topSpacePanel, BorderLayout.NORTH);
        this.add(bottomSpacePanel, BorderLayout.SOUTH);
        this.add(leftSpacePanel, BorderLayout.WEST);
        this.add(rightSpacePanel, BorderLayout.EAST);
    }


    // Main methods

    private List<ChairButton> fetchReserveButtons() {
        List<ChairButton> chairButtons         = new ArrayList<>();
        List<Chair>       getAllChairsResponse = MainController.getAllChairs();

        if (getAllChairsResponse != null) {
            for (Chair chair : getAllChairsResponse) {
                ChairButton button = new ChairButton(this.client, chair);
                chairButtons.add(button);
                chairButtons.get(chairButtons.indexOf(button)).addActionListener(new MainController.ClickActionHandler(button));
            }
        } else {
            logger.warning("Cadeiras não encontradas");
        }

        return chairButtons;
    }

    private void fetchReserves(List<ChairButton> chairButtons) {
        List<Reserve> getAllReservesResponse = MainController.getAllReserves();

        if (getAllReservesResponse != null) {
            for (ChairButton chairButton : chairButtons) {
                for (Reserve reserve : getAllReservesResponse) {
                    if (reserve.getChairId().equals(chairButton.getChair().getId())) {

                        ChairButton newButton = new ChairButton(chairButton.getClient(), chairButton.getChair(), reserve);
                        newButton.addActionListener(new MainController.ClickActionHandler(newButton));
                        chairButtons.set(chairButtons.indexOf(chairButton), newButton);

                        break;
                    }
                }
            }
        }
    }
}
