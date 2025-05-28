
package br.unit;

import br.unit.gui.Gui;

import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @since 1.0
 */
public class Main {


    // Attributes

    private final String DATA_PATH = String.format("%s/src/main/java/br/unit/data", System.getProperty("user.dir"));
    private final Logger LOGGER    = Logger.getLogger(Main.class.getName());

    private static String inputFilePath;
    private static String outputFilePath;
    private static String separator;


    // Constructors

    public Main() {}


    // Getter methods

    public static String getInputFilePath() {
        return inputFilePath;
    }

    public static String getOutputFilePath() {
        return outputFilePath;
    }

    public static String getSeparator() {
        return separator;
    }


    // Setter methods

    public static void setInputFilePath(String inputFilePath) {
        Main.inputFilePath = inputFilePath;
    }

    public static void setOutputFilePath(String outputFilePath) {
        Main.outputFilePath = outputFilePath;
    }

    public static void setSeparator(String separator) {
        Main.separator = separator;
    }


    // Main methods

    public static void main(String[] args) {

        new Gui();
    }
}
