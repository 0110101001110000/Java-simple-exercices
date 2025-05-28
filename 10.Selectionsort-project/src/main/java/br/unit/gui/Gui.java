
package br.unit.gui;

import br.unit.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


/**
 * Classe Gui responsável por criar a interface gráfica do usuário.
 * Fornece campos de entrada para caminho do arquivo, separador e caminho de saída, além de um botão para processar os dados.
 * @author 01101010-01110000
 * @since 1.0
 */
public class Gui extends JFrame {


    // Attributes

    private final Logger LOGGER = Logger.getLogger(Gui.class.getName());

    private final JTextField inputFilePathField;
    private final JTextField outputFilePathField;
    private final JTextField separatorField;
    private final JButton    submitButton;


    // Constructors

    /**
     * Construtor da classe Gui.
     * Inicializa os componentes da interface gráfica, define o layout e adiciona os listeners.
     * @since 1.0
     */
    public Gui() throws HeadlessException {
        LOGGER.info("Aplicando configurações iniciais");

        Color backgroundColor = Color.WHITE;

        this.setResizable(true);
        this.setTitle("Gui-v2");
        this.setBackground(backgroundColor);
        this.setLayout(new BorderLayout());
        this.setSize(512, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Margin panels

        LOGGER.info("Criando painéis de margin");

        JPanel northJPanel  = new JPanel();
        JPanel southJPanel  = new JPanel();
        JPanel westJPanel   = new JPanel();
        JPanel eastJPanel   = new JPanel();
        JPanel centerJPanel = new JPanel();

        northJPanel.setBackground(backgroundColor);
        southJPanel.setBackground(backgroundColor);
        westJPanel.setBackground(backgroundColor);
        eastJPanel.setBackground(backgroundColor);
        centerJPanel.setBackground(backgroundColor);

        northJPanel.setPreferredSize(new Dimension(77, 100));
        southJPanel.setPreferredSize(new Dimension(77, 100));
        westJPanel.setPreferredSize(new Dimension(51, 100));
        eastJPanel.setPreferredSize(new Dimension(51, 100));
        centerJPanel.setPreferredSize(new Dimension(100, 100));


        // Content panels

        LOGGER.info("Criando painéis de conteúdo");

        centerJPanel.setLayout(new GridLayout(2, 1, 0, 2));

        JPanel logoJPanel    = new JPanel();
        JPanel entriesJPanel = new JPanel();
        JPanel entriesItemsJPanel = new JPanel();

        logoJPanel.setBackground(backgroundColor);
        entriesJPanel.setBackground(backgroundColor);
        entriesItemsJPanel.setBackground(backgroundColor);

        logoJPanel.setPreferredSize(new Dimension(100, 100));
        entriesJPanel.setPreferredSize(new Dimension(100, 100));
        entriesItemsJPanel.setPreferredSize(new Dimension(352, 194));


        // Logo content

        LOGGER.info("Adicionando logo");

        logoJPanel.setLayout(new BorderLayout());

        ImageIcon logoIcon = new ImageIcon("src/main/java/br/unit/gui/images/a.png");
        JLabel    logo     = new JLabel(logoIcon);

        logo.setBackground(backgroundColor);
        logo.setOpaque(true);


        // Form content

        LOGGER.info("Adicionando entradas de texto e botão do formulário");

        entriesJPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        entriesItemsJPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        inputFilePathField  = new JTextField();
        outputFilePathField = new JTextField();
        separatorField      = new JTextField();
        submitButton        = new JButton();

        inputFilePathField.setPreferredSize(new Dimension(350, 38));
        outputFilePathField.setPreferredSize(new Dimension(350, 38));
        separatorField.setPreferredSize(new Dimension(350, 38));
        submitButton.setPreferredSize(new Dimension(350, 38));

        inputFilePathField.setText("Input File Path");
        outputFilePathField.setText("Output File Path");
        separatorField.setText("Separator");
        submitButton.setText("Submit");

        submitButton.addActionListener(new ButtonHandler());


        // Add all panels

        LOGGER.info("Adicionando painéis à tela");

        logoJPanel.add(logo, BorderLayout.CENTER);

        entriesItemsJPanel.add(inputFilePathField);
        entriesItemsJPanel.add(outputFilePathField);
        entriesItemsJPanel.add(separatorField);
        entriesItemsJPanel.add(submitButton);

        entriesJPanel.add(entriesItemsJPanel);

        centerJPanel.add(logoJPanel);
        centerJPanel.add(entriesJPanel);

        this.add(northJPanel, BorderLayout.NORTH);
        this.add(southJPanel, BorderLayout.SOUTH);
        this.add(westJPanel, BorderLayout.WEST);
        this.add(eastJPanel, BorderLayout.EAST);
        this.add(centerJPanel, BorderLayout.CENTER);

        this.setVisible(true);
        //this.show();
    }


    // Classes

    private class ButtonHandler implements ActionListener {

        /**
         * Método responsável por lidar com o clique do botão de envio.
         * Recupera os valores dos campos de entrada e os envia para processamento.
         * @param e Evento do clique do botão.
         * @since 1.0
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == submitButton) {
                LOGGER.info(String.format(
                        "Textos recebidos:\n  InputFilePath: %s\n  OutputFilePath: %s\n  Separator: %s",
                        inputFilePathField.getText(),
                        outputFilePathField.getText(),
                        separatorField.getText()
                ));
                try {
                    Main.setInputFilePath(inputFilePathField.getText());
                    Main.setOutputFilePath(outputFilePathField.getText());
                    Main.setSeparator(separatorField.getText());
                    Main.processFile();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado. Tente novamente.", "Ocorreu um Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
