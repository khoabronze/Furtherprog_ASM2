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
    void getAllPolicyHolderDaoThrowsExceoption() throws SQLException{
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            policyHolderDAOImp.getAll();
        });
        assertEquals(thrown.getMessage(),"SQL not connect" );
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
    }
    @Test
    void addFail() throws SQLException {
        PolicyHolder PolicyHolder = new PolicyHolder();
        PolicyHolder.setId("PH-399999999");
        PolicyHolder.setName("PolicyHolderName1");
        PolicyHolder.setPhone("PolicyHolderPhone1");
        PolicyHolder.setEmail("PolicyHolder1@gmail.com");
        PolicyHolder.setAddress("Hà Nội1");
        PolicyHolder.setPassword("PolicyHolderPassword1");
        SQLException thrown = assertThrows(SQLException.class, () -> {
            policyHolderDAOImp.add(PolicyHolder);
        });
        assertEquals("Create fail", thrown.getMessage());
    }

    @Test
    void updateSuccess() throws SQLException {
        PolicyHolder PolicyHolder = new PolicyHolder();
        PolicyHolder.setId("PH-399999999");
        PolicyHolder.setName("Policy");
        PolicyHolder.setPhone("0123456789");
        PolicyHolder.setEmail("Policy@gmail.com");
        PolicyHolder.setAddress("Hà Nội 1");
        PolicyHolder.setPassword("Password");
        boolean result = policyHolderDAOImp.update(PolicyHolder);
        assertTrue(result);

    }
    @Test
    void updateFail() throws SQLException {
        PolicyHolder PolicyHolder = new PolicyHolder();
        PolicyHolder.setId("PH-3999999990000");
        PolicyHolder.setName("PolicyHolderName");
        PolicyHolder.setPhone("PolicyHolderPhone");
        PolicyHolder.setEmail("PolicyHolder@gmail.com");
        PolicyHolder.setAddress("Hà Nội");
        PolicyHolder.setPassword("PolicyHolderPassword");
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            policyHolderDAOImp.update(PolicyHolder);
        });
        assertEquals("Error updating PolicyHolder", thrown.getMessage());

    }
    @Test
    void deleteSucces() {
        PolicyHolder PolicyHolder = new PolicyHolder();
        PolicyHolder.setId("PH-399999999");
        PolicyHolder.setName("PolicyHolderName");
        PolicyHolder.setPhone("PolicyHolderPhone");
        PolicyHolder.setEmail("PolicyHolder@gmail.com");
        PolicyHolder.setAddress("Hà Nội");
        PolicyHolder.setPassword("PolicyHolderPassword");
        boolean result = policyHolderDAOImp.delete(PolicyHolder);
        assertTrue(result);
    }
    @Test
    void deleteNotFound() {
        PolicyHolder PolicyHolder = new PolicyHolder();
        PolicyHolder.setId("123as_1111");
        boolean result = policyHolderDAOImp.delete(PolicyHolder);
        assertFalse(result);
    }
    @Test
    void deleteException() {
        PolicyHolder PolicyHolder = null;
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            policyHolderDAOImp.delete(PolicyHolder);
        });
        assertEquals("Error deleting PolicyHolder", thrown.getMessage());

    }
}