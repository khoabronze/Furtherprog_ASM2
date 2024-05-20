package com.example.furtherprog_asm2;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
        claimDAO.delete(claim);
        boolean result = claimDAO.add(claim);
        assertTrue(result);
        claimDAO.delete(claim);

    }

    @Test
    void updateClaimSuccess() throws SQLException {
        // Retrieve the claim with the ID "55"
        Claim claim = claimDAO.getOne("C-9987654321");

        // Update the properties of the retrieved claim
        claim.setClaimDate(new java.util.Date());
        claim.setDocuments("Document1");

        // Call the update method of the ClaimDAO class
        boolean result = claimDAO.update(claim);

        // Assert that the update was successful
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
        claim.setClaimAmount(-100.00);
        claim.setStatus(ClaimStatus.Done);
        BankingInfo bankingInfo = new BankingInfo();
        bankingInfo.setBank("ABC Bank");
        bankingInfo.setName("John Doe");
        bankingInfo.setNumber("123456");
        claim.setReiveBankingInfo(bankingInfo);
        claim.setDocuments("Document1");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            claimDAO.update(claim);
        });
        assertEquals("Claim amount cannot be negative", thrown.getMessage());
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
        claim.setStatus(ClaimStatus.Done);
        BankingInfo bankingInfo = new BankingInfo();
        bankingInfo.setBank("ABC Bank");
        bankingInfo.setName("John Doe");
        bankingInfo.setNumber("123456");
        claim.setReiveBankingInfo(bankingInfo);
        claim.setDocuments("Document1");
        claimDAO.add(claim);
        boolean result = claimDAO.delete(claim);
        assertTrue(result);
        claimDAO.delete(claim);

    }
    @Test
    void getAllClaimsSuccess() throws SQLException {
        List<Claim> actualClaims = claimDAO.getAll();
        assertEquals(20, actualClaims.size());
    }
    @Test
    void getAllClaims() {
        List<Claim> claims = claimDAO.getAll();
        System.out.println(claims);
        assertFalse(claims.isEmpty());
    }


    @Test
    void getClaimByIdSuccess() {
        Optional<Claim> optionalClaim = claimDAO.get("C-9987654321");
        assertTrue(optionalClaim.isPresent());
        Claim claim = optionalClaim.get();
        assertEquals("C-9987654321", claim.getId());
    }
    @Test
    void getClaimByIdNotFound() {
        Optional<Claim> optionalClaim = claimDAO.get("nonexistent");
        assertTrue(optionalClaim.isEmpty());
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
        assertEquals("Claim ID cannot be null", thrown.getMessage());

    }
    @Test
    void switchStatusSuccess() {
        String id = "C-9987654321";
        String status = "Done";
        claimDAO.switchStatus(id, status);
        Claim claim = claimDAO.getOne(id);
        assertEquals(status, claim.getStatus().toString());
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
