package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.InsuranceCard;
import com.example.furtherprog_asm2.InsuranceCardDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Render_Controller {
    @FXML
    private TextField cardNumberInput;
    @FXML
    private Button render;

    @FXML
    public void render() throws IOException {
        String cardNumberData = cardNumberInput.getText();
        InsuranceCardDao insuranceCardDao = new InsuranceCardDao();
        Optional<InsuranceCard> optionalInsuranceCard = insuranceCardDao.get(cardNumberData);
        if (optionalInsuranceCard.isPresent()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update-InsuranceCard-Form.fxml"));
            Parent newSceneParent = loader.load();

            // Get the controller of the scene
            Update_Controller controller = loader.getController();
            // Pass the card number to the controller
            controller.initializeData(cardNumberData);

            // Create a new scene
            Scene newScene = new Scene(newSceneParent);

            // Get the current stage
            Stage currentStage = (Stage) render.getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(newScene);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No Insurance Card found with the provided card number.");
            alert.showAndWait();

            // Clear the card number input
            cardNumberInput.clear();
        }
    }
}
