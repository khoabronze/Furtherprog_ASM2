package com.example.furtherprog_asm2;

/**
 * @author <Dong Dang Khoa- s3986281>
 */



import java.util.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// Main.java
public class Main extends Application {

    //___________________running app____________________________//
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file for the login interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-Roles.fxml")); // Adjust the file name as needed
            Parent root = loader.load();

            // Set the scene and stage
            primaryStage.setTitle("Login As Customer");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //___________________running app____________________________//
    public static void main(String[] args) {
        // Create a new instance of Filereader
        //Filereader filereader = new Filereader();

        // Call the readClaimsFromFile method with the path to your file
        //HashMap<String, Claim> claims = filereader.readClaimsFromFile("claim.txt");
        //HashMap<String, InsuranceCard> insurancecards = filereader.readInsuranceCardsFromFile("insurancecard.txt");
        //InsuranceCardViewText ICview = new InsuranceCardViewText();
        //ClaimProcessViewText view = new ClaimProcessViewText();
        // ICcontroller = new InsuranceCardController(new InsuranceCard(), ICview, insurancecards);
        //ClaimProcessController controller = new ClaimProcessController(new Claim(), view, claims, new Filewriter());
        //view.setController(controller,ICcontroller);
        //view.MainMenu(controller,ICcontroller);

        Db_function db = new Db_function();
        db.connect_to_db();

        launch(args); // Launch the JavaFX application

    }
}
