package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PolicyHolderDAO_IMPTest {
    private Connection connectionMock;
    private PolicyHolderDAO_IMP policyHolderDAOImp;
    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        policyHolderDAOImp = new PolicyHolderDAO_IMP();
    }
    @Test
    void getAllSuccess() throws SQLException{
        List<PolicyHolder> policyHolders = policyHolderDAOImp.getAll();
        assertEquals(5, policyHolders.size());
    }
    @Test
    void getAllPolicyHolderDaoIsEmpty() throws SQLException{
        List<PolicyHolder> policyHolders = policyHolderDAOImp.getAll();
        assertFalse(policyHolders.isEmpty());
    }

    @Test
    void getPolicyHolderDaoByIdSuccess() {
        Optional<PolicyHolder> optionalPolicyHolderDao = policyHolderDAOImp.get("PH-3129429429");
        assertTrue(optionalPolicyHolderDao.isPresent());
        PolicyHolder PolicyHolder = optionalPolicyHolderDao.get();
        assertEquals("PH-3129429429", PolicyHolder.getId());
    }
    @Test
    void getPolicyHolderDaoByCardNumberNotFound() {
        Optional<PolicyHolder> optionalPolicyHolderDao = policyHolderDAOImp.get("nonexistent");
        assertTrue(optionalPolicyHolderDao.isEmpty());
    }
    @Test
    void addSuccess() throws SQLException {
        PolicyHolder PolicyHolder = new PolicyHolder();
        PolicyHolder.setId("PH-399999999");
        PolicyHolder.setName("PolicyHolderName");
        PolicyHolder.setPhone("PolicyHolderPhone");
        PolicyHolder.setEmail("PolicyHolder@gmail.com");
        PolicyHolder.setAddress("Hà Nội");
        PolicyHolder.setPassword("PolicyHolderPassword");
        boolean result = policyHolderDAOImp.add(PolicyHolder);
        assertTrue(result);
        policyHolderDAOImp.delete(PolicyHolder);
    }
    @Test
    void addFail() {
        PolicyHolder policyHolder = new PolicyHolder();
        policyHolder.setId("PH-3900309429"); // This id should already exist in the database
        policyHolder.setName("Test Name");
        policyHolder.setPhone("1234567890");
        policyHolder.setEmail("test@example.com");
        policyHolder.setAddress("Test Address");
        policyHolder.setPassword("testPassword");

        boolean result = policyHolderDAOImp.add(policyHolder);

        assertFalse(result, "Add should fail for existing id");
    }
    @Test
    void updateSuccess() throws SQLException {
        // Add a new PolicyHolder to the database
        PolicyHolder newPolicyHolder = new PolicyHolder();
        newPolicyHolder.setId("PH-12332145");
        newPolicyHolder.setName("Test Name");
        newPolicyHolder.setPhone("1234567890");
        newPolicyHolder.setEmail("test@example.com");
        newPolicyHolder.setAddress("Test Address");
        newPolicyHolder.setPassword("testPassword");
        policyHolderDAOImp.add(newPolicyHolder);

        // Update the PolicyHolder
        PolicyHolder policyHolder = new PolicyHolder();
        policyHolder.setId("PH-12332145");
        policyHolder.setPhone("policyHolder12333");
        policyHolder.setEmail("policyHolder3333@gmail.com");
        policyHolder.setAddress("Hà Nội 3");
        policyHolder.setPassword("policyHolder1233333");
        boolean result = policyHolderDAOImp.update(policyHolder);
        assertTrue(result);
        policyHolderDAOImp.delete(newPolicyHolder);
    }
    @Test
    void updateFail() {
        PolicyHolder policyHolder = new PolicyHolder();
        policyHolder.setId("PH-3999999990000"); // This id should not exist in the database
        policyHolder.setName("PolicyHolderName");
        policyHolder.setPhone("PolicyHolderPhone");
        policyHolder.setEmail("PolicyHolder@gmail.com");
        policyHolder.setAddress("Hà Nội");
        policyHolder.setPassword("PolicyHolderPassword");

        Boolean result = policyHolderDAOImp.update(policyHolder);

        assertFalse(result, "Update should fail for non-existing id");
    }
    @Test
    void deleteSuccess() {
        // Add a new PolicyHolder to the database
        PolicyHolder newPolicyHolder = new PolicyHolder();
        newPolicyHolder.setId("PH-12332145");
        newPolicyHolder.setName("Test Name");
        newPolicyHolder.setPhone("1234567890");
        newPolicyHolder.setEmail("test@example.com");
        newPolicyHolder.setAddress("Test Address");
        newPolicyHolder.setPassword("testPassword");
        policyHolderDAOImp.add(newPolicyHolder);

        // Delete the PolicyHolder
        PolicyHolder policyHolder = new PolicyHolder();
        policyHolder.setId("PH-12332145");
        boolean result = policyHolderDAOImp.delete(policyHolder);
        assertTrue(result);
    }
    @Test
    void deleteNotFound() {
        PolicyHolder PolicyHolder = new PolicyHolder();
        PolicyHolder.setId("123as_1111");
        boolean result = policyHolderDAOImp.delete(PolicyHolder);
        assertFalse(result);
    }

}