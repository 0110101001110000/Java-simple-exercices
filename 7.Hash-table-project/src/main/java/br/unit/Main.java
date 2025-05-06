package br.unit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }


        // Some simple tests

        HashTable hashTable = new HashTable(10);

        User user1 = new User("Pietro Katbush", "asdf");
        User user2 = new User("Pietra Katbush", "asdf");
        User user3 = new User("Pietra Katbush", "123456");

        hashTable.insert(user1);
        hashTable.insert(user2);
        hashTable.insert(user3);

        System.out.println("\n" + hashTable.search(user1));
        System.out.println(hashTable.search(user2));
        System.out.println(hashTable.search(user3));

        System.out.println("\n" + hashTable.delete(user1));
        System.out.println(hashTable.delete(user2));
        System.out.println(hashTable.delete(user3));

        System.out.println("\n" + hashTable.search(user1));
        System.out.println(hashTable.search(user2));
        System.out.println(hashTable.search(user3));

        System.out.println("\n" + hashTable.isEmpty());
        System.out.println(hashTable.isFull());
        System.out.println(hashTable.getLoadFactor());

    }
}