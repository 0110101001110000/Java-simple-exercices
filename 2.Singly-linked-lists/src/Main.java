
public class Main
{
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
        lista.removeInicio();
        lista.adiciona(a1, 0);
        lista.adicionaFim(a2);
        lista.adiciona(a3, 2);

        a1 = (Aluno) lista.Recupera(1);

        //System.out.println(a1.getNome() + " - " + a1.getIdade());
        lista.mostrarItems();

        System.out.println("Aluno " + a1.getNome() + " existe na lista? " + lista.existeDado(a4));
	}
}
