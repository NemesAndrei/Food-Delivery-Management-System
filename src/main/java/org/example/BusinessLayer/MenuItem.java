package org.example.BusinessLayer;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nemes Mihnea-Andrei
 */
public abstract class MenuItem implements Serializable {

    /**
     * name of the menu item
     */
    protected String name;

    /**
     * rating of the menu item
     */
    protected double rating;

    /**
     * calorie count of the menu item
     */
    protected int calories;

    /**
     * protein count of the menu item
     */
    protected int protein;

    /**
     * fat content of the menu item
     */
    protected int fat;

    /**
     * sodium content of the menu item
     */
    protected int sodium;

    /**
     * price of the menu item
     */
    protected double price;

    /**
     * @return the title of the menu item
     */
    public String getName() {
        return name;
    }

    /**
     * @param name title to be given to the menu item
     *             sets the title of the menu item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the rating of the menu item
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating rating to be given to the menu item
     *               sets the rating of the menu item
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return the calorie count of the menu item
     */
    public int getCalories() {
        return calories;
    }

    /**
     * @param calories the calorie count to be given to the menu item
     *                 sets the calorie count of the menu item
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * @return the protein count of the menu item
     */
    public int getProtein() {
        return protein;
    }

    /**
     * @param protein the protein count to be given to the menu item
     *                sets the protein count of the menu item
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }

    /**
     * @return the fat content of the menu item
     */
    public int getFat() {
        return fat;
    }

    /**
     * @param fat the fat content to be given to the menu item
     *            sets the fat content of the menu item
     */
    public void setFat(int fat) {
        this.fat = fat;
    }

    /**
     * @return the sodium content of the menu item
     */
    public int getSodium() {
        return sodium;
    }

    /**
     * @param sodium the sodium content to be given to the menu item
     *               sets the sodium content of the menu item
     */
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    /**
     * @return the price of the menu item
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to be given to the menu item
     *              sets the price of the menu item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return calculates the price of the menu item
     */
    public abstract double calculatePrice();

    /**
     * @return calculates the protein of the menu item
     */
    public abstract int calculateProtein();

    /**
     * @return string form of the menu item
     */
    @Override
    public String toString() {
        String forReturn = this.name + ", " + this.rating + ", " + this.calories + ", " + this.protein + ", " + this.fat + ", " + this.sodium + ", " + this.price;
        return forReturn;
    }

    /**
     * @param o the object that will be compared
     * @return true if the two menu items are equal, or false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name);
    }

    /**
     * @return the hash code of the menu item
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
