package org.example.PresentationLayer;

import java.lang.String;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.BusinessLayer.*;
import org.example.BusinessLayer.MenuItem;
import org.example.DataLayer.FileWriter;
import org.example.DataLayer.HelperStuff;
import org.example.DataLayer.Serializator;
import org.example.BusinessLayer.User;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Nemes Mihnea-Andrei
 */
public class AdminPageController implements Initializable {

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
    private Button importMenuButton;

    @FXML
    private Button reportOneButton;

    @FXML
    private Button reportTwoButton;

    @FXML
    private Button reportThreeButton;

    @FXML
    private Button reportFourButton;

    @FXML
    private Button editProductButton;

    @FXML
    private Button createBaseProductButton;

    @FXML
    private Button generateBillsButton;

    @FXML
    private Label baseProductTitleLabel;

    @FXML
    private Label baseProductRatingLabel;

    @FXML
    private Label baseProductCaloriesLabel;

    @FXML
    private Label baseProductProteinLabel;

    @FXML
    private Label baseProductFatLabel;

    @FXML
    private Label baseProductSodiumLabel;

    @FXML
    private Label baseProductPriceLabel;

    @FXML
    private TextField baseProductTitleText;

    @FXML
    private TextField baseProductRatingText;

    @FXML
    private TextField baseProductCaloriesText;

    @FXML
    private TextField baseProductProteinText;

    @FXML
    private TextField baseProductFatText;

    @FXML
    private TextField baseProductSodiumText;

    @FXML
    private TextField baseProductPriceText;


    @FXML
    private ComboBox<MenuItem> compositeProductCombo;

    @FXML
    private Button addComponentButton;

    @FXML
    private Label baseProductCreationLabel;

    @FXML
    private Label compositeProductCreationLabel;

    @FXML
    private Button createCompositeProductButton;

    @FXML
    private TextField compositeProductNameText;

    @FXML
    private Label compositeProductTitleLabel;

    @FXML
    private TextField reportOneTextOne;

    @FXML
    private TextField reportOneTextTwo;

    @FXML
    private TextField reportTwoTextTwo;

    @FXML
    private TextField reportThreeTextOne;

    @FXML
    private TextField reportThreeTextTwo;

    @FXML
    private TextField reportFourTextOne;

    @FXML
    private TextField reportFourTextTwo;

    @FXML
    private Button deleteProductButton;

    @FXML
    private TextField reportTwoTextOne;

