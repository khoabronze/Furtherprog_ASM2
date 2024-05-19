package com.example.furtherprog_asm2;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetAllClaimControllerTest {
    @Mock
    private ClaimService claimService;

    @Mock
    private TableView<Claim> tableView;

    @Mock
    private TextField ID_BOX;

    @InjectMocks
    private GetAllClaimController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleSearchButtonAction_claimFound() {
        String id = "123";
        Optional<Claim> claimOptional = Optional.of(new Claim());
        when(ID_BOX.getText()).thenReturn(id);
        when(claimService.getClaim(id)).thenReturn(claimOptional);

        controller.handleSearchButtonAction();

        verify(controller).displayClaim(claimOptional);
    }

    @Test
    public void testHandleSearchButtonAction_claimNotFound() {
        String id = "123";
        when(ID_BOX.getText()).thenReturn(id);
        when(claimService.getClaim(id)).thenReturn(Optional.empty());

        controller.handleSearchButtonAction();
        verify(controller, never()).displayClaim(any());

    }

    @Test
    public void testViewAll() {
        List<Claim> claims = List.of(new Claim(), new Claim());
        when(claimService.getAllClaims()).thenReturn(claims);

        controller.Viewall();

        verify(tableView).setItems(any(ObservableList.class));
    }
}