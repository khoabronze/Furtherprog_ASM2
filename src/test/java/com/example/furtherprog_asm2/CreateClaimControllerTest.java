package com.example.furtherprog_asm2;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.when;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.time.LocalDate;

class CreateClaimControllerTest {
    @Mock
    private TextField Claim_ID_form;

    @Mock
    private DatePicker Claim_Date_form;

    @Mock
    private TextField Card_number_form;

    @Mock
    private DatePicker Claim_exam_date_form;

    @Mock
    private TextField Claim_IP_form;

    @Mock
    private TextField Claim_amount_form;

    @Mock
    private TextField Bank_form;

    @Mock
    private TextField Bank_name_form;

    @Mock
    private TextField Bank_number_form;

    @Mock
    private ChoiceBox<ClaimStatus> CLaim_status_form;

    @Mock
    private ClaimService claimServiceMock;

    @InjectMocks
    private CreateClaimController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller.initialize();
        Db_function dbFunction = new Db_function();
        Connection connection = dbFunction.connect_to_db();
        claimServiceMock = new ClaimService(new ClaimDAO(connection));
    }

    @Test
    public void testSubmit_invalidClaimID() {
        when(Claim_ID_form.getText()).thenReturn("123"); // Invalid claim ID
        controller.submit();
        verify(controller, times(1)).showAlert(eq("Error Dialog"), eq("Input Error"), eq("Invalid claim id. Must be 10 numbers."));
        verify(claimServiceMock, never()).submitClaim(any());
    }

    @Test
    public void testSubmit_validClaim() {
        // Simulate valid input for submission
        when(Claim_ID_form.getText()).thenReturn("1234567890");
        when(Claim_Date_form.getValue()).thenReturn(LocalDate.now());
        when(Card_number_form.getText()).thenReturn("1234567890123456");
        when(Claim_exam_date_form.getValue()).thenReturn(LocalDate.now());
        when(Claim_IP_form.getText()).thenReturn("John Doe");
        when(Claim_amount_form.getText()).thenReturn("100.00");
        when(CLaim_status_form.getValue()).thenReturn(ClaimStatus.New);
        when(Bank_form.getText()).thenReturn("Bank");
        when(Bank_name_form.getText()).thenReturn("Bank Name");
        when(Bank_number_form.getText()).thenReturn("1234567890");

        controller.submit();
        verify(claimServiceMock, times(1)).submitClaim(any());
    }

    @Test
    public void testSubmit_invalidClaimAmount() {
        // Invalid claim amount
        when(Claim_ID_form.getText()).thenReturn("1234567890");
        when(Claim_Date_form.getValue()).thenReturn(LocalDate.now());
        when(Card_number_form.getText()).thenReturn("1234567890123456");
        when(Claim_exam_date_form.getValue()).thenReturn(LocalDate.now());
        when(Claim_IP_form.getText()).thenReturn("John Doe");
        when(Claim_amount_form.getText()).thenReturn("-100.00"); // Negative claim amount
        when(CLaim_status_form.getValue()).thenReturn(ClaimStatus.Done);
        when(Bank_form.getText()).thenReturn("Bank");
        when(Bank_name_form.getText()).thenReturn("Bank Name");
        when(Bank_number_form.getText()).thenReturn("1234567890");

        controller.submit();
        verify(controller, times(1)).showAlert(eq("Error Dialog"), eq("Input Error"), eq("Claim amount cannot be negative"));
        verify(claimServiceMock, never()).submitClaim(any());
    }

    @Test
    public void testSubmit_missingClaimDate() {
        // Simulate missing claim date
        when(Claim_ID_form.getText()).thenReturn("1234567890");
        when(Claim_Date_form.getValue()).thenReturn(null); // Missing claim date
        when(Card_number_form.getText()).thenReturn("1234567890123456");
        when(Claim_exam_date_form.getValue()).thenReturn(LocalDate.now());
        when(Claim_IP_form.getText()).thenReturn("John Doe");
        when(Claim_amount_form.getText()).thenReturn("100.00");
        when(CLaim_status_form.getValue()).thenReturn(ClaimStatus.Processing);
        when(Bank_form.getText()).thenReturn("Bank");
        when(Bank_name_form.getText()).thenReturn("Bank Name");
        when(Bank_number_form.getText()).thenReturn("1234567890");

        controller.submit();
        verify(controller, times(1)).showAlert(eq("Error Dialog"), eq("Input Error"), eq("Claim date is required"));
        verify(claimServiceMock, never()).submitClaim(any());
    }


}