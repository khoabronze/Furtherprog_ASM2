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
    void getAllSuccess() throws SQLException{
        List<InsuranceManager> InsuranceManagers = insuranceManagerDao.getAll();
        assertEquals(5, InsuranceManagers.size());
    }
    @Test
    void getAllinsuranceManagerDaoIsEmpty() throws SQLException{
        List<InsuranceManager> insuranceManagerDaos = insuranceManagerDao.getAll();
        assertFalse(insuranceManagerDaos.isEmpty());
    }
    @Test
    void getAllinsuranceManagerDaoThrowsExceoption() throws SQLException{
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            insuranceManagerDao.getAll();
        });
        assertEquals(thrown.getMessage(),"SQL not connect" );
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
    }
    @Test
    void addFail() throws SQLException {
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("im-12332145");
        insuranceManager.setName("insurance");
        insuranceManager.setPhone("insurance12");
        insuranceManager.setEmail("insurance@gmail.com");
        insuranceManager.setAddress("Hà Nội");
        insuranceManager.setPassword("insuranceManager123");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            insuranceManagerDao.add(insuranceManager);
        });
        assertEquals("Create fail", thrown.getMessage());
    }

    @Test
    void updateSuccess() throws SQLException {
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("im-12332145");
        insuranceManager.setPhone("insuranceManager12333");
        insuranceManager.setEmail("insuranceManager3333@gmail.com");
        insuranceManager.setAddress("Hà Nội 3");
        insuranceManager.setPassword("insuranceManager1233333");
        boolean result = insuranceManagerDao.update(insuranceManager);
        assertTrue(result);

    }
    @Test
    void updateFail() throws SQLException {

        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("im-12332145");
        insuranceManager.setPhone("insurance444");
        insuranceManager.setEmail("insurance4444@gmail.com");
        insuranceManager.setAddress("Hà Nội 4");
        insuranceManager.setPassword("insurance444");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            insuranceManagerDao.update(insuranceManager);
        });
        assertEquals("Update fail", thrown.getMessage());

    }
    @Test
    void deleteSucces() {
        InsuranceManager insuranceManager = new InsuranceManager();
        insuranceManager.setId("im-12332145");
        insuranceManager.setPhone("insurance444");
        insuranceManager.setEmail("insurance4444@gmail.com");
        insuranceManager.setAddress("Hà Nội 4");
        insuranceManager.setPassword("insurance444");
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
    @Test
    void deleteClaimException() {
        InsuranceManager insuranceManager = null;
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            insuranceManagerDao.delete(insuranceManager);
        });
        assertEquals("Error deleting claim", thrown.getMessage());

    }



}