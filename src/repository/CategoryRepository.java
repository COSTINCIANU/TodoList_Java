package repository;

import dao.Env;
import dao.Database;
import models.Category;
import java.sql.*;
import java.util.*;

public class CategoryRepository {

    private static Connection connection = Database.getConnection();
    // Ajouter un compte
    // Méthode ajouter d'une catégorie en base de données
    public void add(Category category) {
        // Récupérer la connexion base de données
        try {
            // Vérifier si la connexion est valide
            if (connection == null) {
                System.out.println("Échec de la connexion à la base de données.");
                // Quitter si la connexion échoue
                return;
            }

            // SQL pour ajouter une catégorie
            String query = "INSERT INTO category (name) VALUES (?)";

            // Préparer et exécuter la requête
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                // Remplacer ? par le nom de la catégorie
                stmt.setString(1, category.getName());
                // Exécuter la mise à jour
                stmt.executeUpdate();
                System.out.println("Catégorie ajoutée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'ajout de la catégorie : " + e.getMessage());
        }
    }

    // Récupére toutes les catégories de la base de données
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            if (connection == null) {
                System.out.println("Échec de la connexion à la base de données.");
                // Retourner une liste vide si la connexion échoue
                return categories;
            }

            // SQL récupére toutes les catégories
            String query = "SELECT * FROM category";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                // Parcour le résultat et ajoute chaque catégorie à la liste
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    categories.add(new Category(id, name));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la récupération des catégories : " + e.getMessage());
        }
        // Retourne la liste des catégories
        return categories;
    }
}
