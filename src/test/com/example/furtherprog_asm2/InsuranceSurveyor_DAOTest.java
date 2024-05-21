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
    void getAllInsuranceSurveyorDaoThrowsExceoption() throws SQLException{
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            insuranceSurveyorDao.getAll();
        });
        assertEquals(thrown.getMessage(),"SQL not connect" );
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
    }
    @Test
    void addFail() throws SQLException {
        InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
        insuranceSurveyor.setId("is-987656789");
        insuranceSurveyor.setName("InsuranceSurveyor");
        insuranceSurveyor.setPhone("InsuranceSurveyor12");
        insuranceSurveyor.setEmail("InsuranceSurveyor@gmail.com");
        insuranceSurveyor.setAddress("Hà Nội");
        insuranceSurveyor.setPassword("InsuranceSurveyor123");
        SQLException thrown = assertThrows(SQLException.class, () -> {
            insuranceSurveyorDao.add(insuranceSurveyor);
        });
        assertEquals("Create fail", thrown.getMessage());
    }

    @Test
    void updateSuccess() throws SQLException {
        InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
        insuranceSurveyor.setId("is-987656789");
        insuranceSurveyor.setPhone("Insurance");
        insuranceSurveyor.setEmail("Insurance@gmail.com");
        insuranceSurveyor.setAddress("Hà Nội 1");
        insuranceSurveyor.setPassword("Insurance123");
        boolean result = insuranceSurveyorDao.update(insuranceSurveyor);
        assertTrue(result);

    }
    @Test
    void updateFail() throws SQLException {
        InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
        insuranceSurveyor.setId("is-987656789aa");
        insuranceSurveyor.setPhone("Insurance");
        insuranceSurveyor.setEmail("Insurance@gmail.com");
        insuranceSurveyor.setAddress("Hà Nội 1");
        insuranceSurveyor.setPassword("Insurance123");
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            insuranceSurveyorDao.update(insuranceSurveyor);
        });
        assertEquals("Error updating InsuranceSurveyor", thrown.getMessage());

    }
    @Test
    void deleteSucces() {
        InsuranceSurveyor InsuranceSurveyor = new InsuranceSurveyor();
        InsuranceSurveyor.setId("is-987656789");
        InsuranceSurveyor.setPhone("insurance444");
        InsuranceSurveyor.setEmail("insurance4444@gmail.com");
        InsuranceSurveyor.setAddress("Hà Nội 4");
        InsuranceSurveyor.setPassword("insurance444");
        boolean result = insuranceSurveyorDao.delete(InsuranceSurveyor);
        assertTrue(result);
    }
    @Test
    void deleteNotFound() {
        InsuranceSurveyor InsuranceSurveyor = new InsuranceSurveyor();
        InsuranceSurveyor.setId("123as_1111");
        boolean result = insuranceSurveyorDao.delete(InsuranceSurveyor);
        assertFalse(result);
    }
    @Test
    void deleteException() {
        InsuranceSurveyor InsuranceSurveyor = null;
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            insuranceSurveyorDao.delete(InsuranceSurveyor);
        });
        assertEquals("Error deleting InsuranceSurveyor", thrown.getMessage());

    }

}