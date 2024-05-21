package com.example.furtherprog_asm2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InsuranceSurveyor_DAO implements InsuranceSurveyorDAO <InsuranceSurveyor>{
    private Db_function dbFunction = new Db_function();

    private InsuranceSurveyor_Service insuranceSurveyorService;
    private Connection connection;
    public InsuranceSurveyor_DAO(Connection connection) {
        this.connection = connection;
    }

    public InsuranceSurveyor_DAO() {
    }

    private static final String INSERT_INSURANCESURVEYOR_SQL = "INSERT INTO \"user\"" + " (id, name, phone, email, address, password, role) VALUES " + " (?, ?, ?, ?, ?, ?, 'Insurance Surveyor');";
    private static final String UPDATE_INSURANCESURVEYOR_SQL = "UPDATE \"user\" SET phone = ?, email = ?, address = ?, password = ? WHERE id = ? AND role = 'Insurance Surveyor'";

    private static final String DELETE_INSURANCESURVEYOR_SQL = "DELETE FROM \"user\" WHERE id = ? AND role = 'Insurance Surveyor'";
    private static final String SELECT_ALL_INSURANCESURVEYORS_SQL = "SELECT * FROM \"user\" WHERE role = 'Insurance Surveyor'";
    private static final String SELECT_INSURANCESURVEYOR_BY_ID_SQL = "SELECT * FROM \"user\" WHERE id = ? AND role = 'Insurance Surveyor'";
    @Override
    public List<InsuranceSurveyor> getAll() {
        List<InsuranceSurveyor> insuranceSurveyors = new ArrayList<>();
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INSURANCESURVEYORS_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
                insuranceSurveyor.setId(resultSet.getString("id"));
                insuranceSurveyor.setName(resultSet.getString("name"));
                insuranceSurveyor.setPhone(resultSet.getString("phone"));
                insuranceSurveyor.setAddress(resultSet.getString("address"));
                insuranceSurveyor.setEmail(resultSet.getString("email"));
                insuranceSurveyor.setPassword(resultSet.getString("password"));
                insuranceSurveyors.add(insuranceSurveyor);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return insuranceSurveyors;
    }

    @Override
    public Optional<InsuranceSurveyor> get(String id) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSURANCESURVEYOR_BY_ID_SQL)) {

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
                insuranceSurveyor.setId(resultSet.getString("id"));
                insuranceSurveyor.setName(resultSet.getString("name"));
                insuranceSurveyor.setPhone(resultSet.getString("phone"));
                insuranceSurveyor.setAddress(resultSet.getString("address"));
                insuranceSurveyor.setEmail(resultSet.getString("email"));
                insuranceSurveyor.setPassword(resultSet.getString("password"));
                return Optional.of(insuranceSurveyor);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public InsuranceSurveyor getOne(String id) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSURANCESURVEYOR_BY_ID_SQL)) {

            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor();
                insuranceSurveyor.setId(resultSet.getString("id"));
                insuranceSurveyor.setName(resultSet.getString("name"));
                insuranceSurveyor.setPhone(resultSet.getString("phone"));
                insuranceSurveyor.setAddress(resultSet.getString("address"));
                insuranceSurveyor.setEmail(resultSet.getString("email"));
                insuranceSurveyor.setPassword(resultSet.getString("password"));
                return insuranceSurveyor;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public boolean add(InsuranceSurveyor insuranceSurveyor) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INSURANCESURVEYOR_SQL)) {

            preparedStatement.setString(1, insuranceSurveyor.getId());
            preparedStatement.setString(2, insuranceSurveyor.getName());
            preparedStatement.setString(3, insuranceSurveyor.getPhone());
            preparedStatement.setString(4, insuranceSurveyor.getEmail());
            preparedStatement.setString(5, insuranceSurveyor.getAddress());
            preparedStatement.setString(6, insuranceSurveyor.getPassword());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(InsuranceSurveyor insuranceSurveyor) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INSURANCESURVEYOR_SQL)) {

            preparedStatement.setString(1, insuranceSurveyor.getPhone());
            preparedStatement.setString(2, insuranceSurveyor.getEmail());
            preparedStatement.setString(3, insuranceSurveyor.getAddress());
            preparedStatement.setString(4, insuranceSurveyor.getPassword());
            preparedStatement.setString(5, insuranceSurveyor.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating InsuranceSurveyor", e);
        }
    }

    @Override
    public boolean delete(InsuranceSurveyor insuranceSurveyor) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INSURANCESURVEYOR_SQL)) {

            preparedStatement.setString(1, insuranceSurveyor.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting InsuranceSurveyor", e);
        }
    }
}
