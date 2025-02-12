package dao;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/todo_list";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            // Vérifiez si la connexion à la base de données est correcte
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            // Si une exception se produit, la connexion est nulle
            return null;
        }
    }
}

