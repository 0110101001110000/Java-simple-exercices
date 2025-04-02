package br.unit;

public class Main {
    public static void main(String[] args) {
    	
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

        // Criando as filas
        Fila<Pessoa> filaEspecial = new Fila<>("Especial");
        Fila<Pessoa> filaNormal = new Fila<>();

        // Inserindo pessoas nas filas
        filaEspecial.inserir(new Pessoa("Ana", "F", "65")); // Prioridade (idosa)
        filaEspecial.inserir(new Pessoa("João", "M", "70")); // Prioridade (idoso)
        filaEspecial.inserir(new Pessoa("Maria", "F", "68")); // Prioridade (idosa)
        filaNormal.inserir(new Pessoa("Pedro", "M", "30"));
        filaNormal.inserir(new Pessoa("Lucas", "M", "25"));

        // Exibindo primeiros itens
        if (!filaEspecial.isEmpity()) {
            System.out.println("Primeiro da fila especial: " + filaEspecial.recuperar());
        }
        if (!filaNormal.isEmpity()) {
            System.out.println("Primeiro da fila normal: " + filaNormal.recuperar() + "\n");
        }

        // Simulação dos 5 caixas
        for (int i = 1; i <= 5; i++) {
            String proxFila;
            Pessoa pessoaAtendida = null;

            // Define qual fila será consumida primeiro
            if (i == 1 || i == 2) { // Caixas 1 e 2 priorizam fila especial
                if (!filaEspecial.isEmpity()) {
                    proxFila = "especial";
                    pessoaAtendida = filaEspecial.recuperar();
                    filaEspecial.remover();
                } else if (!filaNormal.isEmpity()) {
                    proxFila = "normal";
                    pessoaAtendida = filaNormal.recuperar();
                    filaNormal.remover();
                } else {
                    proxFila = null;
                }
            } else { // Caixas 3, 4 e 5 priorizam fila normal
                if (!filaNormal.isEmpity()) {
                    proxFila = "normal";
                    pessoaAtendida = filaNormal.recuperar();
                    filaNormal.remover();
                } else if (!filaEspecial.isEmpity()) {
                    proxFila = "especial";
                    pessoaAtendida = filaEspecial.recuperar();
                    filaEspecial.remover();
                } else {
                    proxFila = null;
                }
            }

            // Exibe o resultado do atendimento
            if (proxFila != null) {
                System.out.println("Caixa " + i + " atendeu: " + pessoaAtendida + " da fila " + proxFila);
            } else {
                System.out.println("Caixa " + i + ": Não há mais pessoas nas filas.");
            }
        }
    }
}