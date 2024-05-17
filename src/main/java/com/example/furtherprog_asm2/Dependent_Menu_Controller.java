package com.example.furtherprog_asm2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Dependent_Menu_Controller {

    @FXML
    private ImageView add_depedent_image;

    public void add_Dependent() throws IOException {
        try {
            // Load the new page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Create-Depedent-Policy.fxml"));
            Parent newSceneParent = loader.load();

            // Get the controller of the scene
            UpdateClaimController controller = loader.getController();


            // Create a new scene
            Scene newScene = new Scene(newSceneParent);

            // Get the current stage
            Stage currentStage = (Stage) add_depedent_image.getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}