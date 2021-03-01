
import org.junit.jupiter.api.Test;

import java.time.LocalTime;


import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    int initialMenuSize;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    public Restaurant addRestaurant(String opening, String closing)
    {
        LocalTime openingTime = LocalTime.parse(opening);
        LocalTime closingTime = LocalTime.parse(closing);
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        initialMenuSize = restaurant.getMenu().size();
        return restaurant;
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant = addRestaurant("10:30:00","22:00:00");

        assertEquals(true,restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant restaurant = addRestaurant("21:30:00","22:00:00");

        assertEquals(false,restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        Restaurant restaurant = addRestaurant("10:30:00","22:00:00");

        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        Restaurant restaurant = addRestaurant("10:30:00","22:00:00");

        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        Restaurant restaurant = addRestaurant("10:30:00","22:00:00");

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //<<<<<<<<<<<<<<<<<<<<<<<ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void given_the_name_of_item_restaurant_should_return_order_value() throws itemNotFoundException {
        Restaurant restaurant = addRestaurant("10:30:00","22:00:00");

        assertEquals(269 + 119,restaurant.getOrderValues("Vegetable lasagne","Sweet corn soup"));
    }
    @Test
    public void given_the_name_of_item_that_does_not_exist_should_throw_exception() throws itemNotFoundException {
        Restaurant restaurant = addRestaurant("10:30:00","22:00:00");

        assertThrows(itemNotFoundException.class,
                ()->restaurant.getOrderValues("Vegetable lasagne","Sweet corn soup","hu"));
    }



    }


