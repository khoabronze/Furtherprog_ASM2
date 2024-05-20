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

public class SearchInsuranceSurveyor_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TableView insuranceSurveyorTableView;
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
    public void handleSearchInsuranceSurveyor() {
        String id = idInput.getText();

        if (id.isEmpty()) {
            showAlert("Error Dialog", "Input Error", "ID field is required");
            return;
        }

        InsuranceSurveyor_DAO insuranceSurveyorDAO = new InsuranceSurveyor_DAO(dbFunction.connect_to_db());
        InsuranceSurveyor insuranceSurveyor = insuranceSurveyorDAO.getOne(id);

        if (insuranceSurveyor != null) {
            insuranceSurveyorTableView.getItems().clear();
            insuranceSurveyorTableView.getItems().add(insuranceSurveyor);

            //clear the input field
            idInput.clear();
        } else {
            showAlert("Information Dialog", "Search Result", "No Insurance Surveyor found with the provided ID");

            //clear the input field
            idInput.clear();
        }
    }

    @FXML
    public void handleViewAllInsuranceSurveyor() {
        InsuranceSurveyor_DAO insuranceSurveyorDAO = new InsuranceSurveyor_DAO(dbFunction.connect_to_db());
        List<InsuranceSurveyor> insuranceSurveyors = insuranceSurveyorDAO.getAll();

        if (insuranceSurveyors.isEmpty()) {
            showAlert("Error Dialog", "No Data", "No Insurance Surveyor found in the database");
        } else {
            insuranceSurveyorTableView.getItems().clear();
            insuranceSurveyorTableView.getItems().addAll(insuranceSurveyors);
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
