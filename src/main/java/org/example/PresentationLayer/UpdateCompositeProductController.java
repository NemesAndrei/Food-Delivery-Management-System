package org.example.PresentationLayer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.BusinessLayer.CompositeProduct;
import org.example.BusinessLayer.DeliveryService;
import org.example.BusinessLayer.MenuItem;
import org.example.DataLayer.FileWriter;
import org.example.DataLayer.HelperStuff;
import org.example.DataLayer.Serializator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * @author Nemes Mihnea-Andrei
 */
public class UpdateCompositeProductController implements Initializable {

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
    private ComboBox<MenuItem> updateCompositeCombo;

    @FXML
    private Button addCompositeButton;

    @FXML
    private Button deleteCompositeButton;

    @FXML
    private Button editCompositeButton;

    @FXML
    private Label compositeTitleLabel;

    @FXML
    private TextField compositeTitleText;

    @FXML
    private Label editCompositeLabel;

    /**
     * @param event interface event
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     *                                method to handle every button click from the update composite product interface
     */
    @FXML
    void buttonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (event.getSource() == addCompositeButton) {
            updateCompositeCombo.getItems().add(menuTable.getSelectionModel().getSelectedItem());
        }
        if (event.getSource() == deleteCompositeButton) {
            updateCompositeCombo.getItems().remove(updateCompositeCombo.getSelectionModel().getSelectedItem());
        }
        if (event.getSource() == editCompositeButton) {
            if (!compositeTitleText.getText().equals("") && !(updateCompositeCombo.getItems().size() == 0)) {
                ArrayList<MenuItem> newList = (ArrayList<MenuItem>) updateCompositeCombo.getItems().stream().map(e -> (MenuItem) e).collect(Collectors.toList());
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.setMenuList(Serializator.deserializeMenuItems());
                deliveryService.editCompositeProductInMenu(HelperStuff.productC, compositeTitleText.getText(), newList);
                Serializator.serializeMenuItems(deliveryService.getMenuList());
            }
        }
    }

    /**
     * @param url
     * @param resourceBundle initialize the interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            ObservableList<MenuItem> products = FXCollections.observableArrayList(deliveryService.getMenuList());
            menuTable.setItems(products);
        }
        updateCompositeCombo.getItems().addAll(HelperStuff.productC.getCompositeProduct());
        compositeTitleText.setText(HelperStuff.productC.getName());
    }
}
