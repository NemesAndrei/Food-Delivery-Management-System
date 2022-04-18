package org.example.BusinessLayer;

import java.util.ArrayList;

/**
 * @author Nemes Mihnea-Andrei
 */
public class CompositeProduct extends MenuItem {
    private ArrayList<MenuItem> compositeProduct;

    /**
     * constructor for the composite product containing a list of menu items and its name
     *
     * @param compositeProduct the list of menu items that comprise the composite product
     * @param name             the name of the composite product
     */
    public CompositeProduct(ArrayList<MenuItem> compositeProduct, String name) {
        this.name = name;
        this.rating = 0;
        this.calories = 0;
        this.protein = 0;
        this.sodium = 0;
        this.compositeProduct = compositeProduct;
        for (MenuItem item : compositeProduct) {
            this.rating += item.rating;
            this.calories += item.calories;
            this.sodium += item.sodium;
            this.fat += item.fat;
        }
        this.rating = this.rating / compositeProduct.size();
        this.protein = calculateProtein();
        this.price = calculatePrice();
    }

    /**
     * @return returns the list of menu items that comprise the composite product
     */
    public ArrayList<MenuItem> getCompositeProduct() {
        return compositeProduct;
    }

    /**
     * @param compositeProduct the list of menu items that are used to set into the composite product
     */
    public void setCompositeProduct(ArrayList<MenuItem> compositeProduct) {
        this.rating = 0;
        this.calories = 0;
        this.protein = 0;
        this.sodium = 0;
        this.compositeProduct = compositeProduct;
        for (MenuItem item : compositeProduct) {
            this.rating += item.rating;
            this.calories += item.calories;
            this.sodium += item.sodium;
            this.fat += item.fat;
        }
        this.rating = this.rating / compositeProduct.size();
        this.protein = calculateProtein();
        this.price = calculatePrice();
    }

    /**
     * @return the total price of the composite product by adding the prices of its components and multiplying by 2/3
     */
    @Override
    public double calculatePrice() {
        double finalPrice = 0;
        for (MenuItem item : compositeProduct) {
            finalPrice += item.getPrice();
        }
        return finalPrice * 2 / 3;
    }

    /**
     * @return the total ammount of proteins of the composite product by adding the protein counts of its components
     */
    @Override
    public int calculateProtein() {
        int finalProtein = 0;
        for (MenuItem item : compositeProduct) {
            finalProtein += item.getProtein();
        }
        return finalProtein;
    }

}
