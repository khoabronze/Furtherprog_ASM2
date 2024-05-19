package com.example.furtherprog_asm2;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


class DeleteClaimControllerTest {
    @Mock
    private TextField ID_DELETE_BOX;

    @Mock
    private ClaimService claimService;

    @Mock
    private Alert alert;

    @InjectMocks
    private DeleteClaimController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDelete_emptyID() {
        when(ID_DELETE_BOX.getText()).thenReturn("");
        controller.Delete();
        verify(claimService, never()).getClaim(anyString());
    }

    @Test
    public void testDelete_claimFound() {
        String id = "123";
        when(ID_DELETE_BOX.getText()).thenReturn(id);

        Claim claim = new Claim();
        when(claimService.getClaim(id)).thenReturn(Optional.of(claim));

        controller.Delete();

        verify(claimService, times(1)).deleteClaim(claim);
        verify(alert, times(1)).setTitle("Claim Deleted");
        verify(alert, times(1)).setContentText("The claim has been deleted successfully.");
        verify(alert, times(1)).showAndWait();
    }

    @Test
    public void testDelete_claimNotFound() {
        String id = "123";
        when(ID_DELETE_BOX.getText()).thenReturn(id);

        when(claimService.getClaim(id)).thenReturn(Optional.empty());

        controller.Delete();

        verify(alert, times(1)).setTitle("Claim Not Found");
        verify(alert, times(1)).setContentText("No claim with the provided ID was found.");
        verify(alert, times(1)).showAndWait();
    }
}