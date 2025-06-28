
package br.unit.seatwise_project.db;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Init -------------------------------------------------------------------- //


public class Database {


    // Attributes

    private final String filename;


    // Constructors

    public Database(String filename) {
        this.filename = filename;
    }


    // Getter methods

    public String getFilename() {
        return filename;
    }


    // Main methods

    /**
     * Cria um novo registro (adiciona uma nova linha ao arquivo).
     */
    public void createRecord(String content) throws IOException {
        FileWriter writer = new FileWriter(this.getFilename(), true);
        writer.write(content);
        writer.write(System.lineSeparator());
        writer.close();
    }

    /**
     * Cria novos registros (adiciona novas linhas ao arquivo).
     */
    public void createRecords(String[] content) throws IOException {
        FileWriter writer = new FileWriter(this.getFilename(), true);
        for (String row : content) {
            writer.write(row);
            writer.write(System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Lê todos os registros do arquivo.
     */
    public List<String> readRecords() throws IOException {
        List<String> list   = new ArrayList<>();
        File         file   = new File(this.getFilename());
        Scanner      reader = new Scanner(file);

        while (reader.hasNextLine()) {
            list.add(reader.nextLine());
        }

        return list;
    }

    /**
     * Atualiza um registro existente (baseado no id).
     */
    public void updateRecord(Long id, String newContent) throws IOException {
        // ...
    }

    /**
     * Remove um registro (baseado no id).
     */
    public boolean deleteRecord(Long id) throws IOException {
        List<String> temporaryList = this.readRecords();

        for (int i = 0; i < temporaryList.size(); i++) {
            Long item_id = Long.parseLong(temporaryList.get(i).split(",")[0]);

            if (id.equals(item_id)) {
                temporaryList.remove(i);
                this.clear();
                this.createRecords(temporaryList.toArray(new String[0]));

                return true;
            }
        }

        return false;
    }

    /**
     * Remove todos os registros presentes no arquivo.
     */
    public void clear() throws IOException {
        FileWriter writer = new FileWriter(this.getFilename(), false);
        writer.write("");
        writer.close();
    }

    /**
     * Gera o próximo ID com base nos registros existentes.
     */
    public long generateNextId() throws IOException {
        List<String> temporaryList = this.readRecords();

        long nextId = 0;

        for (String row : temporaryList) {
            long id = Long.parseLong(row.split(",")[0]);
            nextId = Math.max(id, nextId);
        }

        return ++nextId;
    }
}
