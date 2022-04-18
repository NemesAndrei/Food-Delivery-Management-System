package org.example.BusinessLayer;

import java.lang.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @author Nemes Mihnea-Andrei
 */
public interface IDeliveryServiceProcessing {


    /**
     * @param product  the base product to be changed
     * @param name     the new name of the base product
     * @param rating   the new rating of the base product
     * @param calories the new amount of calories of the base product
     * @param protein  the new amount of protein of the base product
     * @param fat      the new amount of fat of the base product
     * @param sodium   the new amount of sodium of the base product
     * @param price    the new price of the base product
     * @pre product!=null
     * @post itemModify.getName().equals(name)
     * @post itemModify.getPrice()==price
     */
    public void editProductInMenu(BaseProduct product, String name, Double rating, Integer calories, Integer protein, Integer fat, Integer sodium, Double price);

    /**
     * @param product the composite product to be added to the menu
     * @pre product!=null
     * @post menuList.contains(product)
     */
    public void addCompositeProductToMenu(CompositeProduct product);


    /**
     * @param finalMenu the list of menu items obtained in a linked hash set from the .csv file given
     * @post menuList!=null
     */
    public void createMenuList(LinkedHashSet<MenuItem> finalMenu);


    /**
     * @param order    the order that we want to create a bill for
     * @param products the list of menu items that comprise the bill
     * @pre ordersList!=null
     * @pre userList!=null
     */
    public void generateBill(Order order, ArrayList<MenuItem> products);

    /**
     * @param numberOfOrders the minimum number of orders for a client to satisfy the condition
     * @param valueOfOrders  the minimum value of orders for a client to satisfy the condition
     * @return the list of users that satisfy the condition of having created more orders than the numberOfOrders, whose total value is greater than valueOfOrders
     * @pre numberOfOrders > 0
     * @pre valueOfOrders > 0
     */
    public ArrayList<User> generateReportThree(int numberOfOrders, int valueOfOrders);

    /**
     * @param products the list of menu items to be added in the order
     * @param username the username of the user creating the order
     * @pre products!=null
     * @pre username!=null
     * @pre isWellFormed()
     * @post isWellFormed()
     * @post ordersList!=null
     */
    public void addOrder(ArrayList<MenuItem> products, String username);

    /**
     * @param product the base product to be added into the menu
     * @pre product!=null
     * @pre !menuList.contains(product)
     * @post menuList!=null
     * @post menuList.contains(product)
     */
    public void addProductToMenu(BaseProduct product);


    /**
     * @param protein the protein count that we want to filter by
     * @return a list of menu items that have a protein count equal to the one given as parameter
     * @pre menuList!=null
     * @pre isInteger(protein.toString ())
     */
    public ArrayList<MenuItem> filteredProtein(String protein);

    /**
     * @param day   the day the order must have been placed on
     * @param month the month the order must have been placed on
     * @return a list of orders that satisfy the condition that they have been placed on the specified day and month
     * @pre day>0
     * @pre 32>day
     * @pre month>0
     * @pre 13>month
     */
    public ArrayList<Order> generateReportFour(int day, int month);

    /**
     * @param price the price that we want to filter by
     * @return a list of menu items that have a price equal to the one given as a parameter
     * @pre menuList!=null
     * @pre isDouble(price.toString ())
     */
    public ArrayList<MenuItem> filteredPrice(String price);

    /**
     * @param product the product to be changed from the menu
     * @param name    the new name of the composite product
     * @param items   the list of menu items that will comprise the changed composite product
     * @pre product!=null
     */
    public void editCompositeProductInMenu(CompositeProduct product, String name, ArrayList<MenuItem> items);

    /**
     * @param sodium the sodium content that we want to filter by
     * @return a list of menu items that have a sodium content equal to the one given as a parameter
     * @pre menuList!=null
     * @pre isInteger(sodium.toString ())
     */
    public ArrayList<MenuItem> filteredSodium(String sodium);

    /**
     * @param startHour start hour to find orders
     * @param endHour   end hour to find orders
     * @return the list of orders that satisfy the condition of having been created between the two given hours
     * @pre startHour >= 0
     * @pre endHour >= 0
     * @pre 24>endHour
     * @pre endHour>startHour
     */
    public ArrayList<Order> generateReportOne(int startHour, int endHour);

    /**
     * @param fat the fat content that we want to filter by
     * @return a list of menu items that have a fat content equal to the one given as parameter
     * @pre menuList!=null
     * @pre isInteger(fat.toString ())
     */
    public ArrayList<MenuItem> filteredFat(String fat);


    /**
     * @param calories the calories count that we want to filter by
     * @return a list of menu items that have a calorie count equal to the one given as parameter
     * @pre menuList!=null
     * @pre isInteger(calories.toString ())
     */
    public ArrayList<MenuItem> filteredCalories(String calories);


    /**
     * @param item the base product to be deleted from the menu
     * @pre item!=null
     * @pre menuList.contains(item)
     * @post !(menuList.contains(item))
     */
    public void removeProductFromMenu(MenuItem item);

    /**
     * @param name the name that we want to filter by
     * @return a list of menu items that contain the name given as parameter
     * @pre menuList!=null
     * @pre !name.equals("")
     */
    public ArrayList<MenuItem> filteredName(String name);


    /**
     * @param rating the rating that we want to filter by
     * @return a list of menu items that have a rating equal to the one given as parameter
     * @pre menuList!=null
     * @pre isDouble(rating.toString ())
     */
    public ArrayList<MenuItem> filteredRating(String rating);

    /**
     * @param numberOfTimes the minimum number of times a product must have been ordered to satisfy the condition
     * @param productList   a hash map containing each menu item and how many times it has been ordered
     * @return a list of menu items that satisfy the condition of having been ordered at least numberOfTimes times
     * @pre productList!=null
     * @pre numberOfTimes>0
     * @pre orderList!=null
     */
    public ArrayList<MenuItem> generateReportTwo(int numberOfTimes, HashMap<MenuItem, Integer> productList);
}
