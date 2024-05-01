package com.example.furtherprog_asm2;
import java.sql.DriverManager;
import java.sql.Connection;

public class Db_function {
    public Connection connect_to_db(String dbname, String username, String password){
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, username, password);
            if (conn != null) {
                System.out.println("Connected to the database");
            } else {
                System.out.println("Failed to connect to the database");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
