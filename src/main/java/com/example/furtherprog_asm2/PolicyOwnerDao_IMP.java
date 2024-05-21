package com.example.furtherprog_asm2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PolicyOwnerDao_IMP implements PolicyOwnerDAO<PolicyOwner> {
    private static final String INSERT_PO_SQL = "INSERT INTO \"user\"" + " (id, name, phone, email, address, password, role) VALUES " + " (?, ?, ?, ?, ?, ?, 'Policy Owner');";
    private static final String GET_PO_SQL = "SELECT * FROM \"user\" WHERE id = ? AND role = 'Policy Owner'";
    private static final String DELETE_PO_SQL = "DELETE FROM \"user\" WHERE id = ? AND role = 'Policy Owner'";
    private static final String GET_ALL_PO_SQL = "SELECT * FROM \"user\" WHERE role = 'Policy Owner'";
    private static final String UPDATE_PO_SQL = "UPDATE \"user\" SET name = ?, phone = ?, email = ?, address = ?, password = ? WHERE id = ? AND role = 'Policy Owner'";




    private Db_function dbFunction = new Db_function();

    private PolicyOwnerService policyOwnerService;
    private Connection connection;



        public PolicyOwnerDao_IMP(Connection connection) {
            this.connection = connection;
        }

    public PolicyOwnerDao_IMP() {
    }


    @Override
    public List<PolicyOwner> getAll() {
        List<PolicyOwner> policyOwners = new ArrayList<>();

        try (Connection connection = dbFunction.connect_to_db();// đã sửa
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PO_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PolicyOwner policyOwner = new PolicyOwner();
                policyOwner.setId(resultSet.getString("id"));
                policyOwner.setName(resultSet.getString("name"));
                policyOwner.setPhone(resultSet.getString("phone"));
                policyOwner.setAddress(resultSet.getString("address"));
                policyOwner.setEmail(resultSet.getString("email"));
                policyOwner.setPassword(resultSet.getString("password"));
                policyOwners.add(policyOwner);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return policyOwners;
    }


    @Override
    public Optional<PolicyOwner> get(String id) {

        try (Connection connection = dbFunction.connect_to_db();// đã sửa
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PO_SQL)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                PolicyOwner policyOwner = new PolicyOwner();
                policyOwner.setId(resultSet.getString("id"));
                policyOwner.setName(resultSet.getString("name"));
                policyOwner.setPhone(resultSet.getString("phone"));
                policyOwner.setAddress(resultSet.getString("address"));
                policyOwner.setEmail(resultSet.getString("email"));
                policyOwner.setPassword(resultSet.getString("password"));
                return Optional.of(policyOwner);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public PolicyOwner getOne(String i) {
        return null;
    }

    @Override
    public boolean add(PolicyOwner policyOwner) {
        try (Connection connection = dbFunction.connect_to_db();// đã sửa
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PO_SQL)) {
            preparedStatement.setString(1, policyOwner.getId());
            preparedStatement.setString(2, policyOwner.getName());
            preparedStatement.setString(3, policyOwner.getPhone());
            preparedStatement.setString(4, policyOwner.getEmail());
            preparedStatement.setString(5, policyOwner.getAddress());
            preparedStatement.setString(6, policyOwner.getPassword());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(PolicyOwner policyOwner) {
        try (Connection connection = dbFunction.connect_to_db();// đã sửa
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PO_SQL)) {
            preparedStatement.setString(1, policyOwner.getName());
            preparedStatement.setString(2, policyOwner.getPhone());
            preparedStatement.setString(3, policyOwner.getEmail());
            preparedStatement.setString(4, policyOwner.getAddress());
            preparedStatement.setString(5, policyOwner.getPassword());
            preparedStatement.setString(6, policyOwner.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    @Override
    public boolean delete(PolicyOwner policyOwner) {
        try (Connection connection = dbFunction.connect_to_db();// đã sửa
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PO_SQL)) {
            preparedStatement.setString(1, policyOwner.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }    }
