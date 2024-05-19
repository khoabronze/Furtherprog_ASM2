package com.example.furtherprog_asm2;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.IOException;
import java.util.Optional;
import static org.mockito.Mockito.*;

class Propose_SearchClaimControllerTest {
    @Mock
    private TextField Claim_ID_Box;
    @Mock
    private Alert alert;
    @Mock
    private Button search_button;

    @Mock
    private ClaimService claimService;

    @Spy
    private Stage stage = new Stage();

    @InjectMocks
    private Propose_SearchClaimController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearch_claimFound() throws IOException {
        String claimId = "123";
        Optional<Claim> optionalClaim = Optional.of(new Claim());
        when(Claim_ID_Box.getText()).thenReturn(claimId);
        when(claimService.getClaim(claimId)).thenReturn(optionalClaim);

        controller.search();

        verify(stage).setScene(any());
    }

    @Test
    public void testSearch_claimNotFound() throws IOException {
        String claimId = "123";
        Optional<Claim> optionalClaim = Optional.empty();
        when(Claim_ID_Box.getText()).thenReturn(claimId);
        when(claimService.getClaim(claimId)).thenReturn(optionalClaim);

        controller.search();

        verify(alert, times(1)).setTitle("Error");
        verify(alert, times(1)).setHeaderText(null);
        verify(alert, times(1)).setContentText("No Claim found with the provided claim id.");
        verify(alert, times(1)).showAndWait();

        verify(Claim_ID_Box).clear();
    }

}