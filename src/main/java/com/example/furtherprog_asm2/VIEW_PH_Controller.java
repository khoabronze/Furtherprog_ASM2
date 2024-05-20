package com.example.furtherprog_asm2;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class VIEW_PH_Controller {
    @FXML
    private ImageView homeIcon;

    private PolicyHolder_Service policyHolderService;

    public VIEW_PH_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.policyHolderService = new PolicyHolder_Service(new PolicyHolderDAO_IMP(connection));
    }

    @FXML
    private TableView<PolicyHolder> tableView;
    @FXML
    private TableColumn<PolicyHolder, String> idColumn;
    @FXML
    private TableColumn<PolicyHolder, String> nameColumn;
    @FXML
    private TableColumn<PolicyHolder, String> phoneColumn;
    @FXML
    private TableColumn<PolicyHolder, String> mailColumn;
    @FXML
    private TableColumn<PolicyHolder, String> addressColumn;
    @FXML
    private TableColumn<PolicyHolder, String> passColumn;

    @FXML
    private TextField ID_BOX;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        passColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    public void handleSearchButtonAction() {
        String id = ID_BOX.getText();
        Optional<PolicyHolder> optionalPolicyHolder = policyHolderService.getPolicyHolder(id);
        if (optionalPolicyHolder.isPresent()) {
            displayPolicyHolder(optionalPolicyHolder.get());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Search Result");
            alert.setHeaderText(null);
            alert.setContentText("Policy Holder not found");
            alert.showAndWait();
        }
    }

    public void displayPolicyHolder(PolicyHolder policyHolder) {
        ObservableList<PolicyHolder> data = FXCollections.observableArrayList();
        if (policyHolder != null) {
            data.add(policyHolder);
        }
        tableView.setItems(data);
    }

    public void Viewall() {
        List<PolicyHolder> policyHolders = policyHolderService.getAllPolicyHolders();
        ObservableList<PolicyHolder> data = FXCollections.observableArrayList(policyHolders);
        tableView.setItems(data);
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