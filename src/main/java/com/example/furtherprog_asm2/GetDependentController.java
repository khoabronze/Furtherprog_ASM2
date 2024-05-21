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

public class GetDependentController {
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView profileIcon;

    private DependentService dependentService;

    public GetDependentController() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.dependentService = new DependentService(new DependentDAO_IMP(connection));
    }

    @FXML
    private TableView<Dependent> tableView;
    @FXML
    private TableColumn<Dependent, String> idColumn;
    @FXML
    private TableColumn<Dependent, String> nameColumn;
    @FXML
    private TableColumn<Dependent, String> phoneColumn;
    @FXML
    private TableColumn<Dependent, String> mailColumn;
    @FXML
    private TableColumn<Dependent, String> addressColumn;
    @FXML
    private TableColumn<Dependent, String> passColumn;

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
        Dependent dependent = this.dependentService.getOneDependent(id);
        if (dependent != null) {
            displayDependent(dependent);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Search Result");
            alert.setHeaderText(null);
            alert.setContentText("Dependent not found");
            alert.showAndWait();
        }
    }

    public void displayDependent(Dependent dependent) {
        ObservableList<Dependent> data = FXCollections.observableArrayList();
        if (dependent != null) {
            data.add(dependent);
        }
        tableView.setItems(data);
    }

    public void Viewall() {
        List<Dependent> dependents = dependentService.getAllDependents();
        ObservableList<Dependent> data = FXCollections.observableArrayList(dependents);
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
            case "Policy Holder":
                homepageFile = "Homepage-PolicyHolder.fxml";
                break;
            case "Policy Owner":
                homepageFile = "Homepage-PolicyOwner.fxml";
                break;
            case "Insurance Manager":
                homepageFile = "Homepage-InsuranceManager.fxml";
                break;
            case "Insurance Surveyor":
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

    @FXML
    public void navigateUserProfile() throws IOException {
        // Load the FXML file for the new scene
        Parent newSceneParent = FXMLLoader.load(getClass().getResource("User-Profile.fxml"));

        // Create a new scene
        Scene newScene = new Scene(newSceneParent);

        // Get the current stage
        Stage currentStage = (Stage) profileIcon.getScene().getWindow();

        // Set the new scene on the current stage
        currentStage.setScene(newScene);
    }
}