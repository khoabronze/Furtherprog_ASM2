package com.example.furtherprog_asm2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class SearchInsuranceManager_Controller {
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
}
