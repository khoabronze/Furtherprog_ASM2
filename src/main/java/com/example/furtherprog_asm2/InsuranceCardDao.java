package com.example.furtherprog_asm2;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InsuranceCardDao implements DAO<InsuranceCard> {
    private ArrayList<InsuranceCard> insuranceCards = new ArrayList<>();
    private Db_function db = new Db_function();

    @Override
    public List<InsuranceCard> getAll() {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"Insurance Card\"";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String cardNumber = rs.getString("cardNumber");
                String cardHolder = rs.getString("cardHolder");
                String policyOwner = rs.getString("policyOwner");
                LocalDate expirationDate = rs.getDate("expirationDate").toLocalDate();
                String expirationDateString = expirationDate.toString();
                InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDateString);
                insuranceCards.add(insuranceCard);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return insuranceCards;
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
    public boolean update(String cardNumber, String cardHolder, String policyOwner, String expirationDate) {
        Connection con = db.connect_to_db();
        String query = "UPDATE \"Insurance Card\" SET \"cardHolder\" = ?, \"policyOwner\" = ?, \"expirationDate\" = ? WHERE \"cardNumber\" = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, cardHolder);
            stmt.setString(2, policyOwner);
            Date expirationDateValue = Date.valueOf(expirationDate);
            stmt.setDate(3, expirationDateValue);
            stmt.setString(4, cardNumber);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
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
