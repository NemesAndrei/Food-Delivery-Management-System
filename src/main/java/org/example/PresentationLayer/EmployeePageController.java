package org.example.PresentationLayer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.example.DataLayer.HelperStuff;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * @author Nemes Mihnea-Andrei
 */
public class EmployeePageController implements Initializable, Observer {

    @FXML
    private TextArea employeeOrderArea;


    /**
     * @param url
     * @param resourceBundle initialize the interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HelperStuff.employeePageController = this;
    }

    /**
     * @param o
     * @param arg sets the text of the text area, acts as the observer for the client orders
     */
    @Override
    public void update(Observable o, Object arg) {
        employeeOrderArea.appendText(arg.toString());
    }
}
