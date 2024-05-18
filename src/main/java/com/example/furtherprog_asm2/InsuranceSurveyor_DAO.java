package com.example.furtherprog_asm2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InsuranceSurveyor_DAO implements InsuranceSurveyorDAO <InsuranceSurveyor>{
    private ArrayList<InsuranceSurveyor> insuranceSurveyors = new ArrayList<>();
    private Db_function db = new Db_function();
    @Override
    public List<InsuranceSurveyor> getAll() {
        List<InsuranceSurveyor> insuranceSurveyors = new ArrayList<>();
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"user\" WHERE role = 'Insurance Surveyor'";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
                insuranceSurveyor.setId(rs.getString("id"));
                insuranceSurveyor.setName(rs.getString("name"));
                insuranceSurveyor.setPhone(rs.getString("phone"));
                insuranceSurveyor.setAddress(rs.getString("address"));
                insuranceSurveyor.setEmail(rs.getString("email"));
                insuranceSurveyor.setPassword(rs.getString("password"));
                insuranceSurveyors.add(insuranceSurveyor);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return insuranceSurveyors;
    }

    @Override
    public Optional<InsuranceSurveyor> get(String id) {
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"user\" WHERE id = ? AND role = 'Insurance Surveyor'";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
                insuranceSurveyor.setId(rs.getString("id"));
                insuranceSurveyor.setName(rs.getString("name"));
                insuranceSurveyor.setPhone(rs.getString("phone"));
                insuranceSurveyor.setAddress(rs.getString("address"));
                insuranceSurveyor.setEmail(rs.getString("email"));
                insuranceSurveyor.setPassword(rs.getString("password"));
                return Optional.of(insuranceSurveyor);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public InsuranceSurveyor getOne(String id) {
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"user\" WHERE id = ? AND role = 'Insurance Surveyor'";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
                insuranceSurveyor.setId(rs.getString("id"));
                insuranceSurveyor.setName(rs.getString("name"));
                insuranceSurveyor.setPhone(rs.getString("phone"));
                insuranceSurveyor.setAddress(rs.getString("address"));
                insuranceSurveyor.setEmail(rs.getString("email"));
                insuranceSurveyor.setPassword(rs.getString("password"));
                return insuranceSurveyor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean add(InsuranceSurveyor is) {
        Connection con = db.connect_to_db();
        String query = "INSERT INTO \"user\" (id, name, phone, email, address, password, role) VALUES (?, ?, ?, ?, ?, ?, 'Insurance Surveyor')";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, is.getId());
            stmt.setString(2, is.getName());
            stmt.setString(3, is.getPhone());
            stmt.setString(4, is.getEmail());
            stmt.setString(5, is.getAddress());
            stmt.setString(6, is.getPassword());
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
        String query = "UPDATE \"user\" SET name = ?, phone = ?, email = ?, address = ?, password = ? WHERE id = ? AND role = 'Insurance Surveyor'";
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
    public boolean delete(InsuranceSurveyor is) {
        Connection con = db.connect_to_db();
        String query = "DELETE FROM \"user\" WHERE id = ? AND role = 'Insurance Surveyor'";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, is.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
