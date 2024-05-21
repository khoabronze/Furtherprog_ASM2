package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;

public class GetAllClaimController {
    @FXML
    private ImageView homeIcon;
    @FXML
    private ImageView profileIcon;
    private ClaimService claimService;

    public GetAllClaimController() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimService = new ClaimService(new ClaimDAO(connection));
    }

    @FXML
    private TableView<Claim> tableView;
    @FXML
    private TableColumn<Claim, String> idColumn;
    @FXML
    private TableColumn<Claim, Date> claimDateColumn;
    @FXML
    private TableColumn<Claim, String> insuredPersonColumn;
    @FXML
    private TableColumn<Claim, String> cardNumberColumn;
    @FXML
    private TableColumn<Claim, Date> examDateColumn;
    @FXML
    private TableColumn<Claim, Double> claimAmountColumn;
    @FXML
    private TableColumn<Claim, ClaimStatus> statusColumn;
    @FXML
    private TableColumn<Claim, String> bankColumn;
    @FXML
    private TableColumn<Claim, String> nameColumn;
    @FXML
    private TableColumn<Claim, String> numberColumn;
    @FXML
    private TableColumn<Claim, String> documentsColumn;


    @FXML
    private TextField ID_BOX;
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        claimDateColumn.setCellValueFactory(new PropertyValueFactory<>("claimDate"));
        insuredPersonColumn.setCellValueFactory(new PropertyValueFactory<>("insuredPerson"));
        cardNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        examDateColumn.setCellValueFactory(new PropertyValueFactory<>("examDate"));
        claimAmountColumn.setCellValueFactory(new PropertyValueFactory<>("claimAmount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        bankColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReiveBankingInfo().getBank()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReiveBankingInfo().getName()));
        numberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReiveBankingInfo().getNumber()));
        documentsColumn.setCellValueFactory(new PropertyValueFactory<>("documents"));


    }
    public void handleSearchButtonAction() {
        String id = ID_BOX.getText();
        Optional<Claim> claim = this.claimService.getClaim(id);
        if (claim != null) {
            displayClaim(claim);
        } else {
            System.out.println("Claim not found");
        }
    }
    public void displayClaim(Optional<Claim> claimOptional) {
        ObservableList<Claim> data = FXCollections.observableArrayList();
        if (claimOptional.isPresent()) {
            data.add(claimOptional.get());
        }
        tableView.setItems(data);
    }

    public void Viewall() {
        List<Claim> claims = claimService.getAllClaims();
        ObservableList<Claim> data = FXCollections.observableArrayList(claims);
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