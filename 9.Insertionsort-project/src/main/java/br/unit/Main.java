
package br.unit;

import br.unit.entities.RaceLapEntity;
import br.unit.entities.insertSort;

import java.time.Duration;
import java.util.ArrayList;
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
        final String inputFilePath  = String.format("%s/formula1-insertionsort.txt", dataPath);
        final String outputFilePath = String.format("%s/temp-output.txt", dataPath);
        final String separator      = " - ";
        final Logger logger         = Logger.getLogger(Main.class.getName());

        final ArrayList<RaceLapEntity> raceLapEntities = new ArrayList<>();


        // Data pre-processing

        logger.info("# ---------- # Etapa 1: pre-processamento do arquivo # ---------- #");

        String[][] processedEntitiesMatrix = new FilePreProcessor(inputFilePath, separator).process();

        for (String[] entity : processedEntitiesMatrix) {
            if (entity.length != 4) {
                logger.severe(String.format("Ocorreu um erro ao validar o vetor das colunas da linha do arquivo. Erro: a coluna do vetor não pode ter tamanho diferente de 4. Vetor inválido: %s", Arrays.toString(entity)));
                throw new IndexOutOfBoundsException("A coluna do vetor não pode ter tamanho diferente de 4");
            }
            raceLapEntities.add(new RaceLapEntity(entity[0], entity[1], entity[2], entity[3]));
        }

        logger.info("# ---------- # Fim da etapa 1 # ---------- #");

        StringBuilder stringBuilder = new StringBuilder();
        for (RaceLapEntity entity : raceLapEntities.toArray(new RaceLapEntity[0])) {
            stringBuilder.append(String.format("\n  %s%s%s%s%s%s%s",
                    entity.date(), separator, entity.driverName(), separator, entity.team(), separator, entity.duration())
            );
        }
        logger.info(String.format("Resultado do processamento: %s", stringBuilder));

        // <Ordenação das voltas pelo tempo e nome do piloto>

        logger.info("# ---------- # Etapa 2: < Ordenar as voltas pelo tempo e nome do piloto> # ---------- #");
        RaceLapEntity[] lapsArray = raceLapEntities.toArray(new RaceLapEntity[0]);
        insertSort sorter = new insertSort();
        sorter.Ordenar(lapsArray, lapsArray.length);

        logger.info("# ---------- # Fim da etapa 2 # ---------- #");

        StringBuilder sortedResult = new StringBuilder();
        for (RaceLapEntity entity : lapsArray) {
            sortedResult.append(String.format("\n  %s%s%s%s%s%s%s",
                    entity.date(), separator, entity.driverName(), separator, entity.team(), separator, entity.duration())
            );
        }
        logger.info(String.format("Resultado após ordenação: %s", sortedResult));


        // <NOME DA ETAPA AQUI>

        logger.info("# ---------- # Etapa 3: <NOME DA ETAPA AQUI> # ---------- #");
        // <CÓDIGO AQUI>
        logger.info("# ---------- # Fim da etapa 3 # ---------- #");
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

