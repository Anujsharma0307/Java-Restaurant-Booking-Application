package console.program;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * The Restaurant class provides the functionality for the restaurant.
 */
public class Restaurant {

    private String name;
    private String type;
    private Double deliveryCharge;
    private ArrayList<String> items;
    private ArrayList<Double> itemsPrice;
    private ArrayList<Integer> orderedItems;
    private ArrayList<Integer> orderQuantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public ArrayList<Double> getItemsPrice() {
        return itemsPrice;
    }

    public void setItemsPrice(ArrayList<Double> itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    public ArrayList<Integer> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(ArrayList<Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public ArrayList<Integer> getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(ArrayList<Integer> orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Restaurant(String name, String type, Double deliveryCharge,
                      ArrayList<String> items, ArrayList<Double> itemsPrice) {
        this.setName(name);
        this.setType(type);
        this.setDeliveryCharge(deliveryCharge);
        this.setItems(items);
        this.setItemsPrice(itemsPrice);
        this.setOrderedItems(new ArrayList<>());
        this.setOrderQuantity(new ArrayList<>());
    }

    public static void AllList(ArrayList<Restaurant> restaurants, int choice) throws IOException {
        ArrayList<Restaurant> printResults;
        String restaurantType = "";
        System.out.println(Main.BANNER);
        switch (choice) {
            case 1:
                restaurantType = "Restaurant";
                System.out.println("> Select Restaurant List ");
                break;
            case 2:
                restaurantType = "Cafe";
                System.out.println("> Select Cafe List ");
                break;
            case 3:
                restaurantType = "Fast food";
                System.out.println("> Select Fast Food List ");
                break;
        }

        printResults = restaurantDisplayUtil(restaurants, restaurantType);
        System.out.println("\t" + (printResults.size() + 1) + ") Go to main menu");
        System.out.print("Please Select: ");
        choice = Main.readUserChoice();
        Order.printOrder(restaurants, choice, printResults);
    }

     public static ArrayList<Restaurant> restaurantDisplayUtil(ArrayList<Restaurant> restaurants, String restaurantType) {
        ArrayList<Restaurant> l = new ArrayList<>();
        System.out.println(Main.BANNER);
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getType().compareTo(restaurantType) == 0)
                l.add(restaurant);
        }
        for (int i = 0; i < l.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + l.get(i).getName());
        }

        return l;
    }

    public static void RestaurantSearch(ArrayList<Restaurant> restaurants) {
        ArrayList<Restaurant> searchResults;
        int counter = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(" Please enter a restaurant name: ");
            String stringInput = Main.readUserInput();

            System.out.println(Main.BANNER);
            System.out.println("Select From the matching list");
            System.out.println(Main.BANNER);
            try {
                searchResults = restaurantSearchUtil(restaurants, stringInput);
                int size = searchResults.size();
                if (size > 0) {
                    counter = 1;
                    for (Restaurant restaurant : searchResults) {
                        System.out.printf(Main.LIST_FORMAT, (counter), restaurant.getName());
                        counter++;
                    }
                    valid = true;
                }
                if (counter == 0) {
                    System.out.println("OOPS! Sorry Looks like we do not have\n" +
                            "the restaurant you are looking for!\nTry Again!");
                } else if (!valid)
                    counter++;
                System.out.println("   " + (counter) + ") Go to main menu");
                System.out.print("Please Select: ");
                int choice = Main.readUserChoice();
                Order.printOrder(restaurants, choice, searchResults);
            } catch (
                    InputMismatchException e) {
                System.out.println("OOPS Invalid data type input!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS selected option is not valid!");
            }
        }
    }
    public static ArrayList<Restaurant> restaurantSearchUtil(ArrayList<Restaurant> restaurants, String restaurantName) {
        ArrayList<Restaurant> searchResults = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getName().toLowerCase().contains(restaurantName.toLowerCase())) {
                searchResults.add(restaurant);
            }
        }
        return searchResults;
    }
}




















