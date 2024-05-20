package com.example.furtherprog_asm2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SearchInsuranceManager_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TableView insuranceManagerTableView;
    @FXML
    private TableColumn idData;
    @FXML
    private TableColumn nameData;
    @FXML
    private TableColumn phoneData;
    @FXML
    private TableColumn emailData;
    @FXML
    private TableColumn addressData;
    @FXML
    private TableColumn passwordData;
    @FXML
    private TextField idInput;
    @FXML
    private Button searchButton;
    @FXML
    private Button viewAllButton;
    private Db_function dbFunction = new Db_function();

    @FXML
    public void initialize() {
        idData.setCellValueFactory(new PropertyValueFactory<InsuranceSurveyor, Integer>("id"));
        nameData.setCellValueFactory(new PropertyValueFactory<InsuranceSurveyor, String>("name"));
        phoneData.setCellValueFactory(new PropertyValueFactory<InsuranceSurveyor, String>("phone"));
        emailData.setCellValueFactory(new PropertyValueFactory<InsuranceSurveyor, String>("email"));
        addressData.setCellValueFactory(new PropertyValueFactory<InsuranceSurveyor, String>("address"));
        passwordData.setCellValueFactory(new PropertyValueFactory<InsuranceSurveyor, String>("password"));
    }

    @FXML
    public void handleSearchInsuranceManager() {
        String id = idInput.getText();

        if (id.isEmpty()) {
            showAlert("Error Dialog", "Input Error", "ID field is required");
            return;
        }

        InsuranceManager_DAO insuranceManagerDAO = new InsuranceManager_DAO(dbFunction.connect_to_db());
        InsuranceManager insuranceManager = insuranceManagerDAO.getOne(id);

        if (insuranceManager != null) {
            insuranceManagerTableView.getItems().clear();
            insuranceManagerTableView.getItems().add(insuranceManager);

            //clear the input field
            idInput.clear();
        } else {
            showAlert("Information Dialog", "Search Result", "No Insurance Surveyor found with the provided ID");

            //clear the input field
            idInput.clear();
        }
    }

    @FXML
    public void handleViewAllInsuranceManager() {
        InsuranceManager_DAO insuranceManagerDAO = new InsuranceManager_DAO(dbFunction.connect_to_db());
        List<InsuranceManager> insuranceManagers = insuranceManagerDAO.getAll();

        if (insuranceManagers.isEmpty()) {
            showAlert("Error Dialog", "No Data", "No Insurance Surveyor found in the database");
        } else {
            insuranceManagerTableView.getItems().clear();
            insuranceManagerTableView.getItems().addAll(insuranceManagers);
        }
    }

    public void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    @FXML
    public void handleHomeIconClick() {
        String role = Login_Controller.loggedInRole;
        String homepageFile = null;

        switch (role) {
            case "Admin":
                homepageFile = "Homepage-Admin.fxml";
                break;
            case "Dependent":
                homepageFile = "Homepage-Dependent.fxml";
                break;
            case "PolicyHolder":
                homepageFile = "Homepage-PolicyHolder.fxml";
                break;
            case "PolicyOwner":
                homepageFile = "Homepage-PolicyOwner.fxml";
                break;
            case "InsuranceManager":
                homepageFile = "Homepage-InsuranceManager.fxml";
                break;
            case "InsuranceSurveyor":
                homepageFile = "Homepage-InsuranceSurveyor.fxml";
                break;
            default:
                // handle unknown role
                break;
        }

        if (homepageFile != null) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(homepageFile));
                Stage stage = (Stage) homeIcon.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
