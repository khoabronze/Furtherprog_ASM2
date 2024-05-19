package com.example.furtherprog_asm2;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ClaimDAOTest {
    private Connection connectionMock;
    private ClaimDAO claimDAO;



    @BeforeEach
    void setUp() throws SQLException {
        connectionMock = mock(Connection.class);
        claimDAO = new ClaimDAO(connectionMock);
    }
    @Test
    void addSuccess() throws SQLException {

        Claim claim = new Claim();
        claim.setId("55");
        claim.setClaimDate(new java.util.Date());
        claim.setInsuredPerson("John Doe");
        claim.setCardNumber("1234567890");
        claim.setExamDate(new java.util.Date());
        claim.setClaimAmount(1000.0);
        claim.setStatus(ClaimStatus.Processing);
        BankingInfo bankingInfo = new BankingInfo();
        bankingInfo.setBank("ABC Bank");
        bankingInfo.setName("John Doe");
        bankingInfo.setNumber("123456");
        claim.setReiveBankingInfo(bankingInfo);
        claim.setDocuments("Document1");
        boolean result = claimDAO.add(claim);
        assertTrue(result);
    }
    @Test
    void addCalimIdAlready() throws SQLException {
        Claim claim = new Claim();
        claim.setId("55");
        claim.setClaimDate(new java.util.Date());
        claim.setInsuredPerson("John Doe");
        claim.setCardNumber("1234567890");
        claim.setExamDate(new java.util.Date());
        claim.setClaimAmount(1000.0);
        claim.setStatus(ClaimStatus.Processing);
        BankingInfo bankingInfo = new BankingInfo();
        bankingInfo.setBank("ABC Bank");
        bankingInfo.setName("John Doe");
        bankingInfo.setNumber("123456");
        claim.setReiveBankingInfo(bankingInfo);
        claim.setDocuments("Document1");
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            claimDAO.add(claim);
        });
        assertEquals("A claim with the same id already exists", thrown.getMessage());
    }
    @Test
    void updateClaimSuccess() throws SQLException {
        Claim claim = new Claim();
        claim.setId("4");
        claim.setClaimDate(new java.util.Date());
        claim.setInsuredPerson("John Doe 1");
        claim.setCardNumber("1234567890");
        claim.setExamDate(new java.util.Date());
        claim.setClaimAmount(1000.0);
        claim.setStatus(null);
        BankingInfo bankingInfo = new BankingInfo();
        bankingInfo.setBank("ABC Bank");
        bankingInfo.setName("John Doe");
        bankingInfo.setNumber("123456");
        claim.setReiveBankingInfo(bankingInfo);
        claim.setDocuments("Document1");

        boolean result = claimDAO.update(claim);
        assertTrue(result);

    }
    @Test
    void updateClaimSQlException() throws SQLException {
        Claim claim = new Claim();
        claim.setId("4");
        claim.setClaimDate(new java.util.Date());
        claim.setInsuredPerson("John Doe 1");
        claim.setCardNumber("1234567890");
        claim.setExamDate(new java.util.Date());
        claim.setClaimAmount(1000.0);
        claim.setStatus(null);
        BankingInfo bankingInfo = new BankingInfo();
        bankingInfo.setBank("ABC Bank");
        bankingInfo.setName("John Doe");
        bankingInfo.setNumber("123456");
        claim.setReiveBankingInfo(bankingInfo);
        claim.setDocuments("Document1");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            claimDAO.update(claim);
        });
        assertEquals("Error deleting claim", thrown.getMessage());

    }
    @Test
    void getAllClaimsSuccess() throws SQLException {
        List<Claim> actualClaims = claimDAO.getAll();
        assertEquals(5, actualClaims.size());
    }
    @Test
    void getAllClaims() {
        List<Claim> claims = claimDAO.getAll();
        assertTrue(claims.isEmpty());
    }
    @Test
    void getAllClaimsThrowsException() throws SQLException {

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            claimDAO.getAll();
        });
        assertEquals("Error retrieving all claims", thrown.getMessage());
    }

    @Test
    void getClaimByIdSuccess() {
        Optional<Claim> optionalClaim = claimDAO.get("4");
        assertTrue(optionalClaim.isPresent());
        Claim claim = optionalClaim.get();
        assertEquals("4", claim.getId());
    }
    @Test
    void getClaimByIdNotFound() {
        Optional<Claim> optionalClaim = claimDAO.get("nonexistent");
        assertTrue(optionalClaim.isEmpty());
    }
    @Test
    void deleteClaimSucces() {
        Claim claim = new Claim();
        claim.setId("4");
        claim.setClaimDate(new java.util.Date());
        claim.setInsuredPerson("John Doe 1");
        claim.setCardNumber("1234567890");
        claim.setExamDate(new java.util.Date());
        claim.setClaimAmount(1000.0);
        claim.setStatus(null);
        BankingInfo bankingInfo = new BankingInfo();
        bankingInfo.setBank("ABC Bank");
        bankingInfo.setName("John Doe");
        bankingInfo.setNumber("123456");
        claim.setReiveBankingInfo(bankingInfo);
        claim.setDocuments("Document1");
        boolean result = claimDAO.delete(claim);
        assertTrue(result);
    }
    @Test
    void deleteClaimNotFound() {
        Claim claim = new Claim();
        claim.setId("111111111");
        boolean result = claimDAO.delete(claim);
        assertFalse(result);
    }
    @Test
    void deleteClaimException() {
        Claim claim = new Claim();
        claim.setId(null);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            claimDAO.delete(claim);
        });
        assertEquals("Error deleting claim", thrown.getMessage());

    }
    @Test
    void switchStatusSuccess() {
        String id = "1";
        String status = "Done";
        claimDAO.switchStatus(id, status);
        Claim claim = claimDAO.getOne(id);
        assertEquals("Done", claim.getStatus());

    }
    @Test
    void switchStatusRuntimeException() {
        String id = "11111aaaaa";
        String status = "Done";
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            claimDAO.switchStatus(id, status);
        });
        assertEquals("No claim found with id: " + id, thrown.getMessage());

    }

}
