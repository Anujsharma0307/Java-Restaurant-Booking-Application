package console.program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 * The Order class Analysis the choices entered by the user and process it to printout of the menu for the restaurant.
 */
public class Order {

    public static void printOrder(ArrayList<Restaurant> restaurants, int choice, ArrayList<Restaurant> l) {

        try {
            if (choice <= l.size() + 1) {
                if (choice == l.size() + 1)
                    Main.printMenu2(restaurants);
                else if ((choice >= 1) && (choice <= l.size())) {
                    menuPrinter(restaurants, choice, l);
                } else {
                    System.out.println("OOPS Selected option is Invalid please start again");
                    Main.Category(restaurants);
                }
            } else {
                System.out.println("OOPS Selected option is Invalid please start again");
                Main.Category(restaurants);
            }

        } catch (InputMismatchException e) {
            System.out.println("OOPS Invalid data type input!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS selected option is invalid");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     public static void menuPrinter(ArrayList<Restaurant> restaurants, int choice, ArrayList<Restaurant> l) throws IOException {
        Restaurant r = l.get(choice - 1);
        ArrayList<String> itemsName = r.getItems();
        ArrayList<Double> prices = r.getItemsPrice();
        while (true) {
            System.out.println(Main.BANNER);
            System.out.println("Select a food item from " + l.get(choice - 1).getName());
            System.out.println(Main.BANNER);
            for (int i = 0; i < itemsName.size(); i++) {
                System.out.printf(Main.MENUFORMAT, (i + 1), itemsName.get(i), prices.get(i));
            }
            System.out.println("   " + (itemsName.size() + 1) + ") No more");
            System.out.print("Please select: ");
            int selection = Main.readUserChoice();
            if (selection == itemsName.size() + 1) {
                Main.printMenu2(restaurants);
                break;
            } else if ((selection >= 1) && (selection <= itemsName.size())) {
                System.out.print("Please enter an amount: ");
                int itemQuantity = Main.readUserChoice();
                if (itemQuantity <= 0) {
                    System.out.println("Invalid input please enter an acceptable value");
                    continue;
                }
                selection--;
                r.getOrderedItems().add(selection);
                r.getOrderQuantity().add(itemQuantity);
            } else {
                System.out.println("Selected input is Invalid please Try again");
            }
        }
    }
}

