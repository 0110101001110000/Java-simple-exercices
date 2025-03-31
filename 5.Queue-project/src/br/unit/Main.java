
package br.unit;


// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
public class Main {

    public static void main(String[] args) {

        // Welcome message
        System.out.println("""
             .-')                   ('-.                        _ (`-. _  .-')                           ('-.             .-') _   \s
           .(  OO)                _(  OO)                      ( (OO  | \\( -O )                        _(  OO)           (  OO) )  \s
          (_)---\\_)   ,--. ,--.  (,------. ,--. ,--.          _.`     \\,------.  .-'),-----.      ,--.(,------.   .-----./     '._ \s
          '  .-.  '   |  | |  |   |  .---' |  | |  |         (__...--''|   /`. '( OO'  .-.  ' .-')| ,| |  .---'  '  .--./|'--...__)\s
         ,|  | |  |   |  | | .-') |  |     |  | | .-')        |  /  | ||  /  | |/   |  | |  |( OO |(_| |  |      |  |('-.'--.  .--'\s
        (_|  | |  |   |  |_|( OO ||  '--.  |  |_|( OO )       |  |_.' ||  |_.' |\\_) |  |\\|  || `-'|  |(|  '--.  /_) |OO  )  |  |   \s
          |  | |  |   |  | | `-' /|  .--'  |  | | `-' /       |  .___.'|  .  '.'  \\ |  | |  |,--. |  | |  .--'  ||  |`-'|   |  |   \s
          '  '-'  '-.('  '-'(_.-' |  `---.('  '-'(_.-'        |  |     |  |\\  \\    `'  '-'  '|  '-'  / |  `---.(_'  '--'\\   |  |   \s
           `-----'--'  `-----'    `------'  `-----'           `--'     `--' '--'     `-----'  `-----'  `------'   `-----'   `--'   \s
        """);

        // Objects
        Fila<String> filaEspecial = new Fila<>("Especial");
        Fila<String> filaNormal   = new Fila<>();

        // Insert items
        filaEspecial.inserir("1.A");
        filaEspecial.inserir("1.B");
        filaEspecial.inserir("1.C");
        // filaEspecial.inserir("1.D");
        filaNormal.inserir("2.A");
        // filaNormal.inserir("2.B");
        // filaNormal.inserir("2.C");
        // filaNormal.inserir("2.D");
        // filaNormal.inserir("2.E");

        // Get first item for each queue
        if (!filaEspecial.isEmpity()) {
            System.out.println("Primeiro item da fila especial: " + filaEspecial.recuperar());
        }
        if (!filaNormal.isEmpity()) {
            System.out.println("Primeiro item da fila normal: " + filaNormal.recuperar() + "\n");
        }

        // Removes the items from the Queues
        String prox;
        for (int i = 1; i <= 5; i++) {
            try {
                // Set the next to be removed
                if ((i == 1) || (i == 2)) {
                    if (!filaEspecial.isEmpity()) {
                        prox = "especial";
                    } else {
                        prox = (!filaNormal.isEmpity()) ? "normal" : null;
                    }
                } else {
                    if (!filaNormal.isEmpity()) {
                        prox = "normal";
                    } else {
                        prox = (!filaEspecial.isEmpity()) ? "especial" : null;
                    }
                }

                // Removes from the queue
                if (prox != null) {
                    if (prox.equals("especial")) {
                        System.out.println("Removeu " + filaEspecial.recuperar() + " da fila especial");
                        filaEspecial.remover();
                    } else {
                        System.out.println("Removeu " + filaNormal.recuperar() + " da fila normal");
                        filaNormal.remover();
                    }
                } else {
                    System.out.println("Não removeu, pois todas as listas estão vazias");
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}