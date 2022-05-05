package console.program;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The checkout method to display the list of order items selected with total amount
 * payable after applying the discounts (if applicable)
 */
public class Checkout {

    public static void printCheckout(ArrayList<Restaurant> restaurants) throws IOException {
        ArrayList<ArrayList<Integer>> discList = TextParse.readDiscountfiles();
        double sum = 0.0;
        double discountedSum = 0.0;
        double totalDeliveryCharge = 0.0;
        double discountedDeliveryCharge = 0.0;
        double payable;
        double savings;
        int counter = 0;

        System.out.println(Main.BANNER);
        System.out.println("You have ordered the following items ");
        System.out.println(Main.BANNER);
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getOrderQuantity().size() != 0) {
                Double deliveryCharge = restaurant.getDeliveryCharge();
                System.out.println(restaurant.getName());
                for (int j = 0; j < restaurant.getOrderQuantity().size(); j++) {
                    double price = restaurant.getItemsPrice().get(restaurant.getOrderedItems().get(j)) *
                            restaurant.getOrderQuantity().get(j);
                    System.out.printf("   %d %-24s $%s\n", restaurant.getOrderQuantity().get(j),
                            restaurant.getItems().get(restaurant.getOrderedItems().get(j)), price);
                    sum += price;
                }
                System.out.printf("%-29s $%.2f\n", "Delivery Fee:", deliveryCharge);
                System.out.println(Main.BANNER);
                totalDeliveryCharge += deliveryCharge;
                counter++;
            }

        }

        discountedSum = discountCalculator(discList, discountedSum, sum);

        discountedDeliveryCharge = deliveryDiscount(counter, discountedDeliveryCharge, totalDeliveryCharge, discList);

        savings = (sum - discountedSum) + (totalDeliveryCharge - discountedDeliveryCharge);

        payable = discountedSum + discountedDeliveryCharge;

        orderDisplay(savings, discountedSum, payable, discountedDeliveryCharge);
    }
    /**
     * The discountCalculator method analysis the final order price to apply the relevant discount on the order
     */
     public static double discountCalculator(ArrayList<ArrayList<Integer>> discList, double discountedSum, double sum) {
        if ((sum >= discList.get(0).get(0)) && (sum < discList.get(0).get(1)))
            discountedSum = sum * (100 - discList.get(0).get(2)) / 100;
        else if ((sum >= discList.get(1).get(0)) && (sum < discList.get(1).get(1)))
            discountedSum = sum - (sum * (discList.get(1).get(2)) / 100);
        else if ((sum >= discList.get(2).get(0)) && (sum < (discList.get(2).get(1))))
            discountedSum = sum - (sum * (discList.get(2).get(2)) / 100);
        else if (sum >= discList.get(3).get(0))
            discountedSum = sum - (sum * (discList.get(3).get(2)) / 100);
        else {
            System.out.println("invalid sum");
        }
        return discountedSum;
    }

    /**
     * The deliveryDiscount method analysis the final delivery price to apply the relevant discount on
     * the delivery price of the order if applicable
     */
     public static double deliveryDiscount(int counter, double discountedDeliveryCharge, double totalDeliveryCharge,
                                           ArrayList<ArrayList<Integer>> discList) {
        if (counter == 1) {
            discountedDeliveryCharge = totalDeliveryCharge;
        } else if (counter > 1) {
            discountedDeliveryCharge = totalDeliveryCharge - (totalDeliveryCharge * (discList.get(4).get(1)) / 100);
        }
        return discountedDeliveryCharge;
    }
    /**
     * The orderDisplay method displays the final order bill with applied discounts
     */
     public static void orderDisplay(double savings, double discountedSum, double payable, double discountedDeliveryCharge) {
        System.out.printf(" %-27s $%.2f\n", "Order Price:", discountedSum);
        System.out.printf(" %-27s $%.2f\n", "Delivery fee:", discountedDeliveryCharge);
        System.out.printf(" %-27s $%.2f\n", "You have saved:", savings);
        System.out.printf(" %-27s $%.2f\n", "Total amount to pay:", payable);
        System.out.println(Main.BANNER);
        System.out.println("Thanks for ordering with Melbourne eats. Enjoy your Meal.");
    }
}
