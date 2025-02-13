package dao;

import java.sql.*;

public class Database {
    public static Connection getConnection() {
        try {
            // Vérifiez si la connexion à la base de données est correcte
            Connection connection = DriverManager.getConnection(Env.DB_URL, Env.DB_USER, Env.DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            // Si une exception se produit, la connexion est nulle
            return null;
        }
    }
}