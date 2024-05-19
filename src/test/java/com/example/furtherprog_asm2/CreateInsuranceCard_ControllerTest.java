package com.example.furtherprog_asm2;


import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import javafx.scene.control.Button;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
class CreateInsuranceCard_ControllerTest {
    @Mock
    private TextField cardNumber;

    @Mock
    private TextField cardHolder;

    @Mock
    private TextField policyOwner;

    @Mock
    private DatePicker expirationDate;

    @Mock
    private Button submit;

    @Mock
    private InsuranceCardDao insuranceCardDao;

    @InjectMocks
    private CreateInsuranceCard_Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSubmit_invalidCardNumber() {
        when(cardNumber.getText()).thenReturn("123");
        when(cardHolder.getText()).thenReturn("John Doe");
        when(policyOwner.getText()).thenReturn("Jane Doe");
        when(expirationDate.getValue()).thenReturn(LocalDate.now());

        controller.submit(new ActionEvent());

        verify(cardNumber).getText();
        verify(cardHolder, never()).getText();
        verify(policyOwner, never()).getText();
        verify(expirationDate, never()).getValue();
    }

    @Test
    public void testSubmit_validCardDetails() {
        when(cardNumber.getText()).thenReturn("1234567890");
        when(cardHolder.getText()).thenReturn("John Doe");
        when(policyOwner.getText()).thenReturn("Jane Doe");
        when(expirationDate.getValue()).thenReturn(LocalDate.now());
        when(insuranceCardDao.add(any(InsuranceCard.class))).thenReturn(true);
        controller.submit(new ActionEvent());
        verify(cardNumber).getText();
        verify(cardHolder).getText();
        verify(policyOwner).getText();
        verify(expirationDate).getValue();
        verify(insuranceCardDao).add(any(InsuranceCard.class));
    }
    @Test
    public void testSubmit_insuranceCardDaoFailure() {
        when(cardNumber.getText()).thenReturn("1234567890");
        when(cardHolder.getText()).thenReturn("John Doe");
        when(policyOwner.getText()).thenReturn("Jane Doe");
        when(expirationDate.getValue()).thenReturn(LocalDate.now());

        when(insuranceCardDao.add(any(InsuranceCard.class))).thenReturn(false);

        controller.submit(new ActionEvent());

        verify(cardNumber).getText();
        verify(cardHolder).getText();
        verify(policyOwner).getText();
        verify(expirationDate).getValue();
        verify(insuranceCardDao).add(any(InsuranceCard.class));
    }
}