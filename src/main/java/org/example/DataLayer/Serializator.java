package org.example.DataLayer;

import org.example.BusinessLayer.MenuItem;
import org.example.BusinessLayer.Order;
import org.example.BusinessLayer.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author Nemes Mihnea-Andrei
 */
public class Serializator {

    /**
     * @param userList the hash set to be serialized
     * @throws IOException running exception
     */
    public static void serializeUsers(HashSet<User> userList) throws IOException {
        FileOutputStream file = new FileOutputStream("fileU.txt");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(userList);
        out.close();
        file.close();
    }

    /**
     * @return the hash set that has been deserialized
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     */
    public static HashSet<User> deserializeUsers() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("fileU.txt");
        ObjectInputStream input = new ObjectInputStream(file);
        HashSet<User> list = (HashSet<User>) input.readObject();
        input.close();
        file.close();
        return list;
    }

    /**
     * @param orderList the hash map to be serialized
     * @throws IOException running exception
     */
    public static void serializeOrders(HashMap<Order, ArrayList<MenuItem>> orderList) throws IOException {
        FileOutputStream file = new FileOutputStream("fileO.txt");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(orderList);
        out.close();
        file.close();
    }

    /**
     * @return the hash map of orders that was deserialized
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     */
    public static HashMap<Order, ArrayList<MenuItem>> deserializeOrders() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("fileO.txt");
        ObjectInputStream input = new ObjectInputStream(file);
        HashMap<Order, ArrayList<MenuItem>> list = (HashMap<Order, ArrayList<MenuItem>>) input.readObject();
        input.close();
        file.close();
        return list;
    }

    /**
     * @param itemList the linked hash set of menu items that to be serialized
     * @throws IOException running exception
     */
    public static void serializeMenuItems(LinkedHashSet<MenuItem> itemList) throws IOException {
        FileOutputStream file = new FileOutputStream("fileM.txt");
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(itemList);
        out.close();
        file.close();
    }

    /**
     * @return the linked hash set of menu items that was deserialized
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     */
    public static LinkedHashSet<MenuItem> deserializeMenuItems() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("fileM.txt");
        ObjectInputStream input = new ObjectInputStream(file);
        LinkedHashSet<MenuItem> list = (LinkedHashSet<MenuItem>) input.readObject();
        input.close();
        file.close();
        return list;
    }
}