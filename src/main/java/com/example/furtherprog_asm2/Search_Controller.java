package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.InsuranceCard;
import com.example.furtherprog_asm2.InsuranceCardDao;
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

public class Search_Controller {
    @FXML
    private ImageView homeIcon;
    @FXML
    private TextField cardNumberInput;
    @FXML
    private TableView<InsuranceCard> tableView;
    @FXML
    private TableColumn<InsuranceCard, String> cardNumber;
    @FXML
    private TableColumn<InsuranceCard, String> cardHolder;
    @FXML
    private TableColumn<InsuranceCard, String> policyOwner;
    @FXML
    private TableColumn<InsuranceCard, String> expirationDate;
    @FXML
    private Button search;
    @FXML
    private Button viewAll;

    @FXML
    public void initialize() {
        cardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        cardHolder.setCellValueFactory(new PropertyValueFactory<>("cardHolder"));
        policyOwner.setCellValueFactory(new PropertyValueFactory<>("policyOwner"));
        expirationDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
    }

    @FXML
    public void search(ActionEvent event) {
        String cardNumberData = cardNumberInput.getText();
        InsuranceCardDao insuranceCardDao = new InsuranceCardDao();
        InsuranceCard insuranceCard = insuranceCardDao.getOne(cardNumberData);
        if (insuranceCard != null) {
            tableView.getItems().clear();
            tableView.getItems().add(insuranceCard);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Card number does not exist.");
            alert.showAndWait();
        }
        cardNumberInput.clear(); // Clear the input field at the search bar
    }

    @FXML
    public void viewAll(ActionEvent event) {
        InsuranceCardDao insuranceCardDao = new InsuranceCardDao();
        tableView.getItems().clear();
        tableView.getItems().addAll(insuranceCardDao.getAll());
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