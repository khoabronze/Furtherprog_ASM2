package com.example.furtherprog_asm2;


import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ClaimDAO implements DAO<Claim> {
    private static final String INSERT_CLAIM_SQL = "INSERT INTO claims" +
            "  (id, claim_date, insured_person, card_number, exam_date, claim_amount, status, bank_info, document) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SELECT_CLAIM_BY_ID_SQL = "SELECT * FROM claims WHERE id = ?";
    private static final String DELETE_CLAIM_BY_ID_SQL = "DELETE FROM claims WHERE id = ?";
    private static final String SELECT_ALL_CLAIMS_SQL = "SELECT * FROM claims";

    private static final String UPDATE_CLAIM_SQL = "UPDATE claims SET claim_date = ?, insured_person = ?, card_number = ?, exam_date = ?, claim_amount = ?, status = ?, bank_info = ?, document = ? WHERE id = ?";


    private Map<Integer, Claim> claimMap = new HashMap<>();
    private Db_function dbFunction = new Db_function();
    private ClaimDAO claimDAO;
    private Connection connection;

    public ClaimDAO(Connection connection) {
        this.connection = connection;
    }

    public ClaimDAO() {}

    @Override
    public List<Claim> getAll() {
        List<Claim> claims = new ArrayList<>();
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLAIMS_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Claim claim = new Claim();
                claim.setId(resultSet.getString("id"));
                claim.setClaimDate(resultSet.getDate("claim_date"));
                claim.setInsuredPerson(resultSet.getString("insured_person"));
                claim.setCardNumber(resultSet.getString("card_number"));
                claim.setExamDate(resultSet.getDate("exam_date"));
                claim.setClaimAmount(resultSet.getDouble("claim_amount"));
                claim.setStatus(ClaimStatus.valueOf(resultSet.getString("status")));
                String[] bankInfoParts = resultSet.getString("bank_info").split(" – ");
                BankingInfo bankingInfo = new BankingInfo();
                bankingInfo.setBank(bankInfoParts[0]);
                bankingInfo.setName(bankInfoParts[1]);
                bankingInfo.setNumber(bankInfoParts[2]);
                claim.setReiveBankingInfo(bankingInfo);
                String document = resultSet.getString("document");
                claim.setDocuments(document);
                claims.add(claim);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all claims", e);
        }

        return claims;
    }

    @Override
    public Optional<Claim> get(String id) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLAIM_BY_ID_SQL)) {

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Claim claim = new Claim();
                claim.setId(resultSet.getString("id"));
                claim.setClaimDate(resultSet.getDate("claim_date"));
                claim.setInsuredPerson(resultSet.getString("insured_person"));
                claim.setCardNumber(resultSet.getString("card_number"));
                claim.setExamDate(resultSet.getDate("exam_date"));
                claim.setClaimAmount(resultSet.getDouble("claim_amount"));
                claim.setStatus(ClaimStatus.valueOf(resultSet.getString("status")));
                String[] bankInfoParts = resultSet.getString("bank_info").split(" – ");
                BankingInfo bankingInfo = new BankingInfo();
                bankingInfo.setBank(bankInfoParts[0]);
                bankingInfo.setName(bankInfoParts[1]);
                bankingInfo.setNumber(bankInfoParts[2]);
                claim.setReiveBankingInfo(bankingInfo);
                String document = resultSet.getString("document");
                claim.setDocuments(document);
                return Optional.of(claim);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving claim", e);
        }
    }

    @Override
    public Claim getOne(String id) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLAIM_BY_ID_SQL)) {

            preparedStatement.setString(1, id); // Use setString instead of setInt
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Claim claim = new Claim();
                claim.setId(resultSet.getString("id"));
                claim.setClaimDate(resultSet.getDate("claim_date"));
                claim.setInsuredPerson(resultSet.getString("insured_person"));
                claim.setCardNumber(resultSet.getString("card_number"));
                claim.setExamDate(resultSet.getDate("exam_date"));
                claim.setClaimAmount(resultSet.getDouble("claim_amount"));
                claim.setStatus(ClaimStatus.valueOf(resultSet.getString("status")));
                String[] bankInfoParts = resultSet.getString("bank_info").split(" – ");
                BankingInfo bankingInfo = new BankingInfo();
                bankingInfo.setBank(bankInfoParts[0]);
                bankingInfo.setName(bankInfoParts[1]);
                bankingInfo.setNumber(bankInfoParts[2]);
                claim.setReiveBankingInfo(bankingInfo);
                String document = resultSet.getString("document");
                claim.setDocuments(document);
                return claim;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving claim", e);
        }
    }

    @Override
    public boolean add(Claim claim) {
        try (Connection connection = dbFunction.connect_to_db();
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
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new IllegalArgumentException("A claim with the same id already exists", e);
            } else {
                throw new RuntimeException("Error saving claim", e);
            }
        }
    }




    @Override
    public boolean update(Claim claim) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLAIM_SQL)) {
            preparedStatement.setDate(1, new java.sql.Date(claim.getClaimDate().getTime()));
            preparedStatement.setString(2, claim.getInsuredPerson());
            preparedStatement.setString(3, claim.getCardNumber());
            preparedStatement.setDate(4, new java.sql.Date(claim.getExamDate().getTime()));
            preparedStatement.setDouble(5, claim.getClaimAmount());
            if (claim.getStatus() != null) {
                preparedStatement.setString(6, claim.getStatus().toString());
            } else {
                preparedStatement.setNull(6, Types.VARCHAR);
            }
            preparedStatement.setString(7, claim.getReiveBankingInfo().toString());
            String documents = String.join(",", claim.getDocuments());
            preparedStatement.setString(8, documents);
            preparedStatement.setString(9, claim.getId());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating claim", e);
        }
    }

    @Override
    public boolean delete(Claim claim) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLAIM_BY_ID_SQL)) {
            preparedStatement.setString(1, claim.getId());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting claim", e);
        }
    }

    @Override
    public void switchStatus(String id, String status) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE claims SET status = ? WHERE id = ?")) {
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("No claim found with id: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating claim status", e);
        }
    }
}