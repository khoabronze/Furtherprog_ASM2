package com.example.furtherprog_asm2;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class DependentDAO_IMP implements DependentDAO<Dependent>{
    private Db_function dbFunction = new Db_function();

    private DependentService DependentService;
    private Connection connection;

    private static final String INSERT_DEPENDENT_SQL = "INSERT INTO \"user\"" + " (id, name, phone, email, address, password, role) VALUES " + " (?, ?, ?, ?, ?, ?, 'Dependent');";

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Optional get(String i) {
        return Optional.empty();
    }

    @Override
    public Dependent getOne(String i) {
        return null;
    }

    public DependentDAO_IMP(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean add(Dependent dependent) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPENDENT_SQL)) {

            preparedStatement.setString(1, dependent.getId());
            preparedStatement.setString(2, dependent.getName());
            preparedStatement.setString(3, dependent.getPhone());
            preparedStatement.setString(4, dependent.getEmail());
            preparedStatement.setString(5, dependent.getAddress());
            preparedStatement.setString(6, dependent.getPassword());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Dependent dependent) {
        return false;
    }

    @Override
    public boolean delete(Dependent dependent) {
        return false;
    }
}
