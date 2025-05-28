
package br.unit;

import br.unit.entities.StudentEntity;
import br.unit.gui.Gui;
import br.unit.repositories.FilePreProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @since 1.0
 */
public class Main {


    // Attributes

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static String inputFilePath;
    private static String outputFilePath;
    private static String separator;

    private static final ArrayList<StudentEntity> studentEntities = new ArrayList<>();


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

    public static void processFile() {
        LOGGER.info("Iniciando processamento do arquivo fornecido");


        // Data pre-processing

        LOGGER.info("# ---------- # Etapa 1: pre-processamento do arquivo # ---------- #");

        String[][] processedEntitiesMatrix = new FilePreProcessor(getInputFilePath(), getSeparator()).process();

        for (String[] entity : processedEntitiesMatrix) {
            if (entity.length != 5) {
                String message = String.format("Ocorreu um erro ao validar o vetor das colunas da linha do arquivo. Erro: a coluna do vetor não pode ter tamanho diferente de 5. Vetor inválido: %s", Arrays.toString(entity));
                LOGGER.severe(message);
                throw new IndexOutOfBoundsException(message);
            }
            studentEntities.add(new StudentEntity(Long.parseLong(entity[0]), entity[1], entity[2], entity[3], entity[4]));
        }

        LOGGER.info("# ---------- # Fim da etapa 1 # ---------- #");


        // TEMPORARY -------------------------------------------------------------------- //
        StringBuilder stringBuilder = new StringBuilder();
        for (StudentEntity entity : studentEntities.toArray(new StudentEntity[0])) {
            stringBuilder.append(String.format(
                    "\n  %s%s%s%s%s%s%s%s%s",
                    entity.enrolment(), separator,
                    entity.enrollmentDate(), separator,
                    entity.name(), separator,
                    entity.birthDate(), separator,
                    entity.course()
            ));
        }
        LOGGER.info(String.format("Resultado do processamento: %s", stringBuilder));
        // TEMPORARY -------------------------------------------------------------------- //


    }
}
