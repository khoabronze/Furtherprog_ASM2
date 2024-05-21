package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InsuranceSurveyor_DAOTest {
    private Connection connectionMock;
    private InsuranceSurveyor_DAO insuranceSurveyorDao;
    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        insuranceSurveyorDao = new InsuranceSurveyor_DAO();
    }
    @Test
    void getAllSuccess() throws SQLException{
        List<InsuranceSurveyor> insuranceSurveyors = insuranceSurveyorDao.getAll();
        assertEquals(5, insuranceSurveyors.size());
    }
    @Test
    void getAllInsuranceSurveyorDaoIsEmpty() throws SQLException{
        List<InsuranceSurveyor> insuranceSurveyors = insuranceSurveyorDao.getAll();
        assertFalse(insuranceSurveyors.isEmpty());
    }

    @Test
    void getInsuranceSurveyorDaoByIdSuccess() {
        Optional<InsuranceSurveyor> optionalInsuranceSurveyorDao = insuranceSurveyorDao.get("is-97867534457");
        assertTrue(optionalInsuranceSurveyorDao.isPresent());
        InsuranceSurveyor InsuranceSurveyor = optionalInsuranceSurveyorDao.get();
        assertEquals("is-97867534457", InsuranceSurveyor.getId());
    }
    @Test
    void getInsuranceSurveyorDaoByCardNumberNotFound() {
        Optional<InsuranceSurveyor> optionalInsuranceSurveyorDao = insuranceSurveyorDao.get("nonexistent");
        assertTrue(optionalInsuranceSurveyorDao.isEmpty());
    }
    @Test
    void addSuccess() throws SQLException {
        InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
        insuranceSurveyor.setId("is-987656789");
        insuranceSurveyor.setName("InsuranceSurveyor");
        insuranceSurveyor.setPhone("InsuranceSurveyor12");
        insuranceSurveyor.setEmail("InsuranceSurveyor@gmail.com");
        insuranceSurveyor.setAddress("Hà Nội");
        insuranceSurveyor.setPassword("InsuranceSurveyor123");
        boolean result = insuranceSurveyorDao.add(insuranceSurveyor);
        assertTrue(result);
        insuranceSurveyorDao.delete(insuranceSurveyor);
    }
    @Test
    void addFail() {
        // Create an InsuranceSurveyor that already exists in the database
        InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
        insuranceSurveyor.setId("is-8694207534"); // This id should already exist in the database
        insuranceSurveyor.setName("InsuranceSurveyor");
        insuranceSurveyor.setPhone("InsuranceSurveyor12");
        insuranceSurveyor.setEmail("InsuranceSurveyor@gmail.com");
        insuranceSurveyor.setAddress("Hà Nội");
        insuranceSurveyor.setPassword("InsuranceSurveyor123");

        // Try to add the existing InsuranceSurveyor
        boolean result = insuranceSurveyorDao.add(insuranceSurveyor);

        // Assert that the add method returned false
        assertFalse(result, "Add should fail for existing id");
    }

    @Test
    void updateSuccess() {
        // Create an InsuranceSurveyor
        InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
        insuranceSurveyor.setId("is-9876567891");
        insuranceSurveyor.setPhone("Insurance");
        insuranceSurveyor.setEmail("Insurance@gmail.com");
        insuranceSurveyor.setAddress("Hà Nội");
        insuranceSurveyor.setPassword("Insurance123");

        // Add the InsuranceSurveyor to the database
        boolean addResult = insuranceSurveyorDao.add(insuranceSurveyor);
        assertTrue(addResult, "Add should succeed for new id");

        // Update the InsuranceSurveyor in the database
        insuranceSurveyor.setPhone("UpdatedInsurance");
        insuranceSurveyor.setEmail("UpdatedInsurance@gmail.com");
        insuranceSurveyor.setAddress("Updated Hà Nội");
        insuranceSurveyor.setPassword("UpdatedInsurance123");
        boolean updateResult = insuranceSurveyorDao.update(insuranceSurveyor);

        // Assert that the update method returned true
        assertTrue(updateResult, "Update should succeed for existing id");
        insuranceSurveyorDao.delete(insuranceSurveyor);
    }
    @Test
    void updateFail() {
        // Create an InsuranceSurveyor with a non-existing id
        InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
        insuranceSurveyor.setId("non-existing-id");
        insuranceSurveyor.setPhone("1234567890");
        insuranceSurveyor.setEmail("test@example.com");
        insuranceSurveyor.setAddress("Test Address");
        insuranceSurveyor.setPassword("testPassword");

        // Try to update the non-existing InsuranceSurveyor
        boolean result = insuranceSurveyorDao.update(insuranceSurveyor);

        // Assert that the update method returned false
        assertFalse(result, "Update should fail for non-existing id");
    }
    @Test
    void deleteSuccess() {
        // Create an InsuranceSurveyor
        InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
        insuranceSurveyor.setId("is-987656789");
        insuranceSurveyor.setPhone("insurance444");
        insuranceSurveyor.setEmail("insurance4444@gmail.com");
        insuranceSurveyor.setAddress("Hà Nội 4");
        insuranceSurveyor.setPassword("insurance444");

        // Add the InsuranceSurveyor to the database
        boolean addResult = insuranceSurveyorDao.add(insuranceSurveyor);
        assertTrue(addResult, "Add should succeed for new id");

        // Delete the InsuranceSurveyor from the database
        boolean deleteResult = insuranceSurveyorDao.delete(insuranceSurveyor);

        // Assert that the delete method returned true
        assertTrue(deleteResult, "Delete should succeed for existing id");
    }
    @Test
    void deleteNotFound() {
        InsuranceSurveyor InsuranceSurveyor = new InsuranceSurveyor();
        InsuranceSurveyor.setId("123as_1111");
        boolean result = insuranceSurveyorDao.delete(InsuranceSurveyor);
        assertFalse(result);
    }


}