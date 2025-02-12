package repository;

import dao.Database;
import models.Task;
import models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class TaskRepository {
    private Connection connection;

    public TaskRepository() {
        try {
            // Connection
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo_list", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ajouter une tâche
    public void add(Task task) {
        String sql = "INSERT INTO task (title, description, createAt, status, account_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            // Utiliser Timestamp
            stmt.setTimestamp(3, task.getCreateAt());
            stmt.setBoolean(4, task.isStatus());
            stmt.setInt(5, task.getAccountId());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Tâche ajoutée avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout de la tâche !");
        }
    }


    // Trouver toutes les tâches
    public ArrayList<Task> findAll() {
        ArrayList<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM task";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                tasks.add(new Task(rs.getString("title"), rs.getString("description"), rs.getBoolean("status"), rs.getInt("account_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Valider une tâche changer son statut à 'complété'
    public void validateTask(int taskId) {
        String query = "UPDATE task SET status = true WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Category> getCategoriesByTask(int taskId) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT c.* FROM category c " +
                "JOIN task_category tc ON c.id = tc.category_id " +
                "WHERE tc.task_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, taskId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category(rs.getInt("id"), rs.getString("name"));
                categories.add(category);
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des catégories !");
            e.printStackTrace();
        }
        return categories;
    }



    public void addCategoryToTask(int taskId, int categoryId) {
        String sql = "INSERT INTO task_category (task_id, category_id) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, taskId);
            stmt.setInt(2, categoryId);

            stmt.executeUpdate();
            System.out.println("Catégorie associée à la tâche avec succès !");

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'association de la catégorie à la tâche !");
            e.printStackTrace();
        }
    }
}
