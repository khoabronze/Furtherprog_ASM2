/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InsuranceManager_DAOTest {
    private Connection connectionMock;
    private InsuranceManager_DAO insuranceManagerDao;

    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        insuranceManagerDao = new InsuranceManager_DAO();
    }

    @Test
    void getAllSuccess() throws SQLException {
        List<InsuranceManager> InsuranceManagers = insuranceManagerDao.getAll();
        assertEquals(5, InsuranceManagers.size());
    }

    @Test
    void getAllinsuranceManagerDaoIsEmpty() throws SQLException {
        List<InsuranceManager> insuranceManagerDaos = insuranceManagerDao.getAll();
        assertFalse(insuranceManagerDaos.isEmpty());
    }



    @Test
    void getinsuranceManagerDaoByIdSuccess() {
        Optional<InsuranceManager> optionalinsuranceManagerDao = insuranceManagerDao.get("im-1234567895");
        assertTrue(optionalinsuranceManagerDao.isPresent());
        InsuranceManager InsuranceManager = optionalinsuranceManagerDao.get();
        assertEquals("im-1234567895", InsuranceManager.getId());
    }

    @Test
    void getinsuranceManagerDaoByCardNumberNotFound() {
        Optional<InsuranceManager> optionalinsuranceManagerDao = insuranceManagerDao.get("nonexistent");
        assertTrue(optionalinsuranceManagerDao.isEmpty());
    }

    @Test
    void addSuccess() throws SQLException {
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("im-12332145");
        insuranceManager.setName("insuranceManager");
        insuranceManager.setPhone("insuranceManager12");
        insuranceManager.setEmail("insuranceManager@gmail.com");
        insuranceManager.setAddress("Hà Nội");
        insuranceManager.setPassword("insuranceManager123");
        boolean result = insuranceManagerDao.add(insuranceManager);
        assertTrue(result);
        insuranceManagerDao.delete(insuranceManager);
    }

    @Test
    void addFail() {
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("im-1234567895");
        insuranceManager.setName("Test Name");
        insuranceManager.setPhone("1234567890");
        insuranceManager.setEmail("test@example.com");
        insuranceManager.setAddress("Test Address");
        insuranceManager.setPassword("testPassword");

        boolean result = insuranceManagerDao.add(insuranceManager);

        assertFalse(result, "Add should fail for existing id");
    }

    @Test
    void updateSuccess() throws SQLException {
        // Add a new InsuranceManager to the database
        InsuranceManager newInsuranceManager = new InsuranceManager();
        newInsuranceManager.setId("im-12332145");
        newInsuranceManager.setName("Test Name");
        newInsuranceManager.setPhone("1234567890");
        newInsuranceManager.setEmail("test@example.com");
        newInsuranceManager.setAddress("Test Address");
        newInsuranceManager.setPassword("testPassword");
        insuranceManagerDao.add(newInsuranceManager);

        // Update the InsuranceManager
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("im-12332145");
        insuranceManager.setPhone("insuranceManager12333");
        insuranceManager.setEmail("insuranceManager3333@gmail.com");
        insuranceManager.setAddress("Hà Nội 3");
        insuranceManager.setPassword("insuranceManager1233333");
        boolean result = insuranceManagerDao.update(insuranceManager);
        assertTrue(result);
        insuranceManagerDao.delete(newInsuranceManager);
    }

    @Test
    void updateFail() {
        // Create an InsuranceManager with a non-existing id
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("non-existing-id");
        insuranceManager.setPhone("1234567890");
        insuranceManager.setEmail("test@example.com");
        insuranceManager.setAddress("Test Address");
        insuranceManager.setPassword("testPassword");

        // Try to update the non-existing InsuranceManager
        boolean result = insuranceManagerDao.update(insuranceManager);

        // Assert that the update method returned false
        assertFalse(result, "Update should fail for non-existing id");
    }

    @Test
    void deleteSuccess() throws SQLException {
        // Add a new InsuranceManager to the database
        InsuranceManager newInsuranceManager = new InsuranceManager();
        newInsuranceManager.setId("im-12332145");
        newInsuranceManager.setName("Test Name");
        newInsuranceManager.setPhone("1234567890");
        newInsuranceManager.setEmail("test@example.com");
        newInsuranceManager.setAddress("Test Address");
        newInsuranceManager.setPassword("testPassword");
        insuranceManagerDao.add(newInsuranceManager);

        // Delete the InsuranceManager
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("im-12332145");
        boolean result = insuranceManagerDao.delete(insuranceManager);
        assertTrue(result);
    }

    @Test
    void deleteNotFound() {
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("123as_1111");
        boolean result = insuranceManagerDao.delete(insuranceManager);
        assertFalse(result);
    }




}