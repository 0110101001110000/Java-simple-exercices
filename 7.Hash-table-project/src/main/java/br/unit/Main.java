
package br.unit;
import java.util.Scanner;
import static br.unit.utils.MenuPrompt.exibirMenu;


// Init -------------------------------------------------------------------- //


/**
 * ...
 * @author Victor Rafael
 * @since 1.0
 */
public class Main {
    public static void main(String[] args) {

        HashTable hashTable = new HashTable(10);
        Scanner scannerEscolha = new Scanner(System.in);
        int opcao;
        boolean continuar = true;

        while(continuar) {
            exibirMenu();
            System.out.print("Para prosseguir escolha uma das opões acima. \n -------> ");
            while (!scannerEscolha.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                System.out.print("-------> ");
                scannerEscolha.next(); // Descarta a entrada inválida
            }
            opcao = scannerEscolha.nextInt();

            Scanner scannerUsuario = new Scanner(System.in);
            switch (opcao) {
                case 1:
                    System.out.print("Insira um nome de usuário. \n -------> ");
                    String nomeUsuario = scannerUsuario.nextLine();
                    System.out.print("Informe uma senha segura para cadastro. \n -------> ");
                    String senhaUsuario = scannerUsuario.nextLine();
                    hashTable.insert(new User(nomeUsuario, senhaUsuario));
                    System.out.println("Usuário cadastrado com sucesso. ");

                case 2:
                    System.out.print("Insira o nome do usuário para continuar com a exclusão. \n -------> ");
                    String deleteNomeUsuario = scannerUsuario.nextLine();
                    //hashTable.delete();

                case 3:
                    System.out.print("Insira o nome do usuário para verificar se possui cadastro. \n -------> ");
                    String buscarUsuario = scannerUsuario.nextLine();
                    //System.out.println(hashTable.search(buscarUsuario));
                case 5:
                    break;
            }
        }
    }
}
