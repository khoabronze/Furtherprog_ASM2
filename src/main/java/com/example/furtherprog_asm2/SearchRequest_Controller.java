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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchRequest_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField requestIDInput;
    @FXML
    private TableView<Request> tableView;
    @FXML
    private TableColumn<Request, String> rid;
    @FXML
    private TableColumn<Request, String> id;
    @FXML
    private TableColumn<Request, String> note;
    @FXML
    private TableColumn<Request, String> approval;
    @FXML
    private Button searchButton;
    @FXML
    private Button viewAllButton;

    @FXML
    public void initialize() {
        rid.setCellValueFactory(new PropertyValueFactory<>("rid"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));
        approval.setCellValueFactory(new PropertyValueFactory<>("approval"));
    }

    @FXML
    public void handleSearchOneRequest(ActionEvent event) {
        String requestIDData = requestIDInput.getText();
        RequestDAO requestDao = new RequestDAO();
        Request request = requestDao.getOne(requestIDData);
        if (request != null) {
            tableView.getItems().clear();
            tableView.getItems().add(request);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Request ID does not exist.");
            alert.showAndWait();
        }
        requestIDInput.clear(); // Clear the input field at the search bar
    }

    @FXML
    public void handleViewAllRequest(ActionEvent event) {
        RequestDAO requestDao = new RequestDAO();
        tableView.getItems().clear();
        tableView.getItems().addAll(requestDao.getAll());
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
}
