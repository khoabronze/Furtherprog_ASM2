package com.example.furtherprog_asm2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RequestDAO implements Request_DAO<Request>{
    private ArrayList<Request> requestList = new ArrayList<>();
    private Db_function db = new Db_function();
    @Override
    public List<Request> getAll() {
        List<Request> requestList = new ArrayList<>();
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"Request\"";
        try (Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String rid = rs.getString("rid");
                String id = rs.getString("id");
                String note = rs.getString("note");
                String approval = rs.getString("approval");
                Request request = new Request(rid, id, note, Approval.valueOf(approval));
                requestList.add(request);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return requestList;
    }

    @Override
    public Optional<Request> get(String rid) {
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"Request\" WHERE \"rid\" = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setString(1, rid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String note = rs.getString("note");
                String approval = rs.getString("approval");
                Request request = new Request(rid, id, note, Approval.valueOf(approval));
                return Optional.of(request);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println(e);
            return Optional.empty();
        }
    }

    @Override
    public Request getOne(String rid) {
        Connection con = db.connect_to_db();
        String query = "SELECT * FROM \"Request\" WHERE \"rid\" = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, rid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String note = rs.getString("note");
                String approval = rs.getString("approval");
                Request request = new Request(rid, id, note, Approval.valueOf(approval));
                return request;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    @Override
    public boolean create(Request request) {
        Connection con = db.connect_to_db();
        String query = "INSERT INTO \"Request\" (\"rid\", \"id\", \"note\", \"approval\") VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, request.getRid());
            stmt.setString(2, request.getId());
            stmt.setString(3, request.getNote());
            if (request.getApproval() != null) {
                stmt.setString(4, request.getApproval().toString());
            } else {
                stmt.setNull(4, Types.VARCHAR);
            }
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public void processRequest(String rid, String id, String approval) {
        Connection con = db.connect_to_db();
        String query = "UPDATE \"Request\" SET \"approval\" = ? WHERE \"rid\" = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, approval);
            stmt.setString(2, rid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
