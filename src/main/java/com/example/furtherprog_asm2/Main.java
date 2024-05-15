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

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Claim-management-system.fxml"));
        primaryStage.setTitle("Claim Management System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

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
        launch(args);

    }
}
