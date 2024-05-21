package com.example.furtherprog_asm2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PolicyOwnerDao_IMPTest {
    private Connection connectionMock;
    private PolicyOwnerDao_IMP policyOwnerDaoImp;
    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        policyOwnerDaoImp = new PolicyOwnerDao_IMP();
    }
    @Test
    void getAllSuccess() throws SQLException{
        List<PolicyOwner> policyOwners = policyOwnerDaoImp.getAll();
        assertEquals(5, policyOwners.size());
    }
    @Test
    void getAllPolicyOwnerDaoIsEmpty() throws SQLException{
        List<PolicyOwner> policyOwners = policyOwnerDaoImp.getAll();
        assertFalse(policyOwners.isEmpty());
    }

    @Test
    void getPolicyOwnerDaoByIdSuccess() {
        Optional<PolicyOwner> optionalPolicyOwnerDao = policyOwnerDaoImp.get("PO-0239295313");
        assertTrue(optionalPolicyOwnerDao.isPresent());
        PolicyOwner PolicyOwner = optionalPolicyOwnerDao.get();
        assertEquals("PO-0239295313", PolicyOwner.getId());
    }
    @Test
    void getPolicyOwnerDaoByCardNumberNotFound() {
        Optional<PolicyOwner> optionalPolicyOwnerDao = policyOwnerDaoImp.get("nonexistent");
        assertTrue(optionalPolicyOwnerDao.isEmpty());
    }
    @Test
    void addSuccess() throws SQLException {
        PolicyOwner PolicyOwner = new PolicyOwner();
        PolicyOwner.setId("PO-0000000111");
        PolicyOwner.setName("PolicyOwnerName");
        PolicyOwner.setPhone("PolicyOwnerPhone");
        PolicyOwner.setEmail("PolicyOwner@gmail.com");
        PolicyOwner.setAddress("Hà Nội");
        PolicyOwner.setPassword("PolicyOwnerPassword");
        boolean result = policyOwnerDaoImp.add(PolicyOwner);
        assertTrue(result);
        policyOwnerDaoImp.delete(PolicyOwner);
    }
    @Test
    void addFail()  {
        PolicyOwner PolicyOwner = new PolicyOwner();
        PolicyOwner.setId("PO-0239212313");
        PolicyOwner.setName("PolicyOwnerName");
        PolicyOwner.setPhone("PolicyOwnerPhone");
        PolicyOwner.setEmail("PolicyOwner@gmail.com");
        PolicyOwner.setAddress("Hà Nội");
        PolicyOwner.setPassword("PolicyOwnerPassword");
        Boolean result = policyOwnerDaoImp.add(PolicyOwner);
        assertFalse(result, "Add should fail for existing id");
    }

    @Test
    void updateSuccess() throws SQLException {
        PolicyOwner PolicyOwner = new PolicyOwner();
        PolicyOwner.setId("PO-0000000111");
        PolicyOwner.setName("PolicyOwnerName2");
        PolicyOwner.setPhone("PolicyOwnerPhone2");
        PolicyOwner.setEmail("PolicyOwner2@gmail.com");
        PolicyOwner.setAddress("Hà Nội2");
        PolicyOwner.setPassword("PolicyOwnerPassword2");
        policyOwnerDaoImp.add(PolicyOwner);
        boolean result = policyOwnerDaoImp.update(PolicyOwner);
        assertTrue(result);
        policyOwnerDaoImp.delete(PolicyOwner);

    }
    @Test
    void updateFail()  {
        PolicyOwner PolicyOwner = new PolicyOwner();
        PolicyOwner.setId("PO-0000000111aAAAAA");
        PolicyOwner.setName("PolicyOwnerName2");
        PolicyOwner.setPhone("PolicyOwnerPhone2");
        PolicyOwner.setEmail("PolicyOwner2@gmail.com");
        PolicyOwner.setAddress("Hà Nội2");
        PolicyOwner.setPassword("PolicyOwnerPassword2");
        Boolean result = policyOwnerDaoImp.update(PolicyOwner);

        assertFalse(result, "Update should fail for non-existing id");

    }
    @Test
    void deleteSucces() {
        PolicyOwner PolicyOwner = new PolicyOwner();
        PolicyOwner.setId("PO-0000000111");
        PolicyOwner.setName("PolicyOwnerName2");
        PolicyOwner.setPhone("PolicyOwnerPhone2");
        PolicyOwner.setEmail("PolicyOwner2@gmail.com");
        PolicyOwner.setAddress("Hà Nội2");
        PolicyOwner.setPassword("PolicyOwnerPassword2");
        policyOwnerDaoImp.add(PolicyOwner);
        boolean result = policyOwnerDaoImp.delete(PolicyOwner);
        assertTrue(result);
    }
    @Test
    void deleteNotFound() {
        PolicyOwner PolicyOwner = new PolicyOwner();
        PolicyOwner.setId("123as_1111");
        boolean result = policyOwnerDaoImp.delete(PolicyOwner);
        assertFalse(result);
    }

}