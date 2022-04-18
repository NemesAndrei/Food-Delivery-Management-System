package org.example.PresentationLayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.BusinessLayer.DeliveryService;
import org.example.DataLayer.Serializator;
import org.example.BusinessLayer.DeliveryService;
import org.example.BusinessLayer.MenuItem;
import org.example.DataLayer.FileWriter;
import org.example.DataLayer.HelperStuff;
import org.example.DataLayer.Serializator;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Nemes Mihnea-Andrei
 */
public class ClientPageController implements Initializable {

    @FXML
    private TableView<MenuItem> menuTable;

    @FXML
    private TableColumn<MenuItem, String> titleColumn;

    @FXML
    private TableColumn<MenuItem, Double> ratingColumn;

    @FXML
    private TableColumn<MenuItem, Integer> caloriesColumn;

    @FXML
    private TableColumn<MenuItem, Integer> proteinColumn;

    @FXML
    private TableColumn<MenuItem, Integer> fatColumn;

    @FXML
    private TableColumn<MenuItem, Integer> sodiumColumn;

    @FXML
    private TableColumn<MenuItem, Double> priceColumn;

    @FXML
    private ComboBox<String> filterCombo;

    @FXML
    private Label filterLabel;

    @FXML
    private TextField filterText;

    @FXML
    private Button filterButton;

    @FXML
    private Button refreshTableButton;

    @FXML
    private Button submitOrderButton;

    @FXML
    private Label selectedProductsLabel;

    @FXML
    private ComboBox<MenuItem> selectedProductsCombo;

    @FXML
    private Button selectedProductsAddButton;

    @FXML
    private Button selectedProductsRemoveButton;

    /**
     * @param event interface event
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     *                                method to handle every button click from the client interface
     */
    @FXML
    void buttonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (event.getSource() == refreshTableButton) {
            showItems();
        }
        if (event.getSource() == filterButton) {
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.setMenuList(Serializator.deserializeMenuItems());
            switch (filterCombo.getValue().toString()) {
                case "Title": {
                    showFilteredItems(deliveryService.filteredName(filterText.getText().toString()));
                    break;
                }
                case "Rating": {
                    showFilteredItems(deliveryService.filteredRating(filterText.getText().toString()));
                    break;
                }
                case "Calories": {
                    showFilteredItems(deliveryService.filteredCalories(filterText.getText().toString()));
                    break;
                }
                case "Protein": {
                    showFilteredItems(deliveryService.filteredProtein(filterText.getText().toString()));
                    break;
                }
                case "Fat": {
                    showFilteredItems(deliveryService.filteredFat(filterText.getText().toString()));
                    break;
                }
                case "Sodium": {
                    showFilteredItems(deliveryService.filteredSodium(filterText.getText().toString()));
                    break;
                }
                case "Price": {
                    showFilteredItems(deliveryService.filteredPrice(filterText.getText().toString()));
                    break;
                }
            }
        }
        if (event.getSource() == selectedProductsAddButton) {
            selectedProductsCombo.getItems().add(menuTable.getSelectionModel().getSelectedItem());
        }
        if (event.getSource() == selectedProductsRemoveButton) {
            selectedProductsCombo.getItems().remove(selectedProductsCombo.getSelectionModel().getSelectedItem());
        }
        if (event.getSource() == submitOrderButton) {
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.setOrdersList(Serializator.deserializeOrders());
            deliveryService.setUserList(Serializator.deserializeUsers());
            ArrayList<MenuItem> orderList = (ArrayList<MenuItem>) selectedProductsCombo.getItems().stream().map(e -> (MenuItem) e).collect(Collectors.toList());
            deliveryService.addObserver(HelperStuff.employeePageController);
            deliveryService.addOrder(orderList, HelperStuff.user.getUsername());
            Serializator.serializeOrders(deliveryService.getOrdersList());
            Serializator.serializeUsers(deliveryService.getUserList());
        }
    }

    /**
     * @param url
     * @param resourceBundle initialize the interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(HelperStuff.user.getUsername());
        titleColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("Name"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, Double>("Rating"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Calories"));
        proteinColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Protein"));
        fatColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Fat"));
        sodiumColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("Sodium"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<MenuItem, Double>("Price"));
        DeliveryService deliveryService = null;
        try {
            deliveryService = new DeliveryService();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            deliveryService.setMenuList(Serializator.deserializeMenuItems());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (deliveryService.getMenuList() != null) {
            ObservableList<org.example.BusinessLayer.MenuItem> products = FXCollections.observableArrayList(deliveryService.getMenuList());
            menuTable.setItems(products);
        }
        filterCombo.getItems().addAll("Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price");
    }

    /**
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     *                                initializes the data in the table
     */
    private void showItems() throws IOException, ClassNotFoundException {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setMenuList(Serializator.deserializeMenuItems());
        if (deliveryService.getMenuList() != null) {
            ObservableList<MenuItem> products = FXCollections.observableArrayList(deliveryService.getMenuList());
            menuTable.setItems(products);
        }
    }

    /**
     * @param products products to set in the table
     *                 sets the table to display the result of the filter
     */
    private void showFilteredItems(ArrayList<MenuItem> products) {
        menuTable.setItems(FXCollections.observableArrayList(products));
    }
}
