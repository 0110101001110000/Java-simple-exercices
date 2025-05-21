
package br.unit;

import br.unit.entities.RaceLapEntity;

import java.time.Duration;
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



        //TESTE
        RaceLapEntity lap1 = new RaceLapEntity("2025-10-05 10:23:33", "Leonardo Almeida", "Leonardo Almeida", "02:19:718");
        int millis = TempoDeVolta(lap1);
        System.out.println("Teste" + lap1.duration() + " = " + millis + " ms");{
        }
    }

    //converte corretamente o tempo de volta do formato "MM:SS:MMM" para o total de milissegundos.
    public static int TempoDeVolta(RaceLapEntity entity){
        String[] parts = entity.duration().split(":");

        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        int miliseconds = Integer.parseInt(parts[2]);

        Duration duration = Duration.ofMinutes(minutes)
                .plusSeconds(seconds)
                .plusMillis(miliseconds);

        return (int) duration.toMillis();

    }
}

