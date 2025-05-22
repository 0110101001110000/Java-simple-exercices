// Init -------------------------------------------------------------------- //


/**
 * @author Matheussgb
 * @since 1.0
 */

package br.unit;
import br.unit.entities.RaceLapEntity;

public class InsertSort {
    public void Ordenar(RaceLapEntity[] vet, int n) {
        int j;
        RaceLapEntity tmp;

        for (int p = 1; p < n; p++) {
            tmp = vet[p];
            j = p;

            while (j > 0 && compare(tmp, vet[j - 1]) < 0) {
                vet[j] = vet[j - 1];
                j--;
            }
            vet[j] = tmp;
        }
    }

    private int compare(RaceLapEntity a, RaceLapEntity b) {
        int tempoA = Main.TempoDeVolta(a);
        int tempoB = Main.TempoDeVolta(b);

        if (tempoA != tempoB) {
            return tempoA - tempoB;
        }

        return a.driverName().compareToIgnoreCase(b.driverName());
    }
}
