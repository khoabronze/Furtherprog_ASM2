package com.example.furtherprog_asm2;


import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Arrays;
import java.util.List;
class Search_ControllerTest{
    @Mock
    private TextField cardNumberInput;

    @Mock
    private TableView<InsuranceCard> tableView;

    @Mock
    private TableColumn<InsuranceCard, String> cardNumber;

    @Mock
    private TableColumn<InsuranceCard, String> cardHolder;

    @Mock
    private TableColumn<InsuranceCard, String> policyOwner;

    @Mock
    private TableColumn<InsuranceCard, String> expirationDate;

    @Mock
    private Button search;

    @Mock
    private Button viewAll;

    @InjectMocks
    private Search_Controller controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearch_CardFound() {
        String cardNumberData = "1234567890";
        when(cardNumberInput.getText()).thenReturn(cardNumberData);

        InsuranceCardDao insuranceCardDao = mock(InsuranceCardDao.class);
        when(insuranceCardDao.getOne(cardNumberData)).thenReturn(new InsuranceCard(cardNumberData, "John Doe", "Jane Doe", "2024-12-31"));

        controller.search(new ActionEvent());

        verify(tableView).getItems().clear();
        verify(tableView).getItems().add(any(InsuranceCard.class));
        verify(cardNumberInput).clear();
    }

    @Test
    public void testSearch_CardNotFound() {
        String cardNumberData = "1234567890";
        when(cardNumberInput.getText()).thenReturn(cardNumberData);

        InsuranceCardDao insuranceCardDao = mock(InsuranceCardDao.class);
        when(insuranceCardDao.getOne(cardNumberData)).thenReturn(null);

        controller.search(new ActionEvent());

        verify(tableView, never()).getItems().clear();
        verify(tableView, never()).getItems().add(any(InsuranceCard.class));
        verify(cardNumberInput).clear();
    }

    @Test
    public void testViewAll() {
        InsuranceCard card1 = new InsuranceCard("1234567890", "John Doe", "Jane Doe", "2024-12-31");
        InsuranceCard card2 = new InsuranceCard("0987654321", "Alice", "Bob", "2023-06-15");
        List<InsuranceCard> cardList = Arrays.asList(card1, card2);

        InsuranceCardDao insuranceCardDao = mock(InsuranceCardDao.class);
        when(insuranceCardDao.getAll()).thenReturn(cardList);

        ObservableList<InsuranceCard> observableList = mock(ObservableList.class);
        when(tableView.getItems()).thenReturn(observableList);

        controller.viewAll(new ActionEvent());

        verify(tableView).getItems().clear();
        verify(tableView).getItems().addAll(cardList);
    }
}