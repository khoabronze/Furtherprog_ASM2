package com.example.furtherprog_asm2;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

public class InsuranceCardDao implements DAO<InsuranceCard> {
    private HashMap<String, InsuranceCard> insuranceCardHashMap = new HashMap<>();
    private Db_function db = new Db_function();
    @Override
    public HashMap<String, InsuranceCard> getAll() {
        return null;
    }

    @Override
    public Optional<InsuranceCard> get(String cardNumber) {
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"Insurance Card\" WHERE \"cardNumber\" = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, cardNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String cardHolder = rs.getString("cardHolder");
                String policyOwner = rs.getString("policyOwner");
                LocalDate expirationDate = rs.getDate("expirationDate").toLocalDate();
                String expirationDateString = expirationDate.toString();
                InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDateString);
                return Optional.of(insuranceCard);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println(e);
            return Optional.empty();
        }
    }

    @Override
    public InsuranceCard getOne(String cardNumber) {
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"Insurance Card\" WHERE \"cardNumber\" = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, cardNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String cardHolder = rs.getString("cardHolder");
                String policyOwner = rs.getString("policyOwner");
                LocalDate expirationDate = rs.getDate("expirationDate").toLocalDate();
                String expirationDateString = expirationDate.toString();
                InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDateString);
                return insuranceCard;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public boolean add(InsuranceCard insuranceCard) {
        Connection con = db.connect_to_db();
        String query = "INSERT INTO \"Insurance Card\" (\"cardNumber\", \"cardHolder\", \"policyOwner\", \"expirationDate\") VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, insuranceCard.getCardNumber());
            stmt.setString(2, insuranceCard.getCardHolder());
            stmt.setString(3, insuranceCard.getPolicyOwner());
            Date expirationDate = Date.valueOf(insuranceCard.getExpirationDate());
            stmt.setDate(4, expirationDate);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public void update(InsuranceCard insuranceCard) {

    }

    @Override
    public boolean delete(InsuranceCard insuranceCard) {
        Connection con = db.connect_to_db();
        String query = "DELETE FROM \"Insurance Card\" WHERE \"cardNumber\" = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, insuranceCard.getCardNumber());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
