/// AISHA TAHLIL 
/// COMP SCI 303 10/11/26


import java.util.*;

public class Main {

    // PRE: Program starts
    // POST: Runs the menu until the user quits
    public static void main(String[] args) {

        System.out.println("Welcome to Program 3: Creating MyList");
        Scanner input = new Scanner(System.in);

        // Create our array list
        MyArrayList<Integer> list = new MyArrayList<>();

        // Load data from the selected file
        Functions.loadData(input, list);

        String choice = "";
        choice = Functions.getMenuItem(input);

        while (!(choice.equals("Q"))) {

            switch (choice) {

                case "A":
                    Functions.addItem(input, list);
                    break;

                case "B":
                    Functions.addList(input, list);
                    break;

                case "C":
                    Functions.containsList(input, list);
                    break;

                case "D":
                    Functions.deleteValue(input, list);
                    break;

                case "E":
                    Functions.deleteList(input, list);
                    break;

                case "F":
                    Functions.findValue(input, list);
                    break;

                case "M":
                    Functions.modifyValue(input, list);
                    break;

                case "P":
                    Functions.printArray(list);
                    break;

                case "T":
                    list.trimToSize();
                    System.out.println("Array trimmed.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

            choice = Functions.getMenuItem(input);
        }

        System.out.println("Thank you for using the program!");
        input.close();
    }
}
