
import java.util.*;
import java.io.*;

public class Functions {

    // PRE: Scanner has been created
    // POST: Displays the menu and returns the choice
    public static String getMenuItem(Scanner input) {

        System.out.println("\nMODIFY YOUR LIST");
        System.out.println("A: Add Number");
        System.out.println("B: Add a Group of Values to List");
        System.out.println("C: Contains All Values in the List");
        System.out.println("D: Delete (by Index)");
        System.out.println("E: Delete a Group of Values From the List");
        System.out.println("F: Find a Value");
        System.out.println("M: Modify a Value");
        System.out.println("P: Print Array");
        System.out.println("T: Trim Array to Size");
        System.out.println("Q: Quit");

        System.out.print("Enter your choice: ");
        String choice = input.nextLine().trim().toUpperCase();

        return choice;
    }

    // PRE: Scanner and list have been created
    // POST: Loads valid numbers from the selected file
    public static void loadData(Scanner input,
                                MyArrayList<Integer> list) {

        String fileName = "";

        while (true) {
            System.out.print("Choose input file (1 or 2): ");
            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                fileName = "A1input.txt";
                break;
            }
            else if (choice.equals("2")) {
                fileName = "A2input.txt";
                break;
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        try {
            Scanner file = new Scanner(new File(fileName));

            while (file.hasNext()) {

                String value = file.next();

                try {
                    int number = Integer.parseInt(value);
                    list.add(number);
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid value skipped: " + value);
                }
            }

            file.close();
            System.out.println("File processed successfully.");
        }
        catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + fileName);
        }
    }

    // PRE: Scanner and list have been created
    // POST: Adds one number to the list
    public static void addItem(Scanner input,
                               MyArrayList<Integer> list) {

        while (true) {
            System.out.print("Enter a number to add: ");
            String value = input.nextLine();

            try {
                int number = Integer.parseInt(value.trim());
                list.add(number);

                System.out.println(number + " was added.");
                break;
            }
            catch (NumberFormatException e) {
                System.out.println(
                    "You entered incorrect data. Please try again."
                );
            }
        }
    }

    // PRE: Scanner and list have been created
    // POST: Adds a group of numbers to the list
    public static void addList(Scanner input,
                               MyArrayList<Integer> list) {

        System.out.print("Enter numbers separated by spaces: ");
        String line = input.nextLine();

        String[] values = line.trim().split("\\s+");
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            try {
                int number = Integer.parseInt(values[i]);
                numbers.add(number);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid value skipped: " + values[i]);
            }
        }

        list.addAll(numbers);
        System.out.println("Items added to your list: " + numbers);
    }

    // PRE: Scanner and list have been created
    // POST: Checks if all given numbers are in the list
    public static void containsList(Scanner input,
                                    MyArrayList<Integer> list) {

        System.out.print("Enter values to check separated by spaces: ");
        String line = input.nextLine();

        String[] values = line.trim().split("\\s+");
        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            try {
                int number = Integer.parseInt(values[i]);
                numbers.add(number);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid value skipped: " + values[i]);
            }
        }

        if (list.containsAll(numbers)) {
            System.out.println(
                "These valid items are in your list: " + numbers
            );
        }
        else {
            System.out.println(
                "Not all values provided are in your list."
            );
        }
    }

    // PRE: Scanner and list have been created
    // POST: Deletes a number at the given index
    public static void deleteValue(Scanner input,
                                   MyArrayList<Integer> list) {

        while (true) {
            System.out.print("Enter index to delete: ");
            String value = input.nextLine();

            try {
                int index = Integer.parseInt(value.trim());

                if (index < 0 || index >= list.size()) {
                    System.out.println(
                        "Index: " + index +
                        " is not in range. Please try again."
                    );
                }
                else {
                    int removed = list.remove(index);

                    System.out.println(
                        "Item at index: " + index +
                        " was " + removed + " has been removed."
                    );
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println(
                    "You entered incorrect data. Please try again."
                );
            }
        }
    }

    // PRE: Scanner and list have been created
    // POST: Removes matching values from the list
    public static void deleteList(Scanner input,
                                  MyArrayList<Integer> list) {

        System.out.print("Enter values to remove separated by spaces: ");
        String line = input.nextLine();

        String[] values = line.trim().split("\\s+");
        ArrayList<Integer> removed = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            try {
                int number = Integer.parseInt(values[i]);

                if (list.contains(number)) {
                    removed.add(number);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid value skipped: " + values[i]);
            }
        }

        list.removeAll(removed);

        System.out.println("Items removed from your list: " + removed);
    }

    // PRE: Scanner and list have been created
    // POST: Finds the position of a number
    public static void findValue(Scanner input,
                                 MyArrayList<Integer> list) {

        System.out.print("Enter a value to find: ");
        String value = input.nextLine();

        try {
            int number = Integer.parseInt(value.trim());
            int index = list.indexOf(number);

            if (index == -1) {
                System.out.println(
                    "The value " + number + " could not be found."
                );
            }
            else {
                System.out.println(
                    "The value " + number +
                    " was found at position: " + index
                );
            }
        }
        catch (NumberFormatException e) {
            System.out.println(
                "You entered incorrect data. Please try again."
            );
        }
    }

    // PRE: Scanner and list have been created
    // POST: Changes the value at the given index
    public static void modifyValue(Scanner input,
                                   MyArrayList<Integer> list) {

        int index;

        while (true) {
            System.out.print("Enter index to modify: ");
            String value = input.nextLine();

            try {
                index = Integer.parseInt(value.trim());

                if (index < 0 || index >= list.size()) {
                    System.out.println(
                        "Index: " + index +
                        " is not in range. Please try again."
                    );
                }
                else {
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println(
                    "You entered incorrect data. Please try again."
                );
            }
        }

        while (true) {
            System.out.print("Enter new value: ");
            String value = input.nextLine();

            try {
                int number = Integer.parseInt(value.trim());
                int oldValue = list.set(index, number);

                System.out.println(
                    "Item at index: " + index +
                    " was " + oldValue +
                    " is now " + number
                );
                break;
            }
            catch (NumberFormatException e) {
                System.out.println(
                    "You entered incorrect data. Please try again."
                );
            }
        }
    }

    // PRE: List has been created
    // POST: Prints all values in the list
    public static void printArray(MyArrayList<Integer> list) {

        System.out.println("\nPRINTING YOUR LIST");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%5d", list.get(i));

            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }

        System.out.println();
    }
}
