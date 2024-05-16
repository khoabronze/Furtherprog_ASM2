package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GetAllClaimController {

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
}