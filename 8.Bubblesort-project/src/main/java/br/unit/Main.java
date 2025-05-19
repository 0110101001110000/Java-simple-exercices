
package br.unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @since 1.0
 */
public class Main {

    public static void main(String[] args) {

        // Global constants
        final String    documentPath = "src/main/java/br/unit/temp.txt";
        final String    separator    = " [|] ";
        final int       amountOfCompanies;
        final Company[] companiesVector;

        // Global variables
        // ...

        // Read companies from file
        File    document;
        Scanner documentScanner;
        try {
            System.out.println("# ---------- # Etapa 1: processamento das empresas # ---------- #");
            System.out.printf("\nProcessando conteúdo do arquivo %s ...\n", documentPath);

            document        = new File(documentPath);
            documentScanner = new Scanner(document);

            companiesVector   = CompanyProcessor.processCompanies(documentScanner, separator);
            amountOfCompanies = companiesVector.length;

            System.out.printf("%d empresas foram processadas.\n\n", amountOfCompanies);
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
            System.out.println("# ---------- # Fim da etapa 1 # ---------- #");
        }

        // Sorting companies
        BubbleSort.Ordenar(companiesVector, amountOfCompanies);

        for (Company company : companiesVector) {
            if (company != null) {
                System.out.printf(
                        "Name: %s, RegistrationNumber: %s, MarketValue: %.2f\n",
                        company.name(), company.registrationNumber(), company.marketValue()
                );
            }
        }
    }
}
