import java.util.Locale;
import java.util.Scanner;


/* Init -------------------------------------------------------------------- */


public class AccountTerminal {

    public static void main(String[] args) {

        // Welcome message
        System.out.println("""
                .-. .-')      ('-.          .-') _  .-. .-')  \s
                \\  ( OO )    ( OO ).-.     ( OO ) ) \\  ( OO ) \s
                 ;-----.\\    / . --. / ,--./ ,--,'  ,--. ,--. \s
                 | .-.  |    | \\-.  \\  |   \\ |  |\\  |  .'   / \s
                 | '-' /_) .-'-'  |  | |    \\|  | ) |      /, \s
                 | .-. `.   \\| |_.'  | |  .     |/  |     ' _)\s
                 | |  \\  |   |  .-.  | |  |\\    |   |  .   \\  \s
                 | '--'  /   |  | |  | |  | \\   |   |  |\\   \\ \s
                 `------'    `--' `--' `--'  `--'   `--' '--' \s
                """);

        // Objects
        Scanner input = new Scanner(System.in).useLocale(Locale.US);

        // Variables
        String clientName;
        int accountNumber;
        String accountAgency;
        double accountBalance;

        // Get clientName from terminal
        System.out.print("Por favor, digite o seu nome:\n    --> ");
        clientName = input.nextLine();

        // Get accountNumber from terminal
        System.out.print("Por favor, digite o número da Conta:\n    --> ");
        accountNumber = Integer.parseInt(input.nextLine().replace(" ", ""));

        // Get accountAgency from terminal
        System.out.print("Por favor, digite o número da Agência:\n    --> ");
        accountAgency = input.nextLine();

        // Get accountBalance from terminal
        System.out.print("Por favor, digite o saldo da Conta:\n    --> ");
        accountBalance = Double.parseDouble(input.nextLine().replace(" ", ""));

        // Print final message
        System.out.println(
                "Olá " + clientName +
                ", obrigado por criar uma conta em nosso banco, sua agência é " + accountAgency +
                ", conta " + accountNumber +
                " e seu saldo " + accountBalance +
                " já está disponível para saque."
        );

    }

}