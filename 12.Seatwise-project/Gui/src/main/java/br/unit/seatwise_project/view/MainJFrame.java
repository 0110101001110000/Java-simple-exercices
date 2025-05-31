
package br.unit.seatwise_project.view;

import br.unit.seatwise_project.config.ViewConfig;
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
        this.setResizable(ViewConfig.RESIZABLE);
        this.setMinimumSize(ViewConfig.MIN_SIZE);
        this.setDefaultCloseOperation(ViewConfig.CLOSE_OPERATION);
        this.getContentPane().setBackground(ViewConfig.BACKGROUND);
        this.setIconImage(new ImageIcon(ViewConfig.WINDOW_ICON).getImage());


        // Label

        this.setLayout(new BorderLayout());

        JLabel label = new JLabel();

        label.setText("--- SeatWise Application ---");
        label.setFont(new Font(null, Font.BOLD, 22));
        label.setIcon(new ImageIcon("src/main/resources/images/transparent-icon.png"));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(Color.GRAY);
        label.setIconTextGap(16);

        this.add(label, BorderLayout.CENTER);
    }


    // Main methods

    // ...
}
