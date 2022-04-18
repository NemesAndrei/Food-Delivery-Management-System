package org.example.BusinessLayer;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Nemes Mihnea-Andrei
 */
public class User implements Serializable {

    /**
     * id of the user
     */
    private String id;

    /**
     * username of the user
     */
    private String username;

    /**
     * password of the user
     */
    private String password;

    /**
     * credential type of the user
     */
    private int userType;

    /**
     * number of orders that the user has placed
     */
    private int numberOfOrders;

    /**
     * total value of the orders that the user has placed
     */
    private Double valueOfOrders;

    /**
     * @param username username of the user
     * @param password password of the user
     * @param userType credential type of the user
     *                 constructor for creating the user containing the username, password and credential type
     */
    public User(String username, String password, int userType) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.numberOfOrders = 0;
        this.valueOfOrders = 0.0;
    }

    /**
     * @return the id of the user
     */
    public String getId() {
        return id;
    }

    /**
     * @param id id to be given to the user
     *           sets the id of the user
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username username to be given to the user
     *                 sets the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password password to be given to the user
     *                 sets the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the credential type of the user
     */
    public int getUserType() {
        return userType;
    }

    /**
     * @param userType the credential type to be given to the user
     *                 sets the credential type of the user
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * @return the number of orders that the user has created
     */
    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    /**
     * @param numberOfOrders number of orders to be given to the user
     *                       sets the users' number of orders
     */
    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    /**
     * @return the total value of the orders of the user
     */
    public Double getValueOfOrders() {
        return valueOfOrders;
    }

    /**
     * @param valueOfOrders total value of the orders of the user to be given to the user
     *                      sets the users' total value of orders
     */
    public void setValueOfOrders(Double valueOfOrders) {
        this.valueOfOrders = valueOfOrders;
    }

    /**
     * @param o the object that will be compared
     * @return true if the two users are equal, or false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    /**
     * @return the hash code of the user
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
