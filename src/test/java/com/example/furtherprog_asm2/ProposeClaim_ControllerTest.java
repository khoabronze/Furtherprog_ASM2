package com.example.furtherprog_asm2;

import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
class ProposeClaim_ControllerTest {
    @Mock
    private ClaimService claimService;

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
    private Button requestForm;

    @InjectMocks
    private ProposeClaim_Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInitializeData_claimFound() {
        String claimId = "123";
        Claim claim = new Claim();
        claim.setId(claimId);
        claim.setClaimDate(Date.valueOf(LocalDate.now()));
        claim.setCardNumber("1234567890");
        claim.setExamDate(Date.valueOf(LocalDate.now()));
        claim.setInsuredPerson("John Doe");
        claim.setClaimAmount(1000.0);
        claim.setReiveBankingInfo(new BankingInfo("Bank", "Name", "123456"));
        claim.setStatus(ClaimStatus.New);

        Optional<Claim> optionalClaim = Optional.of(claim);
        when(claimService.getClaim(claimId)).thenReturn(optionalClaim);

        controller.initializeData(claimId);

        verify(Claim_ID_form).setText(claimId);
        verify(Claim_Date_form).setValue(any(LocalDate.class));
        verify(Card_number_form).setText(claim.getCardNumber());
        verify(Claim_exam_date_form).setValue(any(LocalDate.class));
        verify(Claim_IP_form).setText(claim.getInsuredPerson());
        verify(Claim_amount_form).setText(String.valueOf(claim.getClaimAmount()));
        verify(Bank_form).setText(claim.getReiveBankingInfo().getBank());
        verify(Bank_name_form).setText(claim.getReiveBankingInfo().getName());
        verify(Bank_number_form).setText(claim.getReiveBankingInfo().getNumber());
        verify(CLaim_status_form).setValue(ClaimStatus.New);

        verify(Claim_ID_form).setEditable(false);
        verify(Claim_Date_form).setDisable(true);
        verify(Card_number_form).setEditable(false);
        verify(Claim_exam_date_form).setDisable(true);
        verify(Claim_IP_form).setEditable(false);
        verify(Claim_amount_form).setEditable(false);
        verify(Bank_form).setEditable(false);
        verify(Bank_name_form).setEditable(false);
        verify(Bank_number_form).setEditable(false);
        verify(CLaim_status_form).setDisable(true);
    }

    @Test
    public void testHandleRequestForm() throws Exception {
        Stage stage = mock(Stage.class);
        when(requestForm.getScene()).thenReturn(mock(javafx.scene.Scene.class));
        when(requestForm.getScene().getWindow()).thenReturn(stage);

        controller.handleRequestForm();

        verify(stage).setScene(any(javafx.scene.Scene.class));
    }

}