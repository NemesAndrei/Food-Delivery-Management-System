package org.example.PresentationLayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.BusinessLayer.BaseProduct;
import org.example.BusinessLayer.DeliveryService;
import org.example.DataLayer.HelperStuff;
import org.example.DataLayer.Serializator;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Nemes Mihnea-Andrei
 */
public class UpdateBaseProductController implements Initializable {

    @FXML
    private Label updateBaseNameLabel;

    @FXML
    private Label updateBaseRatingLabel;

    @FXML
    private Label updateBaseCaloriesLabel;

    @FXML
    private Label updateBaseFatLabel;

    @FXML
    private Label updateBaseProteinLabel;

    @FXML
    private Label updateBaseSodiumLabel;

    @FXML
    private Label updateBasePriceLabel;

    @FXML
    private TextField updateBaseRatingText;

    @FXML
    private TextField updateBaseCaloriesText;

    @FXML
    private TextField updateBaseNameText;

    @FXML
    private TextField updateBaseFatText;

    @FXML
    private TextField updateBaseProteinText;

    @FXML
    private TextField updateBaseSodiumText;

    @FXML
    private TextField updateBasePriceText;

    @FXML
    private Button updateBaseProductButton;

    @FXML
    private Label updateBaseProductLabel;

    /**
     * @param event interface event
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     *                                method to handle every button click from the update base product interface
     */
    @FXML
    void buttonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (event.getSource() == updateBaseProductButton) {
            if (!updateBaseNameText.getText().equals("") && !updateBaseRatingText.getText().equals("") && isDouble(updateBaseRatingText.getText().toString()) && !updateBaseCaloriesText.getText().equals("")
                    && isInteger(updateBaseCaloriesText.getText().toString()) && !updateBaseProteinText.getText().equals("") && isInteger(updateBaseProteinText.getText().toString()) &&
                    !updateBaseFatText.getText().equals("") && isInteger(updateBaseFatText.getText().toString()) && !updateBaseSodiumText.getText().equals("") && isInteger(updateBaseSodiumText.getText().toString())
                    && !updateBasePriceText.getText().equals("") && isDouble(updateBasePriceText.getText().toString())) {
                BaseProduct product = new BaseProduct(updateBaseNameText.getText(), Double.parseDouble(updateBaseRatingText.getText()), Integer.parseInt(updateBaseCaloriesText.getText()), Integer.parseInt(updateBaseProteinText.getText()), Integer.parseInt(updateBaseFatText.getText())
                        , Integer.parseInt(updateBaseSodiumText.getText()), Double.parseDouble(updateBasePriceText.getText()));
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.setMenuList(Serializator.deserializeMenuItems());
                deliveryService.editProductInMenu((BaseProduct) HelperStuff.product, updateBaseNameText.getText(), Double.parseDouble(updateBaseRatingText.getText()), Integer.parseInt(updateBaseCaloriesText.getText()), Integer.parseInt(updateBaseProteinText.getText()), Integer.parseInt(updateBaseFatText.getText()),
                        Integer.parseInt(updateBaseSodiumText.getText()), Double.parseDouble(updateBasePriceText.getText()));
                Serializator.serializeMenuItems(deliveryService.getMenuList());
            } else {
                JOptionPane.showMessageDialog(null, "Invalid data for updating a base product!");
            }
        }
    }

    /**
     * @param string string to be verified
     * @return true if the string can be parsed to double or false otherwise
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
     * @param string string to be verified
     * @return true if the string can be parsed to integer or false otherwise
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
     * @param url
     * @param resourceBundle initialize the interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateBaseNameText.setText(HelperStuff.product.getName().toString());
        updateBaseRatingText.setText("" + HelperStuff.product.getRating());
        updateBaseCaloriesText.setText("" + HelperStuff.product.getCalories());
        updateBaseProteinText.setText("" + HelperStuff.product.getProtein());
        updateBaseFatText.setText("" + HelperStuff.product.getFat());
        updateBaseSodiumText.setText("" + HelperStuff.product.getSodium());
        updateBasePriceText.setText("" + HelperStuff.product.getPrice());
    }
}
