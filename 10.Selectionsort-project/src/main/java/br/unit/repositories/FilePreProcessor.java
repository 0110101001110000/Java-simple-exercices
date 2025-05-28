
package br.unit.repositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;


// Init -------------------------------------------------------------------- //


/**
 * Processa arquivo externo e transforma os dados em uma matriz de strings.
 * É responsável por ler o arquivo, separar os dados de acordo com o separador informado e validar as colunas resultantes.
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
     * Inicializa um novo processador de arquivos.
     * @param filePath Caminho do arquivo a ser processado
     * @param separator Caractere(s) separador das colunas
     * @throws NullPointerException Se algum dos parâmetros for nulo
     * @since 1.0
     */
    public FilePreProcessor(String filePath, String separator) {
        this.validateNotNull(filePath, "O caminho do arquivo não pode ser 'null'");
        this.validateNotNull(separator, "O separador não pode ser 'null");
        if (separator.trim().equals("|")) {
            separator = separator.replace("|", "[|]");
        }

        this.filePath = filePath;
        this.separator = separator;
    }


    // Main methods

    /**
     * Executa o pré-processamento do arquivo e retorna os dados em formato de matriz.
     * @return Matriz de strings contendo os dados processados
     * @since 1.0
     */
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
            String message = String.format("O arquivo não foi encontrado no caminho '%s'", this.filePath);
            logger.severe(String.format("Ocorreu um erro: %s", message));
            throw new RuntimeException(message);
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
            logger.severe(String.format("Ocorreu um erro: %s", message));
            throw new NullPointerException(message);
        }
    }
}
