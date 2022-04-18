package org.example.BusinessLayer;

import java.lang.String;

import org.example.DataLayer.HelperStuff;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nemes Mihnea-Andrei
 * @invariant isWellFormed()
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    /**
     * the linked hash set that contains the menu items
     */
    private LinkedHashSet<MenuItem> menuList = new LinkedHashSet<>();
    /**
     * the has set that contains the users
     */
    private HashSet<User> userList = new HashSet<>();
    /**
     * the hash map that contains the orders and the list of items of the order
     */
    private HashMap<Order, ArrayList<MenuItem>> ordersList = new HashMap<>();


    /**
     * empty constructor
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public DeliveryService() throws IOException, ClassNotFoundException {
    }


    /**
     * @return true if the delivery service object is well formed, meaning the order has protein equal to the sum of its components
     */
    public boolean isWellFormed() {
        boolean wellFormed;
        int protein1 = 0;
        int protein2 = 0;
        for (Order o : ordersList.keySet()) {
            for (MenuItem item : ordersList.get(o)) {
                protein2 += item.calculateProtein();
            }
            protein1 += o.getFinalProtein();
        }

        if (protein1 == protein2 && menuList!=null) {
            wellFormed = true;
        } else {
            wellFormed = false;
        }
        return wellFormed;
    }

    /**
     * @return the list of menu items
     */
    public LinkedHashSet<MenuItem> getMenuList() {
        return menuList;
    }

    /**
     * @param menuList the list of menu items to be set into the menu
     */
    public void setMenuList(LinkedHashSet<MenuItem> menuList) {
        this.menuList = menuList;
    }


    /**
     * @param finalMenu the list of menu items obtained in a linked hash set from the .csv file given
     */
    @Override
    public void createMenuList(LinkedHashSet<MenuItem> finalMenu) {
        setMenuList(finalMenu);
        assert menuList != null;
    }

    /**
     * @param product the base product to be added into the menu
     */
    @Override
    public void addProductToMenu(BaseProduct product) {
        assert product != null;
        assert !menuList.contains(product);
        menuList.add(product);
        assert menuList != null;
        assert menuList.contains(product);
    }


    /**
     * @param product  the base product to be changed
     * @param name     the new name of the base product
     * @param rating   the new rating of the base product
     * @param calories the new amount of calories of the base product
     * @param protein  the new amount of protein of the base product
     * @param fat      the new amount of fat of the base product
     * @param sodium   the new amount of sodium of the base product
     * @param price    the new price of the base product
     */
    @Override
    public void editProductInMenu(BaseProduct product, String name, Double rating, Integer calories, Integer protein, Integer fat, Integer sodium, Double price) {
        assert product != null;
        for (boolean b : new boolean[]{menuList.contains(product), name != null, rating != null, calories != null, protein != null, fat != null, sodium != null, price != null}) {
            assert b;
        }
        MenuItem itemModify = null;
        for (MenuItem menuItem : menuList) {
            if (menuItem.equals(product)) {
                itemModify = menuItem;
                break;
            }
        }
        assert itemModify != null;
        itemModify.setName(name);
        itemModify.setRating(rating);
        itemModify.setCalories(calories);
        itemModify.setProtein(protein);
        itemModify.setFat(fat);
        itemModify.setSodium(sodium);
        itemModify.setPrice(price);
        assert itemModify.getName().equals(name);
        assert itemModify.getPrice() == price;
    }

    /**
     * @param product the composite product to be added to the menu
     */
    @Override
    public void addCompositeProductToMenu(CompositeProduct product) {
        assert product != null;
        menuList.add(product);
        assert menuList.contains(product);
    }

    /**
     * @param product the product to be changed from the menu
     * @param name    the new name of the composite product
     * @param items   the list of menu items that will comprise the changed composite product
     */
    @Override
    public void editCompositeProductInMenu(CompositeProduct product, String name, ArrayList<MenuItem> items) {
        assert product != null;
        for (boolean b : new boolean[]{menuList.contains(product), name != null, items != null}) {
            assert b;
        }
        CompositeProduct itemModify = null;
        for (MenuItem menuItem : menuList) {
            if (menuItem.getClass().getSimpleName().equals("CompositeProduct")) {
                if (((CompositeProduct) menuItem).equals(product)) {
                    itemModify = (CompositeProduct) menuItem;
                    break;
                }
            }
        }
        itemModify.setName(name);
        itemModify.setCompositeProduct(items);
    }

    /**
     * @param products the list of menu items to be added in the order
     * @param username the username of the user creating the order
     */
    public void addOrder(ArrayList<MenuItem> products, String username) {
        assert products != null;
        assert username != null;
        assert isWellFormed();
        Double finalPrice = 0.0;
        Integer finalProtein = 0;
        for (MenuItem menuItem : products) {
            finalPrice += menuItem.getPrice();
            finalProtein += menuItem.getProtein();
        }
        User currUser = HelperStuff.user;
        Order newOrder = new Order(currUser.getId(), currUser.getUsername(), finalPrice, finalProtein);
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                user.setNumberOfOrders(user.getNumberOfOrders() + 1);
                user.setValueOfOrders(user.getValueOfOrders() + finalPrice);
            }
        }
        ordersList.put(newOrder, products);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder observerOrder = new StringBuilder("OrderId: " + newOrder.getOrderId() + "\n" + "Client name: " + newOrder.getClientName() + "\n" + "Date: " + dateFormat.format(newOrder.getDate().getTime()) + "\n" + "Products: ");
        for (MenuItem menuItem : products) {
            observerOrder.append(menuItem.toString()).append(" \n");
        }
        observerOrder.append("Total Price: ").append(newOrder.getFinalPrice()).append("\n\n\n");
        setChanged();
        notifyObservers(observerOrder.toString());
        generateBill(newOrder, products);
        assert isWellFormed();
        assert ordersList != null;
    }

    /**
     * @return the hash set containing the users
     */
    public HashSet<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the list of users to be set into the menu
     */
    public void setUserList(HashSet<User> userList) {
        this.userList = userList;
    }

    /**
     * @param startHour start hour to find orders
     * @param endHour   end hour to find orders
     * @return the list of orders that satisfy the condition of having been created between the two given hours
     */
    @Override
    public ArrayList<Order> generateReportOne(int startHour, int endHour) {
        assert startHour >= 0;
        assert endHour >= 0;
        assert endHour < 24;
        assert startHour < endHour;
        return (ArrayList<Order>) getOrdersList().keySet().stream().filter(obj -> obj.getDate().get(Calendar.HOUR_OF_DAY) >= startHour).filter(obj -> obj.getDate().get(Calendar.HOUR_OF_DAY) <= endHour).collect(Collectors.toList());
    }

    /**
     * @param numberOfOrders the minimum number of orders for a client to satisfy the condition
     * @param valueOfOrders  the minimum value of orders for a client to satisfy the condition
     * @return the list of users that satisfy the condition of having created more orders than the numberOfOrders, whose total value is greater than valueOfOrders
     */
    @Override
    public ArrayList<User> generateReportThree(int numberOfOrders, int valueOfOrders) {
        assert numberOfOrders > 0;
        assert valueOfOrders > 0;
        return (ArrayList<User>) getUserList().stream().filter(obj -> obj.getNumberOfOrders() >= numberOfOrders).filter(obj -> obj.getValueOfOrders() >= valueOfOrders).collect(Collectors.toList());
    }

    /**
     * @param numberOfTimes the minimum number of times a product must have been ordered to satisfy the condition
     * @param productList   a hash map containing each menu item and how many times it has been ordered
     * @return a list of menu items that satisfy the condition of having been ordered at least numberOfTimes times
     */
    @Override
    public ArrayList<MenuItem> generateReportTwo(int numberOfTimes, HashMap<MenuItem, Integer> productList) {
        assert productList != null;
        for (boolean b : new boolean[]{numberOfTimes > 0, ordersList != null}) {
            assert b;
        }
        return (ArrayList<MenuItem>) productList.keySet().stream().filter(o -> productList.get(o) >= numberOfTimes).collect(Collectors.toList());
    }

    /**
     * @param day   the day the order must have been placed on
     * @param month the month the order must have been placed on
     * @return a list of orders that satisfy the condition that they have been placed on the specified day and month
     */
    @Override
    public ArrayList<Order> generateReportFour(int day, int month) {
        assert day > 0;
        assert day <= 31;
        assert month > 0;
        assert month <= 12;
        return (ArrayList<Order>) getOrdersList().keySet().stream().filter(obj -> obj.getDate().get(Calendar.DAY_OF_MONTH) == day).filter(obj -> obj.getDate().get(Calendar.MONTH) == month - 1 && obj.getDate().get(Calendar.YEAR) == 2021).collect(Collectors.toList());
    }

    /**
     * @param name the name that we want to filter by
     * @return a list of menu items that contain the name given as parameter
     */
    @Override
    public ArrayList<MenuItem> filteredName(String name) {
        assert menuList != null;
        assert !name.equals("");
        ArrayList<MenuItem> filteredList = (ArrayList<MenuItem>) menuList.stream().filter(e -> e.getName().toLowerCase().contains(name.toString().toLowerCase())).collect(Collectors.toList());
        return filteredList;
    }

    /**
     * @param rating the rating that we want to filter by
     * @return a list of menu items that have a rating equal to the one given as parameter
     */
    @Override
    public ArrayList<MenuItem> filteredRating(String rating) {
        assert menuList != null;
        assert isDouble(rating.toString());
        ArrayList<MenuItem> filteredList = (ArrayList<MenuItem>) menuList.stream().filter(e -> e.getRating() == Double.parseDouble(rating)).collect(Collectors.toList());
        return filteredList;
    }

    /**
     * @param calories the calories count that we want to filter by
     * @return a list of menu items that have a calorie count equal to the one given as parameter
     */
    @Override
    public ArrayList<MenuItem> filteredCalories(String calories) {
        assert menuList != null;
        assert isInteger(calories.toString());
        ArrayList<MenuItem> filteredList = (ArrayList<MenuItem>) menuList.stream().filter(e -> e.getCalories() == Integer.parseInt(calories)).collect(Collectors.toList());
        return filteredList;
    }

    /**
     * @param protein the protein count that we want to filter by
     * @return a list of menu items that have a protein count equal to the one given as parameter
     */
    @Override
    public ArrayList<MenuItem> filteredProtein(String protein) {
        assert menuList != null;
        assert isInteger(protein.toString());
        ArrayList<MenuItem> filteredList = (ArrayList<MenuItem>) menuList.stream().filter(e -> e.getProtein() == Integer.parseInt(protein)).collect(Collectors.toList());
        return filteredList;
    }

    /**
     * @param fat the fat content that we want to filter by
     * @return a list of menu items that have a fat content equal to the one given as parameter
     */
    @Override
    public ArrayList<MenuItem> filteredFat(String fat) {
        assert menuList != null;
        assert isInteger(fat.toString());
        ArrayList<MenuItem> filteredList = (ArrayList<MenuItem>) menuList.stream().filter(e -> e.getFat() == Integer.parseInt(fat)).collect(Collectors.toList());
        return filteredList;
    }

    /**
     * @param sodium the sodium content that we want to filter by
     * @return a list of menu items that have a sodium content equal to the one given as a parameter
     */
    @Override
    public ArrayList<MenuItem> filteredSodium(String sodium) {
        assert menuList != null;
        assert isInteger(sodium.toString());
        ArrayList<MenuItem> filteredList = (ArrayList<MenuItem>) menuList.stream().filter(e -> e.getSodium() == Integer.parseInt(sodium)).collect(Collectors.toList());
        return filteredList;
    }

    /**
     * @param price the price that we want to filter by
     * @return a list of menu items that have a price equal to the one given as a parameter
     */
    @Override
    public ArrayList<MenuItem> filteredPrice(String price) {
        assert menuList != null;
        assert isDouble(price.toString());
        ArrayList<MenuItem> filteredList = (ArrayList<MenuItem>) menuList.stream().filter(e -> e.getPrice() == Double.parseDouble(price)).collect(Collectors.toList());
        return filteredList;
    }

    /**
     * @param order    the order that we want to create a bill for
     * @param products the list of menu items that comprise the bill
     */
    @Override
    public void generateBill(Order order, ArrayList<MenuItem> products) {
        assert ordersList != null;
        assert userList != null;
        StringBuilder stringBuilder = new StringBuilder("Order" + order.getOrderId() + ".txt");
        try {
            File myFile = new File(stringBuilder.toString());
            myFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder orderContent = new StringBuilder("");
        User orderUser = HelperStuff.user;
        assert orderUser != null;
        orderContent.append("Client: ").append(orderUser.getUsername()).append("\n");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        orderContent.append("Date: ").append(formatDate.format(order.getDate().getTime()) + "\n");
        orderContent.append("Products: ");
        for (MenuItem menuItem : products) {
            orderContent.append(menuItem.getName().toString() + "\n");
        }
        orderContent.append("Total Price: ").append(order.getFinalPrice());
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(stringBuilder.toString());
            fileWriter.write(orderContent.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param string string to be checked
     * @return true if the given string can be parsed to double, or false otherwise
     */
    public boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param string string to be checked
     * @return true if the given string can be parsed to integer, or false otherwise
     */
    public boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @return the hash map containing the orders and the lists of items from the orders
     */
    public HashMap<Order, ArrayList<MenuItem>> getOrdersList() {
        return ordersList;
    }

    /**
     * @param ordersList the hash map containing the order and the lists of items from the orders to be set into the order list
     */
    public void setOrdersList(HashMap<Order, ArrayList<MenuItem>> ordersList) {
        this.ordersList = ordersList;
    }

    /**
     * @param item the base product to be deleted from the menu
     */
    @Override
    public void removeProductFromMenu(MenuItem item) {
        assert item != null;
        assert menuList.contains(item);
        menuList.remove(item);
        assert !(menuList.contains(item));
    }
}
