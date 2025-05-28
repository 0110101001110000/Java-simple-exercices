
package br.unit.gui;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @author 01101010-01110000
 * @since 1.0
 */
public class Gui extends JFrame {


    // Attributes

    private final Logger LOGGER = Logger.getLogger(Gui.class.getName());
    private final JPanel jPanel = new JPanel();

    private String inputFilePath;
    private String outputFilePath;
    private String separator;


    // Constructors

    public Gui() throws HeadlessException {
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Java Gui");
        this.setBackground(Color.WHITE);
        this.setSize(512, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel.setLayout(null);
        jPanel.setBackground(this.getBackground());
        jPanel.setBounds(0, 0, this.getWidth(), this.getHeight());

        JPanel logoPanel = new JPanel();
        logoPanel.setBounds(0, 0, jPanel.getWidth(), jPanel.getHeight() / 2);
        logoPanel.setBackground(jPanel.getBackground());
        logoPanel.setLayout(new BorderLayout());

        ImageIcon logoPath = new ImageIcon("src/main/java/br/unit/gui/images/c.png");
        JLabel    logo     = new JLabel(logoPath);
        logo.setVerticalAlignment(JLabel.CENTER);
        logo.setHorizontalAlignment(JLabel.CENTER);
        logoPanel.add(logo);

        JPanel entriesPanel = new JPanel();
        entriesPanel.setBounds(0, jPanel.getHeight() / 2, jPanel.getWidth(), jPanel.getHeight() / 2);
        entriesPanel.setBackground(jPanel.getBackground());
        entriesPanel.setLayout(new BorderLayout());

        JPanel entriesList = new JPanel();
        entriesList.setBackground(jPanel.getBackground());
        entriesList.setLayout(new BoxLayout(entriesList, BoxLayout.Y_AXIS));
        entriesPanel.add(entriesList);

        JLabel label = new JLabel("Hello, World!");
        label.setFont(new Font(null, Font.PLAIN, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        entriesList.add(label);

        JTextField inputField = new JTextField("Input File Path:");
        inputField.setMaximumSize(new Dimension(450, 40));
        entriesList.add(inputField);

        JTextField separatorField = new JTextField("Separator:");
        separatorField.setAlignmentX(Component.CENTER_ALIGNMENT);
        separatorField.setMaximumSize(new Dimension(450, 40));
        entriesList.add(separatorField);

        JTextField outputField = new JTextField("Output File Path:");
        outputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        outputField.setMaximumSize(new Dimension(450, 40));
        entriesList.add(outputField);

        jPanel.add(logoPanel);
        jPanel.add(entriesPanel);
        this.add(jPanel);
        this.show();
    }


    // Methods

    // ...
}