    /**
     * @param event interface event
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     *                                method to handle every button click from the admin interface
     */
    @FXML
    void buttonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (event.getSource() == importMenuButton) {
            LinkedHashSet<MenuItem> menuItems = FileWriter.readFromFile();
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.setMenuList(menuItems);
            //deliveryService.setMenuList(Serializator.deserializeMenuItems());
            Serializator.serializeMenuItems(deliveryService.getMenuList());
            showItems();
        }
        if (event.getSource() == createBaseProductButton) {
            if (!baseProductTitleText.getText().equals("") && !baseProductRatingText.getText().equals("") && isDouble(baseProductRatingText.getText().toString()) && !baseProductCaloriesText.getText().equals("")
                    && isInteger(baseProductCaloriesText.getText().toString()) && !baseProductProteinText.getText().equals("") && isInteger(baseProductProteinText.getText().toString()) &&
                    !baseProductFatText.getText().equals("") && isInteger(baseProductFatText.getText().toString()) && !baseProductSodiumText.getText().equals("") && isInteger(baseProductSodiumText.getText().toString())
                    && !baseProductPriceText.getText().equals("") && isDouble(baseProductPriceText.getText().toString())) {
                BaseProduct product = new BaseProduct(baseProductTitleText.getText(), Double.parseDouble(baseProductRatingText.getText()), Integer.parseInt(baseProductCaloriesText.getText()), Integer.parseInt(baseProductProteinText.getText()), Integer.parseInt(baseProductFatText.getText())
                        , Integer.parseInt(baseProductSodiumText.getText()), Double.parseDouble(baseProductPriceText.getText()));
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.setMenuList(Serializator.deserializeMenuItems());
                deliveryService.addProductToMenu(product);
                Serializator.serializeMenuItems(deliveryService.getMenuList());
                showItems();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid data for creating a base product");
            }
        }
        if (event.getSource() == addComponentButton) {
            compositeProductCombo.getItems().add(menuTable.getSelectionModel().getSelectedItem());
        }
        if (event.getSource() == createCompositeProductButton) {
            ArrayList<MenuItem> newList = (ArrayList<MenuItem>) compositeProductCombo.getItems().stream().map(e -> (MenuItem) e).collect(Collectors.toList());
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.setMenuList(Serializator.deserializeMenuItems());
            CompositeProduct product = new CompositeProduct(newList, compositeProductNameText.getText());
            deliveryService.addCompositeProductToMenu(product);
            Serializator.serializeMenuItems(deliveryService.getMenuList());
            showItems();
            compositeProductCombo.getItems().clear();
            compositeProductNameText.setText("");
        }
        if (event.getSource() == reportOneButton) {

            if (isInteger(reportOneTextOne.getText().toString()) && isInteger(reportOneTextOne.getText().toString())) {
                StringBuilder stringBuilder = new StringBuilder();
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.setOrdersList(Serializator.deserializeOrders());
                ArrayList<Order> reportList = deliveryService.generateReportOne(Integer.parseInt(reportOneTextOne.getText().toString()), Integer.parseInt(reportOneTextTwo.getText().toString()));
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                System.out.println(reportList.size());
                for (Order order : reportList) {
                    System.out.println("YO ");
                    stringBuilder.append("Order id: ").append(order.getOrderId()).append("\n").append("Order price: ").append(order.getFinalPrice()).append("\n").append("Date: ").append(dateFormat.format(order.getDate().getTime())).append("\n\n");
                }
                HelperStuff.reportContent = stringBuilder.toString();
                openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\ReportsPage.fxml");
            }
        }
        if (event.getSource() == reportThreeButton) {
            if (isInteger(reportThreeTextOne.getText().toString()) && isInteger(reportThreeTextTwo.getText().toString())) {
                StringBuilder stringBuilder = new StringBuilder();
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.setUserList(Serializator.deserializeUsers());
                ArrayList<User> reportList = deliveryService.generateReportThree(Integer.parseInt(reportThreeTextOne.getText().toString()), Integer.parseInt(reportThreeTextTwo.getText().toString()));
                for (User user : reportList) {
                    System.out.println("YO ");
                    stringBuilder.append("User: ").append(user.getUsername()).append("\n").append("Number of Orders: ").append(user.getNumberOfOrders()).append("\n").append("Total Value of Orders: ").append(user.getValueOfOrders()).append("\n\n");
                }
                HelperStuff.reportContent = stringBuilder.toString();
                openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\ReportsPage.fxml");
            }
        }
        if (event.getSource() == reportTwoButton) {
            if (isInteger(reportTwoTextTwo.getText().toString())) {
                StringBuilder stringBuilder = new StringBuilder();
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.setOrdersList(Serializator.deserializeOrders());
                deliveryService.setMenuList(Serializator.deserializeMenuItems());
                ArrayList<MenuItem> newList = new ArrayList<>();
                for (ArrayList<MenuItem> menuItems : deliveryService.getOrdersList().values()) {
                    newList.addAll(menuItems);
                }
                HashMap<MenuItem, Integer> listHash = new HashMap<>();
                for (MenuItem menuItem : newList) {
                    int nr = 1;
                    if (listHash.containsKey(menuItem)) {
                        nr = listHash.get(menuItem);
                        nr++;
                        listHash.put(menuItem, nr);
                    } else {
                        listHash.put(menuItem, nr);
                    }
                }
                ArrayList<MenuItem> reportList = deliveryService.generateReportTwo(Integer.parseInt(reportTwoTextTwo.getText().toString()), listHash);
                for (MenuItem menuItem : reportList) {
                    stringBuilder.append("Product: ").append(menuItem.getName()).append("\n").append("Number of times the product was ordered: ").append(listHash.get(menuItem)).append("\n\n\n");
                }
                HelperStuff.reportContent = stringBuilder.toString();
                openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\ReportsPage.fxml");
            }
        }
        if (event.getSource() == reportFourButton) {
            if (isInteger(reportFourTextOne.getText().toString()) && isInteger(reportFourTextTwo.getText().toString())) {
                DeliveryService deliveryService = new DeliveryService();
                deliveryService.setOrdersList(Serializator.deserializeOrders());
                deliveryService.setMenuList(Serializator.deserializeMenuItems());
                ArrayList<Order> orders = deliveryService.generateReportFour(Integer.parseInt(reportFourTextOne.getText().toString()), Integer.parseInt(reportFourTextTwo.getText().toString()));
                ArrayList<MenuItem> menuItems = new ArrayList<>();
                for (Order order : orders) {
                    menuItems.addAll(deliveryService.getOrdersList().get(order));
                }
                HashMap<MenuItem, Integer> listHash = new HashMap<>();
                for (MenuItem menuItem : menuItems) {
                    int nr = 1;
                    if (listHash.containsKey(menuItem)) {
                        nr = listHash.get(menuItem);
                        nr++;
                        listHash.put(menuItem, nr);
                    } else {
                        listHash.put(menuItem, nr);
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (MenuItem menuItem : listHash.keySet()) {
                    stringBuilder.append("Product: " + menuItem.getName() + "\n" + "Number of times it was ordered: " + listHash.get(menuItem) + "\n\n");
                }
                HelperStuff.reportContent = stringBuilder.toString();
                openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\ReportsPage.fxml");
            }

        }
        if (event.getSource() == deleteProductButton) {
            MenuItem menuItem = menuTable.getSelectionModel().getSelectedItem();
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.setMenuList(Serializator.deserializeMenuItems());
            deliveryService.removeProductFromMenu(menuItem);
            Serializator.serializeMenuItems(deliveryService.getMenuList());
            showItems();
        }
        if (event.getSource() == editProductButton) {
            MenuItem product = menuTable.getSelectionModel().getSelectedItem();
            System.out.println(product.getClass().toString());
            if (product.getClass().getSimpleName().equals("CompositeProduct")) {
                HelperStuff.productC = (CompositeProduct) product;
                openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\UpdateCompositeProduct.fxml");
            }
            if (product.getClass().getSimpleName().equals("BaseProduct")) {
                HelperStuff.product = product;
                openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\UpdateBaseProduct.fxml");
            }
        }
        if (event.getSource() == generateBillsButton) {
            showItems();
        }
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
     * @param pagePath the path of the new window
     *                 handles the opening of new interface windows
     */
    private void openPage(String pagePath) {
        try {
            FXMLLoader loader = new FXMLLoader(new File(pagePath).toURI().toURL());
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

