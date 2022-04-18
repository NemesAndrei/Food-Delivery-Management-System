package org.example.PresentationLayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.BusinessLayer.DeliveryService;
import org.example.DataLayer.Serializator;
import org.example.BusinessLayer.User;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Nemes Mihnea-Andrei
 */
public class RegisterPageController implements Initializable {

    private DeliveryService deliveryService;

    public RegisterPageController() throws IOException, ClassNotFoundException {
    }

    @FXML
    private ComboBox selectAccountTypeCombo;

    @FXML
    private Label accountTypeLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confirmPasswordTextField;

    @FXML
    private Button registerButton;

    /**
     * @param event interface event
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     *                                method to handle every button click from the register page interface
     */
    @FXML
    void buttonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (event.getSource() == registerButton) {
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.setUserList(Serializator.deserializeUsers());
            if (usernameTextField.getText().equals("") || passwordTextField.getText().equals("") || confirmPasswordTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No fields can be left empty!");
            } else {
                if (!(passwordTextField.getText().equals(confirmPasswordTextField.getText()))) {
                    JOptionPane.showMessageDialog(null, "Passwords must match!");
                } else {
                    User user = null;
                    String s = selectAccountTypeCombo.getValue().toString();
                    if ("Admin".equals(s)) {
                        user = new User(usernameTextField.getText(), passwordTextField.getText(), 0);
                    } else if ("Client".equals(s)) {
                        user = new User(usernameTextField.getText(), passwordTextField.getText(), 1);
                    } else if ("Employee".equals(s)) {
                        user = new User(usernameTextField.getText(), passwordTextField.getText(), 2);
                    }
                    deliveryService.getUserList().add(user);
                    Serializator.serializeUsers(deliveryService.getUserList());
                }
            }
        }
    }

    /**
     * @param url
     * @param resourceBundle initialize the interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectAccountTypeCombo.getItems().addAll("Admin", "Client", "Employee");
    }
}
