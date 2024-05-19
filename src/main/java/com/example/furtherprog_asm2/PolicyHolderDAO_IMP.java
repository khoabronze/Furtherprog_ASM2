package com.example.furtherprog_asm2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PolicyHolderDAO_IMP implements PolicyHolderDAO<PolicyHolder> {
    private static final String INSERT_PH_SQL = "INSERT INTO \"user\"" + " (id, name, phone, email, address, password, role) VALUES " + " (?, ?, ?, ?, ?, ?, 'Policy Holder');";

    private Db_function dbFunction = new Db_function();

    private PolicyHolder_Service policyHolderService;
    private Connection connection;
    @Override
    public List<PolicyHolder> getAll() {
        return null;
    }

    @Override
    public Optional<PolicyHolder> get(String i) {
        return Optional.empty();
    }

    @Override
    public PolicyHolder getOne(String i) {
        return null;
    }

    @Override
    public boolean add(PolicyHolder po) {

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_PH_SQL)) {
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
        return false;
    }

    @Override
    public boolean delete(PolicyHolder po) {
        return false;
    }
}
