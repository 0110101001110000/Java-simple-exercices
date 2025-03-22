
public class Main
{
	public static void main(String[] args) {
		
		Aluno a1 = new Aluno("Anderson", 42);
		Aluno a2 = new Aluno("Ana", 31);
		Aluno a3 = new Aluno("José", 23);

	    ListaSimples lista = new ListaSimples();
        
        lista.adicionaInicio(a1);  //funcionando
        lista.adicionaInicio(a2);  //funcionando
        lista.adicionaInicio(a3);  //funcionando        

        lista.removeInicio();  //funcionando
        
        a1 = (Aluno) lista.Recupera(0);
        a2 = (Aluno) lista.Recupera(1);

        System.out.println(a1.getNome() + " - " + a1.getIdade());  //funcionando
        System.out.println(a2.getNome() + " - " + a2.getIdade());  //funcionando
   
	}
}
