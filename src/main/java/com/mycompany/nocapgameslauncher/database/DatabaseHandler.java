package com.mycompany.nocapgameslauncher.database;

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:mysql://192.168.1.10:3306/NoCapServer";
    private static final String DB_USER = "Admin";
    private static final String DB_PASS = "nocap";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Add connector dependency.");
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static boolean register(String username, String password) throws SQLException {
        String checkSql = "SELECT userID FROM users WHERE username = ?";
        String insertSql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, username);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next()) {
                    return false; // user exists
                }
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, username);
                insertStmt.setString(2, password); // NOTE: store hashed passwords in production
                int affected = insertStmt.executeUpdate();
                return affected == 1;
            }
        }
    }

    public static boolean login(String username, String password) throws SQLException {
        String sql = "SELECT userID FROM users WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
