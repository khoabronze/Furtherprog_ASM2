package com.example.furtherprog_asm2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClaimDAO implements DAO<Claim> {
    private static final String INSERT_CLAIM_SQL = "INSERT INTO claims" +
            "  (id, claim_date, insured_person, card_number, exam_date, claim_amount, status, bank_info, document) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private Map<Integer, Claim> claimMap = new HashMap<>();
    private Db_function dbFunction = new Db_function();

    @Override
    public Claim get(int id) {
        // Implementation to get a claim by its id
        return null;
    }

    @Override
    public List<Claim> getAll() {
        // Implementation to get all claims
        return null;
    }

    @Override
    public void save(Claim claim) {
        try (Connection connection = dbFunction.connect_to_db();

             // Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLAIM_SQL)) {
            preparedStatement.setString(1, claim.getId());
            preparedStatement.setDate(2, new java.sql.Date(claim.getClaimDate().getTime()));
            preparedStatement.setString(3, claim.getInsuredPerson());
            preparedStatement.setString(4, claim.getCardNumber());
            preparedStatement.setDate(5, new java.sql.Date(claim.getExamDate().getTime()));
            preparedStatement.setDouble(6, claim.getClaimAmount());
            preparedStatement.setString(7, claim.getStatus().toString());
            preparedStatement.setString(8, claim.getReiveBankingInfo().toString());
            String documents = String.join(",", claim.getDocuments());
            preparedStatement.setString(9, documents);
            System.out.println(preparedStatement);
            // Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // print SQL exception information
            System.out.println(e);;
        }
    }

    @Override
    public void update(Claim claim) {
        // Implementation to update a claim
    }

    @Override
    public void delete(Claim claim) {
        // Implementation to delete a claim
    }
}