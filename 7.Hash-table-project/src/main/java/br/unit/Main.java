
package br.unit;
import br.unit.hash_table.HashTable;
import br.unit.models.HashTableEntity;

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
                    System.out.println(hashTable.insert(new User(nomeUsuario, senhaUsuario)));
                    System.out.println("Usuário cadastrado com sucesso. ");
                    break;

                case 2:
                    System.out.print("Insira o nome do usuário para continuar com a exclusão. \n -------> ");
                    String deleteNomeUsuario = scannerUsuario.nextLine();
                    if (hashTable.delete(deleteNomeUsuario)) {
                        System.out.println("O usuário foi deletado com sucesso");
                    }
                    else {
                        System.out.println("Usuário não encontrado");
                    }
                    break;

                case 3:
                    System.out.print("Insira o nome do usuário para verificar se possui cadastro. \n -------> ");
                    String buscarUsuario = scannerUsuario.nextLine();
                    int index = hashTable.search(buscarUsuario);
                    if (index != -1) {
                        System.out.println("O usuário possui cadastro definido no index " + index);
                    } else {
                        System.out.println("O usuário não possui cadastro definido no index " + index);
                    }
                    break;

                case 4:
                    System.out.print("Insira um nome de usuário para login. \n -------> ");
                    String usuarioLogin = scannerUsuario.nextLine();
                    System.out.print("Informe a senha. \n -------> ");
                    String senhaLogin = scannerUsuario.nextLine();
                    User userToComparer = hashTable.getElement(usuarioLogin);
                    if (userToComparer != null) {
                        if (userToComparer.getPassword().equals(senhaLogin)) {
                            System.out.println("Login efetuado com sucesso ");
                        } else {
                            System.out.println("Verifique suas credenciais, nome de usuário ou senha estão incorretos. ");
                        }
                    } else {
                        System.out.println("Batman Verifique suas credenciais, nome de usuário ou senha estão incorretos. ");
                    }
                    break;
                case 5:
                    break;
            }
        }
    }
}
