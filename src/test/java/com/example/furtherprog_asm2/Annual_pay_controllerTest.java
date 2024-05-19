package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.scene.control.TextField;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Annual_pay_controllerTest {
    private Annual_pay_controller annualPayController;

    @Mock
    private DependentDAO<Dependent> mockDependentDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        annualPayController = new Annual_pay_controller();
        annualPayController.setDependentDAO(mockDependentDAO);
        annualPayController.setAnnual_pay(new TextField());
    }

    @Test
    public void testCalculate() {
        // Mocking data
        Dependent dependent1 = new Dependent("1", "John Doe", "1234567890", "john@example.com", "Address1", "password1", null, new ArrayList<>());
        Dependent dependent2 = new Dependent("2", "Jane Doe", "0987654321", "jane@example.com", "Address2", "password2", null, new ArrayList<>());
        List<Dependent> dependents = new ArrayList<>();
        dependents.add(dependent1);
        dependents.add(dependent2);
        when(mockDependentDAO.getAll()).thenReturn(dependents);
        annualPayController.Calculate();
        verify(mockDependentDAO, times(1)).getAll();
        assertEquals("1000.00", annualPayController.getAnnual_pay().getText());
    }
}