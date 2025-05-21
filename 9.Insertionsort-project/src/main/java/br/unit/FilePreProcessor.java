
package br.unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @author 01101010-01110000
 * @since 1.0
 */
public class FilePreProcessor {


    // Attributes

    private final Logger logger = Logger.getLogger(FilePreProcessor.class.getName());
    private final String filePath;
    private final String separator;


    // Constructors

    /**
     * ...
     * @since 1.0
     */
    public FilePreProcessor(String filePath, String separator) {
        this.validateNotNull(filePath, "O caminho do arquivo não pode ser 'null'");
        this.validateNotNull(separator, "O separador não pode ser 'null");

        this.filePath = filePath;
        this.separator = separator;
    }


    // Main methods

    public String[][] process() {
        logger.info(String.format("Iniciando pre-processamento do arquivo '%s'", filePath));

        Scanner reader = openFile();
        String[][] matrix = this.readFile(reader);

        logger.info(String.format("Pre-processamento do arquivo '%s' concluído", filePath));

        return matrix;
    }

    private Scanner openFile() {
        try {
            File file = new File(this.filePath);

            return new Scanner(file);
        }
        catch (FileNotFoundException exception) {
            throw new RuntimeException(String.format("O arquivo não foi encontrado no caminho '%s'", this.filePath));
        }
    }

    private String[][] readFile(Scanner reader) {
        ArrayList<String[]> arrayList = new ArrayList<>();

        while (reader.hasNext()) {
            String   row     = reader.nextLine();
            String[] columns = row.split(this.separator);
            if (this.columnsIsValid(columns)) {
                arrayList.add(columns);
            }
        }

        return arrayList.toArray(new String[0][0]);
    }

    private boolean columnsIsValid(String[] columns) {
        boolean isValid = true;
        String  vector  = Arrays.toString(columns);

        if (columns.length < 1) {
            logger.warning(String.format("Ocorreu um erro ao validar o vetor das colunas da linha do arquivo. Erro: o vetor não pode ter length menor que 1. Vetor inválido: %s. Essa linha será ignorada", vector));
            isValid = false;
        }
        for (String item : columns) {
            if (item.isBlank()) {
                logger.warning(String.format("Ocorreu um erro ao validar o vetor das colunas da linha do arquivo. Erro: a coluna do vetor não pode ser vazia. Vetor inválido: %s. Essa linha será ignorada", vector));
                isValid = false;
            }
        }

        return isValid;
    }


    // Validation methods

    private void validateNotNull(Object file, String message) {
        if (file == null) {
            throw new NullPointerException(message);
        }
    }


    // Util methods

    // ...
}
