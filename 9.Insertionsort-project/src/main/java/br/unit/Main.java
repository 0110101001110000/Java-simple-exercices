
package br.unit;

import java.util.Arrays;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {

        // Global Constants
        final String dataPath       = "src/main/java/br/unit/data";
        final String inputFilePath  = String.format("%s/temp-input.txt", dataPath);
        final String outputFilePath = String.format("%s/temp-output.txt", dataPath);
        final String separator      = " - ";
        final Logger logger         = Logger.getLogger(Main.class.getName());

        // Data pre-processing
        logger.info("# ---------- # Etapa 1: pre-processamento do arquivo # ---------- #");
        String resultToPrint = Arrays.deepToString(new FilePreProcessor(inputFilePath, separator).process());
        logger.info("# ---------- # Fim da etapa 1 # ---------- #");
        logger.info(String.format("Resultado do processamento: %s", resultToPrint));

        // <NOME DA ETAPA AQUI>
        logger.info("# ---------- # Etapa 2: <NOME DA ETAPA AQUI> # ---------- #");
        // <CÓDIGO AQUI>
        logger.info("# ---------- # Fim da etapa 2 # ---------- #");

        // <NOME DA ETAPA AQUI>
        logger.info("# ---------- # Etapa 3: <NOME DA ETAPA AQUI> # ---------- #");
        // <CÓDIGO AQUI>
        logger.info("# ---------- # Fim da etapa 3 # ---------- #");
    }
}
