
public class Main
{
	public static void main(String[] args) {
		
		Aluno a1 = new Aluno("Carlos Kayky", 19);
		Aluno a2 = new Aluno("Victor Rafael", 24);
		Aluno a3 = new Aluno("Luis Gustavo", 20);
        Aluno a4 = new Aluno("João Paulo", 21);

	    ListaSimples lista = new ListaSimples();

        lista.adicionaInicio(a1);

        lista.removeInicio();

        lista.adiciona(a1, 0);
        
        a1 = (Aluno) lista.Recupera(0);

        System.out.println(a1.getNome() + " - " + a1.getIdade());
   
	}
}
