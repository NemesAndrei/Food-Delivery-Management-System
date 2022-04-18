package org.example.BusinessLayer;

/**
 * @author Nemes Mihnea-Andrei
 */
public class BaseProduct extends MenuItem {

    /**
     * @param name
     * @param rating
     * @param calories
     * @param protein
     * @param fat
     * @param sodium
     * @param price    constructor for the base product containing its name, rating, calories, protein, fat, sodium and price
     */
    public BaseProduct(String name, double rating, int calories, int protein, int fat, int sodium, double price) {
        this.name = name;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    /**
     * @return the price of the base product
     */
    @Override
    public double calculatePrice() {
        return getPrice();
    }

    /**
     * @return the protein count of the base product
     */
    @Override
    public int calculateProtein() {
        return getProtein();
    }
}
