
package br.unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @author 01101010-01110000
 * @since 1.0
 */
public class CompanyProcessor {


    // Main methods

    /**
     * ...
     * @param documentScanner ...
     * @param separator ...
     * @return ...
     * @since 1.0
     */
    public static Company[] processCompanies(Scanner documentScanner, String separator) {
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


    // Validation methods

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


    // Other methods

    private static boolean isConvertibleToDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
