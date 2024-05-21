/**
 * @author <Group 24>
 */
package com.example.furtherprog_asm2;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DependentDAO_IMP implements DependentDAO<Dependent>{
    private Db_function dbFunction = new Db_function();

    private DependentService DependentService;
    private Connection connection;

    private static final String INSERT_DEPENDENT_SQL = "INSERT INTO \"user\"" + " (id, name, phone, email, address, password, role) VALUES " + " (?, ?, ?, ?, ?, ?, 'Dependent');";
    private static final String UPDATE_DEPENDENT_SQL = "UPDATE \"user\" SET phone = ?, email = ?, address = ?, password = ? WHERE id = ? AND role = 'Dependent'";

    private static final String DELETE_DEPENDENT_SQL = "DELETE FROM \"user\" WHERE id = ? AND role = 'Dependent'";
    private static final String SELECT_ALL_DEPENDENTS_SQL = "SELECT * FROM \"user\" WHERE role = 'Dependent'";
    private static final String SELECT_DEPENDENT_BY_ID_SQL = "SELECT * FROM \"user\" WHERE id = ? AND role = 'Dependent'";


    @Override
    public List<Dependent> getAll() {
        List<Dependent> dependents = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEPENDENTS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Dependent dependent = new Dependent();
                dependent.setId(resultSet.getString("id"));
                dependent.setName(resultSet.getString("name"));
                dependent.setPhone(resultSet.getString("phone"));
                dependent.setAddress(resultSet.getString("address"));
                dependent.setEmail(resultSet.getString("email"));
                dependent.setPassword(resultSet.getString("password"));
                dependents.add(dependent);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return dependents;
    }

    @Override
    public Optional<Dependent> get(String id) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPENDENT_BY_ID_SQL)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Dependent dependent = new Dependent();
                dependent.setId(resultSet.getString("id"));
                dependent.setName(resultSet.getString("name"));
                dependent.setPhone(resultSet.getString("phone"));
                dependent.setAddress(resultSet.getString("address"));
                dependent.setEmail(resultSet.getString("email"));
                dependent.setPassword(resultSet.getString("password"));
                return Optional.of(dependent);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public Dependent getOne(String id) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPENDENT_BY_ID_SQL)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Dependent dependent = new Dependent();
                dependent.setId(resultSet.getString("id"));
                dependent.setName(resultSet.getString("name"));
                dependent.setPhone(resultSet.getString("phone"));
                dependent.setAddress(resultSet.getString("address"));
                dependent.setEmail(resultSet.getString("email"));
                dependent.setPassword(resultSet.getString("password"));
                return dependent;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

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
            preparedStatement.setString(2, dependent.getName()); // Add this line
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
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEPENDENT_SQL)) {
            preparedStatement.setString(1, dependent.getPhone());
            preparedStatement.setString(2, dependent.getEmail());
            preparedStatement.setString(3, dependent.getAddress());
            preparedStatement.setString(4, dependent.getPassword());
            preparedStatement.setString(5, dependent.getId());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating dependent", e);
        }}

    @Override
    public boolean delete(Dependent dependent) {
        try (Connection connection = dbFunction.connect_to_db();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPENDENT_SQL)) {
            preparedStatement.setString(1, dependent.getId());
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting dependent", e);
        }
    }}