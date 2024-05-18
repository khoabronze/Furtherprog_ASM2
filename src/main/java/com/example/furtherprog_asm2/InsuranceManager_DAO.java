package com.example.furtherprog_asm2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InsuranceManager_DAO implements InsuranceManagerDAO <InsuranceManager>{
    private ArrayList<InsuranceManager> insuranceManagers = new ArrayList<>();
    private Db_function db = new Db_function();
    @Override
    public List<InsuranceManager> getAll() {
        List<InsuranceManager> insuranceManagers = new ArrayList<>();
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"user\" WHERE role = 'Insurance Manager'";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                InsuranceManager insuranceManager = new InsuranceManager();
                insuranceManager.setId(rs.getString("id"));
                insuranceManager.setName(rs.getString("name"));
                insuranceManager.setPhone(rs.getString("phone"));
                insuranceManager.setAddress(rs.getString("address"));
                insuranceManager.setEmail(rs.getString("email"));
                insuranceManager.setPassword(rs.getString("password"));
                insuranceManagers.add(insuranceManager);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return insuranceManagers;
    }

    @Override
    public Optional<InsuranceManager> get(String id) {
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"user\" WHERE id = ? AND role = 'Insurance Manager'";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                InsuranceManager insuranceManager = new InsuranceManager();
                insuranceManager.setId(rs.getString("id"));
                insuranceManager.setName(rs.getString("name"));
                insuranceManager.setPhone(rs.getString("phone"));
                insuranceManager.setAddress(rs.getString("address"));
                insuranceManager.setEmail(rs.getString("email"));
                insuranceManager.setPassword(rs.getString("password"));
                return Optional.of(insuranceManager);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public InsuranceManager getOne(String id) {
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"user\" WHERE id = ? AND role = 'Insurance Manager'";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                InsuranceManager insuranceManager = new InsuranceManager();
                insuranceManager.setId(rs.getString("id"));
                insuranceManager.setName(rs.getString("name"));
                insuranceManager.setPhone(rs.getString("phone"));
                insuranceManager.setAddress(rs.getString("address"));
                insuranceManager.setEmail(rs.getString("email"));
                insuranceManager.setPassword(rs.getString("password"));
                return insuranceManager;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(InsuranceManager im) {
        Connection con = db.connect_to_db();
        String query = "INSERT INTO \"user\" (id, name, phone, email, address, password, role) VALUES (?, ?, ?, ?, ?, ?, 'Insurance Manager')";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, im.getId());
            stmt.setString(2, im.getName());
            stmt.setString(3, im.getPhone());
            stmt.setString(4, im.getEmail());
            stmt.setString(5, im.getAddress());
            stmt.setString(6, im.getPassword());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(String id, String name, String phone, String email, String address, String password) {
        Connection con = db.connect_to_db();
        String query = "UPDATE \"user\" SET name = ?, phone = ?, email = ?, address = ?, password = ? WHERE id = ? AND role = 'Insurance Manager'";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, email);
            stmt.setString(4, address);
            stmt.setString(5, password);
            stmt.setString(6, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(InsuranceManager im) {
        Connection con = db.connect_to_db();
        String query = "DELETE FROM \"user\" WHERE id = ? AND role = 'Insurance Manager'";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, im.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
