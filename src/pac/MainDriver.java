package pac;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainDriver {

    private static Scanner scannerObj = new Scanner(System.in);

    public static void main(String[] args) {

        //path of json data
        String filepath = "src\\pac\\data.json";
        
        //will call constructor 
        DictionaryDriver<String, String> dict = new DictionaryDriver<>(JsonUtils.parseSimpleJsonFile(filepath));

        while (true) {
            System.out.println("\nEnter which operation you want to perform\n");
            System.out.println("1. Add a word");
            System.out.println("2. Get meaning of a word");
            System.out.println("3. Delete a word");
            System.out.println("4. Show all words (sorted)");
            System.out.println("5. Get items in a range");
            System.out.println("6. Exit");
            int choice = takePositiveIntegerInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter word: ");
                    String word = scannerObj.nextLine();
                    System.out.print("Enter meaning: ");
                    String meaning = scannerObj.nextLine();
                    dict.addWordToDictionary(word, meaning);
                    System.out.println("Word added successfully.");
                    break;
                case 2:
                    System.out.print("Enter word to search: ");
                    word = scannerObj.nextLine();
                    System.out.println("Meaning: " + dict.getMeaningOfAWord(word));
                    break;
                case 3:
                    System.out.print("Enter word to delete: ");
                    word = scannerObj.nextLine();
                    System.out.println(dict.deleteWordFromDictionary(word));
                    break;
                case 4:
                    System.out.println("All dictionary entries (sorted):");
                    for (Map.Entry<String, String> entry : dict.getSortedItems()) {
                        System.out.println(entry.getKey() + " => " + entry.getValue());
                    }
                    break;
                case 5:
                    System.out.print("Enter lower bound word: ");
                    String low = scannerObj.nextLine();
                    System.out.print("Enter upper bound word: ");
                    String high = scannerObj.nextLine();
                    List<Map.Entry<String, String>> rangeItems = dict.getItemsInRange(low, high);
                    System.out.println("Entries in range:");
                    for (Map.Entry<String, String> entry : rangeItems) {
                        System.out.println(entry.getKey() + " => " + entry.getValue());
                    }
                    break;
                case 6:
                    System.out.println("Exiting program.");
                    scannerObj.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * function will take input choice from user
     *
     * @return input choice from user
     */
    private static int takePositiveIntegerInput() {
        while (!scannerObj.hasNextInt()) {
            System.out.print("Enter a valid integer: ");
            scannerObj.next();
        }
        int input = scannerObj.nextInt();
        scannerObj.nextLine();
        if (input < 0) {
            System.out.print("Enter a positive integer: ");
            return takePositiveIntegerInput();
        }
        return input;
    }
}
