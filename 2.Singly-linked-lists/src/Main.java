

// Init -------------------------------------------------------------------- //


/**
 * @author 01101010-01110000
 */
public class Main {

	public static void main(String[] args) {

        // Welcome message
        System.out.println("""
                  .-')               .-') _                                                           .-') _.-. .-')    ('-. _ .-') _                          .-')   .-') _    .-')   \s
                 ( OO ).            ( OO ) )                                                         ( OO ) \\  ( OO ) _(  OO( (  OO) )                        ( OO ).(  OO) )  ( OO ). \s
                (_)---\\_) ,-.-'),--./ ,--,' ,----.    ,--.      ,--.   ,--.       ,--.     ,-.-'),--./ ,--,',--. ,--.(,------\\     .'_        ,--.     ,-.-')(_)---\\_/     '._(_)---\\_)\s
                /    _ |  |  |OO|   \\ |  |\\'  .-./-') |  |.-')   \\  `.'  /        |  |.-') |  |OO|   \\ |  |\\|  .'   / |  .---,`'--..._)       |  |.-') |  |OO/    _ ||'--...__/    _ | \s
                \\  :` `.  |  |  |    \\|  | |  |_( O- )|  | OO ).-')     /         |  | OO )|  |  |    \\|  | |      /, |  |   |  |  \\  '       |  | OO )|  |  \\  :` `.'--.  .--\\  :` `. \s
                 '..`''.) |  |(_|  .     |/|  | .--, \\|  |`-' (OO  \\   /          |  |`-' ||  |(_|  .     |/|     ' _(|  '--.|  |   ' |       |  |`-' ||  |(_/'..`''.)  |  |   '..`''.)\s
                .-._)   \\,|  |_.|  |\\    |(|  | '. (_(|  '---.'|   /  /\\_        (|  '---.,|  |_.|  |\\    | |  .   \\  |  .--'|  |   / :      (|  '---.,|  |_..-._)   \\  |  |  .-._)   \\\s
                \\       (_|  |  |  | \\   | |  '--'  | |      | `-./  /.__)        |      (_|  |  |  | \\   | |  |\\   \\ |  `---|  '--'  /       |      (_|  |  \\       /  |  |  \\       /\s
                 `-----'  `--'  `--'  `--'  `------'  `------'   `--'             `------' `--'  `--'  `--' `--' '--' `------`-------'        `------' `--'   `-----'   `--'   `-----' \s
        """);


        // Objects

        Aluno a1 = new Aluno("Carlos Kayky", 20);
        Aluno a2 = new Aluno("Victor Rafael", 24);
        Aluno a3 = new Aluno("Luis Gustavo", 19);
        Aluno a4 = new Aluno("Matheus Gabriel", 19);

        ListaSimples lista = new ListaSimples();


        // Some tests

        lista.adicionaInicio(a1);
        lista.adiciona(a2, 1);
        lista.adicionaFim(a3);
        lista.adicionaFim(a4);

        System.out.printf("Alunos:\n  - %s;\n  - %s;\n  - %s;\n  - %s;\n",
                ((Aluno) lista.Recupera(0)).getNome(),
                ((Aluno) lista.Recupera(1)).getNome(),
                ((Aluno) lista.Recupera(2)).getNome(),
                ((Aluno) lista.Recupera(3)).getNome()
        );
        System.out.printf("Tamanho da lista: %d.\n", lista.tamanho());
	}
}
