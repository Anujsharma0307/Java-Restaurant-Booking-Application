package console.program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import static console.program.Restaurant.*;

/**
 * Main class to start the application.
 */
public class Main {
    protected static String BANNER = "----------------------------------------";
    protected static final String MENUFORMAT = "   %d) %-27s $%s\n";
    protected static final String LIST_FORMAT = "   %d) %-27s\n";

    /**
     * The Main method to run the whole program.
     */
    public static void main(String[] args) throws IOException {
        System.out.println(" Welcome to Melbourne Eats");
        ArrayList<Restaurant> restaurants = TextParse.readRestaurantList();
        printMenu2(restaurants);

    }

    /**
     * The Print method is to print main menu options.
     */
    public static void printMenu() {
        System.out.println(BANNER);
        System.out.println("> Select from main menu");
        System.out.println(BANNER);
        System.out.println("\t1) Browse by Category");
        System.out.println("\t2) Search by restaurant");
        System.out.println("\t3) Checkout");
        System.out.println("\t4) Exit");
        System.out.print(" Please select: ");
    }

    /**
     * The utility method to analyze menu option selected and call the relevant functions required.
     */
    public static void printMenu2(ArrayList<Restaurant> restaurants) throws IOException {
        boolean exit = false;
        do {
            printMenu();
            String stringInput = readUserInput();
            if (stringInput.isEmpty()) {
                continue;
            }
            char input = stringInput.charAt(0);
            switch (input) {
                case '1':
                    Category(restaurants);
                    exit = true;
                    break;
                case '2':
                    RestaurantSearch(restaurants);
                    exit = true;
                    break;
                case '3':
                    Checkout.printCheckout(restaurants);
                    exit = true;
                    break;
                case '4':
                    exit = true;
                    break;
                default:
                    System.out.println("Please select a valid menu option.");
                    break;
            }
        } while (!exit);
    }

    /**
     * The utility method to read user input.
     */
    public static String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static int readUserChoice() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    /**
     * The Category method to print different categories of restaurant and analyze
     * option selected and call the relevant functions required.
     */
    public static void Category(ArrayList<Restaurant> restaurants) {
        int choice = 0;
        System.out.println(BANNER);
        System.out.println("> Select by Category");
        System.out.println(BANNER);
        System.out.println("\t1) Restaurant");
        System.out.println("\t2) Cafe");
        System.out.println("\t3) Fast Food");
        System.out.println("\t4) Go to main menu");
        System.out.print("Please Select: ");
        typeSelection(restaurants, choice);
    }
    public static void typeSelection(ArrayList<Restaurant> restaurants,int choice){
        try {
            choice = readUserChoice();
            switch (choice) {
                case 1:
                    AllList(restaurants,1);
                    break;
                case 2:
                    AllList(restaurants,2);
                    break;
                case 3:
                    AllList(restaurants,3);
                    break;
                case 4:
                    printMenu2(restaurants);
                    break;
                    default:
                    System.out.println("Please Enter a valid input");
                    Category(restaurants);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR- Not a valid input");
            Category(restaurants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


