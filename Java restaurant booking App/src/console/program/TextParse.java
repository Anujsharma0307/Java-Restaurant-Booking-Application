package console.program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TextParse {

    /**
     * The readRestaurantList method to read and analyze data from restaurant.txt
     */

    public static ArrayList<Restaurant> readRestaurantList() throws IOException {
        ArrayList<Restaurant> ret = new ArrayList<>();
        //BufferedReader reads the Restaurant text file entered by user
        BufferedReader MenuReader = new BufferedReader(new FileReader("/Users/ANUJ/Users/ANUJ/Desktop/JAVA intelliJ/Assignment_1/src/console/program/Restaurants.txt"));
        String line = MenuReader.readLine();
        while (line != null) {
            //splits the different characteristics of restaurant
            ArrayList<String> arrline = new ArrayList<>(Arrays.asList(line.split(",")));
            String name = arrline.get(0);
            String type = arrline.get(1);
            Double deliveryCharge = Double.parseDouble(arrline.get(2).substring(1));
            ArrayList<String> itemsList = new ArrayList<>();
            ArrayList<Double> itemsPrice = new ArrayList<>();
            for (int i = 3; i < arrline.size(); i++) {
                String item = arrline.get(i);
                //splits the menu and price of restaurant
                ArrayList<String> arrline2 = new ArrayList<>(Arrays.asList(item.split("-")));
                String itemName = arrline2.get(0);
                Double itemPrice = Double.parseDouble(arrline2.get(1).substring(1));
                itemsList.add(itemName);
                itemsPrice.add(itemPrice);
            }
            Restaurant r = new Restaurant(name, type, deliveryCharge, itemsList, itemsPrice);
            ret.add(r);
            line = MenuReader.readLine();
        }
        return ret;
    }

    /**
     * The readDiscountList method to read and analyze data from Discount.txt
     */
    public static ArrayList<ArrayList<Integer>> readDiscountfiles() throws IOException {
        ArrayList<ArrayList<Integer>> DiscList = new ArrayList<ArrayList<Integer>>();
        BufferedReader DiscountReader = new BufferedReader(new FileReader("/Users/ANUJ/Users/ANUJ/Desktop/JAVA intelliJ/Assignment_1/src/console/program/Discounts.txt"));
        String line = DiscountReader.readLine();
        while (line != null) {
            ArrayList<Integer> temp = new ArrayList<>();
            ArrayList<String> arrline = new ArrayList<>(Arrays.asList(line.split(",")));
            if (arrline.size() == 3) {
                Integer minRange = Integer.parseInt(arrline.get(0).substring(1));
                temp.add(minRange);
                if (arrline.get(1).length() > 1) {
                    Integer maxRange = Integer.parseInt(arrline.get(1).substring(0, arrline.get(1).length() - 1));
                    temp.add(maxRange);
                } else if (arrline.get(1).length() == 1) {
                    Integer maxRange = 999999999;   //To be considered as infinity
                    temp.add(maxRange);
                }
                Integer discount = Integer.parseInt(arrline.get(2).substring(0, arrline.get(2).length() - 1));
                temp.add(discount);
            } else {
                Integer NoOfRestaurant = Integer.parseInt(arrline.get(0));
                temp.add(NoOfRestaurant);
                Integer discount = Integer.parseInt(arrline.get(1).substring(0, arrline.get(1).length() - 1));
                temp.add(discount);
            }
            DiscList.add(temp);
            line = DiscountReader.readLine();
        }
        return DiscList;
    }

}
