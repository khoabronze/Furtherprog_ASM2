package com.example.furtherprog_asm2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return Optional.empty();
    }

    @Override
    public void getOne(InsuranceCard insuranceCard) {

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
    public void delete(InsuranceCard insuranceCard) {

    }
}
