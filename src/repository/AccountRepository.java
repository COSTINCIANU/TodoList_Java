package repository;
// Import  BCryptUtil
import BCrypt.BCrypt;

import dao.Database;
import models.Account;
import java.util.List;
import java.util.ArrayList;

import java.sql.*;

public class AccountRepository {

    // Ajouter un compte
    public void add(Account account) {
        // Utilisation de la méthode getConnection pour obtenir une connexion
        try (Connection connection = Database.getConnection()) {
            // Hacher le mot de passe avant de l'ajouter dans la base de données
            String salt = BCrypt.gensalt();  // Générer un salt
            String hashedPassword = BCrypt.hashpw(account.getPassword(), salt);  // Hacher le mot de passe avec le salt

            String query = "INSERT INTO account (lastname, firstname, email, password) VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, account.getLastname());
                stmt.setString(2, account.getFirstname());
                stmt.setString(3, account.getEmail());
                // Ajouter le mot de passe haché
                stmt.setString(4, hashedPassword);
                stmt.executeUpdate();
                System.out.println("Compte ajouté avec succès.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'ajout du compte : " + e.getMessage());
        }
    }

    // Méthode pour vérifier si le mot de passe correspond au hachage
    public boolean verifyPassword(String password, String hashedPassword) {
        // Vérifier si le mot de passe correspond au hachage dans la base de données
        return BCrypt.checkpw(password, hashedPassword);
    }

    // Méthode pour récupérer un compte par son email
    public Account findByEmail(String email) {
        Account account = null;
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT * FROM account WHERE email = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    account = new Account(
                            rs.getString("lastname"),
                            rs.getString("firstname"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // Autres méthodes (pour récupérer tous les comptes)
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = Database.getConnection()) {
            String query = "SELECT * FROM account";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    accounts.add(new Account(
                            rs.getString("lastname"),
                            rs.getString("firstname"),
                            rs.getString("email"),
                            rs.getString("password")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
