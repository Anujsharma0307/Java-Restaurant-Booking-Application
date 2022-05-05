package console.program;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;

public  class RestaurantTest {


    ArrayList<Restaurant> templist=new ArrayList();

        @BeforeClass
        public static void setUpBeforeClass()  {
        }
        @Before
        public void setUp() throws Exception {
            templist.add(new Restaurant("Burger King","Fast food",5.00,new ArrayList<>  (),new ArrayList<>()));
            templist.add(new Restaurant("Chicken King","Fast food",5.0, new ArrayList<>(), new ArrayList<>()));
            templist.add(new Restaurant("Holly Cow","Fast food",5.5, new ArrayList<>(),new ArrayList<>()));
            templist.add(new Restaurant("Moeders R.","Restaurant",10.0, new ArrayList<>(),new ArrayList<>()));
            templist.add(new Restaurant("Just Salads R.","Restaurant",6.0, new ArrayList<>(),new ArrayList<>()));
            templist.add(new Restaurant("Tea House","Cafe",4.0, new ArrayList<>(),new ArrayList<>()));
        }

        @Test
        public void testRestaurantDisplayUtilCorrectInput(){
            ArrayList<Restaurant> result=Restaurant.restaurantSearchUtil(templist,"in");
            assertEquals( "Chicken King", result.get(0).getName());
            assertEquals( "Burger King", result.get(1).getName());

        }
    @Test
    public void testRestaurantDisplayUtilIncorrectInput(){
        ArrayList<Restaurant> result=Restaurant.restaurantSearchUtil(templist,"in");
        result=Restaurant.restaurantSearchUtil(templist,"qq");
        assertEquals( 0,result.size());

    }


    @Test
        public void testRestaurantSearchUtilFastFoodList() {
            ArrayList<Restaurant> search = Restaurant.restaurantDisplayUtil(templist, "Fast food");
            assertEquals("Fast food", search.get(0).getType());
            assertEquals("Fast food", search.get(1).getType());
            assertEquals("Fast food", search.get(2).getType());
        }
    @Test
    public void testRestaurantSearchUtilRestaurantList() {
        ArrayList<Restaurant> search = Restaurant.restaurantDisplayUtil(templist, "Fast food");
        search=Restaurant.restaurantDisplayUtil(templist,"Restaurant");
        assertEquals("Restaurant",search.get(3).getType());
        assertEquals("Restaurant",search.get(4).getType());
    }
    @Test
    public void testRestaurantSearchUtilCafeList() {
        ArrayList<Restaurant> search = Restaurant.restaurantDisplayUtil(templist, "Fast food");
        search=Restaurant.restaurantDisplayUtil(templist,"cafe");
        assertEquals("Cafe",search.get(5).getType());
}
        @After
        public void tearDownAfter(){
        }

        @AfterClass
        public static void tearDownAfterClass(){
        }

    }

