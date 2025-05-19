
package br.unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;


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
        System.out.println("# ---------- # Etapa 1: processamento das empresas # ---------- #");
        File    document;
        Scanner documentScanner;
        try {
            System.out.printf("\nProcessando conteúdo do arquivo %s ...\n", documentPath);

            document        = new File(documentPath);
            documentScanner = new Scanner(document);

            companiesVector   = readCompanies(documentScanner, separator);
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
    }

    /**
     * ...
     * @since 1.0
     */
    private static Company[] readCompanies(Scanner documentScanner, String separator) {
        ArrayList<Company> list = new ArrayList<>();

        while (documentScanner.hasNext()) {
            String   companyRow  = documentScanner.nextLine();
            String[] companyCols = companyRow.split(separator);

            try {
                validateCompanyColumns(companyCols);
            }
            catch (NullPointerException | IllegalArgumentException exception) {
                System.err.printf("Ocorreu um erro ao validar a empresa: %s\n", exception);
            }
            catch (Exception exception) {
                throw new RuntimeException("Ocorreu uma exceção: ", exception);
            }

            String  companyName      = companyCols[0];
            String  companyRegNum    = companyCols[1];
            double  companyMarkValue = Double.parseDouble(companyCols[2]);
            Company currentCompany   = new Company(companyName, companyRegNum, companyMarkValue);

            list.add(currentCompany);
        }

        return list.toArray(new Company[0]);
    }

    /**
     * ...
     * @since 1.0
     */
    private static void validateCompanyColumns(String[] columns) {
        String row = (columns.length == 3) ? String.format("%s | %s | %s", columns[0], columns[1], columns[2]) : Arrays.toString(columns);

        if (columns.length != 3) {
            throw new IllegalArgumentException(String.format("A estrutura da empresa %s é inválida. Estrutura permitida: Nome Fantasia | Inscrição Estadual | Valor de Mercado", row));
        }
        if ((columns[0] == null) | (columns[1] == null) | (columns[2] == null)) {
            throw new NullPointerException(String.format("Algum dos seguintes valores da empresa é nulo: Nome Fantasia: %s, Inscrição Estadual: %s, Valor de Mercado: %s", columns[0], columns[1], columns[2]));
        }
        if (columns[0].isBlank() | columns[1].isBlank() | columns[2].isBlank()) {
            throw new IllegalArgumentException(String.format("Algum dos seguintes valores da empresa está vazio: Nome Fantasia: %s, Inscrição Estadual: %s, Valor de Mercado: %s", columns[0], columns[1], columns[2]));
        }
        if (!isConvertibleToDouble(columns[2])) {
            throw new IllegalArgumentException(String.format("O valor de mercado da empresa não é convertível para Double. Empresa: Nome Fantasia: %s, Inscrição Estadual: %s, Valor de Mercado: %s", columns[0], columns[1], columns[2]));
        }
    }

    /**
     * ...
     * @since 1.0
     */
    private static boolean isConvertibleToDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
