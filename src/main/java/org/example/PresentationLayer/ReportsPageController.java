package org.example.PresentationLayer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import org.example.DataLayer.HelperStuff;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Nemes Mihnea-Andrei
 */
public class ReportsPageController implements Initializable {

    @FXML
    private TextArea reportsPageText;

    /**
     * @param url
     * @param resourceBundle initialize the interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reportsPageText.appendText(HelperStuff.reportContent.toString());
    }
}
