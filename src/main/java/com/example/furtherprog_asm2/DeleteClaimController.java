package com.example.furtherprog_asm2;

import com.example.furtherprog_asm2.Claim;
import com.example.furtherprog_asm2.ClaimDAO;
import com.example.furtherprog_asm2.ClaimService;
import com.example.furtherprog_asm2.Db_function;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.util.Optional;

public class DeleteClaimController {
    @FXML
    private TextField ID_DELETE_BOX;

    private ClaimService claimService;

    public DeleteClaimController() {
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        this.claimService = new ClaimService(new ClaimDAO(connection));
    }

    public void Delete() {
        String id = ID_DELETE_BOX.getText();
        if (id.isEmpty()) {
            return;
        }
        Optional<Claim> optionalClaim = claimService.getClaim(id);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            claimService.deleteClaim(claim);

            // Show a notification
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Claim Deleted");
            alert.setHeaderText(null);
            alert.setContentText("The claim has been deleted successfully.");
            alert.showAndWait();
        } else {
            // Show a different notification
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Claim Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No claim with the provided ID was found.");
            alert.showAndWait();
        }
    }
    }