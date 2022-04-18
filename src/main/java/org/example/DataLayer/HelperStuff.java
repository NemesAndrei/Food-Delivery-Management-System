package org.example.DataLayer;

import org.example.BusinessLayer.CompositeProduct;
import org.example.BusinessLayer.MenuItem;
import org.example.BusinessLayer.User;
import org.example.PresentationLayer.EmployeePageController;

/**
 * @author Nemes Mihnea-Andrei
 */
public class HelperStuff {

    /**
     * stores the current client logged in
     */
    public static User user;

    /**
     * stores the product that will have changes made to it
     */
    public static MenuItem product;

    /**
     * stores the composite product that will have changes made to it
     */
    public static CompositeProduct productC;

    /**
     * stores the employee controller for the observer window
     */
    public static EmployeePageController employeePageController;

    /**
     * stores the content of the report in order to be able to transfer it between controllers
     */
    public static String reportContent;
}
