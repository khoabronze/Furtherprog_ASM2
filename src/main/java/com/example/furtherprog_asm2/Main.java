/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

/**
 * @author <Dong Dang Khoa- s3986281>
 */



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Main.java
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Db_function db = new Db_function();
        db.connect_to_db();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Insurance Claim Management System");
        stage.setScene(scene); // Set the scene to the stage
        stage.show(); // Show the stage
    }

    public static void main(String[] args) {
        launch();
    }
}
