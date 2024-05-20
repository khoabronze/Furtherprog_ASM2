package com.example.furtherprog_asm2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InsuranceManager_DAO implements InsuranceManagerDAO <InsuranceManager>{
    private Db_function dbFunction = new Db_function();

    private InsuranceSurveyor_Service insuranceSurveyorService;
    private Connection connection;
    public InsuranceManager_DAO(Connection connection) {
        this.connection = connection;
    }

    public InsuranceManager_DAO() {}

    private static final String INSERT_INSURANCEMANAGER_SQL = "INSERT INTO \"user\"" + " (id, name, phone, email, address, password, role) VALUES " + " (?, ?, ?, ?, ?, ?, 'Insurance Manager');";
    private static final String UPDATE_INSURANCEMANAGER_SQL = "UPDATE \"user\" SET phone = ?, email = ?, address = ?, password = ? WHERE id = ? AND role = 'Insurance Manager'";

    private static final String DELETE_INSURANCEMANAGER_SQL = "DELETE FROM \"user\" WHERE id = ? AND role = 'Insurance Manager'";
    private static final String SELECT_ALL_INSURANCEMANAGERS_SQL = "SELECT * FROM \"user\" WHERE role = 'Insurance Manager'";
    private static final String SELECT_INSURANCEMANAGER_BY_ID_SQL = "SELECT * FROM \"user\" WHERE id = ? AND role = 'Insurance Manager'";
    @Override
    public List<InsuranceManager> getAll() {
        List<InsuranceManager> insuranceManagers = new ArrayList<>();
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INSURANCEMANAGERS_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InsuranceManager insuranceManager = new InsuranceManager();
                insuranceManager.setId(resultSet.getString("id"));
                insuranceManager.setName(resultSet.getString("name"));
                insuranceManager.setPhone(resultSet.getString("phone"));
                insuranceManager.setAddress(resultSet.getString("address"));
                insuranceManager.setEmail(resultSet.getString("email"));
                insuranceManager.setPassword(resultSet.getString("password"));
                insuranceManagers.add(insuranceManager);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return insuranceManagers;
    }

    @Override
    public Optional<InsuranceManager> get(String id) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSURANCEMANAGER_BY_ID_SQL)) {

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                InsuranceManager insuranceManager = new InsuranceManager();
                insuranceManager.setId(resultSet.getString("id"));
                insuranceManager.setName(resultSet.getString("name"));
                insuranceManager.setPhone(resultSet.getString("phone"));
                insuranceManager.setAddress(resultSet.getString("address"));
                insuranceManager.setEmail(resultSet.getString("email"));
                insuranceManager.setPassword(resultSet.getString("password"));
                return Optional.of(insuranceManager);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public InsuranceManager getOne(String id) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSURANCEMANAGER_BY_ID_SQL)) {

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                InsuranceManager insuranceManager = new InsuranceManager();
                insuranceManager.setId(resultSet.getString("id"));
                insuranceManager.setName(resultSet.getString("name"));
                insuranceManager.setPhone(resultSet.getString("phone"));
                insuranceManager.setAddress(resultSet.getString("address"));
                insuranceManager.setEmail(resultSet.getString("email"));
                insuranceManager.setPassword(resultSet.getString("password"));
                return insuranceManager;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public boolean add(InsuranceManager insuranceManager) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INSURANCEMANAGER_SQL)) {

            preparedStatement.setString(1, insuranceManager.getId());
            preparedStatement.setString(2, insuranceManager.getName());
            preparedStatement.setString(3, insuranceManager.getPhone());
            preparedStatement.setString(4, insuranceManager.getEmail());
            preparedStatement.setString(5, insuranceManager.getAddress());
            preparedStatement.setString(6, insuranceManager.getPassword());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(InsuranceManager insuranceManager) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INSURANCEMANAGER_SQL)) {

            preparedStatement.setString(1, insuranceManager.getPhone());
            preparedStatement.setString(2, insuranceManager.getEmail());
            preparedStatement.setString(3, insuranceManager.getAddress());
            preparedStatement.setString(4, insuranceManager.getPassword());
            preparedStatement.setString(5, insuranceManager.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating InsuranceSurveyor", e);
        }
    }

    @Override
    public boolean delete(InsuranceManager insuranceManager) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INSURANCEMANAGER_SQL)) {

            preparedStatement.setString(1, insuranceManager.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting InsuranceSurveyor", e);
        }
    }
}
