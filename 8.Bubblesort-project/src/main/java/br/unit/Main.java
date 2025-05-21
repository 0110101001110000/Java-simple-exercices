
package br.unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


// Init -------------------------------------------------------------------- //


/**
 * Classe principal para o processo de leitura, ordenação, exibição de informações sobre as empresas e escrita em novos arquivo txt.
 * @since 1.0
 */
public class Main {


    /**
     * Método principal que inicia o processo.
     * @param args Argumentos da linha de comando
     * @since 1.0
     */
    public static void main(String[] args) {

        // Global constants
        final String    documentPath = "src/main/java/br/unit/Empresas_BubbleSort.txt";
        final String    separator    = " [|] ";
        final int       amountOfCompanies;
        final Company[] companiesVector;

        // Read companies from file
        File    document;
        Scanner documentScanner;
        try {
            System.out.println("\n# ---------- # Etapa 1: processamento das empresas # ---------- #");
            System.out.printf("\nProcessando conteúdo do arquivo %s ...\n", documentPath);

            document        = new File(documentPath);
            documentScanner = new Scanner(document);

            companiesVector   = CompanyProcessor.processCompanies(documentScanner, separator);
            amountOfCompanies = companiesVector.length;

            System.out.printf("%d empresas foram processadas.\n", amountOfCompanies);
        }
        catch (FileNotFoundException exception) {
            throw new RuntimeException(String.format("Arquivo não encontrado %s", documentPath), exception);
        }
        catch (Exception exception) {
            throw new RuntimeException("Ocorreu uma exceção: ", exception);
        }
        finally {
            document        = null;
            documentScanner = null;
            System.out.println("\n# ---------- # Fim da etapa 1 # ---------- #");
        }

        // Sorting companies
        System.out.println("\n# ---------- # Etapa 2: Ordenação # ---------- #\n");
        BubbleSort.Ordenar(companiesVector, amountOfCompanies);
        System.out.println("\n# ---------- # Fim da etapa 2 # ---------- #\n");

        for (Company company : companiesVector) {
            if (company != null) {
                System.out.printf(
                        "Name: %s, RegistrationNumber: %s, MarketValue: %.2f\n",
                        company.name(), company.registrationNumber(), company.marketValue()
                );
            }
        }

        //--------------
        String home = System.getProperty("user.dir"); //Busca o caminho que foi alocado o projeto
        String nomeArquivo = "BubbleSort.txt";
        String caminho = home + File.separator + nomeArquivo;
        System.out.println(caminho);

        try{
            FileWriter Writer = new FileWriter(caminho, false); //parametro false me permite sobrescrever o documento ; enquanto o true adiciona ao documento
            Writer.write("Lista Ordenada Utilizando BubbleSort \n \n");

            for (Company company : companiesVector) {
                if (company != null) {
                    String padraoDados = String.format(
                            "%s | %s | %.2f \n",
                            company.name(), company.registrationNumber(), company.marketValue());
                    Writer.write(padraoDados);

                }
            }
            System.out.println("Arquivo foi salvo com sucesso verifique no caminho: \n "+ nomeArquivo);
            Writer.close();
        }

        catch (IOException e) {
            System.err.println("Ocorreu um erro algo gravar o arquivo"+ e.getMessage());
        }
    }
}

        /*

        int totalEmpresas = companiesVector.length;
        int progresso = 100000; // Atualiza a barra a cada 100.000 empresas
        int processed = 0; // Contador de empresas processadas


        processed++; // Incrementa o contador
        // A cada 100.000 empresas, atualiza a barra de progresso
        if (processed % progresso == 0) {
            int percent = (processed * 100) / totalEmpresas;
            // Monta a barra de progresso
            StringBuilder barra = new StringBuilder();
            int barrasCompletas = percent / 10;

            // Preenche com █ (completadas) e ▒ (incompletadas)
            for (int j = 1; j <= barrasCompletas; j++) {
                barra.append("█");
            }
            for (int j = barrasCompletas + 1; j <= 10; j++) {
                barra.append("▒");
            }

            // Limpa a linha anterior e exibe a nova barra
            System.out.print("\rProcessando: " + barra + " " + percent + "% (" + processed + " empresas)");

        }
         */
