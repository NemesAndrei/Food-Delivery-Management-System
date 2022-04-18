package org.example.PresentationLayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.BusinessLayer.DeliveryService;
import org.example.DataLayer.HelperStuff;
import org.example.DataLayer.Serializator;
import org.example.BusinessLayer.User;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Nemes Mihnea-Andrei
 */
public class LoginPageController implements Initializable {

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

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    /**
     * @param event interface event
     * @throws IOException            running exception
     * @throws ClassNotFoundException running exception
     *                                method to handle every button click from the login interface
     */
    @FXML
    void buttonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if (event.getSource() == registerButton) {
            openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\RegisterPage.fxml");
        }
        if (event.getSource() == loginButton) {
            DeliveryService deliveryService = new DeliveryService();
            deliveryService.setUserList(Serializator.deserializeUsers());
            if (usernameTextField.getText().equals("") || passwordTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Username or Password fields cannot be empty");
            } else {
                User user = null;
                int found = 0;
                int usern = 0;
                for (User user1 : deliveryService.getUserList()) {
                    if (user1.getUsername().equals(usernameTextField.getText())) {
                        usern = 1;
                        if (user1.getPassword().equals(passwordTextField.getText())) {
                            user = user1;
                            found = 1;
                            System.out.println(user.getUserType());
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect password!");
                        }
                    }
                }
                if (usern == 0) {
                    JOptionPane.showMessageDialog(null, "Incorrect username!");
                }
                if (found == 1) {
                    if (user.getUserType() == 0) {
                        openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\AdminPage.fxml");
                    } else if (user.getUserType() == 1) {
                        HelperStuff.user = user;
                        openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\ClientPage.fxml");
                    } else if (user.getUserType() == 2) {
                        openPage("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\EmployeePage.fxml");
                    }
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

    }
}

