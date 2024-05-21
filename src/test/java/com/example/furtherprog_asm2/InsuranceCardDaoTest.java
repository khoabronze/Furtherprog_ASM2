/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class InsuranceCardDaoTest {
    private Connection connectionMock;
    private InsuranceCardDao insuranceCardDao;
    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        insuranceCardDao = new InsuranceCardDao();
    }
    @Test
    void getAllSuccess() throws SQLException{
        List<InsuranceCard> insuranceCards = insuranceCardDao.getAll();
        assertEquals(21, insuranceCards.size());
    }
    @Test
    void getAllInsuranceCardIsEmpty() throws SQLException{
        List<InsuranceCard> insuranceCards = insuranceCardDao.getAll();
        assertFalse(insuranceCards.isEmpty());
    }

    @Test
    void getInsuranceCardByCardNumberSuccess() {
        Optional<InsuranceCard> optionalInsuranceCard = insuranceCardDao.get("1234567898");
        assertTrue(optionalInsuranceCard.isPresent());
        InsuranceCard insuranceCard = optionalInsuranceCard.get();
        assertEquals("1234567898", insuranceCard.getCardNumber());
    }
    @Test
    void getInsuranceCardByCardNumberNotFound() {
        Optional<InsuranceCard> optionalInsuranceCard = insuranceCardDao.get("nonexistent");
        assertTrue(optionalInsuranceCard.isEmpty());
    }
    @Test
    void addSuccess() throws SQLException {
        InsuranceCard insuranceCard = new InsuranceCard();
        insuranceCard.setCardNumber("123as");
        insuranceCard.setCardHolder("July");
        insuranceCard.setPolicyOwner("VAT");
        insuranceCard.setExpirationDate("2024-05-10");
        boolean result = insuranceCardDao.add(insuranceCard);
        assertTrue(result);
        insuranceCardDao.delete(insuranceCard);
    }

    @Test
    void updateSuccess() throws SQLException {
        Optional<InsuranceCard> optionalInsuranceCard = insuranceCardDao.get("1234567890");
        if (optionalInsuranceCard.isPresent()) {
            InsuranceCard insuranceCard = optionalInsuranceCard.get();
            insuranceCard.setPolicyOwner("VAT1");
            boolean result = insuranceCardDao.update(insuranceCard.getCardNumber(), insuranceCard.getCardHolder(), insuranceCard.getPolicyOwner(), insuranceCard.getExpirationDate());
            assertTrue(result);
        } else {
            fail("Insurance card not found");
        }
    }
    @Test
    void updateFail() throws SQLException {
        InsuranceCard insuranceCard = new InsuranceCard();
        insuranceCard.setCardNumber("123as");
        insuranceCard.setCardHolder("July1");
        insuranceCard.setPolicyOwner("VAT1");
        insuranceCard.setExpirationDate("2024-05-11");

        boolean result = insuranceCardDao.update(insuranceCard.getCardNumber(), insuranceCard.getCardHolder(), insuranceCard.getPolicyOwner(), insuranceCard.getExpirationDate());
        assertFalse(result);

    }
    @Test
    void deleteSucces() {
        InsuranceCard insuranceCard = new InsuranceCard();
        insuranceCard.setCardNumber("123as");
        insuranceCard.setCardHolder("July");
        insuranceCard.setPolicyOwner("VAT");
        insuranceCard.setExpirationDate("2024-05-01"); // Set the day to 01
        insuranceCardDao.add(insuranceCard);
        boolean result = insuranceCardDao.delete(insuranceCard);
        assertTrue(result);
    }
    @Test
    void deleteNotFound() {
        InsuranceCard insuranceCard = new InsuranceCard();
        insuranceCard.setCardNumber("123as_1111");
        boolean result = insuranceCardDao.delete(insuranceCard);
        assertFalse(result);
    }

}
