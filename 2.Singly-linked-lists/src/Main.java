
public class Main
{
	public static void main(String[] args) {
		
		Aluno a1 = new Aluno("Carlos Kayky", 20);
		Aluno a2 = new Aluno("Victor Rafael", 24);
		Aluno a3 = new Aluno("Luis Gustavo", 19);
        Aluno a4 = new Aluno("Matheus Gabriel", 19);

	    ListaSimples lista = new ListaSimples();

        lista.adicionaInicio(a1);

        lista.removeInicio();

        lista.adiciona(a1, 0);

        lista.adicionaFim(a2);

        lista.adiciona(a3, 2);

        lista.adiciona(a4, 0);
        
        a1 = (Aluno) lista.Recupera(1);

        //System.out.println(a1.getNome() + " - " + a1.getIdade());
        lista.mostrarItems();
   
	}
}
