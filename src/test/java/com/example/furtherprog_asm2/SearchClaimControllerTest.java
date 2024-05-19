package com.example.furtherprog_asm2;

import static org.junit.jupiter.api.Assertions.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

import static org.mockito.Mockito.*;
class SearchClaimControllerTest {
    @Mock
    private TextField Claim_ID_Box;

    @Mock
    private Button search_button;

    @Mock
    private ClaimService claimService;

    @InjectMocks
    private SearchClaimController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearch_ClaimFound() throws IOException {
        String claimId = "123456";
        when(Claim_ID_Box.getText()).thenReturn(claimId);

        Claim claim = new Claim();
        Optional<Claim> optionalClaim = Optional.of(claim);
        when(claimService.getClaim(claimId)).thenReturn(optionalClaim);

        FXMLLoader loader = mock(FXMLLoader.class);
        when(loader.load()).thenReturn(mock(Parent.class));

        UpdateClaimController updateClaimController = mock(UpdateClaimController.class);
        when(loader.getController()).thenReturn(updateClaimController);

        Stage currentStage = mock(Stage.class);
        Scene newScene = mock(Scene.class);
        when(newScene.getWindow()).thenReturn(currentStage);

        when(search_button.getScene()).thenReturn(newScene);

        controller.search();

        verify(updateClaimController).initializeData(claimId);
        verify(currentStage).setScene(newScene);
    }

    @Test
    public void testSearch_ClaimNotFound() throws IOException {
        String claimId = "123456";
        when(Claim_ID_Box.getText()).thenReturn(claimId);
        Optional<Claim> optionalClaim = Optional.empty();
        when(claimService.getClaim(claimId)).thenReturn(optionalClaim);
        try (MockedConstruction<Alert> mockedConstruction = Mockito.mockConstruction(Alert.class)) {
            controller.search();

            Alert alert = mockedConstruction.constructed().get(0);
            verify(alert).setTitle("Error");
            verify(alert).setHeaderText(null);
            verify(alert).setContentText("No Claim found with the provided claim id.");
            verify(alert).showAndWait();
        }
        verify(Claim_ID_Box).clear();
    }

}