package br.unit;


// Init -------------------------------------------------------------------- //


/**
 * Método da Bolha
 * @author Matheussgb
 * @since 1.0
 */

public class BubbleSort {
    public static void Ordenar(Company[] vet, int n){
        int i,j;
        Company tmp;

        for (i = 0; i < n - 1; i++){
            System.out.println("Realizando ordenação. Etapa " + (i+1) + "/" + (n));
            for (j = n - 1; j > i; j--){
                if (vet[j].marketValue() > vet[j - 1].marketValue()){
                    tmp = vet[j - 1];
                    vet[j - 1] = vet[j];
                    vet[j] = tmp;
                }
            }
        }
    }
}
