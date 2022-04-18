package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Andrei\\Desktop\\Facultate Semestrul 2\\Programming Tecniques\\AssignmentFour\\PT2021_30424_Nemes_Mihnea-Andrei_Assignment_4\\src\\main\\java\\org\\example\\PresentationLayer\\LoginPage.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Restaurant Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        launch(args);
    }
}
