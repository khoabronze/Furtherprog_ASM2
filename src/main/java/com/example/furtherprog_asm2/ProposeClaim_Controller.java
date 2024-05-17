package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class ProposeClaim_Controller {
    private String documentName; // new field to hold the name of the uploaded document
    @FXML
    private TextField Claim_ID_form;

    @FXML
    private DatePicker Claim_Date_form;

    @FXML
    private TextField Card_number_form;

    @FXML
    private DatePicker Claim_exam_date_form;

    @FXML
    private TextField Claim_IP_form;

    @FXML
    private TextField Claim_amount_form;

    @FXML
    private TextField Bank_form;

    @FXML
    private TextField Bank_name_form;

    @FXML
    private TextField Bank_number_form;

    @FXML
    private ChoiceBox<ClaimStatus> CLaim_status_form;
    private ClaimService claimService;
    private String claimIdData;

    public ProposeClaim_Controller() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimService = new ClaimService(new ClaimDAO(connection));
    }

    public void chooseFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            documentName = selectedFile.getName(); // store the name of the file
        }
    }

    public void initializeData(String claimIdData) {
        this.claimIdData = claimIdData;
        Claim_ID_form.setEditable(false);
        Claim_Date_form.setEditable(false);
        Card_number_form.setEditable(false);
        Claim_exam_date_form.setEditable(false);
        Claim_IP_form.setEditable(false);
        Claim_amount_form.setEditable(false);
        Bank_form.setEditable(false);
        Bank_name_form.setEditable(false);
        Bank_number_form.setEditable(false);
        CLaim_status_form.setDisable(true); // ChoiceBox does not have setEditable method, use setDisable instead
    }

    public void handleRequestForm() throws IOException {

    }
}
