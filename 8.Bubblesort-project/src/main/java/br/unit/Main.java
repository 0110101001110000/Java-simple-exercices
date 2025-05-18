
package br.unit;

import java.io.File;
import java.util.Scanner;


// Init -------------------------------------------------------------------- //


public class Main {

    public static void main(String[] args) {

        // Global constants
        final String    documentPath      = "src/main/java/br/unit/Empresas_BubbleSort.txt";
        final int       amountOfCompanies = 1000000;
        final Company[] companiesVector   = new Company[amountOfCompanies];

        // Global variables
        boolean documentReaderLoop = true;

        // Read document
        try {
            final File    document        = new File(documentPath);
            final Scanner documentScanner = new Scanner(document);
            final String  separator       = " [|] ";

            for (int companyIndex = 0; companyIndex < amountOfCompanies; companyIndex++) {
                if (documentScanner.hasNext()) {
                    String   companyRow       = documentScanner.nextLine(); 
                    String[] companyCols      = companyRow.split(separator);
                    String   companyName      = companyCols[0];
                    String   companyRegNum    = companyCols[1];
                    String   companyMarkValue = companyCols[2];
                    double   doubComMarkValue = (!companyMarkValue.isBlank()) ? Double.parseDouble(companyMarkValue) : null;
                    Company  currentCompany   = new Company(companyName, companyRegNum, doubComMarkValue);
                    companiesVector[companyIndex] = currentCompany;
                }
                else {
                    break;
                }
            }

            int totalEmpresasLidas = 0;
            for (Company company : companiesVector) {
                if (company != null) totalEmpresasLidas++;
                else{
                    break;
                }
            }

            BubbleSort.Ordenar(companiesVector, totalEmpresasLidas);

            for (Company company : companiesVector) {
                if (company != null) {
                    System.out.printf(
                            "Name: %s, RegistrationNumber: %s, MarketValue: %.2f\n",
                            company.name(), company.registrationNumber(), company.marketValue()
                    );
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /*
    private static String[] treatCompanyVector(String[] companyCols) {

    }
    */
}
