package com.example.furtherprog_asm2;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Role_Controller {

    @FXML
    private Button adminLoginButton;

    @FXML
    private void handleAdminLogin() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) adminLoginButton.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
}