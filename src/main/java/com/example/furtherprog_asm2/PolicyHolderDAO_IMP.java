/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PolicyHolderDAO_IMP implements PolicyHolderDAO<PolicyHolder> {
    private static final String INSERT_PH_SQL = "INSERT INTO \"user\"" + " (id, name, phone, email, address, password, role) VALUES " + " (?, ?, ?, ?, ?, ?, 'Policy Holder');";

    private static final String GET_PH_SQL = "SELECT * FROM \"user\" WHERE id = ? AND role = 'Policy Holder'";
    private static String DELETE_PH_SQL = "DELETE FROM \"user\" WHERE id = ? AND role = 'Policy Holder'";
    private static String GET_ALL_PH_SQL = "SELECT * FROM \"user\" WHERE role = 'Policy Holder'";
    private static String UPDATE_PH_SQL = "UPDATE \"user\" SET name = ?, phone = ?, email = ?, address = ?, password = ? WHERE id = ? AND role = 'Policy Holder'";


    private Db_function dbFunction = new Db_function();
    private Connection connection;

    public PolicyHolderDAO_IMP(Connection connection) {
        this.connection = connection;
    }

    public PolicyHolderDAO_IMP() {
    }

    @Override
    public List<PolicyHolder> getAll() {
        List<PolicyHolder> policyHolders = new ArrayList<>();
        try (Connection connection = dbFunction.connect_to_db();// đã sửa
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PH_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PolicyHolder policyHolder = new PolicyHolder();
                policyHolder.setId(resultSet.getString("id"));
                policyHolder.setName(resultSet.getString("name"));
                policyHolder.setPhone(resultSet.getString("phone"));
                policyHolder.setAddress(resultSet.getString("address"));
                policyHolder.setEmail(resultSet.getString("email"));
                policyHolder.setPassword(resultSet.getString("password"));
                policyHolders.add(policyHolder);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return policyHolders;
    }

    @Override
    public Optional<PolicyHolder> get(String id) {

        try (
                Connection connection = dbFunction.connect_to_db();// đã sửa
                PreparedStatement preparedStatement = connection.prepareStatement(GET_PH_SQL)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                PolicyHolder policyHolder = new PolicyHolder();
                policyHolder.setId(resultSet.getString("id"));
                policyHolder.setName(resultSet.getString("name"));
                policyHolder.setPhone(resultSet.getString("phone"));
                policyHolder.setAddress(resultSet.getString("address"));
                policyHolder.setEmail(resultSet.getString("email"));
                policyHolder.setPassword(resultSet.getString("password"));
                return Optional.of(policyHolder);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Optional.empty();
    }


    @Override
    public PolicyHolder getOne(String i) {
        return null;
    }

    @Override
    public boolean add(PolicyHolder po) {
        try (Connection connection = dbFunction.connect_to_db();// đã sửa
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PH_SQL)) {
            preparedStatement.setString(1, po.getId());
            preparedStatement.setString(2, po.getName());
            preparedStatement.setString(3, po.getPhone());
            preparedStatement.setString(4, po.getEmail());
            preparedStatement.setString(5, po.getAddress());
            preparedStatement.setString(6, po.getPassword());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(PolicyHolder po) {
        try (Connection connection = dbFunction.connect_to_db();// đã sửa
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PH_SQL)) {
            preparedStatement.setString(1, po.getName());
            preparedStatement.setString(2, po.getPhone());
            preparedStatement.setString(3, po.getEmail());
            preparedStatement.setString(4, po.getAddress());
            preparedStatement.setString(5, po.getPassword());
            preparedStatement.setString(6, po.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    @Override
    public boolean delete(PolicyHolder po) {

        try (Connection connection = dbFunction.connect_to_db();// đã sửa
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PH_SQL)) {
            // Set the ID parameter
            preparedStatement.setString(1, po.getId());

            // Execute the DELETE statement
            int affectedRows = preparedStatement.executeUpdate();

            // Return true if a row was deleted
            return affectedRows > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}